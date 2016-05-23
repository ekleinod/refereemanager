
package de.edgesoft.refereemanager.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Wish complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Wish">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="type" type="{}WishType"/>
 *         &lt;element name="saturday" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="sunday" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="tournament_only" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="league_games_only" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="club" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
 *         &lt;element name="league" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
 *         &lt;element name="sex_type" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Wish", propOrder = {
    "type",
    "saturday",
    "sunday",
    "tournamentOnly",
    "leagueGamesOnly",
    "club",
    "league",
    "sexType"
})
public class Wish {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected WishType type;
    protected Boolean saturday;
    protected Boolean sunday;
    @XmlElement(name = "tournament_only")
    protected Boolean tournamentOnly;
    @XmlElement(name = "league_games_only")
    protected Boolean leagueGamesOnly;
    @XmlElement(type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Club club;
    @XmlElement(type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected League league;
    @XmlElement(name = "sex_type", type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected SexType sexType;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link WishType }
     *     
     */
    public WishType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link WishType }
     *     
     */
    public void setType(WishType value) {
        this.type = value;
    }

    /**
     * Gets the value of the saturday property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSaturday() {
        return saturday;
    }

    /**
     * Sets the value of the saturday property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSaturday(Boolean value) {
        this.saturday = value;
    }

    /**
     * Gets the value of the sunday property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSunday() {
        return sunday;
    }

    /**
     * Sets the value of the sunday property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSunday(Boolean value) {
        this.sunday = value;
    }

    /**
     * Gets the value of the tournamentOnly property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTournamentOnly() {
        return tournamentOnly;
    }

    /**
     * Sets the value of the tournamentOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTournamentOnly(Boolean value) {
        this.tournamentOnly = value;
    }

    /**
     * Gets the value of the leagueGamesOnly property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLeagueGamesOnly() {
        return leagueGamesOnly;
    }

    /**
     * Sets the value of the leagueGamesOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLeagueGamesOnly(Boolean value) {
        this.leagueGamesOnly = value;
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
     * Gets the value of the sexType property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public SexType getSexType() {
        return sexType;
    }

    /**
     * Sets the value of the sexType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setSexType(SexType value) {
        this.sexType = value;
    }

}
