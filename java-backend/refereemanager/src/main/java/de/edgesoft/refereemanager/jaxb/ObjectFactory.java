
package de.edgesoft.refereemanager.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.edgesoft.refereemanager.jaxb package. 
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

    private final static QName _Refereemanager_QNAME = new QName("", "refereemanager");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.edgesoft.refereemanager.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RefereeManagerType }
     * 
     */
    public RefereeManagerType createRefereeManagerType() {
        return new RefereeManagerType();
    }

    /**
     * Create an instance of {@link SexType }
     * 
     */
    public SexType createSexType() {
        return new SexType();
    }

    /**
     * Create an instance of {@link ContactType }
     * 
     */
    public ContactType createContactType() {
        return new ContactType();
    }

    /**
     * Create an instance of {@link ContentType }
     * 
     */
    public ContentType createContentType() {
        return new ContentType();
    }

    /**
     * Create an instance of {@link PersonType }
     * 
     */
    public PersonType createPersonType() {
        return new PersonType();
    }

    /**
     * Create an instance of {@link TitledType }
     * 
     */
    public TitledType createTitledType() {
        return new TitledType();
    }

    /**
     * Create an instance of {@link RefereeType }
     * 
     */
    public RefereeType createRefereeType() {
        return new RefereeType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RefereeManagerType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "refereemanager")
    public JAXBElement<RefereeManagerType> createRefereemanager(RefereeManagerType value) {
        return new JAXBElement<RefereeManagerType>(_Refereemanager_QNAME, RefereeManagerType.class, null, value);
    }

}
