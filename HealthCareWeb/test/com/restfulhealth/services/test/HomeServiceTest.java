/**
 * 
 */
package com.restfulhealth.services.test;

import static org.junit.Assert.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author JingSong
 *
 */
public class HomeServiceTest {

	private static String uri = "http://localhost:8080/healthcare/service/";
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String username = "jing";
		String password = "123";
		try {
			Client client = Client.create();
			WebResource clientResource = client.resource(uri+"login");
			MultivaluedMap formData = new MultivaluedMapImpl();
			  formData.add("username", username);
			  formData.add("password", password);
			ClientResponse loginResp = clientResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData);

			System.out.println("login as :" + loginResp.getEntity(String.class));
		
			System.out.println(" .... \n");
		}
		catch(Throwable t){
			t.printStackTrace();
		}
	}

}
