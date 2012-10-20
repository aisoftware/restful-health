package com.restfulhealth.services;

import java.util.UUID;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hl7.fhir.instance.model.Person;

import com.mongodb.BasicDBObject;
import com.restfulhealth.mongoDB.MongoDB;

@Path("/person")
public class PersonService {
	private MongoDB mongo  = null;
	private String dbCollectionName = "person";
	
	public PersonService(@Context ServletContext servletContext) {
		if (ServiceUtil.mongoDBname == null) {
			ServiceUtil.init(servletContext);
			mongo = ServiceUtil.mongo;
		}
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPerson(String personJson) throws Throwable{
		try{
			BasicDBObject obj = new BasicDBObject();
			obj.put("personID", UUID.randomUUID().toString());
			obj.put("PersonJSON", personJson);
			mongo.put(dbCollectionName, obj);
		}
		catch(Throwable t){
			t.printStackTrace();
			throw t;
		}
		return Response.status(200).entity("added person").build();
	}

}
