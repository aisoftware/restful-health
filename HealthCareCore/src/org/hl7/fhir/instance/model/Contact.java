package org.hl7.fhir.instance.model;

public class Contact {

    /**
     * What kind of contact this is - what communications system is required to make use of the contact
     */
    private ContactType contactType;

    /**
     * The actual contact details, in a form that is meaningful to the designated communication system (i.e. phone number or email address).
     */
    private String value;

    /**
     * Identifies the context for the address
     */
    private ContactUse use;

    /**
     * Time period when the contact was/is in use
     */
    private Period period;

    public ContactType getType() { 
      return this.contactType;
    }

    public void setType(ContactType value) { 
      this.contactType = value;
    }

    public String getValue() { 
      return this.value;
    }

    public void setValue(String value) { 
      this.value = value;
    }

    public ContactUse getUse() { 
      return this.use;
    }

    public void setUse(ContactUse value) { 
      this.use = value;
    }

    public Period getPeriod() { 
      return this.period;
    }

    public void setPeriod(Period value) { 
      this.period = value;
    }


}

