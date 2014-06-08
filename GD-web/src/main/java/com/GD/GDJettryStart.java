package com.GD;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class GDJettryStart {
	public static void main(String[] args) throws Exception {
		// new ClassPathXmlApplicationContext("spring.xml");
		Server server = new Server();

		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(80);
		server.addConnector(connector);

		WebAppContext context = new WebAppContext();
//		String webapp = "E:/workspace2/GD/GD-web/src/main/webapp";

		// WebAppContext webContext = new WebAppContext("src/main/webapp", "/");
		// webContext.setDefaultsDescriptor("leopard-test/webdefault.xml");

//		context.setContextPath("/");
//		context.setDescriptor(webapp + "/WEB-INF/web.xml");
//		context.setResourceBase(webapp);
//		context.setConfigurationDiscovered(true);
		
		context.setContextPath("/");
//		context.setDescriptor(webapp + "/WEB-INF/web.xml");
		context.setResourceBase("src/main/webapp");
		context.setConfigurationDiscovered(true);

		System.out.println(context.getContextPath());  
        System.out.println(context.getDescriptor());  
        System.out.println(context.getResourceBase());  
        System.out.println(context.getBaseResource());  
        System.out.println(context.getDefaultsDescriptor());  
		// server.setHandler(new MyHandler());
		server.setHandler(context);
		server.start();
		server.join();
	}
}
