
package de.edgesoft.refereemanager.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import de.edgesoft.refereemanager.model.PersonModel;


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
 *         &lt;element name="revoke_license" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="training_level" type="{}TrainingLevel" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wish" type="{}Wish" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="club_relation" type="{}ClubRelation" maxOccurs="2"/>
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
    "revokeLicense",
    "trainingLevel",
    "wish",
    "clubRelation",
    "status"
})
public class Referee
    extends PersonModel
{

    @XmlElement(name = "docs_by_letter")
    protected boolean docsByLetter;
    @XmlElement(name = "revoke_license")
    protected Boolean revokeLicense;
    @XmlElement(name = "training_level")
    protected List<TrainingLevel> trainingLevel;
    protected List<Wish> wish;
    @XmlElement(name = "club_relation", required = true)
    protected List<ClubRelation> clubRelation;
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
     * Gets the value of the revokeLicense property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRevokeLicense() {
        return revokeLicense;
    }

    /**
     * Sets the value of the revokeLicense property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRevokeLicense(Boolean value) {
        this.revokeLicense = value;
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
     * Gets the value of the wish property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wish property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWish().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Wish }
     * 
     * 
     */
    public List<Wish> getWish() {
        if (wish == null) {
            wish = new ArrayList<Wish>();
        }
        return this.wish;
    }

    /**
     * Gets the value of the clubRelation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clubRelation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClubRelation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClubRelation }
     * 
     * 
     */
    public List<ClubRelation> getClubRelation() {
        if (clubRelation == null) {
            clubRelation = new ArrayList<ClubRelation>();
        }
        return this.clubRelation;
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
