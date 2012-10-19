package org.hl7.fhir.instance.model;

public enum ContactType {
        phone, // The value is a telephone number used for voice calls. Use of full international numbers starting with + is recommended to enable automatic dialing support but not required.
        fax, // The value is a fax machine. Use of full international numbers starting with + is recommended to enable automatic dialing support but not required.
        email, // The value is an email address
        url; // The value is a url. This is intended for various personal contacts including blogs, Twitter, Facebook, etc. Do not use for email addresses
        
        public static ContactType fromCode(String codeString) throws Exception {
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
