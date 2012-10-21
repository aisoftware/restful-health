package com.restfulhealth.services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.io.IOUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/labReport")
public class LabService {
	private String dbCollectionName = "labResult"; 
	
	public LabService(@Context ServletContext servletContext) {
		if (ServiceUtil.mongoDBname == null) {
			ServiceUtil.init(servletContext);
		}
	}

	/**
	 * Returns a full lab report
	 * @param reportId
	 * @return
	 */
	@GET
	@Path("/{reportId}")
	public Response getLab(@PathParam("reportId") String reportId) throws Throwable{	
		String labJSON = "";
		try{
			BasicDBObject query = new BasicDBObject();
			query.put("documentID", reportId);
			ArrayList<DBObject> obj = ServiceUtil.mongo.query(dbCollectionName, query);
			if(obj != null && obj.size() >0){
				DBObject dbo = obj.get(0);
				Object labObj = dbo.get("labJSON");
				labJSON = labObj.toString();
			}
		}
		catch(Throwable t){
			t.printStackTrace();
			throw t;
		}
		return Response.status(200).entity(labJSON).build();
	}
	
	/* =====================================File upload & Download ============================================================== */

	/**
	 * This method provides the caller to upload the file with its meta-data through HTTP POST using content-type: multipart/form-data
	 * @param headers HttpHeaders will carry all meta-data
	 * @param uploadedInputStream File InputStream
	 * @param fileDetail Content-Disposition: attachment; filename=xxxxx.file_extension;
	 * @return HttpResponse with status code or error messages
	 */
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@Context HttpHeaders headers,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		String fileName = null;
		String patientID = null;
		String documentID = "";
		try{
			if(fileDetail != null)
				fileName = fileDetail.getFileName();
			else
				return Response.noContent().status(400).build();
			
			MultivaluedMap<String, String> maps = headers.getRequestHeaders();
			if(maps != null){
				if(maps.get("pid") != null)
					patientID = maps.get("pid").get(0);
			}
			String labXML = IOUtils.toString(uploadedInputStream);			
			XMLSerializer xmlSerializer = new XMLSerializer(); 
			JSON json = xmlSerializer.read(labXML);
			String jsonResult = json.toString(2);
			jsonResult = jsonResult.replaceAll("@", "");
			
			documentID = UUID.randomUUID().toString();
			BasicDBObject obj = new BasicDBObject();				
			obj.put("personID", patientID);
			obj.put("labJSON", jsonResult);
			obj.put("documentName", fileName);
			obj.put("documentID", documentID);
			
			ServiceUtil.mongo.put(dbCollectionName, obj) ;
			
			return Response.status(200).entity(documentID).build();
		}
		catch(Throwable t){
			t.printStackTrace();
			return Response.status(500).build();
		}
	}

	/**
	 * This method provides the caller to download the file with its meta-data through HTTP GET using content-type: multipart/form-data
	 * @param headers HttpHeaders will carry all meta-data of the request on the way in Skyline
	 * @param fileName The key of the value
	 * @return binary data of the value in HttpResponse body, and the status cod or error messages in HttpResponse headers
	 */
	@GET
	@Path("/download")
	@Produces(MediaType.MULTIPART_FORM_DATA)
	public Response downloadFile(@Context HttpHeaders headers, @PathParam("fileName") String fileName){
		try{
			
			return Response.status(200).build();
		}
		catch(Throwable t){
			t.printStackTrace();
			return Response.status(500).build();
		}
	}


}
