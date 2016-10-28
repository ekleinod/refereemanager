
package de.edgesoft.refereemanager.jaxb;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.edgesoft.edgeutils.javafx.SimpleBooleanPropertyAdapter;
import de.edgesoft.edgeutils.javafx.SimpleIntegerPropertyAdapter;
import de.edgesoft.refereemanager.model.RefereeModel;


/**
 * <p>Java class for Trainee complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trainee">
 *   &lt;complexContent>
 *     &lt;extension base="{}Referee">
 *       &lt;sequence>
 *         &lt;element name="points_written_a" type="{}IntegerProperty" minOccurs="0"/>
 *         &lt;element name="points_written_b" type="{}IntegerProperty" minOccurs="0"/>
 *         &lt;element name="points_practical" type="{}IntegerProperty" minOccurs="0"/>
 *         &lt;element name="points_oral" type="{}IntegerProperty" minOccurs="0"/>
 *         &lt;element name="passed" type="{}BooleanProperty" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trainee", propOrder = {
    "pointsWrittenA",
    "pointsWrittenB",
    "pointsPractical",
    "pointsOral",
    "passed"
})
public class Trainee
    extends RefereeModel
{

    @XmlElement(name = "points_written_a", type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty pointsWrittenA;
    @XmlElement(name = "points_written_b", type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty pointsWrittenB;
    @XmlElement(name = "points_practical", type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty pointsPractical;
    @XmlElement(name = "points_oral", type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty pointsOral;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(SimpleBooleanPropertyAdapter.class)
    @XmlSchemaType(name = "boolean")
    protected SimpleBooleanProperty passed;

    /**
     * Gets the value of the pointsWrittenA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getPointsWrittenA() {
        return pointsWrittenA;
    }

    /**
     * Sets the value of the pointsWrittenA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointsWrittenA(SimpleIntegerProperty value) {
        this.pointsWrittenA = value;
    }

    /**
     * Gets the value of the pointsWrittenB property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getPointsWrittenB() {
        return pointsWrittenB;
    }

    /**
     * Sets the value of the pointsWrittenB property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointsWrittenB(SimpleIntegerProperty value) {
        this.pointsWrittenB = value;
    }

    /**
     * Gets the value of the pointsPractical property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getPointsPractical() {
        return pointsPractical;
    }

    /**
     * Sets the value of the pointsPractical property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointsPractical(SimpleIntegerProperty value) {
        this.pointsPractical = value;
    }

    /**
     * Gets the value of the pointsOral property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getPointsOral() {
        return pointsOral;
    }

    /**
     * Sets the value of the pointsOral property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointsOral(SimpleIntegerProperty value) {
        this.pointsOral = value;
    }

    /**
     * Gets the value of the passed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleBooleanProperty getPassed() {
        return passed;
    }

    /**
     * Sets the value of the passed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassed(SimpleBooleanProperty value) {
        this.passed = value;
    }

}
