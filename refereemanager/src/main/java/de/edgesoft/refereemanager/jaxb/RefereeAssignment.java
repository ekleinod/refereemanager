
package de.edgesoft.refereemanager.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import de.edgesoft.edgeutils.commons.AbstractModelClass;


/**
 * <p>Java class for RefereeAssignment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RefereeAssignment">
 *   &lt;complexContent>
 *     &lt;extension base="{}AbstractModelClass">
 *       &lt;sequence>
 *         &lt;element name="referee" type="{http://www.w3.org/2001/XMLSchema}IDREF"/>
 *         &lt;element name="referee_assigment_type" type="{http://www.w3.org/2001/XMLSchema}IDREF"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RefereeAssignment", propOrder = {
    "referee",
    "refereeAssigmentType"
})
public class RefereeAssignment
    extends AbstractModelClass
{

    @XmlElement(required = true, type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Referee referee;
    @XmlElement(name = "referee_assigment_type", required = true, type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected RefereeAssignmentType refereeAssigmentType;

    /**
     * Gets the value of the referee property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Referee getReferee() {
        return referee;
    }

    /**
     * Sets the value of the referee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setReferee(Referee value) {
        this.referee = value;
    }

    /**
     * Gets the value of the refereeAssigmentType property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public RefereeAssignmentType getRefereeAssigmentType() {
        return refereeAssigmentType;
    }

    /**
     * Sets the value of the refereeAssigmentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setRefereeAssigmentType(RefereeAssignmentType value) {
        this.refereeAssigmentType = value;
    }

}
