
package de.edgesoft.refereemanager.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Content complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Content">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="season" type="{}Season"/>
 *         &lt;element name="person" type="{}Person" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="referee" type="{}Referee" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="league" type="{}League" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sex_type" type="{}SexType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="contact_type" type="{}ContactType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="referee_assignment_type" type="{}RefereeAssignmentType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Content", propOrder = {
    "season",
    "person",
    "referee",
    "league",
    "sexType",
    "contactType",
    "refereeAssignmentType"
})
public class Content {

    @XmlElement(required = true)
    protected Season season;
    protected List<Person> person;
    protected List<Referee> referee;
    protected List<League> league;
    @XmlElement(name = "sex_type")
    protected List<SexType> sexType;
    @XmlElement(name = "contact_type")
    protected List<ContactType> contactType;
    @XmlElement(name = "referee_assignment_type")
    protected List<RefereeAssignmentType> refereeAssignmentType;

    /**
     * Gets the value of the season property.
     * 
     * @return
     *     possible object is
     *     {@link Season }
     *     
     */
    public Season getSeason() {
        return season;
    }

    /**
     * Sets the value of the season property.
     * 
     * @param value
     *     allowed object is
     *     {@link Season }
     *     
     */
    public void setSeason(Season value) {
        this.season = value;
    }

    /**
     * Gets the value of the person property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the person property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPerson().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Person }
     * 
     * 
     */
    public List<Person> getPerson() {
        if (person == null) {
            person = new ArrayList<Person>();
        }
        return this.person;
    }

    /**
     * Gets the value of the referee property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the referee property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferee().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Referee }
     * 
     * 
     */
    public List<Referee> getReferee() {
        if (referee == null) {
            referee = new ArrayList<Referee>();
        }
        return this.referee;
    }

    /**
     * Gets the value of the league property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the league property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLeague().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link League }
     * 
     * 
     */
    public List<League> getLeague() {
        if (league == null) {
            league = new ArrayList<League>();
        }
        return this.league;
    }

    /**
     * Gets the value of the sexType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sexType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSexType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SexType }
     * 
     * 
     */
    public List<SexType> getSexType() {
        if (sexType == null) {
            sexType = new ArrayList<SexType>();
        }
        return this.sexType;
    }

    /**
     * Gets the value of the contactType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contactType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContactType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContactType }
     * 
     * 
     */
    public List<ContactType> getContactType() {
        if (contactType == null) {
            contactType = new ArrayList<ContactType>();
        }
        return this.contactType;
    }

    /**
     * Gets the value of the refereeAssignmentType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refereeAssignmentType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefereeAssignmentType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RefereeAssignmentType }
     * 
     * 
     */
    public List<RefereeAssignmentType> getRefereeAssignmentType() {
        if (refereeAssignmentType == null) {
            refereeAssignmentType = new ArrayList<RefereeAssignmentType>();
        }
        return this.refereeAssignmentType;
    }

}
