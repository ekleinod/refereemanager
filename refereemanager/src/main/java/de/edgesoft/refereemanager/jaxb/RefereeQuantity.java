
package de.edgesoft.refereemanager.jaxb;

import javafx.beans.property.SimpleIntegerProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.edgesoft.edgeutils.commons.AbstractModelClass;
import de.edgesoft.edgeutils.javafx.SimpleIntegerPropertyAdapter;


/**
 * <p>Java class for RefereeQuantity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RefereeQuantity">
 *   &lt;complexContent>
 *     &lt;extension base="{}AbstractModelClass">
 *       &lt;sequence>
 *         &lt;element name="quantity" type="{}IntegerProperty"/>
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
@XmlType(name = "RefereeQuantity", propOrder = {
    "quantity",
    "refereeAssigmentType"
})
public class RefereeQuantity
    extends AbstractModelClass
{

    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty quantity;
    @XmlElement(name = "referee_assigment_type", required = true, type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected RefereeAssignmentType refereeAssigmentType;

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuantity(SimpleIntegerProperty value) {
        this.quantity = value;
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
