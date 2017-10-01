
package de.edgesoft.refereemanager.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import de.edgesoft.edgeutils.commons.AbstractModelClass;
import de.edgesoft.refereemanager.model.ClubModel;
import de.edgesoft.refereemanager.model.LeagueGameModel;
import de.edgesoft.refereemanager.model.LeagueModel;
import de.edgesoft.refereemanager.model.OtherEventModel;
import de.edgesoft.refereemanager.model.PersonModel;
import de.edgesoft.refereemanager.model.RefereeModel;
import de.edgesoft.refereemanager.model.SeasonModel;
import de.edgesoft.refereemanager.model.StatusTypeModel;
import de.edgesoft.refereemanager.model.TeamModel;
import de.edgesoft.refereemanager.model.TournamentModel;
import de.edgesoft.refereemanager.model.TraineeModel;
import de.edgesoft.refereemanager.model.TrainingLevelTypeModel;
import de.edgesoft.refereemanager.model.VenueModel;


/**
 * <p>Java class for Content complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Content">
 *   &lt;complexContent>
 *     &lt;extension base="{}AbstractModelClass">
 *       &lt;sequence>
 *         &lt;element name="season" type="{}Season"/>
 *         &lt;element name="exam" type="{}Exam"/>
 *         &lt;element name="person" type="{}Person" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="referee" type="{}Referee" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="trainee" type="{}Trainee" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="league" type="{}League" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="club" type="{}Club" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="team" type="{}Team" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="league_game" type="{}LeagueGame" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tournament" type="{}Tournament" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="other_event" type="{}OtherEvent" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="venue" type="{}Venue" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sex_type" type="{}SexType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="contact_type" type="{}ContactType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="status_type" type="{}StatusType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="referee_assignment_type" type="{}RefereeAssignmentType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="training_level_type" type="{}TrainingLevelType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="role_type" type="{}PersonRoleType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="event_date_type" type="{}EventDateType" maxOccurs="unbounded" minOccurs="0"/>
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
    "leagueGame",
    "tournament",
    "otherEvent",
    "venue",
    "sexType",
    "contactType",
    "statusType",
    "refereeAssignmentType",
    "trainingLevelType",
    "roleType",
    "eventDateType"
})
public class Content
    extends AbstractModelClass
{

    @XmlElement(required = true, type = SeasonModel.class)
    protected SeasonModel season;
    @XmlElement(required = true)
    protected Exam exam;
    @XmlElement(type = PersonModel.class)
    protected List<Person> person;
    @XmlElement(type = RefereeModel.class)
    protected List<Referee> referee;
    @XmlElement(type = TraineeModel.class)
    protected List<Trainee> trainee;
    @XmlElement(type = LeagueModel.class)
    protected List<League> league;
    @XmlElement(type = ClubModel.class)
    protected List<Club> club;
    @XmlElement(type = TeamModel.class)
    protected List<Team> team;
    @XmlElement(name = "league_game", type = LeagueGameModel.class)
    protected List<LeagueGame> leagueGame;
    @XmlElement(type = TournamentModel.class)
    protected List<Tournament> tournament;
    @XmlElement(name = "other_event", type = OtherEventModel.class)
    protected List<OtherEvent> otherEvent;
    @XmlElement(type = VenueModel.class)
    protected List<Venue> venue;
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
    @XmlElement(name = "role_type")
    protected List<PersonRoleType> roleType;
    @XmlElement(name = "event_date_type")
    protected List<EventDateType> eventDateType;

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
     * Gets the value of the leagueGame property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the leagueGame property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLeagueGame().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LeagueGame }
     * 
     * 
     */
    public List<LeagueGame> getLeagueGame() {
        if (leagueGame == null) {
            leagueGame = new ArrayList<LeagueGame>();
        }
        return this.leagueGame;
    }

    /**
     * Gets the value of the tournament property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tournament property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTournament().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Tournament }
     * 
     * 
     */
    public List<Tournament> getTournament() {
        if (tournament == null) {
            tournament = new ArrayList<Tournament>();
        }
        return this.tournament;
    }

    /**
     * Gets the value of the otherEvent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the otherEvent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOtherEvent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OtherEvent }
     * 
     * 
     */
    public List<OtherEvent> getOtherEvent() {
        if (otherEvent == null) {
            otherEvent = new ArrayList<OtherEvent>();
        }
        return this.otherEvent;
    }

    /**
     * Gets the value of the venue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the venue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVenue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Venue }
     * 
     * 
     */
    public List<Venue> getVenue() {
        if (venue == null) {
            venue = new ArrayList<Venue>();
        }
        return this.venue;
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

    /**
     * Gets the value of the roleType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roleType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoleType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PersonRoleType }
     * 
     * 
     */
    public List<PersonRoleType> getRoleType() {
        if (roleType == null) {
            roleType = new ArrayList<PersonRoleType>();
        }
        return this.roleType;
    }

    /**
     * Gets the value of the eventDateType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventDateType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventDateType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EventDateType }
     * 
     * 
     */
    public List<EventDateType> getEventDateType() {
        if (eventDateType == null) {
            eventDateType = new ArrayList<EventDateType>();
        }
        return this.eventDateType;
    }

}
