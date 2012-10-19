package com.restfulhealth.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/person")
public class PersonService {
	
	@POST
	@Path("/add")
	public Response login(@FormParam("username") String username, @FormParam("password") String password) {		
		System.out.println("username="+username);
		
		return Response.status(200).entity(username).build();
	}

}
