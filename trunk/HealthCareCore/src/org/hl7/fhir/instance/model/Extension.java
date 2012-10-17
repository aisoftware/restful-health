package org.hl7.fhir.instance.model;

import java.util.*;

/**
 * Optional Extensions Element - found in all resources
 */
public class Extension {

    /**
     * The code that identifies the meaning of the extension by reference to the definitions
     */
    private String code;

    /**
     * Source of the definition for the extension code - a namespace or a URL
     */
    private java.net.URI profile;

    /**
     * Internal reference to context of the extension - a pointer to an xml:id in the same resource
     */
    private String ref;

    /**
     * If this element is set to true, then the containing resource element and its children are only safe to process if the reader understands this extension. 
     */
    private java.lang.Boolean mustUnderstand;

    /**
     * Value of extension - may be a resource or one of a constraint set of the data types (see Extensibility in the spec for list)
     */
    private org.hl7.fhir.instance.model.Type value;

    /**
     * Nested Extensions - further extensions that are part of the extension
     */
    private List<Extension> extension = new ArrayList<Extension>();

    public String getCode() { 
      return this.code;
    }

    public void setCode(String value) { 
      this.code = value;
    }

    public java.net.URI getProfile() { 
      return this.profile;
    }

    public void setProfile(java.net.URI value) { 
      this.profile = value;
    }

    public String getRef() { 
      return this.ref;
    }

    public void setRef(String value) { 
      this.ref = value;
    }

    public java.lang.Boolean getMustUnderstand() { 
      return this.mustUnderstand;
    }

    public void setMustUnderstand(java.lang.Boolean value) { 
      this.mustUnderstand = value;
    }

    public org.hl7.fhir.instance.model.Type getValue() { 
      return this.value;
    }

    public void setValue(org.hl7.fhir.instance.model.Type value) { 
      this.value = value;
    }

    public List<Extension> getExtension() { 
      return this.extension;
    }


}


