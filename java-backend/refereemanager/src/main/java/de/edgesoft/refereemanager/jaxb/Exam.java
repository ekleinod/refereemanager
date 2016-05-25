
package de.edgesoft.refereemanager.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Exam complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Exam">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="points_written" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="points_practical" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="points_oral" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="points" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Exam", propOrder = {
    "pointsWritten",
    "pointsPractical",
    "pointsOral",
    "points"
})
public class Exam {

    @XmlElement(name = "points_written")
    protected int pointsWritten;
    @XmlElement(name = "points_practical")
    protected int pointsPractical;
    @XmlElement(name = "points_oral")
    protected int pointsOral;
    protected int points;

    /**
     * Gets the value of the pointsWritten property.
     * 
     */
    public int getPointsWritten() {
        return pointsWritten;
    }

    /**
     * Sets the value of the pointsWritten property.
     * 
     */
    public void setPointsWritten(int value) {
        this.pointsWritten = value;
    }

    /**
     * Gets the value of the pointsPractical property.
     * 
     */
    public int getPointsPractical() {
        return pointsPractical;
    }

    /**
     * Sets the value of the pointsPractical property.
     * 
     */
    public void setPointsPractical(int value) {
        this.pointsPractical = value;
    }

    /**
     * Gets the value of the pointsOral property.
     * 
     */
    public int getPointsOral() {
        return pointsOral;
    }

    /**
     * Sets the value of the pointsOral property.
     * 
     */
    public void setPointsOral(int value) {
        this.pointsOral = value;
    }

    /**
     * Gets the value of the points property.
     * 
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the value of the points property.
     * 
     */
    public void setPoints(int value) {
        this.points = value;
    }

}
