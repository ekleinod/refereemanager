
package de.edgesoft.refereemanager.jaxb;

import javafx.beans.property.SimpleIntegerProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.edgesoft.edgeutils.javafx.SimpleIntegerPropertyAdapter;
import de.edgesoft.refereemanager.model.RefereeEventModel;


/**
 * <p>Java class for LeagueGame complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LeagueGame">
 *   &lt;complexContent>
 *     &lt;extension base="{}RefereeEvent">
 *       &lt;sequence>
 *         &lt;element name="game_number" type="{}IntegerProperty"/>
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
    "league",
    "homeTeam",
    "offTeam"
})
public class LeagueGame
    extends RefereeEventModel
{

    @XmlElement(name = "game_number", required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty gameNumber;
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
