
package de.edgesoft.refereemanager.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import de.edgesoft.refereemanager.model.EventDayModel;
import de.edgesoft.refereemanager.model.OtherEventModel;
import de.edgesoft.refereemanager.model.RefereeEventModel;
import de.edgesoft.refereemanager.model.TitledIDTypeModel;


/**
 * <p>Java class for EventDate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EventDate">
 *   &lt;complexContent>
 *     &lt;extension base="{}TitledIDType">
 *       &lt;sequence>
 *         &lt;element name="day" type="{}EventDay" maxOccurs="unbounded"/>
 *         &lt;element name="venue" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventDate", propOrder = {
    "day",
    "venue",
    "type"
})
@XmlSeeAlso({
    OtherEventModel.class,
    RefereeEventModel.class
})
public abstract class EventDate
    extends TitledIDTypeModel
{

    @XmlElement(required = true, type = EventDayModel.class)
    protected List<EventDay> day;
    @XmlElement(type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Venue venue;
    @XmlElement(type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected EventDateType type;

    /**
     * Gets the value of the day property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the day property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDay().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EventDay }
     * 
     * 
     */
    public List<EventDay> getDay() {
        if (day == null) {
            day = new ArrayList<EventDay>();
        }
        return this.day;
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

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public EventDateType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setType(EventDateType value) {
        this.type = value;
    }

}
