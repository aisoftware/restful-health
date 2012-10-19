package com.restfulhealth.services;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path ("/")
public class HomeService {
	public static String storageServers = null;
	public static String mongoDBname = null;
	
	public HomeService(@Context ServletContext servletContext) {
		try{
			if(servletContext.getInitParameter("storageServers") != null)
				storageServers = servletContext.getInitParameter("storageServers");
			
			if(servletContext.getInitParameter("mongoDBname") != null)
				mongoDBname = servletContext.getInitParameter("mongoDBname");
			
		}
		catch(Throwable t){
			t.printStackTrace();
		}
	}

	@POST
	@Path("/login")
	public Response login(@FormParam("username") String username, @FormParam("password") String password) {		
		System.out.println("username="+username);
		return Response.status(200).entity(username).build();
	}
	
	
	@GET
	@Path("/hello/{username}")
	public Response sayPlainTextHello( @PathParam("username") String username) {
		return Response.status(200).entity("Hello " + username).build();
	}
}
