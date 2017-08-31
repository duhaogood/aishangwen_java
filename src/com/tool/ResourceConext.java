/*
 * @(#)ECAResourceConext.java  2011-7-14
 *
 * Copyright 2003 JSCA,ltd. All rights reserved.
 * JSCA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.tool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * @author Administrator
 * 
 */
public class ResourceConext implements ServletContextListener {
	private static WebApplicationContext springContext;

	public void contextDestroyed(ServletContextEvent sce) {
	}

	public void contextInitialized(ServletContextEvent sce) {
		springContext = WebApplicationContextUtils.getWebApplicationContext(sce
				.getServletContext());
	}

	public static WebApplicationContext getApplicationContext() {
		return springContext;
	}

}
