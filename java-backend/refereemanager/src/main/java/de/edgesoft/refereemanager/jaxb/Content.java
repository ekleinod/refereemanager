
package de.edgesoft.refereemanager.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import de.edgesoft.edgeutils.commons.ModelClass;
import de.edgesoft.refereemanager.model.ClubModel;
import de.edgesoft.refereemanager.model.PersonModel;
import de.edgesoft.refereemanager.model.RefereeModel;
import de.edgesoft.refereemanager.model.SeasonModel;
import de.edgesoft.refereemanager.model.StatusTypeModel;
import de.edgesoft.refereemanager.model.TeamModel;
import de.edgesoft.refereemanager.model.TrainingLevelTypeModel;


/**
 * <p>Java class for Content complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Content">
 *   &lt;complexContent>
 *     &lt;extension base="{}ModelClass">
 *       &lt;sequence>
 *         &lt;element name="season" type="{}Season"/>
 *         &lt;element name="exam" type="{}Exam"/>
 *         &lt;element name="person" type="{}Person" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="referee" type="{}Referee" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="trainee" type="{}Trainee" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="league" type="{}League" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="club" type="{}Club" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="team" type="{}Team" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sex_type" type="{}SexType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="contact_type" type="{}ContactType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="status_type" type="{}StatusType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="referee_assignment_type" type="{}RefereeAssignmentType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="training_level_type" type="{}TrainingLevelType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Content", propOrder = {
    "season",
    "exam",
    "person",
    "referee",
    "trainee",
    "league",
    "club",
    "team",
    "sexType",
    "contactType",
    "statusType",
    "refereeAssignmentType",
    "trainingLevelType"
})
public class Content
    extends ModelClass
{

    @XmlElement(required = true, type = SeasonModel.class)
    protected SeasonModel season;
    @XmlElement(required = true)
    protected Exam exam;
    @XmlElement(type = PersonModel.class)
    protected List<Person> person;
    @XmlElement(type = RefereeModel.class)
    protected List<Referee> referee;
    protected List<Trainee> trainee;
    protected List<League> league;
    @XmlElement(type = ClubModel.class)
    protected List<Club> club;
    @XmlElement(type = TeamModel.class)
    protected List<Team> team;
    @XmlElement(name = "sex_type")
    protected List<SexType> sexType;
    @XmlElement(name = "contact_type")
    protected List<ContactType> contactType;
    @XmlElement(name = "status_type", type = StatusTypeModel.class)
    protected List<StatusType> statusType;
    @XmlElement(name = "referee_assignment_type")
    protected List<RefereeAssignmentType> refereeAssignmentType;
    @XmlElement(name = "training_level_type", type = TrainingLevelTypeModel.class)
    protected List<TrainingLevelType> trainingLevelType;

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
        this.season = ((SeasonModel) value);
    }

    /**
     * Gets the value of the exam property.
     * 
     * @return
     *     possible object is
     *     {@link Exam }
     *     
     */
    public Exam getExam() {
        return exam;
    }

    /**
     * Sets the value of the exam property.
     * 
     * @param value
     *     allowed object is
     *     {@link Exam }
     *     
     */
    public void setExam(Exam value) {
        this.exam = value;
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
     * Gets the value of the trainee property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trainee property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrainee().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Trainee }
     * 
     * 
     */
    public List<Trainee> getTrainee() {
        if (trainee == null) {
            trainee = new ArrayList<Trainee>();
        }
        return this.trainee;
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
     * Gets the value of the club property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the club property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClub().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Club }
     * 
     * 
     */
    public List<Club> getClub() {
        if (club == null) {
            club = new ArrayList<Club>();
        }
        return this.club;
    }

    /**
     * Gets the value of the team property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the team property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTeam().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Team }
     * 
     * 
     */
    public List<Team> getTeam() {
        if (team == null) {
            team = new ArrayList<Team>();
        }
        return this.team;
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
     * Gets the value of the statusType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the statusType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStatusType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StatusType }
     * 
     * 
     */
    public List<StatusType> getStatusType() {
        if (statusType == null) {
            statusType = new ArrayList<StatusType>();
        }
        return this.statusType;
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

    /**
     * Gets the value of the trainingLevelType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trainingLevelType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrainingLevelType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TrainingLevelType }
     * 
     * 
     */
    public List<TrainingLevelType> getTrainingLevelType() {
        if (trainingLevelType == null) {
            trainingLevelType = new ArrayList<TrainingLevelType>();
        }
        return this.trainingLevelType;
    }

}
