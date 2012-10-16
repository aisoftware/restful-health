package com.healthcare.model;

public class Contact {

    public enum ContactSystem {
        phone, // The value is a telephone number used for voice calls. Use of full international numbers starting with + is recommended to enable automatic dialing support but not required.
        fax, // The value is a fax machine. Use of full international numbers starting with + is recommended to enable automatic dialing support but not required.
        email, // The value is an email address
        url; // The value is a url. This is intended for various personal contacts including blogs, Twitter, Facebook, etc. Do not use for email addresses
        public static ContactSystem fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("phone".equals(codeString))
          return phone;
        if ("fax".equals(codeString))
          return fax;
        if ("email".equals(codeString))
          return email;
        if ("url".equals(codeString))
          return url;
        throw new Exception("Unknown ContactSystem code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case phone: return "phone";
            case fax: return "fax";
            case email: return "email";
            case url: return "url";
            default: return "?";
          }
        }
    }

    public enum ContactUse {
        home, // A communication contact at a home; attempted contacts for business purposes might intrude privacy and chances are one will contact family or other household members instead of the person one wishes to call. Typically used with urgent cases, or if no other contacts are available.
        work, // An office contact. First choice for business related contacts during business hours.
        temp, // A temporary contact. The period can provide more detailed information.
        old, // This contact is no longer in use (or was never correct, but retained for records)
        mobile; // A telecommunication device that moves and stays with its owner. May have characteristics of all other use codes, suitable for urgent matters, not the first choice for routine business
        public static ContactUse fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("home".equals(codeString))
          return home;
        if ("work".equals(codeString))
          return work;
        if ("temp".equals(codeString))
          return temp;
        if ("old".equals(codeString))
          return old;
        if ("mobile".equals(codeString))
          return mobile;
        throw new Exception("Unknown ContactUse code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case home: return "home";
            case work: return "work";
            case temp: return "temp";
            case old: return "old";
            case mobile: return "mobile";
            default: return "?";
          }
        }
    }

    /**
     * What kind of contact this is - what communications system is required to make use of the contact
     */
    private ContactSystem system;

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

    public ContactSystem getSystem() { 
      return this.system;
    }

    public void setSystem(ContactSystem value) { 
      this.system = value;
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

