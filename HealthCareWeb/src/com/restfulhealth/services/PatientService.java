package com.restfulhealth.services;

import java.util.UUID;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mongodb.BasicDBObject;
import com.restfulhealth.mongoDB.MongoDB;

/**
 * Restful services related to the patient object
 * 
 * @author Tom Wilson
 * @author Jing Song
 *
 */
@Path("/patient")
public class PatientService {
	private String dbCollectionName = "test"; //patient is subset of person
	
	public PatientService(@Context ServletContext servletContext) {
		if (ServiceUtil.mongoDBname == null) {
			ServiceUtil.init(servletContext);
		}
	}

	@POST
	@Path("/add")
//	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPatient(String patientJson) throws Throwable{
		if(patientJson == null || patientJson.isEmpty())
			return null;
		String psersonID = UUID.randomUUID().toString();
		try{
			BasicDBObject obj = new BasicDBObject();			
			obj.put("personID", psersonID);
			obj.put("PersonJSON", patientJson);
			ServiceUtil.mongo.put(dbCollectionName, obj);
		}
		catch(Throwable t){
			t.printStackTrace();
			throw t;
		}
		return Response.status(200).entity(psersonID).build();
	}
	
	
	/**
	 * Return a list of patients
	 * 
	 * @return
	 */
	@GET
	@Path("/patient/list")
	public Response getPatientList() {
		return Response.status(200)
				.entity("Would have returned a full patient list.").build();
	}

	/**
	 * Return a Patient object, given the identifier
	 * 
	 * @param identifier patient identifier
	 * @return
	 */
	@GET
	@Path("/patient/{id}")
	public Response getPatientByID(@PathParam("id") String id) {
		return Response.status(200)
				.entity("Would have gotten patient " + id).build();
	}
	
	/**
	 * Return a Patient object, given the identifier
	 * 
	 * @param identifier patient identifier
	 * @return
	 */
	@GET
	@Path("/patient/{firstname}/{lastname}")
	public Response getPatient(@FormParam("firstname") String firstname,
			@FormParam("lastname") String lastname) {
		
		BasicDBObject query = new BasicDBObject();
//		query.put(key, val)
		return Response.status(200)
				.entity("").build();
	}
	
	
	/**
	 * Returns a summary list of labs, as a collection of LabReport objects
	 * The LabReport objects do not need to be fully populated, only require:
	 * reportId, reportName, diagnosticTime, conclusion
	 * 
	 * @param identifier the patient identifier
	 * @return a collection of LabReport
	 */
	@GET
	@Path("/patient/{identifier}/labs")
	public Response getPatientLabs(@PathParam("identifier") String identifier) {
		return Response.status(200)
				.entity("Would have gotten labs for patient " + identifier).build();
	}
	
	

}
