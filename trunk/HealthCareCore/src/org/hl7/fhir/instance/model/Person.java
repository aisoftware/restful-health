package org.hl7.fhir.instance.model;

import java.util.*;

/**
 * A person who is involved in the healthcare process
 */
public class Person  {

    /**
     * Identifier for the person that is used to identify the person across multiple disparate systems and also for face to face identification of the person
     */
    private List<HumanId> identifier = new ArrayList<HumanId>();

    /**
     * A name associated with the person
     */
//    private List<HumanName> name = new ArrayList<HumanName>();
    private HumanName name = null;
    /**
     * A contact detail for the person
     */
    private List<Contact> telecom = new ArrayList<Contact>();

    /**
     * Administrative Gender
     */
    private CodeableConcept gender;

    /**
     * The birth date for the person
     */
    private DateTime birthDate;

    /**
     * Indicates if the Person deceased or not
     */
    private Boolean deceased;

    /**
     * An address for the person
     */
    private List<Address> address = new ArrayList<Address>();

    /**
     * This field contains the patient's marital (civil) status.
     */
    private DateTime maritalStatus;

    /**
     * A generic contact party for the person. 
     */
    private List<ContactParty> contactParty = new ArrayList<ContactParty>();

    /**
     * A language spoken by the person, with proficiency
     */
    private List<Language> language = new ArrayList<Language>();
    
    
    private String password  = null;

    public List<HumanId> getIdentifier() { 
      return this.identifier;
    }

    public HumanName getName() { 
      return this.name;
    }

    public List<Contact> getTelecom() { 
      return this.telecom;
    }

    public CodeableConcept getGender() { 
      return this.gender;
    }

    public void setGender(CodeableConcept value) { 
      this.gender = value;
    }

    public DateTime getBirthDate() { 
      return this.birthDate;
    }

    public void setBirthDate(DateTime value) { 
      this.birthDate = value;
    }

    public Boolean getDeceased() { 
      return this.deceased;
    }

    public void setDeceased(Boolean value) { 
      this.deceased = value;
    }

    public List<Address> getAddress() { 
      return this.address;
    }

    public DateTime getMaritalStatus() { 
      return this.maritalStatus;
    }

    public void setMaritalStatus(DateTime value) { 
      this.maritalStatus = value;
    }

    public List<ContactParty> getContactParty() { 
      return this.contactParty;
    }

    public List<Language> getLanguage() { 
      return this.language;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setIdentifier(List<HumanId> identifier) {
		this.identifier = identifier;
	}

	public void setName(HumanName name) {
		this.name = name;
	}

	public void setTelecom(List<Contact> telecom) {
		this.telecom = telecom;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public void setContactParty(List<ContactParty> contactParty) {
		this.contactParty = contactParty;
	}

	public void setLanguage(List<Language> language) {
		this.language = language;
	}


}

