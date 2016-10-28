
package de.edgesoft.refereemanager.jaxb;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.edgesoft.edgeutils.javafx.SimpleStringPropertyAdapter;
import de.edgesoft.refereemanager.model.DateModel;


/**
 * <p>Java class for Tournament complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Tournament">
 *   &lt;complexContent>
 *     &lt;extension base="{}Date">
 *       &lt;sequence>
 *         &lt;element name="announcement_u_r_l" type="{}StringProperty" minOccurs="0"/>
 *         &lt;element name="information_u_r_l" type="{}StringProperty" minOccurs="0"/>
 *         &lt;element name="result_u_r_l" type="{}StringProperty" minOccurs="0"/>
 *         &lt;element name="referee_quantity" type="{}RefereeQuantity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="organizing_club" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
 *         &lt;element name="organizer" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tournament", propOrder = {
    "announcementURL",
    "informationURL",
    "resultURL",
    "refereeQuantity",
    "organizingClub",
    "organizer"
})
public class Tournament
    extends DateModel
{

    @XmlElement(name = "announcement_u_r_l", type = String.class)
    @XmlJavaTypeAdapter(SimpleStringPropertyAdapter.class)
    protected SimpleStringProperty announcementURL;
    @XmlElement(name = "information_u_r_l", type = String.class)
    @XmlJavaTypeAdapter(SimpleStringPropertyAdapter.class)
    protected SimpleStringProperty informationURL;
    @XmlElement(name = "result_u_r_l", type = String.class)
    @XmlJavaTypeAdapter(SimpleStringPropertyAdapter.class)
    protected SimpleStringProperty resultURL;
    @XmlElement(name = "referee_quantity")
    protected List<RefereeQuantity> refereeQuantity;
    @XmlElement(name = "organizing_club", type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Club organizingClub;
    @XmlElement(type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Person organizer;

    /**
     * Gets the value of the announcementURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleStringProperty getAnnouncementURL() {
        return announcementURL;
    }

    /**
     * Sets the value of the announcementURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnnouncementURL(SimpleStringProperty value) {
        this.announcementURL = value;
    }

    /**
     * Gets the value of the informationURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleStringProperty getInformationURL() {
        return informationURL;
    }

    /**
     * Sets the value of the informationURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInformationURL(SimpleStringProperty value) {
        this.informationURL = value;
    }

    /**
     * Gets the value of the resultURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleStringProperty getResultURL() {
        return resultURL;
    }

    /**
     * Sets the value of the resultURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultURL(SimpleStringProperty value) {
        this.resultURL = value;
    }

    /**
     * Gets the value of the refereeQuantity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refereeQuantity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefereeQuantity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RefereeQuantity }
     * 
     * 
     */
    public List<RefereeQuantity> getRefereeQuantity() {
        if (refereeQuantity == null) {
            refereeQuantity = new ArrayList<RefereeQuantity>();
        }
        return this.refereeQuantity;
    }

    /**
     * Gets the value of the organizingClub property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Club getOrganizingClub() {
        return organizingClub;
    }

    /**
     * Sets the value of the organizingClub property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setOrganizingClub(Club value) {
        this.organizingClub = value;
    }

    /**
     * Gets the value of the organizer property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Person getOrganizer() {
        return organizer;
    }

    /**
     * Sets the value of the organizer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setOrganizer(Person value) {
        this.organizer = value;
    }

}
