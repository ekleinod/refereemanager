
package de.edgesoft.refereemanager.jaxb;

import javafx.beans.property.SimpleDoubleProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.edgesoft.edgeutils.javafx.SimpleDoublePropertyAdapter;
import de.edgesoft.refereemanager.model.AddressModel;


/**
 * <p>Java class for Venue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Venue">
 *   &lt;complexContent>
 *     &lt;extension base="{}Address">
 *       &lt;sequence>
 *         &lt;element name="latitude" type="{}DoubleProperty" minOccurs="0"/>
 *         &lt;element name="longitude" type="{}DoubleProperty" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Venue", propOrder = {
    "latitude",
    "longitude"
})
public class Venue
    extends AddressModel
{

    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(SimpleDoublePropertyAdapter.class)
    @XmlSchemaType(name = "double")
    protected SimpleDoubleProperty latitude;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(SimpleDoublePropertyAdapter.class)
    @XmlSchemaType(name = "double")
    protected SimpleDoubleProperty longitude;

    /**
     * Gets the value of the latitude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleDoubleProperty getLatitude() {
        return latitude;
    }

    /**
     * Sets the value of the latitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatitude(SimpleDoubleProperty value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the longitude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleDoubleProperty getLongitude() {
        return longitude;
    }

    /**
     * Sets the value of the longitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongitude(SimpleDoubleProperty value) {
        this.longitude = value;
    }

}
