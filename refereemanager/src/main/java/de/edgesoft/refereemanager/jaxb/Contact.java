
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
import de.edgesoft.refereemanager.model.TitledIDTypeModel;
import de.edgesoft.refereemanager.model.URLModel;


/**
 * <p>Java class for Contact complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Contact">
 *   &lt;complexContent>
 *     &lt;extension base="{}TitledIDType">
 *       &lt;sequence>
 *         &lt;element name="is_primary" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="editor_only" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="contact_type" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
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
    URLModel.class,
    AddressModel.class,
    EMailModel.class,
    PhoneNumberModel.class
})
public abstract class Contact
    extends TitledIDTypeModel
{

    @XmlElement(name = "is_primary")
    protected Boolean isPrimary;
    @XmlElement(name = "editor_only")
    protected Boolean editorOnly;
    @XmlElement(name = "contact_type", type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected ContactType contactType;

    /**
     * Gets the value of the isPrimary property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsPrimary() {
        return isPrimary;
    }

    /**
     * Sets the value of the isPrimary property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsPrimary(Boolean value) {
        this.isPrimary = value;
    }

    /**
     * Gets the value of the editorOnly property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEditorOnly() {
        return editorOnly;
    }

    /**
     * Sets the value of the editorOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEditorOnly(Boolean value) {
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
