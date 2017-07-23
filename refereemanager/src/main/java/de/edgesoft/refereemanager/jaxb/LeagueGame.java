
package de.edgesoft.refereemanager.jaxb;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.edgesoft.edgeutils.javafx.SimpleIntegerPropertyAdapter;
import de.edgesoft.refereemanager.model.EventDateModel;


/**
 * <p>Java class for LeagueGame complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LeagueGame">
 *   &lt;complexContent>
 *     &lt;extension base="{}EventDate">
 *       &lt;sequence>
 *         &lt;element name="game_number" type="{}IntegerProperty"/>
 *         &lt;element name="referee_assignment" type="{}RefereeAssignment" maxOccurs="unbounded"/>
 *         &lt;element name="league" type="{http://www.w3.org/2001/XMLSchema}IDREF"/>
 *         &lt;element name="home_team" type="{http://www.w3.org/2001/XMLSchema}IDREF"/>
 *         &lt;element name="off_team" type="{http://www.w3.org/2001/XMLSchema}IDREF"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LeagueGame", propOrder = {
    "gameNumber",
    "refereeAssignment",
    "league",
    "homeTeam",
    "offTeam"
})
public class LeagueGame
    extends EventDateModel
{

    @XmlElement(name = "game_number", required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty gameNumber;
    @XmlElement(name = "referee_assignment", required = true)
    protected List<RefereeAssignment> refereeAssignment;
    @XmlElement(required = true, type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected League league;
    @XmlElement(name = "home_team", required = true, type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Team homeTeam;
    @XmlElement(name = "off_team", required = true, type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Team offTeam;

    /**
     * Gets the value of the gameNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getGameNumber() {
        return gameNumber;
    }

    /**
     * Sets the value of the gameNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGameNumber(SimpleIntegerProperty value) {
        this.gameNumber = value;
    }

    /**
     * Gets the value of the refereeAssignment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refereeAssignment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefereeAssignment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RefereeAssignment }
     * 
     * 
     */
    public List<RefereeAssignment> getRefereeAssignment() {
        if (refereeAssignment == null) {
            refereeAssignment = new ArrayList<RefereeAssignment>();
        }
        return this.refereeAssignment;
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
     * Gets the value of the homeTeam property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Team getHomeTeam() {
        return homeTeam;
    }

    /**
     * Sets the value of the homeTeam property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setHomeTeam(Team value) {
        this.homeTeam = value;
    }

    /**
     * Gets the value of the offTeam property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Team getOffTeam() {
        return offTeam;
    }

    /**
     * Sets the value of the offTeam property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setOffTeam(Team value) {
        this.offTeam = value;
    }

}
