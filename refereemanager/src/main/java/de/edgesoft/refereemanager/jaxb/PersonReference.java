
package de.edgesoft.refereemanager.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import de.edgesoft.edgeutils.commons.AbstractModelClass;


/**
 * 
 * 				This type just stores a person.
 * 				The person cannot be used directly, as this would break the inheritance needed for generation of input dialogs.
 * 				This is only needed for lists.
 * 			
 * 
 * <p>Java class for PersonReference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PersonReference">
 *   &lt;complexContent>
 *     &lt;extension base="{}AbstractModelClass">
 *       &lt;sequence>
 *         &lt;element name="person" type="{http://www.w3.org/2001/XMLSchema}IDREF"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonReference", propOrder = {
    "person"
})
public class PersonReference
    extends AbstractModelClass
{

    @XmlElement(required = true, type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Person person;

    /**
     * Gets the value of the person property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Sets the value of the person property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setPerson(Person value) {
        this.person = value;
    }

}
