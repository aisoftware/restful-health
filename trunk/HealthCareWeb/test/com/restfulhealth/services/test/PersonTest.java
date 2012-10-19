package com.restfulhealth.services.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hl7.fhir.instance.model.Address;
import org.hl7.fhir.instance.model.AddressType;
import org.hl7.fhir.instance.model.Contact;
import org.hl7.fhir.instance.model.ContactType;
import org.hl7.fhir.instance.model.GenderType;
import org.hl7.fhir.instance.model.HumanName;
import org.hl7.fhir.instance.model.NameType;
import org.hl7.fhir.instance.model.NameUse;
import org.hl7.fhir.instance.model.Person;
import org.hl7.fhir.instance.model.AddressPartType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

public class PersonTest {
	private Gson gson = new Gson();
	private String personJSON = null;

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
	public void json() {
		Person person = new Person();
		person.setBirthDate(new Date());
		
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
		
		String personJSON = gson.toJson(person);
		
		System.out.println("personJSON="+personJSON);
	}
	
//	@Test()
//	private void addPerson(){
//		json();
//		
//	}

}
