
package de.edgesoft.refereemanager.jaxb;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
// fixing wrong JAXB generation of referenced lists
// before: import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlIDREF;
// end of fix
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.edgesoft.edgeutils.javafx.SimpleBooleanPropertyAdapter;
import de.edgesoft.edgeutils.javafx.SimpleStringPropertyAdapter;
import de.edgesoft.refereemanager.model.TitledIDTypeModel;
import de.edgesoft.refereemanager.model.URLModel;


/**
 * <p>Java class for Club complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Club">
 *   &lt;complexContent>
 *     &lt;extension base="{}TitledIDType">
 *       &lt;sequence>
 *         &lt;element name="is_local" type="{}BooleanProperty" minOccurs="0"/>
 *         &lt;element name="filename" type="{}StringProperty" minOccurs="0"/>
 *         &lt;element name="u_r_l" type="{}URL" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="venue" type="{http://www.w3.org/2001/XMLSchema}IDREF" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="contact_person" type="{http://www.w3.org/2001/XMLSchema}IDREF" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Club", propOrder = {
    "isLocal",
    "filename",
    "url",
    "venue",
    "contactPerson"
})
public class Club
    extends TitledIDTypeModel
{

    @XmlElement(name = "is_local", type = String.class)
    @XmlJavaTypeAdapter(SimpleBooleanPropertyAdapter.class)
    @XmlSchemaType(name = "boolean")
    protected SimpleBooleanProperty isLocal;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(SimpleStringPropertyAdapter.class)
    protected SimpleStringProperty filename;
    @XmlElement(name = "u_r_l", type = URLModel.class)
    protected List<URL> url;
    // fixing wrong JAXB generation of referenced lists
    // before: @XmlElementRef(name = "venue", type = JAXBElement.class, required = false)
    @XmlElement(name = "venue", type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    // end of fix
    protected List<Venue> venue;
    // fixing wrong JAXB generation of referenced lists
    // before: @XmlElementRef(name = "contact_person", type = JAXBElement.class, required = false)
    @XmlElement(name = "contact_person", type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    // end of fix
    protected List<Person> contactPerson;

    /**
     * Gets the value of the isLocal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleBooleanProperty getIsLocal() {
        return isLocal;
    }

    /**
     * Sets the value of the isLocal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsLocal(SimpleBooleanProperty value) {
        this.isLocal = value;
    }

    /**
     * Gets the value of the filename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleStringProperty getFilename() {
        return filename;
    }

    /**
     * Sets the value of the filename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilename(SimpleStringProperty value) {
        this.filename = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the url property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getURL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link URL }
     * 
     * 
     */
    public List<URL> getURL() {
        if (url == null) {
            url = new ArrayList<URL>();
        }
        return this.url;
    }

    /**
     * Gets the value of the venue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the venue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVenue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * - fixing wrong JAXB generation of referenced lists
     * - before: {@link JAXBElement }{@code <}{@link Object }{@code >}
     * {@link Venue }
     * - end of fix
     */
    public List<Venue> getVenue() {
        if (venue == null) {
            venue = new ArrayList<Venue>();
        }
        return this.venue;
    }

    /**
     * Gets the value of the contactPerson property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contactPerson property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContactPerson().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * - fixing wrong JAXB generation of referenced lists
     * - before: {@link JAXBElement }{@code <}{@link Object }{@code >}
     * {@link Person }
     * - end of fix
     */
    public List<Person> getContactPerson() {
        if (contactPerson == null) {
            contactPerson = new ArrayList<Person>();
        }
        return this.contactPerson;
    }

}
