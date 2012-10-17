package org.hl7.fhir.instance.model;

public class Period {

    /**
     * The start of the period. The boundary is inclusive.
     */
    private String start;

    /**
     * The end of the period. If the high is missing, it means that the period is ongoing
     */
    private String end;

    public String getStart() { 
      return this.start;
    }

    public void setStart(String value) { 
      this.start = value;
    }

    public String getEnd() { 
      return this.end;
    }

    public void setEnd(String value) { 
      this.end = value;
    }


}

