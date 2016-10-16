
package de.edgesoft.refereemanager.jaxb;

import java.time.LocalDateTime;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.edgesoft.edgeutils.commons.ext.LocalDateTimeAdapter;
import de.edgesoft.refereemanager.model.TitledIDTypeModel;


/**
 * <p>Java class for Date complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Date">
 *   &lt;complexContent>
 *     &lt;extension base="{}TitledIDType">
 *       &lt;sequence>
 *         &lt;element name="start" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="end" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="venue" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Date", propOrder = {
    "start",
    "end",
    "venue"
})
@XmlSeeAlso({
    OtherDate.class,
    Tournament.class
})
public class Date
    extends TitledIDTypeModel
{

    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected LocalDateTime start;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected LocalDateTime end;
    @XmlElement(type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Venue venue;

    /**
     * Gets the value of the start property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStart(LocalDateTime value) {
        this.start = value;
    }

    /**
     * Gets the value of the end property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Sets the value of the end property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnd(LocalDateTime value) {
        this.end = value;
    }

    /**
     * Gets the value of the venue property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Venue getVenue() {
        return venue;
    }

    /**
     * Sets the value of the venue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setVenue(Venue value) {
        this.venue = value;
    }

}
