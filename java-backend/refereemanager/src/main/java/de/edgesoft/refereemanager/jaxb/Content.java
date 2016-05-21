
package de.edgesoft.refereemanager.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Content complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Content">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="person" type="{}Person" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="referee" type="{}Referee" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sextype" type="{}SexType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="contacttype" type="{}ContactType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Content", propOrder = {
    "person",
    "referee",
    "sextype",
    "contacttype"
})
public class Content {

    protected List<Person> person;
    protected List<Referee> referee;
    protected List<SexType> sextype;
    protected List<ContactType> contacttype;

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
     * {@link Person }
     * 
     * 
     */
    public List<Person> getPerson() {
        if (person == null) {
            person = new ArrayList<Person>();
        }
        return this.person;
    }

    /**
     * Gets the value of the referee property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the referee property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferee().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Referee }
     * 
     * 
     */
    public List<Referee> getReferee() {
        if (referee == null) {
            referee = new ArrayList<Referee>();
        }
        return this.referee;
    }

    /**
     * Gets the value of the sextype property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sextype property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSextype().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SexType }
     * 
     * 
     */
    public List<SexType> getSextype() {
        if (sextype == null) {
            sextype = new ArrayList<SexType>();
        }
        return this.sextype;
    }

    /**
     * Gets the value of the contacttype property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contacttype property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContacttype().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContactType }
     * 
     * 
     */
    public List<ContactType> getContacttype() {
        if (contacttype == null) {
            contacttype = new ArrayList<ContactType>();
        }
        return this.contacttype;
    }

}
