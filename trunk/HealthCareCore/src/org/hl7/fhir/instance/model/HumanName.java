package org.hl7.fhir.instance.model;

import java.util.*;

/**
 * A human's name with the ability to identify parts and usage
 */
public class HumanName {

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

    public enum NamePartType {
        family, // Family name, this is the name that links to the genealogy. In some cultures (e.g. Eritrea) the family name of a son is the first name of his father.
        given, // Given name. NOTE: Not to be called "first name" since given names do not always come first.
        suffix, // Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that comes at the end of the name
        prefix; // Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that comes at the start of the name
        public static NamePartType fromCode(String codeString) throws Exception {
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
        throw new Exception("Unknown NamePartType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case family: return "family";
            case given: return "given";
            case suffix: return "suffix";
            case prefix: return "prefix";
            default: return "?";
          }
        }
    }

    public class Part {
        /**
         * Type of name part
         */
        private NamePartType type;

        /**
         * The content of the name part
         */
        private String value;

        public NamePartType getType() { 
          return this.type;
        }

        public void setType(NamePartType value) { 
          this.type = value;
        }

        public String getValue() { 
          return this.value;
        }

        public void setValue(String value) { 
          this.value = value;
        }

    }

    /**
     * Identifies the purpose for this name
     */
    private NameUse use;

    /**
     * a full text representation of the name
     */
    private String text;

    /**
     * Subdivision of the name at a level of granularity useful for analysis, sorting, matching or other purposes.
     */
    private List<Part> part = new ArrayList<Part>();

    /**
     * Indicates the period of time when this name was valid for the named person.
     */
    private Period period;

    public NameUse getUse() { 
      return this.use;
    }

    public void setUse(NameUse value) { 
      this.use = value;
    }

    public String getText() { 
      return this.text;
    }

    public void setText(String value) { 
      this.text = value;
    }

    public List<Part> getPart() { 
      return this.part;
    }

    public Period getPeriod() { 
      return this.period;
    }

    public void setPeriod(Period value) { 
      this.period = value;
    }


}

