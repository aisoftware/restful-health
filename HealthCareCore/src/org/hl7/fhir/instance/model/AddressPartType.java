package org.hl7.fhir.instance.model;


public enum AddressPartType {
    part, // Part of an address line (typically used with an extension that further defines the meaning of the part).
    line, // A line of an address (typically used for street names & numbers, unit details, delivery hints, etc.) .
    city, // The name of the city, town, village, or other community or delivery centre.
    state, // Sub-unit of a country with limited sovereignty in a federally organized country. A code may be used if codes are in common use (i.e. US 2 letter state codes).
    country, // Country. ISO 3166 3 letter codes can be used in place of a full country name.
    zip, // A postal code designating a region defined by the postal service.
    dpid; // A value that uniquely identifies the postal address. (Often used in barcodes).
    
    public static AddressPartType fromCode(String codeString) throws Exception {
        if (codeString == null || "".equals(codeString))
            return null;
	    if ("part".equals(codeString))
	      return part;
	    if ("line".equals(codeString))
	      return line;
	    if ("city".equals(codeString))
	      return city;
	    if ("state".equals(codeString))
	      return state;
	    if ("country".equals(codeString))
	      return country;
	    if ("zip".equals(codeString))
	      return zip;
	    if ("dpid".equals(codeString))
	      return dpid;
	    throw new Exception("Unknown AddressPartType code '"+codeString+"'");
    }
    
    public String toCode() {
      switch (this) {
        case part: return "part";
        case line: return "line";
        case city: return "city";
        case state: return "state";
        case country: return "country";
        case zip: return "zip";
        case dpid: return "dpid";
        default: return "?";
      }
    }
}
