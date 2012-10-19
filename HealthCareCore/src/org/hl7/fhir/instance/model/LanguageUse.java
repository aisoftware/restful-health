package org.hl7.fhir.instance.model;

public enum LanguageUse {
    none, // The person does not speak the language at all
    poor, // The person has minimal functional capability in the language
    useable, // The person can use the language, but may not be full conversant, particularly with regards to health concepts
    fluent; // The person is fully capable of using the language
    public static LanguageUse fromCode(String codeString) throws Exception {
        if (codeString == null || "".equals(codeString))
            return null;
    if ("none".equals(codeString))
      return none;
    if ("poor".equals(codeString))
      return poor;
    if ("useable".equals(codeString))
      return useable;
    if ("fluent".equals(codeString))
      return fluent;
    throw new Exception("Unknown LanguageUse code '"+codeString+"'");
    }
    public String toCode() {
      switch (this) {
        case none: return "none";
        case poor: return "poor";
        case useable: return "useable";
        case fluent: return "fluent";
        default: return "?";
      }
    }
}
