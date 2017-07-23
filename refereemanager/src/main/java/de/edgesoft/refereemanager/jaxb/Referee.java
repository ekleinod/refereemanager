
package de.edgesoft.refereemanager.jaxb;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.edgesoft.edgeutils.javafx.SimpleBooleanPropertyAdapter;
import de.edgesoft.refereemanager.model.PersonModel;
import de.edgesoft.refereemanager.model.TraineeModel;
import de.edgesoft.refereemanager.model.TrainingLevelModel;
import de.edgesoft.refereemanager.model.WishModel;


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
 *         &lt;element name="docs_by_letter" type="{}BooleanProperty"/>
 *         &lt;element name="training_level" type="{}TrainingLevel" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="prefer" type="{}Wish" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="avoid" type="{}Wish" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="member" type="{http://www.w3.org/2001/XMLSchema}IDREF"/>
 *         &lt;element name="reffor" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
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
    "trainingLevel",
    "prefer",
    "avoid",
    "member",
    "reffor",
    "status"
})
@XmlSeeAlso({
    TraineeModel.class
})
public class Referee
    extends PersonModel
{

    @XmlElement(name = "docs_by_letter", required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleBooleanPropertyAdapter.class)
    @XmlSchemaType(name = "boolean")
    protected SimpleBooleanProperty docsByLetter;
    @XmlElement(name = "training_level", type = TrainingLevelModel.class)
    protected List<TrainingLevel> trainingLevel;
    @XmlElement(type = WishModel.class)
    protected List<Wish> prefer;
    @XmlElement(type = WishModel.class)
    protected List<Wish> avoid;
    @XmlElement(required = true, type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Club member;
    @XmlElement(type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Club reffor;
    @XmlElement(required = true, type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected StatusType status;

    /**
     * Gets the value of the docsByLetter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleBooleanProperty getDocsByLetter() {
        return docsByLetter;
    }

    /**
     * Sets the value of the docsByLetter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocsByLetter(SimpleBooleanProperty value) {
        this.docsByLetter = value;
    }

    /**
     * Gets the value of the trainingLevel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trainingLevel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrainingLevel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TrainingLevel }
     * 
     * 
     */
    public List<TrainingLevel> getTrainingLevel() {
        if (trainingLevel == null) {
            trainingLevel = new ArrayList<TrainingLevel>();
        }
        return this.trainingLevel;
    }

    /**
     * Gets the value of the prefer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prefer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrefer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Wish }
     * 
     * 
     */
    public List<Wish> getPrefer() {
        if (prefer == null) {
            prefer = new ArrayList<Wish>();
        }
        return this.prefer;
    }

    /**
     * Gets the value of the avoid property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the avoid property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAvoid().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Wish }
     * 
     * 
     */
    public List<Wish> getAvoid() {
        if (avoid == null) {
            avoid = new ArrayList<Wish>();
        }
        return this.avoid;
    }

    /**
     * Gets the value of the member property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Club getMember() {
        return member;
    }

    /**
     * Sets the value of the member property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setMember(Club value) {
        this.member = value;
    }

    /**
     * Gets the value of the reffor property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Club getReffor() {
        return reffor;
    }

    /**
     * Sets the value of the reffor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setReffor(Club value) {
        this.reffor = value;
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
