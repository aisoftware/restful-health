package org.hl7.fhir.instance.model;

import java.util.ArrayList;
import java.util.List;

public class ContactParty {
    /**
     * The type of the contact party
     */
    private CodeableConcept role;

    /**
     * The name of the contact party
     */
    private HumanName name;

    /**
     * The address of the contact party
     */
    private List<Address> address = new ArrayList<Address>();

    /**
     * The telecommunication addresses of the contact party, e.g. phone, fax, email etcetera
     */
    private List<Contact> telecom = new ArrayList<Contact>();

    /**
     * Reference to the Person|Organization|Agent resource that is the contact party
     */
    private ResourceReference party;

    public CodeableConcept getRole() { 
      return this.role;
    }

    public void setRole(CodeableConcept value) { 
      this.role = value;
    }

    public HumanName getName() { 
      return this.name;
    }

    public void setName(HumanName value) { 
      this.name = value;
    }

    public List<Address> getAddress() { 
      return this.address;
    }

    public List<Contact> getTelecom() { 
      return this.telecom;
    }

    public ResourceReference getParty() { 
      return this.party;
    }

    public void setParty(ResourceReference value) { 
      this.party = value;
    }

}
