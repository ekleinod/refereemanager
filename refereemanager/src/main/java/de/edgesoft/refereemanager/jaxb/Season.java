
package de.edgesoft.refereemanager.jaxb;

import javafx.beans.property.SimpleIntegerProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.edgesoft.edgeutils.javafx.SimpleIntegerPropertyAdapter;
import de.edgesoft.refereemanager.model.TitledIDTypeModel;


/**
 * <p>Java class for Season complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Season">
 *   &lt;complexContent>
 *     &lt;extension base="{}TitledIDType">
 *       &lt;sequence>
 *         &lt;element name="start_year" type="{}IntegerProperty" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Season", propOrder = {
    "startYear"
})
public class Season
    extends TitledIDTypeModel
{

    @XmlElement(name = "start_year", type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty startYear;

    /**
     * Gets the value of the startYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getStartYear() {
        return startYear;
    }

    /**
     * Sets the value of the startYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartYear(SimpleIntegerProperty value) {
        this.startYear = value;
    }

}
