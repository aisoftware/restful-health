package com.restfulhealth.services;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public class LabService {

	/**
	 * Returns a full lab report
	 * @param reportId
	 * @return
	 */
	@GET
	@Path("/labReport/{reportId}")
	public Response getLab(@PathParam("reportId") String reportId) {
		return Response.status(200)
				.entity("Would have gotten lab report " + reportId).build();
	}
	
	@PUT
	@Path("/labReport")
	public Response putLab() {
		return Response.status(200)
				.entity("Putting lab report is not yet implemented").build();
	}

}
