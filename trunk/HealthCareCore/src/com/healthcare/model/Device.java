package com.healthcare.model;

import java.util.*;

/**
 * This resource identifies a manufactured entity that is used in the provision of healthcare. The device may be a machine, an insert, a computer, an application, etc
 */
public class Device {

    /**
     * Describes what kind of device that this 
     */
    private CodeableConcept type;

    /**
     * The name of the manufacturer
     */
    private String_ manufacturer;

    /**
     * The "model" - an identifier assigned by the manufacturer to identify the product by it's type. This number is shared by the all devices sold as the same type
     */
    private String_ model;

    /**
     * The version of the device, if the device has multiple releases under the same model, or if the device is software or carries firmware
     */
    private String_ version;

    /**
     * The serial number assigned by the organisation when the device was manufactured
     */
    private String_ serialNumber;

    /**
     * The organization that is responsible for the provision and ongoing maintenance of the device
     */
    private ResourceReference owner;

    /**
     * The identifier assigned to the device by the organisation that owns/manages the device
     */
    private List<Identifier> assignedId = new ArrayList<Identifier>();

    /**
     * The resource may be found in a literal location (i.e. GPS coordinates), a logical place (i.e. "in/with the patient"), or a coded location
     */
    private ResourceReference location;

    /**
     * Contact details for an organization or a particular human that is responsible for the device
     */
    private List<Contact> contact = new ArrayList<Contact>();

    /**
     * A network address on which the device may be contacted directly
     */
    private Contact address;

    public CodeableConcept getType() { 
      return this.type;
    }

    public void setType(CodeableConcept value) { 
      this.type = value;
    }

    public String_ getManufacturer() { 
      return this.manufacturer;
    }

    public void setManufacturer(String_ value) { 
      this.manufacturer = value;
    }

    public String_ getModel() { 
      return this.model;
    }

    public void setModel(String_ value) { 
      this.model = value;
    }

    public String_ getVersion() { 
      return this.version;
    }

    public void setVersion(String_ value) { 
      this.version = value;
    }

    public String_ getSerialNumber() { 
      return this.serialNumber;
    }

    public void setSerialNumber(String_ value) { 
      this.serialNumber = value;
    }

    public ResourceReference getOwner() { 
      return this.owner;
    }

    public void setOwner(ResourceReference value) { 
      this.owner = value;
    }

    public List<Identifier> getAssignedId() { 
      return this.assignedId;
    }

    public ResourceReference getLocation() { 
      return this.location;
    }

    public void setLocation(ResourceReference value) { 
      this.location = value;
    }

    public List<Contact> getContact() { 
      return this.contact;
    }

    public Contact getAddress() { 
      return this.address;
    }

    public void setAddress(Contact value) { 
      this.address = value;
    }


}
