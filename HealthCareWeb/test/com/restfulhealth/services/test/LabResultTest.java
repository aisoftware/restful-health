package com.restfulhealth.services.test;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

public class LabResultTest {

	private static String uri = "http://localhost:8080/healthcare/service/";
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void convertXMLtoJSON() {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try{
	
			File file = new File("labSample.txt");
			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			InputStream is = LabResultTest.class.getResourceAsStream("/test-sample.xml");
			String xml = IOUtils.toString(is);				
			XMLSerializer xmlSerializer = new XMLSerializer(); 
			JSON json = xmlSerializer.read( xml );			
			String jsonResult = json.toString(2);
			jsonResult = jsonResult.replaceAll("@", "");
			fw.write(jsonResult);
			System.out.println( jsonResult);
		}
		catch(Throwable t){
			t.printStackTrace();
		}
		finally{
			try{
				if(bw != null)
					bw.close();
				if(fw != null)
					fw.close();
			}
			catch(Throwable t){
				t.printStackTrace();
			}
		}
	}
	
	@Test
	public void labReportUpload() {
		FormDataMultiPart fdmp = new FormDataMultiPart();
//		String pid = "c25654d2-6b87-48af-86e1-ac96e161e302"; //{"family":"simpson","loginName":"homer","given":"jing22"}		
		String pid = "79726790-97a9-4537-8f90-2c60c8463e30"; //jing song
		try {
			Client client = Client.create();
			WebResource uploadResource = client.resource(uri);			
			fdmp.bodyPart(new FileDataBodyPart("file", new File("/Users/Cause/Healthcare/HealthCareWeb/test/test-sample.xml")));
			ClientResponse cr = uploadResource.path("labReport/upload").type(MediaType.MULTIPART_FORM_DATA_TYPE).header("pid", pid).post(ClientResponse.class, fdmp);
			fdmp.close();
			System.out.println("labReportUpload_status="+cr.getStatus());
			System.out.println("labReportUpload_documentID="+cr.getEntity(String.class));	
		} 
		catch (Throwable t) {
			t.printStackTrace();
		}
		finally{
			try{
				fdmp.close();
			}
			catch(Throwable t){
				t.printStackTrace();
			}
		}
	}
	
	@Test
	public void getLabReportByID(){		
		try {
			String documentId = "858a33f4-6fdb-4f0c-b6a9-5c65597e0674"; //song22's reportID
			Client client = Client.create();
			WebResource clientResource = client.resource(uri+"labReport/"+documentId);
			
			ClientResponse getLabReportResp = clientResource.get(ClientResponse.class);
			
			System.out.println("getLabReportResp response :" + getLabReportResp.getStatus());
			String labJSON =  getLabReportResp.getEntity(String.class);
			if(labJSON != null)
				System.out.println("Get labReport by documentID :" + labJSON);
			else
				System.out.println("get no lab data back");
		
			System.out.println(" .... \n");
		}
		catch(Throwable t){
			t.printStackTrace();
		}
	}
	
	@Test
	public void getLabReportByPatientID(){		
		try {
			String pid = "c25654d2-6b87-48af-86e1-ac96e161e302"; //homer's patientID
			Client client = Client.create();
			WebResource clientResource = client.resource(uri+"labReport/patient/"+pid);
			
			ClientResponse getLabReportResp = clientResource.get(ClientResponse.class);
			
			System.out.println("getLabReportResp response :" + getLabReportResp.getStatus());
			String labJSON =  getLabReportResp.getEntity(String.class);
			if(labJSON != null)
				System.out.println("Get labReport by patientID :" + labJSON);
			else
				System.out.println("get no lab data back");
		
			System.out.println(" .... \n");
		}
		catch(Throwable t){
			t.printStackTrace();
		}
	}

}
