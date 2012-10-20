package com.restfulhealth.services;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/")
public class HomeService {

	public HomeService(@Context ServletContext servletContext) {
		if (ServiceUtil.mongoDBname == null) {
			ServiceUtil.init(servletContext);
		}
	}


	@POST
	@Path("/login")
	public Response login(@FormParam("username") String username,
			@FormParam("password") String password) {
		System.out.println("username=" + username);
		
		return Response.status(200).entity(username).build();
	}

	@GET
	@Path("/hello/{username}")
	public Response sayPlainTextHello(@PathParam("username") String username) {
		return Response.status(200).entity("Hello " + username).build();
	}



}
