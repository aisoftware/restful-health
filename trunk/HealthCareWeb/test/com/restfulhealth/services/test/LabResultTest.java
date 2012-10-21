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
		String pid = "ba75ec50-dbba-4671-8556-79c668a37c1d"; //for jing song (patient)
		try {
			Client client = Client.create();
			WebResource uploadResource = client.resource(uri);			
			fdmp.bodyPart(new FileDataBodyPart("file", new File("/Users/JingSong/HealthCare/data/test-sample.xml")));
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
			String documentId =  "56a1f4d4-00ee-45e0-942f-2fca28d31636"; //song's reportID
			Client client = Client.create();
			WebResource clientResource = client.resource(uri+"labReport/"+documentId);
			
			ClientResponse getLabReportResp = clientResource.get(ClientResponse.class);
			
			System.out.println("getLabReportResp response :" + getLabReportResp.getStatus());
			String labJSON =  getLabReportResp.getEntity(String.class);
			if(labJSON != null)
				System.out.println("Get labReport by UUID :" + labJSON);
			else
				System.out.println("get no patlabient data back");
		
			System.out.println(" .... \n");
		}
		catch(Throwable t){
			t.printStackTrace();
		}
	}

}
