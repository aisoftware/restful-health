package org.hl7.fhir.instance.model;

public enum NameType {
    family, // Family name, this is the name that links to the genealogy. In some cultures (e.g. Eritrea) the family name of a son is the first name of his father.
    given, // Given name. NOTE: Not to be called "first name" since given names do not always come first.
    suffix, // Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that comes at the end of the name
    prefix, // Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that comes at the start of the name
    loginName;  //web screen login name
    
    
    public static NameType fromCode(String codeString) throws Exception {
        if (codeString == null || "".equals(codeString))
            return null;
	    if ("family".equals(codeString))
	      return family;
	    if ("given".equals(codeString))
	      return given;
	    if ("suffix".equals(codeString))
	      return suffix;
	    if ("prefix".equals(codeString))
	      return prefix;
	    if ("loginName".equals(codeString))
	        return loginName;
    
	    throw new Exception("Unknown NamePartType code '"+codeString+"'");
    }
    
    public String toCode() {
      switch (this) {
        case family: return "family";
        case given: return "given";
        case suffix: return "suffix";
        case prefix: return "prefix";
        case loginName: return "loginName";
        default: return "?";
      }
    }
}
