
package de.edgesoft.refereemanager.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import de.edgesoft.edgeutils.commons.AbstractModelClass;


/**
 * 
 * 				This type just stores a venue.
 * 				The venue cannot be used directly, as this would break the inheritance needed for generation of input dialogs.
 * 				This is only needed for lists.
 * 			
 * 
 * <p>Java class for VenueReference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VenueReference">
 *   &lt;complexContent>
 *     &lt;extension base="{}AbstractModelClass">
 *       &lt;sequence>
 *         &lt;element name="venue" type="{http://www.w3.org/2001/XMLSchema}IDREF"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VenueReference", propOrder = {
    "venue"
})
public class VenueReference
    extends AbstractModelClass
{

    @XmlElement(required = true, type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Venue venue;

    /**
     * Gets the value of the venue property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Venue getVenue() {
        return venue;
    }

    /**
     * Sets the value of the venue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setVenue(Venue value) {
        this.venue = value;
    }

}
