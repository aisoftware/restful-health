package com.restfulhealth.services.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.core.MultivaluedMap;

import org.hl7.fhir.instance.model.Address;
import org.hl7.fhir.instance.model.AddressType;
import org.hl7.fhir.instance.model.Contact;
import org.hl7.fhir.instance.model.ContactParty;
import org.hl7.fhir.instance.model.ContactType;
import org.hl7.fhir.instance.model.GenderType;
import org.hl7.fhir.instance.model.HumanName;
import org.hl7.fhir.instance.model.NameType;
import org.hl7.fhir.instance.model.NameUse;
import org.hl7.fhir.instance.model.Patient;
import org.hl7.fhir.instance.model.Person;
import org.hl7.fhir.instance.model.AddressPartType;
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

public class PersonTest {
	private Gson gson = new Gson();
	
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
	public void jsonPerson() {
		Person person = generatePerson();		
		String personJSON = gson.toJson(person);
		
		System.out.println("personJSON="+personJSON);
	}
		
	@Test
	public void jsonPatient() {
		Patient patient = generatePatient();
		String patientJSON = gson.toJson(patient);
				
		System.out.println("patientJSON="+patientJSON);
	}
	
	@Test
	public void addPatient(){		
		try {
			Client client = Client.create();
			WebResource clientResource = client.resource(uri+"patient/add");
			Patient patient = generatePatient();
			String patientJSON = gson.toJson(patient);
			
			ClientResponse addPatientResp = clientResource.post(ClientResponse.class, patientJSON);
			
			System.out.println("addPatientResp response :" + addPatientResp.getStatus());
			System.out.println("Added patient UUID :" + addPatientResp.getEntity(String.class));
		
			System.out.println(" .... \n");
		}
		catch(Throwable t){
			t.printStackTrace();
		}
	}
	
	@Test
	public void getPatientByID(){		
		try {
//			String uuid = "ba75ec50-dbba-4671-8556-79c668a37c1d"; //song
			String uuid =  "78fcfbc4-b7dc-4714-a446-1ebfd9ed58a9"; //song22
//			String uuid = "66792a0d-c4ae-4468-8a38-2cb108a0e0c2";//binary song2
			Client client = Client.create();
			WebResource clientResource = client.resource(uri+"patient/"+uuid);
			
			ClientResponse getPatientResp = clientResource.get(ClientResponse.class);
			
			System.out.println("getPatientResp response :" + getPatientResp.getStatus());
			String patientJSON =  getPatientResp.getEntity(String.class);
			if(patientJSON != null)
				System.out.println("Get patient by UUID :" + patientJSON);
			else
				System.out.println("get no patient data back");
		
			System.out.println(" .... \n");
		}
		catch(Throwable t){
			t.printStackTrace();
		}
	}
	
	@Test
	public void getPatientByName(){		
		try {
			String firstname="Jing";
			String lastname = "Song";
			
			MultivaluedMap formData = new MultivaluedMapImpl();
			  formData.add("firstname", firstname);
			  formData.add("lastname", lastname);
			  
			Client client = Client.create();
			WebResource clientResource = client.resource(uri+"patient/name");
			
			ClientResponse getPatientResp = clientResource.post(ClientResponse.class, formData);
			
			System.out.println("getPatientResp response :" + getPatientResp.getStatus());
			String patientJSON =  getPatientResp.getEntity(String.class);
			if(patientJSON != null)
				System.out.println("Get patient by Name :" + patientJSON);
			else
				System.out.println("get no patient data back");
		
			System.out.println(" .... \n");
		}
		catch(Throwable t){
			t.printStackTrace();
		}
	}
	
	private Person generatePerson() {
		Person person = new Person();
		person.setBirthDate(new Date());
		person.setUuid(UUID.randomUUID().toString());
		
		Address address = new Address();
		address.setUse(AddressType.home);
		HashMap<AddressPartType, String> addressSet = new HashMap<AddressPartType, String>();
		addressSet.put(AddressPartType.line, "123 street");
		addressSet.put(AddressPartType.city, "palo alto");
		addressSet.put(AddressPartType.state, "CA");
		addressSet.put(AddressPartType.country, "US");
		addressSet.put(AddressPartType.zip, "94025");
		ArrayList<Address> addresses = new ArrayList<Address>();
		addresses.add(address);
		
		person.setAddress(addresses);
		person.setGender(GenderType.female);
		
		HumanName name = new HumanName();
		HashMap<NameType, String> nameSet = new HashMap<NameType, String>();
		nameSet.put(NameType.family, "song");
		nameSet.put(NameType.given, "jing");
		nameSet.put(NameType.loginName, "jingsong");
		name.setNameSet(nameSet);
		name.setUse(NameUse.usual);
		person.setName(name);
		person.setPassword("123");
		
		
		List<Contact> contacts = new ArrayList<Contact>();
		Contact email  = new Contact();
		email.setType(ContactType.email);
		email.setValue("jing@yahoo.com");
		contacts.add(email);
		
		Contact phone  = new Contact();
		phone.setType(ContactType.phone);
		phone.setValue("1-408-1234678");
		contacts.add(phone);
		person.setContacts(contacts);
		return person;
	}
	
	private Patient generatePatient() {
		Patient patient = new Patient();
		patient.setBirthDate(new Date());
		patient.setUuid(UUID.randomUUID().toString());
		
		Address address = new Address();
		address.setUse(AddressType.home);
		HashMap<AddressPartType, String> addressSet = new HashMap<AddressPartType, String>();
		addressSet.put(AddressPartType.line, "123 street");
		addressSet.put(AddressPartType.city, "palo alto");
		addressSet.put(AddressPartType.state, "CA");
		addressSet.put(AddressPartType.country, "US");
		addressSet.put(AddressPartType.zip, "94025");
		ArrayList<Address> addresses = new ArrayList<Address>();
		addresses.add(address);
		
		patient.setAddress(addresses);
		patient.setGender(GenderType.female);
		
		HumanName name = new HumanName();
		HashMap<NameType, String> nameSet = new HashMap<NameType, String>();
		nameSet.put(NameType.family, "song22");
		nameSet.put(NameType.given, "jing22");
		nameSet.put(NameType.loginName, "jingsong22");
		name.setNameSet(nameSet);
		name.setUse(NameUse.usual);
		patient.setName(name);
		patient.setPassword("123");
		
		
		List<Contact> contacts = new ArrayList<Contact>();
		Contact email  = new Contact();
		email.setType(ContactType.email);
		email.setValue("jing@yahoo.com");
		contacts.add(email);
		
		Contact phone  = new Contact();
		phone.setType(ContactType.phone);
		phone.setValue("1-408-1234567");
		contacts.add(phone);
		patient.setContacts(contacts);
		
		patient.setActive(true);
		
		List<ContactParty> contactParties = new ArrayList<ContactParty>();
		ContactParty contactParty = new ContactParty();
		HumanName name1 = new HumanName();
		HashMap<NameType, String> nameSet1 = new HashMap<NameType, String>();
		nameSet1.put(NameType.family, "Xu");
		nameSet1.put(NameType.given, "Mike");
		name1.setNameSet(nameSet1);
		name1.setUse(NameUse.usual);
		
		contactParty.setName(name1);
		patient.setContactParty(contactParties);
		
		return patient;
	}

}
