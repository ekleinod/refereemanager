
package de.edgesoft.refereemanager.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Referee complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Referee">
 *   &lt;complexContent>
 *     &lt;extension base="{}Person">
 *       &lt;sequence>
 *         &lt;element name="docs_by_letter" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}IDREF"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Referee", propOrder = {
    "docsByLetter",
    "status"
})
public class Referee
    extends Person
{

    @XmlElement(name = "docs_by_letter")
    protected boolean docsByLetter;
    @XmlElement(required = true, type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected StatusType status;

    /**
     * Gets the value of the docsByLetter property.
     * 
     */
    public boolean isDocsByLetter() {
        return docsByLetter;
    }

    /**
     * Sets the value of the docsByLetter property.
     * 
     */
    public void setDocsByLetter(boolean value) {
        this.docsByLetter = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public StatusType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setStatus(StatusType value) {
        this.status = value;
    }

}
