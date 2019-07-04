package com.pdsu.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerStartupListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//将web应用的名称保存在application中
		ServletContext servletContext = sce.getServletContext();
		String path = servletContext.getContextPath();
		servletContext.setAttribute("APP_PATH", path);
	}

}
