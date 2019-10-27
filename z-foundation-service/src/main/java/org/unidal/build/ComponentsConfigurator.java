package org.unidal.build;

import org.unidal.formatter.DateFormatter;
import org.unidal.helper.Threads.LoggerThreadListener;
import org.unidal.initialization.DefaultModuleContext;
import org.unidal.initialization.DefaultModuleInitializer;
import org.unidal.initialization.DefaultModuleManager;
import org.unidal.lookup.configuration.AbstractResourceConfigurator;
import org.unidal.lookup.configuration.Component;
import org.unidal.net.transport.ClientTransportHandler;
import org.unidal.net.transport.DefaultClientTransport;
import org.unidal.net.transport.DefaultServerTransport;
import org.unidal.net.transport.ServerTransportHandler;

import java.util.ArrayList;
import java.util.List;

class ComponentsConfigurator extends AbstractResourceConfigurator {
    public static void main(String[] args) {
        generatePlexusComponentsXmlFile(new ComponentsConfigurator());
    }

    @Override
    public List<Component> defineComponents() {
        List<Component> all = new ArrayList<Component>();

        all.add(A(DefaultModuleManager.class));
        all.add(A(DefaultModuleInitializer.class));
        all.add(A(DefaultModuleContext.class));
        all.add(A(DateFormatter.class));
        all.add(A(LoggerThreadListener.class));

        all.add(A(DefaultClientTransport.class));
        all.add(A(DefaultServerTransport.class));
        all.add(A(ClientTransportHandler.class));
        all.add(A(ServerTransportHandler.class));

        return all;
    }
}
