
package de.edgesoft.refereemanager.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import de.edgesoft.refereemanager.model.TitledIDTypeModel;


/**
 * <p>Java class for StatusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StatusType">
 *   &lt;complexContent>
 *     &lt;extension base="{}TitledIDType">
 *       &lt;sequence>
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="mmdmarkupstart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mmdmarkupend" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusType", propOrder = {
    "active",
    "mmdmarkupstart",
    "mmdmarkupend"
})
public class StatusType
    extends TitledIDTypeModel
{

    protected boolean active;
    protected String mmdmarkupstart;
    protected String mmdmarkupend;

    /**
     * Gets the value of the active property.
     * 
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     */
    public void setActive(boolean value) {
        this.active = value;
    }

    /**
     * Gets the value of the mmdmarkupstart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMmdmarkupstart() {
        return mmdmarkupstart;
    }

    /**
     * Sets the value of the mmdmarkupstart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMmdmarkupstart(String value) {
        this.mmdmarkupstart = value;
    }

    /**
     * Gets the value of the mmdmarkupend property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMmdmarkupend() {
        return mmdmarkupend;
    }

    /**
     * Sets the value of the mmdmarkupend property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMmdmarkupend(String value) {
        this.mmdmarkupend = value;
    }

}
