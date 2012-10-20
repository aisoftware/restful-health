package com.restfulhealth.services;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

public class ServiceUtil {

	public static String storageServers = null;
	public static String mongoDBname = null;

	public static void init(@Context ServletContext servletContext) {
		try {
			if (storageServers == null) {
				if (servletContext.getInitParameter("storageServers") != null)
					storageServers = servletContext
							.getInitParameter("storageServers");
			}

			if (mongoDBname == null) {
				if (servletContext.getInitParameter("mongoDBname") != null)
					mongoDBname = servletContext
							.getInitParameter("mongoDBname");
			}

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
