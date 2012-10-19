package org.hl7.fhir.instance.model;

import java.util.*;

/**
 * A human's name with the ability to identify parts and usage
 */
public class HumanName {

    /**
     * Identifies the purpose for this name
     */
    private NameUse use;

    /**
     * a full text representation of the name
     */
    private String text;

    /**
     * Subdivision of the name at a level of granularity useful for analysis, sorting, matching or other purposes.
     * <NameType.family, "song">
     * <NameType.given, "jing">
     */
    private HashMap<NameType, String> name = new HashMap<NameType, String>();

    /**
     * Indicates the period of time when this name was valid for the named person.
     */
    private Period period;

    public NameUse getUse() { 
      return this.use;
    }

    public void setUse(NameUse value) { 
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

	public HashMap<NameType, String> getNameSet() {
		return name;
	}

	public void setNameSet(HashMap<NameType, String> nameSet) {
		this.name = nameSet;
	}


}

