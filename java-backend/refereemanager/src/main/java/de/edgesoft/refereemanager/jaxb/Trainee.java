
package de.edgesoft.refereemanager.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trainee complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trainee">
 *   &lt;complexContent>
 *     &lt;extension base="{}Person">
 *       &lt;sequence>
 *         &lt;element name="points_written_a" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="points_written_b" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="points_practical" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="points_oral" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
    "pointsOral"
})
public class Trainee
    extends Person
{

    @XmlElement(name = "points_written_a")
    protected Integer pointsWrittenA;
    @XmlElement(name = "points_written_b")
    protected Integer pointsWrittenB;
    @XmlElement(name = "points_practical")
    protected Integer pointsPractical;
    @XmlElement(name = "points_oral")
    protected Integer pointsOral;

    /**
     * Gets the value of the pointsWrittenA property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPointsWrittenA() {
        return pointsWrittenA;
    }

    /**
     * Sets the value of the pointsWrittenA property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPointsWrittenA(Integer value) {
        this.pointsWrittenA = value;
    }

    /**
     * Gets the value of the pointsWrittenB property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPointsWrittenB() {
        return pointsWrittenB;
    }

    /**
     * Sets the value of the pointsWrittenB property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPointsWrittenB(Integer value) {
        this.pointsWrittenB = value;
    }

    /**
     * Gets the value of the pointsPractical property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPointsPractical() {
        return pointsPractical;
    }

    /**
     * Sets the value of the pointsPractical property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPointsPractical(Integer value) {
        this.pointsPractical = value;
    }

    /**
     * Gets the value of the pointsOral property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPointsOral() {
        return pointsOral;
    }

    /**
     * Sets the value of the pointsOral property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPointsOral(Integer value) {
        this.pointsOral = value;
    }

}
