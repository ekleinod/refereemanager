//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2018.01.05 um 11:45:53 AM CET 
//


package generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ListsRefString0U_QNAME = new QName("", "ref_String_0_u");
    private final static QName _ListsRefString1U_QNAME = new QName("", "ref_String_1_u");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Lists }
     * 
     */
    public Lists createLists() {
        return new Lists();
    }

    /**
     * Create an instance of {@link MyString }
     * 
     */
    public MyString createMyString() {
        return new MyString();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ref_String_0_u", scope = Lists.class)
    @XmlIDREF
    public JAXBElement<Object> createListsRefString0U(Object value) {
        return new JAXBElement<Object>(_ListsRefString0U_QNAME, Object.class, Lists.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ref_String_1_u", scope = Lists.class)
    @XmlIDREF
    public JAXBElement<Object> createListsRefString1U(Object value) {
        return new JAXBElement<Object>(_ListsRefString1U_QNAME, Object.class, Lists.class, value);
    }

}
