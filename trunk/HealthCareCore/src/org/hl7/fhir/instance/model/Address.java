package org.hl7.fhir.instance.model;

import java.util.*;

/**
 * There is a variety of postal address formats defined around the world. This format defines a superset that is the basis for addresses all around the world 
 */
public class Address {

    /**
     * Identifies the intended purpose of this address
     */
    private AddressType use;

    /**
     * a full text representation of the address
     */
    private String text;
    
    private HashMap<AddressPartType, String> addressSet = null;

    /**
     * Time period when address was/is in use
     */
    private Period period;

    public AddressType getUse() { 
      return this.use;
    }

    public void setUse(AddressType value) { 
      this.use = value;
    }

    public String getText() { 
      return this.text;
    }

    public void setText(String value) { 
      this.text = value;
    }

    public Period getPeriod() { 
      return this.period;
    }

    public void setPeriod(Period value) { 
      this.period = value;
    }

	public HashMap<AddressPartType, String> getAddressSet() {
		return addressSet;
	}

	public void setAddressSet(HashMap<AddressPartType, String> addressSet) {
		this.addressSet = addressSet;
	}


}
