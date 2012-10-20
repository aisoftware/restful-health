package com.restfulhealth.services;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Restful services related to the patient object
 * 
 * @author Tom Wilson
 * @author Jing Song
 *
 */
public class PatientService {

	
	public PatientService(@Context ServletContext servletContext) {
		if (ServiceUtil.mongoDBname == null) {
		ServiceUtil.init(servletContext);
		}
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
	@Path("/patient/{identifier}")
	public Response getPatient(@PathParam("identifier") String identifier) {
		return Response.status(200)
				.entity("Would have gotten patient " + identifier).build();
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
