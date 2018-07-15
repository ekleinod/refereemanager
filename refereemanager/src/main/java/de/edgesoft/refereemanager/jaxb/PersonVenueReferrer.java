
package de.edgesoft.refereemanager.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import de.edgesoft.refereemanager.model.ClubModel;
import de.edgesoft.refereemanager.model.EventDateModel;
import de.edgesoft.refereemanager.model.LeagueModel;
import de.edgesoft.refereemanager.model.PersonReferenceModel;
import de.edgesoft.refereemanager.model.TeamModel;
import de.edgesoft.refereemanager.model.TitledIDTypeModel;
import de.edgesoft.refereemanager.model.VenueReferenceModel;


/**
 * 
 * 				This class is introduces for reducing the number of GUI classes.
 * 				In my classes, they reflect the needed methods per class, thus e.g. a venue is needed in the class used for the controller class.
 * 				This would lead to different controllers and fxml files for the same kind of GUI, just because of reflection.
 * 				This means, all classes refer to venues and people through the name "venue" and "person" which does not reflect the role they play for the class.
 * 
 * 				This means, too, that classes refer to people, that should only refer to venues (e.g. events), and vice versa (e.g. leagues) but I did not want to introduce other interim classes.
 * 
 * 				Another downside ist, that all references are 0..unbounded.
 * 			
 * 
 * <p>Java class for PersonVenueReferrer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PersonVenueReferrer">
 *   &lt;complexContent>
 *     &lt;extension base="{}TitledIDType">
 *       &lt;sequence>
 *         &lt;element name="venue" type="{}VenueReference" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="person" type="{}PersonReference" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonVenueReferrer", propOrder = {
    "venue",
    "person"
})
@XmlSeeAlso({
    LeagueModel.class,
    TeamModel.class,
    ClubModel.class,
    EventDateModel.class
})
public class PersonVenueReferrer
    extends TitledIDTypeModel
{

    @XmlElement(type = VenueReferenceModel.class)
    protected List<VenueReference> venue;
    @XmlElement(type = PersonReferenceModel.class)
    protected List<PersonReference> person;

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
     * {@link VenueReference }
     * 
     * 
     */
    public List<VenueReference> getVenue() {
        if (venue == null) {
            venue = new ArrayList<VenueReference>();
        }
        return this.venue;
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
     * {@link PersonReference }
     * 
     * 
     */
    public List<PersonReference> getPerson() {
        if (person == null) {
            person = new ArrayList<PersonReference>();
        }
        return this.person;
    }

}
