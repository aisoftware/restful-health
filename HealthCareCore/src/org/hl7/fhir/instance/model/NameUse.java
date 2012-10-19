package org.hl7.fhir.instance.model;


public enum NameUse {
    usual, // Known as/conventional/the one you normally use
    official, // The formal name as registered in an official (government) registry, but which name might not be commonly used. May be called "legal name".
    temp, // A temporary name. A name valid time can provide more detailed information. This may also be used for temporary names assigned at birth or in emergency situations.
    anonymous, // Anonymous assigned name, alias, or pseudonym (used to protect a person's identity for privacy reasons)
    old, // This name is no longer in use (or was never correct, but retained for records)
    maiden; // A name used prior to marriage. Marriage naming customs vary greatly around the world. This name use is for use by applications that collect and store "maiden" names. Though the concept of maiden name is often gender specific, the use of this term is not gender specific. The use of this term does not imply any particular history for a person's name, nor should the maiden name be determined algorithmically.
    public static NameUse fromCode(String codeString) throws Exception {
        if (codeString == null || "".equals(codeString))
            return null;
    if ("usual".equals(codeString))
      return usual;
    if ("official".equals(codeString))
      return official;
    if ("temp".equals(codeString))
      return temp;
    if ("anonymous".equals(codeString))
      return anonymous;
    if ("old".equals(codeString))
      return old;
    if ("maiden".equals(codeString))
      return maiden;
    throw new Exception("Unknown NameUse code '"+codeString+"'");
    }
    public String toCode() {
      switch (this) {
        case usual: return "usual";
        case official: return "official";
        case temp: return "temp";
        case anonymous: return "anonymous";
        case old: return "old";
        case maiden: return "maiden";
        default: return "?";
      }
    }
}
