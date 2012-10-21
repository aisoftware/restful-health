package com.restfulhealth.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.regex.Pattern;

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
import com.mongodb.DBObject;
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
	private String dbCollectionName = "patient"; //patient is subset of person
	
	public PatientService(@Context ServletContext servletContext) {
		if (ServiceUtil.mongoDBname == null) {
			ServiceUtil.init(servletContext);
		}
	}

	@POST
	@Path("/add")
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
	@Path("/list")
	public Response getPatientList() throws Throwable{
		String patientJSON = "";
		HashMap<String, String> patients = new HashMap<String, String>();
		try{
			ArrayList<DBObject> objs = ServiceUtil.mongo.query(dbCollectionName, null);
			if(objs != null && objs.size() >0){
				StringBuffer sb = new StringBuffer("[{ ");
				for(int i=0; i< objs.size(); i++){
					DBObject obj = objs.get(i);
					Object pID = obj.get("personID");
					sb.append("\"personID\":\""+ (String)pID +"\",");
					Object personObj = obj.get("PersonJSON");
					patientJSON = personObj.toString();
					sb.append("\"PersonJSON\":" +patientJSON +"},");
					if(i != objs.size()-1)
						sb.append("{");
						
				}
				String str = sb.toString();
				patientJSON = str.substring(0, str.length()-1)+ "]";
			}
		}
		catch(Throwable t){
			t.printStackTrace();
			throw t;
		}
		
		return Response.status(200).entity(patientJSON).build();
	}

	/**
	 * Return a Patient object, given the identifier
	 * 
	 * @param identifier patient identifier
	 * @return
	 */
	@GET
	@Path("/{id}")
	public Response getPatientByID(@PathParam("id") String id) throws Throwable{
		String patientJSON = "";
		try{
			BasicDBObject query = new BasicDBObject();			
			query.put("personID", id);
			ArrayList<DBObject> obj = ServiceUtil.mongo.query(dbCollectionName, query);
			if(obj != null && obj.size() >0){
				DBObject dbo = obj.get(0);	
				Object personObj = dbo.get("PersonJSON");
				patientJSON = personObj.toString();
			}
		}
		catch(Throwable t){
			t.printStackTrace();
			throw t;
		}
		
		return Response.status(200)
				.entity(patientJSON).build();
	}
	
	/**
	 * Return a Patient object, given the identifier
	 * 
	 * @param identifier patient identifier
	 * @return
	 */
	@POST
	@Path("/name")
	public Response getPatientByName(@FormParam("firstname") String firstname,
			@FormParam("lastname") String lastname) throws Throwable{
		String patientJSON = "";
		try{
			BasicDBObject query = new BasicDBObject();
			//I treat: just do lastname for now, filter later on firstname: "given":"jing"
			Pattern regex = Pattern.compile("\"family\":\""+lastname +"\"",Pattern.CASE_INSENSITIVE );
			query.put("PersonJSON",  regex);

			ArrayList<DBObject> obj = ServiceUtil.mongo.query(dbCollectionName, query);
			if(obj != null && obj.size() >0){
				DBObject dbo = obj.get(0);
				Object personObj = dbo.get("PersonJSON");
				patientJSON = personObj.toString();
			}
		}
		catch(Throwable t){
			t.printStackTrace();
			throw t;
		}
		
		return Response.status(200)
				.entity(patientJSON).build();
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
	@Path("/{identifier}/labs")
	public Response getPatientLabs(@PathParam("identifier") String identifier) throws Throwable{
		return Response.status(200)
				.entity("Would have gotten labs for patient " + identifier).build();
	}
	
	

}
