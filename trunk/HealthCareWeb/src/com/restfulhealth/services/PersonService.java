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
	private String dbCollectionName = "person";
	
	public PersonService(@Context ServletContext servletContext) {
		if (ServiceUtil.mongoDBname == null) {
			ServiceUtil.init(servletContext);
		}
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPerson(String personJson) throws Throwable{
		String uuid = UUID.randomUUID().toString();
		try{
			BasicDBObject obj = new BasicDBObject();
			obj.put("personID", uuid);
			obj.put("PersonJSON", personJson);
			ServiceUtil.mongo.put(dbCollectionName, obj);
		}
		catch(Throwable t){
			t.printStackTrace();
			throw t;
		}
		return Response.status(200).entity(uuid).build();
	}

}
