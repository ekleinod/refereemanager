
package de.edgesoft.refereemanager.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import de.edgesoft.refereemanager.model.AddressModel;
import de.edgesoft.refereemanager.model.EMailModel;
import de.edgesoft.refereemanager.model.PhoneNumberModel;
import de.edgesoft.refereemanager.model.TitledTypeModel;


/**
 * <p>Java class for Contact complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Contact">
 *   &lt;complexContent>
 *     &lt;extension base="{}TitledType">
 *       &lt;sequence>
 *         &lt;element name="is_primary" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="editor_only" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="contact_type" type="{http://www.w3.org/2001/XMLSchema}IDREF"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Contact", propOrder = {
    "isPrimary",
    "editorOnly",
    "contactType"
})
@XmlSeeAlso({
    AddressModel.class,
    URL.class,
    EMailModel.class,
    PhoneNumberModel.class
})
public abstract class Contact
    extends TitledTypeModel
{

    @XmlElement(name = "is_primary")
    protected boolean isPrimary;
    @XmlElement(name = "editor_only")
    protected boolean editorOnly;
    @XmlElement(name = "contact_type", required = true, type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected ContactType contactType;

    /**
     * Gets the value of the isPrimary property.
     * 
     */
    public boolean isIsPrimary() {
        return isPrimary;
    }

    /**
     * Sets the value of the isPrimary property.
     * 
     */
    public void setIsPrimary(boolean value) {
        this.isPrimary = value;
    }

    /**
     * Gets the value of the editorOnly property.
     * 
     */
    public boolean isEditorOnly() {
        return editorOnly;
    }

    /**
     * Sets the value of the editorOnly property.
     * 
     */
    public void setEditorOnly(boolean value) {
        this.editorOnly = value;
    }

    /**
     * Gets the value of the contactType property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public ContactType getContactType() {
        return contactType;
    }

    /**
     * Sets the value of the contactType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setContactType(ContactType value) {
        this.contactType = value;
    }

}
