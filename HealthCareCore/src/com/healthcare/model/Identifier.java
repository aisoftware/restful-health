package com.healthcare.model;

public class Identifier {

    /**
     * Establishes the namespace in which set of possible id values is unique.
     */
    private java.net.URI system;

    /**
     * The portion of the identifier typically displayed to the user and which is unique within the context of the system.
     */
    private String id;

    public java.net.URI getSystem() { 
      return this.system;
    }

    public void setSystem(java.net.URI value) { 
      this.system = value;
    }

    public String getId() { 
      return this.id;
    }

    public void setId(String value) { 
      this.id = value;
    }


}

