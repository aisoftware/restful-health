package com.restfulhealth.services;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import com.restfulhealth.mongoDB.MongoDB;

public class ServiceUtil {

	public static String storageServers = null;
	public static String mongoDBname = null;
	public static MongoDB mongo = null;

	public static void init(@Context ServletContext servletContext) {
		try {
			if (storageServers == null) {
				if (servletContext.getInitParameter("storageServers") != null){
					storageServers = servletContext.getInitParameter("storageServers");
					System.setProperty("serverList", storageServers);
				}
			}

			if (mongoDBname == null) {
				if (servletContext.getInitParameter("mongoDBname") != null){
					mongoDBname = servletContext.getInitParameter("mongoDBname");
					
					if(mongoDBname != null){
						System.setProperty("mongoDBname", mongoDBname);
						if(mongo == null)
							mongo = MongoDB.getInstance();
					}
				}
			}

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
