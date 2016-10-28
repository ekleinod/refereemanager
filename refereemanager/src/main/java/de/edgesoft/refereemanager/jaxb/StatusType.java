
package de.edgesoft.refereemanager.jaxb;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.edgesoft.edgeutils.javafx.SimpleBooleanPropertyAdapter;
import de.edgesoft.edgeutils.javafx.SimpleStringPropertyAdapter;
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
 *         &lt;element name="active" type="{}BooleanProperty"/>
 *         &lt;element name="mmdmarkupstart" type="{}StringProperty" minOccurs="0"/>
 *         &lt;element name="mmdmarkupend" type="{}StringProperty" minOccurs="0"/>
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

    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleBooleanPropertyAdapter.class)
    @XmlSchemaType(name = "boolean")
    protected SimpleBooleanProperty active;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(SimpleStringPropertyAdapter.class)
    protected SimpleStringProperty mmdmarkupstart;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(SimpleStringPropertyAdapter.class)
    protected SimpleStringProperty mmdmarkupend;

    /**
     * Gets the value of the active property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleBooleanProperty getActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActive(SimpleBooleanProperty value) {
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
    public SimpleStringProperty getMmdmarkupstart() {
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
    public void setMmdmarkupstart(SimpleStringProperty value) {
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
    public SimpleStringProperty getMmdmarkupend() {
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
    public void setMmdmarkupend(SimpleStringProperty value) {
        this.mmdmarkupend = value;
    }

}
