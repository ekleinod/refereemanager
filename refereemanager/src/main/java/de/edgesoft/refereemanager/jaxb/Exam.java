
package de.edgesoft.refereemanager.jaxb;

import javafx.beans.property.SimpleIntegerProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.edgesoft.edgeutils.commons.AbstractModelClass;
import de.edgesoft.edgeutils.javafx.SimpleIntegerPropertyAdapter;


/**
 * <p>Java class for Exam complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Exam">
 *   &lt;complexContent>
 *     &lt;extension base="{}AbstractModelClass">
 *       &lt;sequence>
 *         &lt;element name="max_points_written_a" type="{}IntegerProperty"/>
 *         &lt;element name="max_points_written_b" type="{}IntegerProperty"/>
 *         &lt;element name="max_points_practical" type="{}IntegerProperty"/>
 *         &lt;element name="max_points_oral" type="{}IntegerProperty"/>
 *         &lt;element name="needed_points_written" type="{}IntegerProperty"/>
 *         &lt;element name="needed_points_practical" type="{}IntegerProperty"/>
 *         &lt;element name="needed_points_oral" type="{}IntegerProperty"/>
 *         &lt;element name="needed_points" type="{}IntegerProperty"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Exam", propOrder = {
    "maxPointsWrittenA",
    "maxPointsWrittenB",
    "maxPointsPractical",
    "maxPointsOral",
    "neededPointsWritten",
    "neededPointsPractical",
    "neededPointsOral",
    "neededPoints"
})
public class Exam
    extends AbstractModelClass
{

    @XmlElement(name = "max_points_written_a", required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty maxPointsWrittenA;
    @XmlElement(name = "max_points_written_b", required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty maxPointsWrittenB;
    @XmlElement(name = "max_points_practical", required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty maxPointsPractical;
    @XmlElement(name = "max_points_oral", required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty maxPointsOral;
    @XmlElement(name = "needed_points_written", required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty neededPointsWritten;
    @XmlElement(name = "needed_points_practical", required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty neededPointsPractical;
    @XmlElement(name = "needed_points_oral", required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty neededPointsOral;
    @XmlElement(name = "needed_points", required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty neededPoints;

    /**
     * Gets the value of the maxPointsWrittenA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getMaxPointsWrittenA() {
        return maxPointsWrittenA;
    }

    /**
     * Sets the value of the maxPointsWrittenA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxPointsWrittenA(SimpleIntegerProperty value) {
        this.maxPointsWrittenA = value;
    }

    /**
     * Gets the value of the maxPointsWrittenB property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getMaxPointsWrittenB() {
        return maxPointsWrittenB;
    }

    /**
     * Sets the value of the maxPointsWrittenB property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxPointsWrittenB(SimpleIntegerProperty value) {
        this.maxPointsWrittenB = value;
    }

    /**
     * Gets the value of the maxPointsPractical property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getMaxPointsPractical() {
        return maxPointsPractical;
    }

    /**
     * Sets the value of the maxPointsPractical property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxPointsPractical(SimpleIntegerProperty value) {
        this.maxPointsPractical = value;
    }

    /**
     * Gets the value of the maxPointsOral property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getMaxPointsOral() {
        return maxPointsOral;
    }

    /**
     * Sets the value of the maxPointsOral property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxPointsOral(SimpleIntegerProperty value) {
        this.maxPointsOral = value;
    }

    /**
     * Gets the value of the neededPointsWritten property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getNeededPointsWritten() {
        return neededPointsWritten;
    }

    /**
     * Sets the value of the neededPointsWritten property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNeededPointsWritten(SimpleIntegerProperty value) {
        this.neededPointsWritten = value;
    }

    /**
     * Gets the value of the neededPointsPractical property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getNeededPointsPractical() {
        return neededPointsPractical;
    }

    /**
     * Sets the value of the neededPointsPractical property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNeededPointsPractical(SimpleIntegerProperty value) {
        this.neededPointsPractical = value;
    }

    /**
     * Gets the value of the neededPointsOral property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getNeededPointsOral() {
        return neededPointsOral;
    }

    /**
     * Sets the value of the neededPointsOral property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNeededPointsOral(SimpleIntegerProperty value) {
        this.neededPointsOral = value;
    }

    /**
     * Gets the value of the neededPoints property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getNeededPoints() {
        return neededPoints;
    }

    /**
     * Sets the value of the neededPoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNeededPoints(SimpleIntegerProperty value) {
        this.neededPoints = value;
    }

}
