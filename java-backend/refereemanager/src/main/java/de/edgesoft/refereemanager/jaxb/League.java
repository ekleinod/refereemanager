
package de.edgesoft.refereemanager.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for League complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="League">
 *   &lt;complexContent>
 *     &lt;extension base="{}TitledIDType">
 *       &lt;sequence>
 *         &lt;element name="abbreviation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="referee_report" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="referee_quantity" type="{}RefereeQuantity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sex_type" type="{http://www.w3.org/2001/XMLSchema}IDREF"/>
 *         &lt;element name="referee_report_recipient" type="{http://www.w3.org/2001/XMLSchema}IDREF" maxOccurs="unbounded"/>
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
    "abbreviation",
    "refereeReport",
    "refereeQuantity",
    "sexType",
    "refereeReportRecipient"
})
public class League
    extends TitledIDType
{

    @XmlElement(required = true)
    protected String abbreviation;
    @XmlElement(name = "referee_report")
    @XmlSchemaType(name = "anyURI")
    protected String refereeReport;
    @XmlElement(name = "referee_quantity")
    protected List<RefereeQuantity> refereeQuantity;
    @XmlElement(name = "sex_type", required = true, type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected SexType sexType;
    @XmlElementRef(name = "referee_report_recipient", type = JAXBElement.class)
    protected List<Person> refereeReportRecipient;

    /**
     * Gets the value of the abbreviation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Sets the value of the abbreviation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbbreviation(String value) {
        this.abbreviation = value;
    }

    /**
     * Gets the value of the refereeReport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefereeReport() {
        return refereeReport;
    }

    /**
     * Sets the value of the refereeReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefereeReport(String value) {
        this.refereeReport = value;
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

    /**
     * Gets the value of the refereeReportRecipient property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refereeReportRecipient property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefereeReportRecipient().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * 
     */
    public List<Person> getRefereeReportRecipient() {
        if (refereeReportRecipient == null) {
            refereeReportRecipient = new ArrayList<Person>();
        }
        return this.refereeReportRecipient;
    }

}
