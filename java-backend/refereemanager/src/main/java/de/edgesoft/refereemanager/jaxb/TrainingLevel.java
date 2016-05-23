
package de.edgesoft.refereemanager.jaxb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for TrainingLevel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrainingLevel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="since" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="update" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="training_level_type" type="{http://www.w3.org/2001/XMLSchema}IDREF"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrainingLevel", propOrder = {
    "since",
    "update",
    "trainingLevelType"
})
public class TrainingLevel {

    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected Calendar since;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "date")
    protected List<Calendar> update;
    @XmlElement(name = "training_level_type", required = true, type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected TrainingLevelType trainingLevelType;

    /**
     * Gets the value of the since property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getSince() {
        return since;
    }

    /**
     * Sets the value of the since property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSince(Calendar value) {
        this.since = value;
    }

    /**
     * Gets the value of the update property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the update property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUpdate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<Calendar> getUpdate() {
        if (update == null) {
            update = new ArrayList<Calendar>();
        }
        return this.update;
    }

    /**
     * Gets the value of the trainingLevelType property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public TrainingLevelType getTrainingLevelType() {
        return trainingLevelType;
    }

    /**
     * Sets the value of the trainingLevelType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setTrainingLevelType(TrainingLevelType value) {
        this.trainingLevelType = value;
    }

}
