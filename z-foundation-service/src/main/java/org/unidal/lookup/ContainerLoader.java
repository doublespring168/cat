package org.unidal.lookup;

import com.doublespring.common.U;
import com.doublespring.log.LogUtil;
import org.codehaus.plexus.ContainerConfiguration;
import org.codehaus.plexus.DefaultContainerConfiguration;
import org.codehaus.plexus.DefaultPlexusContainer;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.classworlds.ClassWorld;
import org.codehaus.plexus.classworlds.realm.ClassRealm;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.codehaus.plexus.lifecycle.AbstractLifecycleHandler;
import org.codehaus.plexus.lifecycle.LifecycleHandler;
import org.codehaus.plexus.lifecycle.phase.Phase;
import org.unidal.helper.Reflects;
import org.unidal.lookup.extension.EnumComponentManagerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ContainerLoader {


    private static volatile DefaultPlexusContainer s_container;

    private static ConcurrentMap<Key, Object> m_components = new ConcurrentHashMap<Key, Object>();

    public static void destroyDefaultContainer() {


        LogUtil.info("即将销毁 DefaultContainer");

        if (s_container != null) {
            m_components.clear();
            s_container.dispose();
            s_container = null;

        }
    }


    public static PlexusContainer getDefaultContainer() {

        LogUtil.info("加载 PlexusContainer");

        LogUtil.info("实例化 DefaultContainerConfiguration");
        DefaultContainerConfiguration configuration = new DefaultContainerConfiguration();

        LogUtil.info("设置 plexus.xml配置文件");
        configuration.setContainerConfiguration("/META-INF/plexus/plexus.xml");
        return getDefaultContainer(configuration);
    }

    public static PlexusContainer getDefaultContainer(ContainerConfiguration configuration) {

        LogUtil.info("加载 PlexusContainer");
        if (s_container == null) {

            // Two ContainerLoaders should share the same PlexusContainer
            Class<?> loaderClass = findLoaderClass();

            synchronized (ContainerLoader.class) {


                if (loaderClass != null) {
                    LogUtil.info("加载到 loaderClass", U.format("loaderClass", loaderClass));
                    s_container = getContainerFromLookupLibrary(loaderClass);
                }

                if (s_container == null) {

                    LogUtil.info("不存在 PlexusContainer,即将实例化");

                    try {
                        preConstruction(configuration);

                        s_container = new DefaultPlexusContainer(configuration);

                        LogUtil.info("已实例化 PlexusContainer", U.format("DefaultPlexusContainer", U.toJSS(s_container)));

                        LogUtil.info("已实例化 PlexusContainer,即将执行构造器调用后操作");
                        postConstruction(s_container);

                        if (loaderClass != null) {
                            setContainerToLookupLibrary(loaderClass, s_container);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("Unable to create Plexus container.", e);
                    }
                } else {
                    LogUtil.info("已存在 PlexusContainer,不需重新实例化");
                }
            }
        } else {
            LogUtil.info("PlexusContainer 已存在,不需实例化");
        }

        return s_container;
    }

    private static Class<?> findLoaderClass() {


        LogUtil.info("加载 com.site.lookup.ContainerLoader");
        String loaderClassName = "com.site.lookup.ContainerLoader";
        Class<?> loaderClass = null;

        try {
            loaderClass = ContainerLoader.class.getClassLoader().loadClass(loaderClassName);
        } catch (ClassNotFoundException e) {
            // ignore it
        }

        try {
            loaderClass = Thread.currentThread().getContextClassLoader().loadClass(loaderClassName);
        } catch (ClassNotFoundException e) {
            // ignore it
        }

        return loaderClass;
    }

    // for back compatible
    private static DefaultPlexusContainer getContainerFromLookupLibrary(Class<?> loaderClass) {
        try {

            LogUtil.info("从 loaderClass 中提取 PlexusContainer");
            Field field = loaderClass.getDeclaredField("s_container");
            field.setAccessible(true);

            return (DefaultPlexusContainer) field.get(null);
        } catch (Exception e) {
            // ignore it
            e.printStackTrace();
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    private static void preConstruction(ContainerConfiguration configuration) throws Exception {


        LogUtil.info("预处理 ContainerConfiguration", U.format("configuration", U.toJSS(configuration)));
        LifecycleHandler plexus = configuration.getLifecycleHandlerManager().getLifecycleHandler("plexus");
        Field field = Reflects.forField().getDeclaredField(AbstractLifecycleHandler.class, "beginSegment");

        field.setAccessible(true);

        List<Phase> segment = (List<Phase>) field.get(plexus);

        segment.add(0, new org.unidal.lookup.extension.PostConstructionPhase());

        try {
            new ContainerConfigurationDecorator().process(configuration);
            LogUtil.info("处理后 ContainerConfiguration", U.toJSS(configuration));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void postConstruction(DefaultPlexusContainer container) {
        LogUtil.info("为 DefaultPlexusContainer 注册组件管理工厂类");
        container.getComponentRegistry().registerComponentManagerFactory(new EnumComponentManagerFactory());
    }

    private static void setContainerToLookupLibrary(Class<?> loaderClass, PlexusContainer container) {
        try {

            LogUtil.info("将PlexusContainer加入到LookupLibrary");
            Field field = loaderClass.getDeclaredField("s_container");

            field.setAccessible(true);
            field.set(null, container);


        } catch (Exception e) {
            // ignore it
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    static <T> T lookupById(Class<T> role, String roleHint, String id) throws ComponentLookupException {
        Key key = new Key(role, roleHint, id);
        Object component = m_components.get(key);

        if (component == null) {
            component = s_container.lookup(role, roleHint);

            if (m_components.putIfAbsent(key, component) != null) {
                component = m_components.get(key);
            }
        }

        return (T) component;
    }

    static class ContainerConfigurationDecorator {

        private String m_defaultPath = "META-INF/plexus/plexus.xml";

        public ContainerConfigurationDecorator() {
            LogUtil.info("实例化 ContainerConfigurationDecorator");
        }

        public void process(ContainerConfiguration configuration) throws Exception {


            LogUtil.info("实例化 DocumentBuilderFactory");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            LogUtil.info("实例化 DocumentBuilder");
            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            LogUtil.info("创建 Document");
            Document document = builder.newDocument();

            LogUtil.info("实例化 ClassRealm");
            ClassRealm realm = new ClassWorld("plexus.core", Thread.currentThread().getContextClassLoader())
                    .getRealm("plexus.core");

            Enumeration<URL> resources = realm.getResources(m_defaultPath);
            LogUtil.info("读取到 resources", U.format("resources", U.toString(resources), "m_defaultPath", m_defaultPath));

            LogUtil.info("创建document root节点");
            Element root = document.createElement("plexus");

            LogUtil.info("root节点添加components节点");
            root.appendChild(document.createElement("components"));
            document.appendChild(root);
            document.setXmlStandalone(true);

            LogUtil.info("document添加root节点", U.format("document", U.toString(document)));


            String path = configuration.getContainerConfiguration();

            LogUtil.info("合并ContainerConfiguration中指定的配置文件", U.format("path", path));

            if (path != null && !path.endsWith(m_defaultPath)) {
                URL url = realm.getResource(path);
                if (url != null) {
                    LogUtil.info("合并xml文件", U.format("path", U.toString(url)));
                    fillFrom(document, builder, url);
                }
            }

            LogUtil.info("合并resources下默认配置文件", U.format("resources", U.toString(resources)));

            for (URL url : Collections.list(resources)) {
                LogUtil.info("合并resources下默认配置文件", U.format("url", url.toString()));
                fillFrom(document, builder, url);
            }

            LogUtil.info("转换document为新的格式", U.format("resources", U.toString(resources)));
            if (document.getDocumentElement().hasChildNodes()) {
                // Use a Transformer for output

                LogUtil.info("实例化 TransformerFactory");
                TransformerFactory transforerFactory = TransformerFactory.newInstance();
                LogUtil.info("实例化 Transformer");
                Transformer transformer = transforerFactory.newTransformer();
                File tempFile = File.createTempFile("plexus-", ".xml");
                LogUtil.info("创建plexus xml临时文件", U.format("tempFile", U.toString(tempFile)));
                StreamResult result = new StreamResult(new FileOutputStream(tempFile));

                tempFile.deleteOnExit();
                LogUtil.info("document内容写入tempFile");
                transformer.transform(new DOMSource(document), result);

                URL tmpUrl = tempFile.toURI().toURL();
                LogUtil.info("设置ContainerConfigurationURL为临时文件URL", U.format("tmpUrl", tmpUrl.toString()));
                configuration.setContainerConfigurationURL(tmpUrl);
            }
        }

        private void fillFrom(Document to, DocumentBuilder builder, URL url) throws Exception {

            LogUtil.info("合并xml配置文件", U.format("url", U.toString(url)));
            InputStream in = url.openStream();
            Document from = builder.parse(in);

            in.close();

            Node source = from.getDocumentElement().getElementsByTagName("components").item(0);
            Node target = to.getDocumentElement().getFirstChild();
            NodeList list = source.getChildNodes();
            int len = list.getLength();

            for (int i = 0; i < len; i++) {
                target.appendChild(to.importNode(list.item(i), true));
            }
        }
    }

    static class Key {
        private Class<?> m_role;

        private String m_roleHint;

        private String m_id;

        public Key(Class<?> role, String roleHint, String id) {
            m_role = role;
            m_roleHint = roleHint == null ? "default" : roleHint;
            m_id = id;
        }

        @Override
        public int hashCode() {
            int hashCode = 0;

            hashCode = hashCode * 31 + m_role.hashCode();
            hashCode = hashCode * 31 + m_roleHint.hashCode();
            hashCode = hashCode * 31 + m_id.hashCode();

            return hashCode;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Key) {
                Key e = (Key) obj;

                if (e.m_role != m_role) {
                    return false;
                }

                if (!e.m_roleHint.equals(m_roleHint)) {
                    return false;
                }

                return e.m_id.equals(m_id);
            }

            return false;
        }
    }
}
