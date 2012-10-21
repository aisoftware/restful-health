package com.restfulhealth.services;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Path("/")
public class HomeService {
	private String dbCollectionName = "patient";
	
	public HomeService(@Context ServletContext servletContext) {
		if (ServiceUtil.mongoDBname == null) {
			ServiceUtil.init(servletContext);
		}
	}


	@POST
	@Path("/login")
	public Response login(@FormParam("username") String username,
			@FormParam("password") String password) throws Throwable{
		try{
		BasicDBObject query = new BasicDBObject();
		Pattern regex = Pattern.compile("\"loginName\":\""+username +"\"",Pattern.CASE_INSENSITIVE );
		query.put("PersonJSON",  regex);

		ArrayList<DBObject> obj = ServiceUtil.mongo.query(dbCollectionName, query);
		if(obj != null){
			DBObject dbo = obj.get(0);
			String patientJSON = dbo.toString();
			if(patientJSON.indexOf("password:"+ password) == -1){				
				return Response.status(200).entity("0").build();
			}
			else
				return Response.status(200).entity("1").build();
		}
		else
			return Response.status(200).entity("Not such username").build();
		}
		catch(Throwable t){
			t.printStackTrace();			
			throw t;
		}
		
	}

	@GET
	@Path("/hello/{username}")
	public Response sayPlainTextHello(@PathParam("username") String username) {
		return Response.status(200).entity("Hello " + username).build();
	}



}
