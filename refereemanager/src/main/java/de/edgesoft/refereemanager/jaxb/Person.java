
package de.edgesoft.refereemanager.jaxb;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.edgesoft.edgeutils.javafx.SimpleObjectPropertyLocalDateAdapter;
import de.edgesoft.edgeutils.javafx.SimpleStringPropertyAdapter;
import de.edgesoft.refereemanager.model.AddressModel;
import de.edgesoft.refereemanager.model.EMailModel;
import de.edgesoft.refereemanager.model.PhoneNumberModel;
import de.edgesoft.refereemanager.model.RefereeModel;
import de.edgesoft.refereemanager.model.TitledIDTypeModel;
import de.edgesoft.refereemanager.model.URLModel;


/**
 * <p>Java class for Person complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Person">
 *   &lt;complexContent>
 *     &lt;extension base="{}TitledIDType">
 *       &lt;sequence>
 *         &lt;element name="first_name" type="{}StringProperty" minOccurs="0"/>
 *         &lt;element name="name" type="{}StringProperty"/>
 *         &lt;element name="birthday" type="{}LocalDateProperty" minOccurs="0"/>
 *         &lt;element name="day_of_death" type="{}LocalDateProperty" minOccurs="0"/>
 *         &lt;element name="e_mail" type="{}EMail" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="address" type="{}Address" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="phone_number" type="{}PhoneNumber" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="u_r_l" type="{}URL" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sex_type" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Person", propOrder = {
    "firstName",
    "name",
    "birthday",
    "dayOfDeath",
    "eMail",
    "address",
    "phoneNumber",
    "url",
    "sexType"
})
@XmlSeeAlso({
    RefereeModel.class
})
public class Person
    extends TitledIDTypeModel
{

    @XmlElement(name = "first_name", type = String.class)
    @XmlJavaTypeAdapter(SimpleStringPropertyAdapter.class)
    protected SimpleStringProperty firstName;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleStringPropertyAdapter.class)
    protected SimpleStringProperty name;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(SimpleObjectPropertyLocalDateAdapter.class)
    @XmlSchemaType(name = "date")
    protected SimpleObjectProperty birthday;
    @XmlElement(name = "day_of_death", type = String.class)
    @XmlJavaTypeAdapter(SimpleObjectPropertyLocalDateAdapter.class)
    @XmlSchemaType(name = "date")
    protected SimpleObjectProperty dayOfDeath;
    @XmlElement(name = "e_mail", type = EMailModel.class)
    protected List<EMail> eMail;
    @XmlElement(type = AddressModel.class)
    protected List<Address> address;
    @XmlElement(name = "phone_number", type = PhoneNumberModel.class)
    protected List<PhoneNumber> phoneNumber;
    @XmlElement(name = "u_r_l", type = URLModel.class)
    protected List<URL> url;
    @XmlElement(name = "sex_type", type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected SexType sexType;

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleStringProperty getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(SimpleStringProperty value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleStringProperty getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(SimpleStringProperty value) {
        this.name = value;
    }

    /**
     * Gets the value of the birthday property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleObjectProperty getBirthday() {
        return birthday;
    }

    /**
     * Sets the value of the birthday property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthday(SimpleObjectProperty value) {
        this.birthday = value;
    }

    /**
     * Gets the value of the dayOfDeath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleObjectProperty getDayOfDeath() {
        return dayOfDeath;
    }

    /**
     * Sets the value of the dayOfDeath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDayOfDeath(SimpleObjectProperty value) {
        this.dayOfDeath = value;
    }

    /**
     * Gets the value of the eMail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eMail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEMail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EMail }
     * 
     * 
     */
    public List<EMail> getEMail() {
        if (eMail == null) {
            eMail = new ArrayList<EMail>();
        }
        return this.eMail;
    }

    /**
     * Gets the value of the address property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the address property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Address }
     * 
     * 
     */
    public List<Address> getAddress() {
        if (address == null) {
            address = new ArrayList<Address>();
        }
        return this.address;
    }

    /**
     * Gets the value of the phoneNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the phoneNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPhoneNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PhoneNumber }
     * 
     * 
     */
    public List<PhoneNumber> getPhoneNumber() {
        if (phoneNumber == null) {
            phoneNumber = new ArrayList<PhoneNumber>();
        }
        return this.phoneNumber;
    }

    /**
     * Gets the value of the url property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the url property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getURL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link URL }
     * 
     * 
     */
    public List<URL> getURL() {
        if (url == null) {
            url = new ArrayList<URL>();
        }
        return this.url;
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
