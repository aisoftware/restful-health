package org.hl7.fhir.instance.model;


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
