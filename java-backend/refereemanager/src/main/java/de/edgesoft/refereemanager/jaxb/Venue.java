
package de.edgesoft.refereemanager.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import de.edgesoft.refereemanager.model.AddressModel;


/**
 * <p>Java class for Venue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Venue">
 *   &lt;complexContent>
 *     &lt;extension base="{}Address">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Venue")
public class Venue
    extends AddressModel
{


}
