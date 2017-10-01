
package de.edgesoft.refereemanager.jaxb;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.edgesoft.edgeutils.commons.AbstractModelClass;
import de.edgesoft.edgeutils.javafx.SimpleObjectPropertyLocalDateAdapter;
import de.edgesoft.edgeutils.javafx.SimpleObjectPropertyLocalTimeAdapter;


/**
 * <p>Java class for EventDay complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EventDay">
 *   &lt;complexContent>
 *     &lt;extension base="{}AbstractModelClass">
 *       &lt;sequence>
 *         &lt;element name="date" type="{}LocalDateProperty"/>
 *         &lt;element name="start_time" type="{}LocalTimeProperty" minOccurs="0"/>
 *         &lt;element name="end_time" type="{}LocalTimeProperty" minOccurs="0"/>
 *         &lt;element name="referee_assignment" type="{}RefereeAssignment" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventDay", propOrder = {
    "date",
    "startTime",
    "endTime",
    "refereeAssignment"
})
public class EventDay
    extends AbstractModelClass
{

    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleObjectPropertyLocalDateAdapter.class)
    @XmlSchemaType(name = "date")
    protected SimpleObjectProperty date;
    @XmlElement(name = "start_time", type = String.class)
    @XmlJavaTypeAdapter(SimpleObjectPropertyLocalTimeAdapter.class)
    @XmlSchemaType(name = "time")
    protected SimpleObjectProperty startTime;
    @XmlElement(name = "end_time", type = String.class)
    @XmlJavaTypeAdapter(SimpleObjectPropertyLocalTimeAdapter.class)
    @XmlSchemaType(name = "time")
    protected SimpleObjectProperty endTime;
    @XmlElement(name = "referee_assignment")
    protected List<RefereeAssignment> refereeAssignment;

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleObjectProperty getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(SimpleObjectProperty value) {
        this.date = value;
    }

    /**
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleObjectProperty getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartTime(SimpleObjectProperty value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleObjectProperty getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndTime(SimpleObjectProperty value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the refereeAssignment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refereeAssignment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefereeAssignment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RefereeAssignment }
     * 
     * 
     */
    public List<RefereeAssignment> getRefereeAssignment() {
        if (refereeAssignment == null) {
            refereeAssignment = new ArrayList<RefereeAssignment>();
        }
        return this.refereeAssignment;
    }

}
