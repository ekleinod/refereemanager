
package de.edgesoft.refereemanager.jaxb;

import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.edgesoft.edgeutils.commons.IDType;
import de.edgesoft.edgeutils.javafx.SimpleStringPropertyAdapter;
import de.edgesoft.refereemanager.model.ClubModel;
import de.edgesoft.refereemanager.model.ContactModel;
import de.edgesoft.refereemanager.model.EventDateModel;
import de.edgesoft.refereemanager.model.LeagueModel;
import de.edgesoft.refereemanager.model.PersonModel;
import de.edgesoft.refereemanager.model.SeasonModel;
import de.edgesoft.refereemanager.model.StatusTypeModel;
import de.edgesoft.refereemanager.model.TeamModel;
import de.edgesoft.refereemanager.model.TrainingLevelTypeModel;


/**
 * <p>Java class for TitledIDType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TitledIDType">
 *   &lt;complexContent>
 *     &lt;extension base="{}IDType">
 *       &lt;sequence>
 *         &lt;element name="title" type="{}StringProperty" minOccurs="0"/>
 *         &lt;element name="shorttitle" type="{}StringProperty" minOccurs="0"/>
 *         &lt;element name="remark" type="{}StringProperty" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TitledIDType", propOrder = {
    "title",
    "shorttitle",
    "remark"
})
@XmlSeeAlso({
    ContactType.class,
    TrainingLevelTypeModel.class,
    TeamModel.class,
    SeasonModel.class,
    SexType.class,
    LeagueModel.class,
    PersonRoleType.class,
    ClubModel.class,
    StatusTypeModel.class,
    ContactModel.class,
    RefereeAssignmentType.class,
    PersonModel.class,
    EventDateModel.class
})
public abstract class TitledIDType
    extends IDType
{

    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(SimpleStringPropertyAdapter.class)
    protected SimpleStringProperty title;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(SimpleStringPropertyAdapter.class)
    protected SimpleStringProperty shorttitle;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(SimpleStringPropertyAdapter.class)
    protected SimpleStringProperty remark;

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleStringProperty getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(SimpleStringProperty value) {
        this.title = value;
    }

    /**
     * Gets the value of the shorttitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleStringProperty getShorttitle() {
        return shorttitle;
    }

    /**
     * Sets the value of the shorttitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShorttitle(SimpleStringProperty value) {
        this.shorttitle = value;
    }

    /**
     * Gets the value of the remark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleStringProperty getRemark() {
        return remark;
    }

    /**
     * Sets the value of the remark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(SimpleStringProperty value) {
        this.remark = value;
    }

}
