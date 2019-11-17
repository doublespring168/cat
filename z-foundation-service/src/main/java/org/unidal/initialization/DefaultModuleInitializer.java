package org.unidal.initialization;

import com.doublespring.common.U;
import com.doublespring.log.LogUtil;
import org.unidal.lookup.annotation.Inject;
import org.unidal.lookup.annotation.InjectAttribute;
import org.unidal.lookup.annotation.Named;

import java.util.LinkedHashSet;
import java.util.Set;

@Named(type = ModuleInitializer.class)
public class DefaultModuleInitializer implements ModuleInitializer {
    @Inject
    private ModuleManager m_manager;

    @InjectAttribute
    private boolean m_verbose;

    private int m_index = 1;


    public DefaultModuleInitializer() {
        LogUtil.info("实例化 DefaultModuleInitializer");
    }

    @Override
    public void execute(ModuleContext ctx) {
        Module[] modules = m_manager.getTopLevelModules();
        LogUtil.info("即将初始化各个模块组件", U.format("modules", U.toString(modules)));
        execute(ctx, modules);
    }

    @Override
    public void execute(ModuleContext ctx, Module... modules) {
        Set<Module> all = new LinkedHashSet<Module>();

        info(ctx, "即将初始化顶层模块");

        for (Module module : modules) {
            info(ctx, "  模块 >> " + module.getClass().getName());
        }

        try {
            expandAll(ctx, modules, all);

            for (Module module : all) {
                if (!module.isInitialized()) {
                    LogUtil.info("初始化模块", U.format("module", U.toString(module)));
                    executeModule(ctx, module, m_index++);
                } else {
                    LogUtil.info("当前模块已存在,不需要重新初始化", U.format("module", U.toString(module)));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Error when initializing modules! Exception: " + e, e);
        }
    }

    private synchronized void executeModule(ModuleContext ctx, Module module, int index) throws Exception {
        long start = System.currentTimeMillis();

        String name = module.getClass().getName();
        // set flat to avoid re-entrance
        module.setInitialized(true);


        LogUtil.info("初始化单个模块", U.format("index", index, "module", name));
        // execute itself after its dependencies
        module.initialize(ctx);

        long end = System.currentTimeMillis();

        LogUtil.info("模块初始化耗时", U.format("module", name, "ms", end - start));


    }

    private void expandAll(ModuleContext ctx, Module[] modules, Set<Module> all) throws Exception {

        LogUtil.info("按模块依赖顺序汇总模块", U.format("modules", U.toString(modules), "all modules", U.toString(all)));
        if (modules != null) {
            for (Module module : modules) {

                LogUtil.info("展开单个模块", U.format("module", U.toString(module)));
                expandAll(ctx, module.getDependencies(ctx), all);

                if (!all.contains(module)) {
                    if (module instanceof AbstractModule) {
                        LogUtil.info("初始化模块配置项");
                        ((AbstractModule) module).setup(ctx);
                    } else {
                        LogUtil.info("当前模块不需要初始化配置项");
                    }

                    all.add(module);

                } else {
                    LogUtil.info("模块已存在,不需要汇总");
                }
            }
        }
    }

    private void info(ModuleContext ctx, String message) {
        if (m_verbose) {
            ctx.info(message);
        }
    }

    public void setVerbose(boolean verbose) {
        m_verbose = verbose;
    }
}
