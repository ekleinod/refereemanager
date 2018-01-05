//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2018.01.05 um 11:45:53 AM CET 
//


package generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="direct_String_0_1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="direct_String_1_1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="direct_String_0_u" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="direct_String_1_u" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="ref_String_0_1" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
 *         &lt;element name="ref_String_1_1" type="{http://www.w3.org/2001/XMLSchema}IDREF"/>
 *         &lt;element name="ref_String_0_u" type="{http://www.w3.org/2001/XMLSchema}IDREF" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ref_String_1_u" type="{http://www.w3.org/2001/XMLSchema}IDREF" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "directString01",
    "directString11",
    "directString0U",
    "directString1U",
    "refString01",
    "refString11",
    "refString0U",
    "refString1U"
})
@XmlRootElement(name = "lists")
public class Lists {

    @XmlElement(name = "direct_String_0_1")
    protected String directString01;
    @XmlElement(name = "direct_String_1_1", required = true)
    protected String directString11;
    @XmlElement(name = "direct_String_0_u")
    protected List<String> directString0U;
    @XmlElement(name = "direct_String_1_u", required = true)
    protected List<String> directString1U;
    @XmlElement(name = "ref_String_0_1", type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected MyString refString01;
    @XmlElement(name = "ref_String_1_1", required = true, type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected MyString refString11;
    @XmlElementRef(name = "ref_String_0_u", type = JAXBElement.class, required = false)
    protected List<MyString> refString0U;
    @XmlElementRef(name = "ref_String_1_u", type = JAXBElement.class)
    protected List<MyString> refString1U;

    /**
     * Ruft den Wert der directString01-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirectString01() {
        return directString01;
    }

    /**
     * Legt den Wert der directString01-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirectString01(String value) {
        this.directString01 = value;
    }

    /**
     * Ruft den Wert der directString11-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirectString11() {
        return directString11;
    }

    /**
     * Legt den Wert der directString11-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirectString11(String value) {
        this.directString11 = value;
    }

    /**
     * Gets the value of the directString0U property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the directString0U property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDirectString0U().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDirectString0U() {
        if (directString0U == null) {
            directString0U = new ArrayList<String>();
        }
        return this.directString0U;
    }

    /**
     * Gets the value of the directString1U property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the directString1U property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDirectString1U().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDirectString1U() {
        if (directString1U == null) {
            directString1U = new ArrayList<String>();
        }
        return this.directString1U;
    }

    /**
     * Ruft den Wert der refString01-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public MyString getRefString01() {
        return refString01;
    }

    /**
     * Legt den Wert der refString01-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setRefString01(MyString value) {
        this.refString01 = value;
    }

    /**
     * Ruft den Wert der refString11-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public MyString getRefString11() {
        return refString11;
    }

    /**
     * Legt den Wert der refString11-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setRefString11(MyString value) {
        this.refString11 = value;
    }

    /**
     * Gets the value of the refString0U property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refString0U property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefString0U().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * 
     */
    public List<MyString> getRefString0U() {
        if (refString0U == null) {
            refString0U = new ArrayList<MyString>();
        }
        return this.refString0U;
    }

    /**
     * Gets the value of the refString1U property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refString1U property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefString1U().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * 
     */
    public List<MyString> getRefString1U() {
        if (refString1U == null) {
            refString1U = new ArrayList<MyString>();
        }
        return this.refString1U;
    }

}
