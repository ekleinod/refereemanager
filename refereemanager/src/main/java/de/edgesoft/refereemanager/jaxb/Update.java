
package de.edgesoft.refereemanager.jaxb;

import javafx.beans.property.SimpleObjectProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.edgesoft.edgeutils.commons.AbstractModelClass;
import de.edgesoft.edgeutils.javafx.SimpleObjectPropertyLocalDateAdapter;


/**
 * 
 * 				This type just stores a date.
 * 				The date cannot be used directly, as this would break the inheritance needed for generation of input dialogs.
 * 				This is only needed for lists.
 * 			
 * 
 * <p>Java class for Update complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Update">
 *   &lt;complexContent>
 *     &lt;extension base="{}AbstractModelClass">
 *       &lt;sequence>
 *         &lt;element name="date" type="{}LocalDateProperty"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Update", propOrder = {
    "date"
})
public class Update
    extends AbstractModelClass
{

    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleObjectPropertyLocalDateAdapter.class)
    @XmlSchemaType(name = "date")
    protected SimpleObjectProperty date;

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleObjectProperty getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(SimpleObjectProperty value) {
        this.date = value;
    }

}
