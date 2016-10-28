
package de.edgesoft.refereemanager.jaxb;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.edgesoft.edgeutils.javafx.SimpleIntegerPropertyAdapter;
import de.edgesoft.refereemanager.model.TitledIDTypeModel;


/**
 * <p>Java class for Team complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Team">
 *   &lt;complexContent>
 *     &lt;extension base="{}TitledIDType">
 *       &lt;sequence>
 *         &lt;element name="number" type="{}IntegerProperty" minOccurs="0"/>
 *         &lt;element name="club" type="{http://www.w3.org/2001/XMLSchema}IDREF"/>
 *         &lt;element name="league" type="{http://www.w3.org/2001/XMLSchema}IDREF"/>
 *         &lt;element name="venue" type="{http://www.w3.org/2001/XMLSchema}IDREF" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="contact_person" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Team", propOrder = {
    "number",
    "club",
    "league",
    "venue",
    "contactPerson"
})
public class Team
    extends TitledIDTypeModel
{

    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty number;
    @XmlElement(required = true, type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Club club;
    @XmlElement(required = true, type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected League league;
    @XmlElementRef(name = "venue", type = JAXBElement.class, required = false)
    protected List<Venue> venue;
    @XmlElement(name = "contact_person", type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Person contactPerson;

    /**
     * Gets the value of the number property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumber(SimpleIntegerProperty value) {
        this.number = value;
    }

    /**
     * Gets the value of the club property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Club getClub() {
        return club;
    }

    /**
     * Sets the value of the club property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setClub(Club value) {
        this.club = value;
    }

    /**
     * Gets the value of the league property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public League getLeague() {
        return league;
    }

    /**
     * Sets the value of the league property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setLeague(League value) {
        this.league = value;
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
     * {@link JAXBElement }{@code <}{@link Object }{@code >}
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
     * Gets the value of the contactPerson property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Person getContactPerson() {
        return contactPerson;
    }

    /**
     * Sets the value of the contactPerson property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setContactPerson(Person value) {
        this.contactPerson = value;
    }

}
