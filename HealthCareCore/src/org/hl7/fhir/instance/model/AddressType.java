package org.hl7.fhir.instance.model;

public enum AddressType {
	home, // A communication address at a home
    work, // An office address. First choice for business related contacts during business hours
    temp, // A temporary address. The period can provide more detailed information
    old; // This address is no longer in use (or was never correct, but retained for records)
    public static AddressType fromCode(String codeString) throws Exception {
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
    throw new Exception("Unknown AddressUse code '"+codeString+"'");
    }
    public String toCode() {
      switch (this) {
        case home: return "home";
        case work: return "work";
        case temp: return "temp";
        case old: return "old";
        default: return "?";
      }
    }

}
