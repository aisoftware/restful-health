package org.hl7.fhir.instance.model;

import java.util.*;

/**
 * A patient is a person or animal that is receiving care
 */
public class Patient extends Person{

    /**
     * A linked patient record is a record that concerns the same patient. Records are linked after it is realised that at least one was created in error.
     */
    private List<ResourceReference> link = new ArrayList<ResourceReference>();

    /**
     * Whether the patient record is in use, or has been removed from active use
     */
    private java.lang.Boolean active;

    /**
     * The person or animal that this patient record is about
     */
    private ResourceReference subject;

    /**
     * The provider for whom this is a patient record
     */
    private ResourceReference provider;


    /**
     * Dietary restrictions for the patient
     */
    private CodeableConcept diet;

    /**
     * Confidentiality of the patient records
     */
    private CodeableConcept confidentiality;

    /**
     * The location of the paper record for the patient, if there is one
     */
    private CodeableConcept recordLocation;

    public List<ResourceReference> getLink() { 
      return this.link;
    }

    public java.lang.Boolean getActive() { 
      return this.active;
    }

    public void setActive(java.lang.Boolean value) { 
      this.active = value;
    }

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public ResourceReference getProvider() { 
      return this.provider;
    }

    public void setProvider(ResourceReference value) { 
      this.provider = value;
    }

    public CodeableConcept getDiet() { 
      return this.diet;
    }

    public void setDiet(CodeableConcept value) { 
      this.diet = value;
    }

    public CodeableConcept getConfidentiality() { 
      return this.confidentiality;
    }

    public void setConfidentiality(CodeableConcept value) { 
      this.confidentiality = value;
    }

    public CodeableConcept getRecordLocation() { 
      return this.recordLocation;
    }

    public void setRecordLocation(CodeableConcept value) { 
      this.recordLocation = value;
    }


}


