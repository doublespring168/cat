package org.unidal.lookup.extension;

import com.doublespring.common.U;
import com.doublespring.log.LogUtil;
import org.apache.xbean.recipe.ObjectRecipe;
import org.codehaus.plexus.MutablePlexusContainer;
import org.codehaus.plexus.component.builder.XBeanComponentBuilder;
import org.codehaus.plexus.component.factory.ComponentInstantiationException;
import org.codehaus.plexus.component.manager.AbstractComponentManager;
import org.codehaus.plexus.component.manager.ComponentManager;
import org.codehaus.plexus.component.manager.ComponentManagerFactory;
import org.codehaus.plexus.component.repository.ComponentDescriptor;
import org.codehaus.plexus.component.repository.exception.ComponentLifecycleException;
import org.codehaus.plexus.lifecycle.LifecycleHandler;
import org.unidal.helper.Reflects;
import org.unidal.helper.Splitters;

import java.util.List;

/**
 * @author spring
 */
public class EnumComponentManagerFactory implements ComponentManagerFactory {
    public EnumComponentManagerFactory() {
        LogUtil.info("实例化组件管理工厂类 EnumComponentManagerFactory");
    }

    @Override
    public String getId() {
        return "enum";
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public ComponentManager<?> createComponentManager(MutablePlexusContainer container, LifecycleHandler lifecycleHandler,
                                                      ComponentDescriptor componentDescriptor, String role, String roleHint) {
        LogUtil.info("即将实例化 EnumComponentManager", U.format(
                "MutablePlexusContainer", U.toJSS(container),
                "LifecycleHandler", U.toJSS(lifecycleHandler),
                "ComponentDescriptor", U.toJSS(componentDescriptor),
                "role", role,
                "roleHint", roleHint
        ));
        EnumComponentManager enumComponentManager = new EnumComponentManager(container, lifecycleHandler, componentDescriptor, role, roleHint);
        return enumComponentManager;
    }

    static class EnumComponentManager<T> extends AbstractComponentManager<T> {

        public EnumComponentManager(MutablePlexusContainer container, LifecycleHandler lifecycleHandler,
                                    ComponentDescriptor<T> componentDescriptor, String role, String roleHint) {
            super(container, lifecycleHandler, componentDescriptor, role, roleHint);
            LogUtil.info("实例化 EnumComponentManager");

        }

        @Override
        public synchronized void dispose() throws ComponentLifecycleException {
            LogUtil.info("关闭 EnumComponentManager");
        }

        @Override
        public synchronized void release(Object component) throws ComponentLifecycleException {
            LogUtil.info("释放组件", U.format("component", component.getClass(), "component", U.toJSS(component)));
        }

        @Override
        @SuppressWarnings("unchecked")
        public synchronized T getComponent() throws ComponentInstantiationException, ComponentLifecycleException {

            ComponentDescriptor<T> descriptor = getComponentDescriptor();
            Class<? extends T> enumClass = descriptor.getImplementationClass();

            LogUtil.info("加载组件", U.format("ComponentDescriptor", U.toJSS(descriptor), "enumClass", enumClass.getClass()));

            if (!enumClass.isEnum()) {
                throw new ComponentInstantiationException(String.format("%s is not an emum class!", enumClass));
            }

            List<String> parts = Splitters.by(':').split(getRoleHint());
            String field = parts.get(0);
            Object[] values = Reflects.forMethod().invokeStaticMethod(enumClass, "values");

            LogUtil.info("enum values");

            for (Object value : values) {
                if (field.equals(value.toString())) {

                    EnumValueHolder enumValueHolder = new EnumValueHolder();

                    try {

                        LogUtil.info("实例化 XBeanComponentBuilder");
                        XBeanComponentBuilder<T> builder = new XBeanComponentBuilder<T>(this);

                        LogUtil.info("实例化 ObjectRecipe");
                        ObjectRecipe recipe = builder.createObjectRecipe((T) enumValueHolder, descriptor, getRealm());

                        EnumValueHolder.put(value);
                        recipe.setFactoryMethod("get");
                        recipe.create(Object.class, false);

                        LogUtil.info("注册Component监听器", U.format("value", U.toString(value)));
                        start(value);
                    } catch (Exception e) {
                        throw new ComponentInstantiationException(e.getMessage(), e);
                    } finally {
                        EnumValueHolder.reset();
                    }

                    return (T) value;
                }
            }

            throw new ComponentInstantiationException(String.format("Field(%s) is not defined in the %s!", field, enumClass));
        }
    }

    public static class EnumValueHolder {
        private static ThreadLocal<Object> m_threadLocal = new ThreadLocal<Object>();

        public static Object get() {
            return m_threadLocal.get();
        }

        public static void put(Object obj) {
            m_threadLocal.set(obj);
        }

        public static void reset() {
            m_threadLocal.remove();
        }
    }
}
