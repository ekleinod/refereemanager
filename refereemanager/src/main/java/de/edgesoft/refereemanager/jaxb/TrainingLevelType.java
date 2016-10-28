
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
 * <p>Java class for TrainingLevelType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrainingLevelType">
 *   &lt;complexContent>
 *     &lt;extension base="{}TitledIDType">
 *       &lt;sequence>
 *         &lt;element name="rank" type="{}IntegerProperty"/>
 *         &lt;element name="update_interval" type="{}IntegerProperty"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrainingLevelType", propOrder = {
    "rank",
    "updateInterval"
})
public class TrainingLevelType
    extends TitledIDTypeModel
{

    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty rank;
    @XmlElement(name = "update_interval", required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty updateInterval;

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
     * Gets the value of the updateInterval property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getUpdateInterval() {
        return updateInterval;
    }

    /**
     * Sets the value of the updateInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdateInterval(SimpleIntegerProperty value) {
        this.updateInterval = value;
    }

}
