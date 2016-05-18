
package de.edgesoft.refereemanager.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RefereeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RefereeType">
 *   &lt;complexContent>
 *     &lt;extension base="{}PersonType">
 *       &lt;sequence>
 *         &lt;element name="docs_by_letter" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RefereeType", propOrder = {
    "docsByLetter"
})
public class RefereeType
    extends PersonType
{

    @XmlElement(name = "docs_by_letter")
    protected Boolean docsByLetter;

    /**
     * Gets the value of the docsByLetter property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDocsByLetter() {
        return docsByLetter;
    }

    /**
     * Sets the value of the docsByLetter property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDocsByLetter(Boolean value) {
        this.docsByLetter = value;
    }

}
