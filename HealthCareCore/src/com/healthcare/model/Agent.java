package com.healthcare.model;

import java.util.*;

/**
 * A person who represents an organisation, and is authorised to perform actions on its behalf
 */
public class Agent {

    /**
     * The person who acts as the agent
     */
    private Person person;

    /**
     * The organisation that is being represented
     */
    private ResourceReference organization;

    /**
     * The way in which the person represents the organisation - what role do they have?
     */
    private List<CodeableConcept> role = new ArrayList<CodeableConcept>();

    /**
     * The time period during which the agent was/is authorised to represent the organisation.
     */
    private Period period;

    /**
     * An identifier that applies to this person in this role
     */
    private List<HumanId> identifier = new ArrayList<HumanId>();

    /**
     * A postal address for this person playing this role
     */
    private List<Address> address = new ArrayList<Address>();

    /**
     * A contact detail address for this person playing this role
     */
    private List<Contact> contact = new ArrayList<Contact>();

    public ResourceReference getPerson() { 
      return this.person;
    }

    public void setPerson(ResourceReference value) { 
      this.person = value;
    }

    public ResourceReference getOrganization() { 
      return this.organization;
    }

    public void setOrganization(ResourceReference value) { 
      this.organization = value;
    }

    public List<CodeableConcept> getRole() { 
      return this.role;
    }

    public Period getPeriod() { 
      return this.period;
    }

    public void setPeriod(Period value) { 
      this.period = value;
    }

    public List<HumanId> getIdentifier() { 
      return this.identifier;
    }

    public List<Address> getAddress() { 
      return this.address;
    }

    public List<Contact> getContact() { 
      return this.contact;
    }


}

