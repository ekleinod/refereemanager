
package de.edgesoft.refereemanager.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
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
 *         &lt;element name="rank" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="update_interval" type="{http://www.w3.org/2001/XMLSchema}int"/>
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

    protected int rank;
    @XmlElement(name = "update_interval")
    protected int updateInterval;

    /**
     * Gets the value of the rank property.
     * 
     */
    public int getRank() {
        return rank;
    }

    /**
     * Sets the value of the rank property.
     * 
     */
    public void setRank(int value) {
        this.rank = value;
    }

    /**
     * Gets the value of the updateInterval property.
     * 
     */
    public int getUpdateInterval() {
        return updateInterval;
    }

    /**
     * Sets the value of the updateInterval property.
     * 
     */
    public void setUpdateInterval(int value) {
        this.updateInterval = value;
    }

}
