
package de.edgesoft.refereemanager.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ContentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ContentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="person" type="{}PersonType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="referee" type="{}RefereeType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContentType", propOrder = {
    "person",
    "referee"
})
public class ContentType {

    protected List<PersonType> person;
    protected List<RefereeType> referee;

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
     * {@link PersonType }
     * 
     * 
     */
    public List<PersonType> getPerson() {
        if (person == null) {
            person = new ArrayList<PersonType>();
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
     * {@link RefereeType }
     * 
     * 
     */
    public List<RefereeType> getReferee() {
        if (referee == null) {
            referee = new ArrayList<RefereeType>();
        }
        return this.referee;
    }

}
