package org.hl7.fhir.instance.model;

public class Language {
    /**
     * A code that identifies the language
     */
    private String code;

    /**
     * A code that describes how well the language is spoken
     */
    private LanguageUse level;

    public String getCode() { 
      return this.code;
    }

    public void setCode(String value) { 
      this.code = value;
    }

    public LanguageUse getLevel() { 
      return this.level;
    }

    public void setLevel(LanguageUse value) { 
      this.level = value;
    }

}
