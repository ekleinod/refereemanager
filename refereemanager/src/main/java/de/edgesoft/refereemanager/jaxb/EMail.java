
package de.edgesoft.refereemanager.jaxb;

import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.edgesoft.edgeutils.javafx.SimpleStringPropertyAdapter;
import de.edgesoft.refereemanager.model.ContactModel;


/**
 * <p>Java class for EMail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EMail">
 *   &lt;complexContent>
 *     &lt;extension base="{}Contact">
 *       &lt;sequence>
 *         &lt;element name="e_mail" type="{}StringProperty"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EMail", propOrder = {
    "eMail"
})
public class EMail
    extends ContactModel
{

    @XmlElement(name = "e_mail", required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleStringPropertyAdapter.class)
    protected SimpleStringProperty eMail;

    /**
     * Gets the value of the eMail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleStringProperty getEMail() {
        return eMail;
    }

    /**
     * Sets the value of the eMail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEMail(SimpleStringProperty value) {
        this.eMail = value;
    }

}
