
package de.edgesoft.refereemanager.jaxb;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.edgesoft.edgeutils.javafx.SimpleBooleanPropertyAdapter;
import de.edgesoft.edgeutils.javafx.SimpleIntegerPropertyAdapter;
import de.edgesoft.edgeutils.javafx.SimpleStringPropertyAdapter;


/**
 * <p>Java class for League complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="League">
 *   &lt;complexContent>
 *     &lt;extension base="{}PersonVenueReferrer">
 *       &lt;sequence>
 *         &lt;element name="rank" type="{}IntegerProperty"/>
 *         &lt;element name="national" type="{}BooleanProperty" minOccurs="0"/>
 *         &lt;element name="results_u_r_l" type="{}StringProperty"/>
 *         &lt;element name="referee_report_u_r_l" type="{}StringProperty"/>
 *         &lt;element name="referee_quantity" type="{}RefereeQuantity" maxOccurs="unbounded"/>
 *         &lt;element name="sex_type" type="{http://www.w3.org/2001/XMLSchema}IDREF"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "League", propOrder = {
    "rank",
    "national",
    "resultsURL",
    "refereeReportURL",
    "refereeQuantity",
    "sexType"
})
public class League
    extends PersonVenueReferrer
{

    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty rank;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(SimpleBooleanPropertyAdapter.class)
    @XmlSchemaType(name = "boolean")
    protected SimpleBooleanProperty national;
    @XmlElement(name = "results_u_r_l", required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleStringPropertyAdapter.class)
    protected SimpleStringProperty resultsURL;
    @XmlElement(name = "referee_report_u_r_l", required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleStringPropertyAdapter.class)
    protected SimpleStringProperty refereeReportURL;
    @XmlElement(name = "referee_quantity", required = true)
    protected List<RefereeQuantity> refereeQuantity;
    @XmlElement(name = "sex_type", required = true, type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected SexType sexType;

    /**
     * Gets the value of the rank property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getRank() {
        return rank;
    }

    /**
     * Sets the value of the rank property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRank(SimpleIntegerProperty value) {
        this.rank = value;
    }

    /**
     * Gets the value of the national property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleBooleanProperty getNational() {
        return national;
    }

    /**
     * Sets the value of the national property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNational(SimpleBooleanProperty value) {
        this.national = value;
    }

    /**
     * Gets the value of the resultsURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleStringProperty getResultsURL() {
        return resultsURL;
    }

    /**
     * Sets the value of the resultsURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultsURL(SimpleStringProperty value) {
        this.resultsURL = value;
    }

    /**
     * Gets the value of the refereeReportURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleStringProperty getRefereeReportURL() {
        return refereeReportURL;
    }

    /**
     * Sets the value of the refereeReportURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefereeReportURL(SimpleStringProperty value) {
        this.refereeReportURL = value;
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
