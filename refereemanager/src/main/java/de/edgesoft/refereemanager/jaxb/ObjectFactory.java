
package de.edgesoft.refereemanager.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import de.edgesoft.refereemanager.model.AddressModel;
import de.edgesoft.refereemanager.model.ClubModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.EMailModel;
import de.edgesoft.refereemanager.model.EventDayModel;
import de.edgesoft.refereemanager.model.LeagueGameModel;
import de.edgesoft.refereemanager.model.LeagueModel;
import de.edgesoft.refereemanager.model.OtherEventModel;
import de.edgesoft.refereemanager.model.PersonModel;
import de.edgesoft.refereemanager.model.PhoneNumberModel;
import de.edgesoft.refereemanager.model.RefereeModel;
import de.edgesoft.refereemanager.model.SeasonModel;
import de.edgesoft.refereemanager.model.StatusTypeModel;
import de.edgesoft.refereemanager.model.TeamModel;
import de.edgesoft.refereemanager.model.TournamentModel;
import de.edgesoft.refereemanager.model.TraineeModel;
import de.edgesoft.refereemanager.model.TrainingLevelModel;
import de.edgesoft.refereemanager.model.TrainingLevelTypeModel;
import de.edgesoft.refereemanager.model.URLModel;
import de.edgesoft.refereemanager.model.UpdateModel;
import de.edgesoft.refereemanager.model.VenueModel;
import de.edgesoft.refereemanager.model.WishModel;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.edgesoft.refereemanager.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Refereemanager_QNAME = new QName("", "refereemanager");
    private final static QName _LeagueRefereeReportRecipient_QNAME = new QName("", "referee_report_recipient");
    private final static QName _ClubVenue_QNAME = new QName("", "venue");
    private final static QName _ClubContactPerson_QNAME = new QName("", "contact_person");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.edgesoft.refereemanager.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RefereeManager }
     * 
     */
    public RefereeManager createRefereeManager() {
        return new RefereeManager();
    }

    /**
     * Create an instance of {@link ContactType }
     * 
     */
    public ContactType createContactType() {
        return new ContactType();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new AddressModel();
    }

    /**
     * Create an instance of {@link TrainingLevelType }
     * 
     */
    public TrainingLevelType createTrainingLevelType() {
        return new TrainingLevelTypeModel();
    }

    /**
     * Create an instance of {@link RoleType }
     * 
     */
    public RoleType createRoleType() {
        return new RoleType();
    }

    /**
     * Create an instance of {@link RefereeQuantity }
     * 
     */
    public RefereeQuantity createRefereeQuantity() {
        return new RefereeQuantity();
    }

    /**
     * Create an instance of {@link Update }
     * 
     */
    public Update createUpdate() {
        return new UpdateModel();
    }

    /**
     * Create an instance of {@link OtherEvent }
     * 
     */
    public OtherEvent createOtherEvent() {
        return new OtherEventModel();
    }

    /**
     * Create an instance of {@link Trainee }
     * 
     */
    public Trainee createTrainee() {
        return new TraineeModel();
    }

    /**
     * Create an instance of {@link EventDateType }
     * 
     */
    public EventDateType createEventDateType() {
        return new EventDateType();
    }

    /**
     * Create an instance of {@link TrainingLevel }
     * 
     */
    public TrainingLevel createTrainingLevel() {
        return new TrainingLevelModel();
    }

    /**
     * Create an instance of {@link Exam }
     * 
     */
    public Exam createExam() {
        return new Exam();
    }

    /**
     * Create an instance of {@link League }
     * 
     */
    public League createLeague() {
        return new LeagueModel();
    }

    /**
     * Create an instance of {@link Venue }
     * 
     */
    public Venue createVenue() {
        return new VenueModel();
    }

    /**
     * Create an instance of {@link EventDay }
     * 
     */
    public EventDay createEventDay() {
        return new EventDayModel();
    }

    /**
     * Create an instance of {@link Content }
     * 
     */
    public Content createContent() {
        return new ContentModel();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new PersonModel();
    }

    /**
     * Create an instance of {@link URL }
     * 
     */
    public URL createURL() {
        return new URLModel();
    }

    /**
     * Create an instance of {@link Team }
     * 
     */
    public Team createTeam() {
        return new TeamModel();
    }

    /**
     * Create an instance of {@link Season }
     * 
     */
    public Season createSeason() {
        return new SeasonModel();
    }

    /**
     * Create an instance of {@link SexType }
     * 
     */
    public SexType createSexType() {
        return new SexType();
    }

    /**
     * Create an instance of {@link RefereeAssignment }
     * 
     */
    public RefereeAssignment createRefereeAssignment() {
        return new RefereeAssignment();
    }

    /**
     * Create an instance of {@link Referee }
     * 
     */
    public Referee createReferee() {
        return new RefereeModel();
    }

    /**
     * Create an instance of {@link EMail }
     * 
     */
    public EMail createEMail() {
        return new EMailModel();
    }

    /**
     * Create an instance of {@link Wish }
     * 
     */
    public Wish createWish() {
        return new WishModel();
    }

    /**
     * Create an instance of {@link Club }
     * 
     */
    public Club createClub() {
        return new ClubModel();
    }

    /**
     * Create an instance of {@link StatusType }
     * 
     */
    public StatusType createStatusType() {
        return new StatusTypeModel();
    }

    /**
     * Create an instance of {@link LeagueGame }
     * 
     */
    public LeagueGame createLeagueGame() {
        return new LeagueGameModel();
    }

    /**
     * Create an instance of {@link PhoneNumber }
     * 
     */
    public PhoneNumber createPhoneNumber() {
        return new PhoneNumberModel();
    }

    /**
     * Create an instance of {@link RefereeAssignmentType }
     * 
     */
    public RefereeAssignmentType createRefereeAssignmentType() {
        return new RefereeAssignmentType();
    }

    /**
     * Create an instance of {@link Tournament }
     * 
     */
    public Tournament createTournament() {
        return new TournamentModel();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RefereeManager }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "refereemanager")
    public JAXBElement<RefereeManager> createRefereemanager(RefereeManager value) {
        return new JAXBElement<RefereeManager>(_Refereemanager_QNAME, RefereeManager.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "referee_report_recipient", scope = League.class)
    @XmlIDREF
    public JAXBElement<Object> createLeagueRefereeReportRecipient(Object value) {
        return new JAXBElement<Object>(_LeagueRefereeReportRecipient_QNAME, Object.class, League.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "venue", scope = Club.class)
    @XmlIDREF
    public JAXBElement<Object> createClubVenue(Object value) {
        return new JAXBElement<Object>(_ClubVenue_QNAME, Object.class, Club.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "contact_person", scope = Club.class)
    @XmlIDREF
    public JAXBElement<Object> createClubContactPerson(Object value) {
        return new JAXBElement<Object>(_ClubContactPerson_QNAME, Object.class, Club.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "venue", scope = Team.class)
    @XmlIDREF
    public JAXBElement<Object> createTeamVenue(Object value) {
        return new JAXBElement<Object>(_ClubVenue_QNAME, Object.class, Team.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "contact_person", scope = Team.class)
    @XmlIDREF
    public JAXBElement<Object> createTeamContactPerson(Object value) {
        return new JAXBElement<Object>(_ClubContactPerson_QNAME, Object.class, Team.class, value);
    }

}
