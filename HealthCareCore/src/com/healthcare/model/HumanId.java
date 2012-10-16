package com.healthcare.model;

public class HumanId  {

    /**
     * The type of the identifier - to allow a particular identifier to be selected elsewhere
     */
    private Coding type;

    /**
     * The identifier itself
     */
    private Identifier identifier;

    /**
     * Time period during which identifier was valid for use
     */
    private Period period;

    /**
     * Organisation that issued/manages the identifier
     */
    private ResourceReference assigner;

    public Coding getType() { 
      return this.type;
    }

    public void setType(Coding value) { 
      this.type = value;
    }

    public Identifier getIdentifier() { 
      return this.identifier;
    }

    public void setIdentifier(Identifier value) { 
      this.identifier = value;
    }

    public Period getPeriod() { 
      return this.period;
    }

    public void setPeriod(Period value) { 
      this.period = value;
    }

    public ResourceReference getAssigner() { 
      return this.assigner;
    }

    public void setAssigner(ResourceReference value) { 
      this.assigner = value;
    }


}

