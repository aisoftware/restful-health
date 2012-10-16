package com.healthcare.model;

import java.util.*;

/**
 * For any company/corporation/institution/government department that has relevance to the care process
 */
public class Organization {

    public class Name {
        /**
         * The actual name of the organization
         */
        private String value;

        /**
         * The period that this name was in use by the organization
         */
        private Period period;

        public String getValue() { 
          return this.value;
        }

        public void setValue(String value) { 
          this.value = value;
        }

        public Period getPeriod() { 
          return this.period;
        }

        public void setPeriod(Period value) { 
          this.period = value;
        }

    }

    public class Accreditation {
        /**
         * The identifier of the accreditation
         */
        private Identifier identifier;

        /**
         * The type of the accreditation
         */
        private CodeableConcept code;

        /**
         * The organization that confered/confers the accreditation
         */
        private ResourceReference institution;

        /**
         * The period for which the accreditation is held
         */
        private Period period;

        public Identifier getIdentifier() { 
          return this.identifier;
        }

        public void setIdentifier(Identifier value) { 
          this.identifier = value;
        }

        public CodeableConcept getCode() { 
          return this.code;
        }

        public void setCode(CodeableConcept value) { 
          this.code = value;
        }

        public ResourceReference getInstitution() { 
          return this.institution;
        }

        public void setInstitution(ResourceReference value) { 
          this.institution = value;
        }

        public Period getPeriod() { 
          return this.period;
        }

        public void setPeriod(Period value) { 
          this.period = value;
        }

    }

    public class RelatedOrganization {
        /**
         * Identifier the related organization - may be a full link to an Organization resource, or some other kind of identifier
         */
        private HumanId identifier;

        /**
         * Code that specifies how this organization is related to the subject. A code is required.
         */
        private CodeableConcept code;

        /**
         * A name should be specified for the related organization
         */
        private String name;

        /**
         * Postal addresses may be provided for the related organization
         */
        private List<Address> address = new ArrayList<Address>();

        /**
         * Contact details (phone, email etc) may be provided for the related organization
         */
        private List<Contact> contact = new ArrayList<Contact>();

        /**
         * The period during which the organizations were related in this fashion
         */
        private Period period;

        public HumanId getIdentifier() { 
          return this.identifier;
        }

        public void setIdentifier(HumanId value) { 
          this.identifier = value;
        }

        public CodeableConcept getCode() { 
          return this.code;
        }

        public void setCode(CodeableConcept value) { 
          this.code = value;
        }

        public String getName() { 
          return this.name;
        }

        public void setName(String value) { 
          this.name = value;
        }

        public List<Address> getAddress() { 
          return this.address;
        }

        public List<Contact> getContact() { 
          return this.contact;
        }

        public Period getPeriod() { 
          return this.period;
        }

        public void setPeriod(Period value) { 
          this.period = value;
        }

    }

    /**
     * Identifier for the organization that is used to identify the organization across multiple disparate systems
     */
    private List<HumanId> identifier = new ArrayList<HumanId>();

    /**
     * A name associated with the organization
     */
    private List<Name> name = new ArrayList<Name>();

    /**
     * An address for the organization
     */
    private List<Address> address = new ArrayList<Address>();

    /**
     * A contact detail for the organization
     */
    private List<Contact> contact = new ArrayList<Contact>();

    /**
     * The kind of organization that this is
     */
    private CodeableConcept code;

    /**
     * The industry that this organization is involved in
     */
    private CodeableConcept industryCode;

    /**
     * The qualifications/certifications an organisation has, including format educational achievements, accreditations, and current certifications. All these qualifications may be used to determine what roles the organisation may play in a healthcare environment
     */
    private List<Accreditation> accreditation = new ArrayList<Accreditation>();

    /**
     * Other organizations who are related to this person. The relationship might be one of several types: sub- or super- orgnizations (i.e. ward in a hospital, owning corporation of a hospital) or partner organizations (i.e. the operating corporation for a hospital)
     */
    private List<RelatedOrganization> relatedOrganization = new ArrayList<RelatedOrganization>();

    public List<HumanId> getIdentifier() { 
      return this.identifier;
    }

    public List<Name> getName() { 
      return this.name;
    }

    public List<Address> getAddress() { 
      return this.address;
    }

    public List<Contact> getContact() { 
      return this.contact;
    }

    public CodeableConcept getCode() { 
      return this.code;
    }

    public void setCode(CodeableConcept value) { 
      this.code = value;
    }

    public CodeableConcept getIndustryCode() { 
      return this.industryCode;
    }

    public void setIndustryCode(CodeableConcept value) { 
      this.industryCode = value;
    }

    public List<Accreditation> getAccreditation() { 
      return this.accreditation;
    }

    public List<RelatedOrganization> getRelatedOrganization() { 
      return this.relatedOrganization;
    }


}


