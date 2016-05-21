
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
     * Create an instance of {@link RefereeManager }
     * 
     */
    public RefereeManager createRefereeManager() {
        return new RefereeManager();
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
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link Referee }
     * 
     */
    public Referee createReferee() {
        return new Referee();
    }

    /**
     * Create an instance of {@link EMail }
     * 
     */
    public EMail createEMail() {
        return new EMail();
    }

    /**
     * Create an instance of {@link URL }
     * 
     */
    public URL createURL() {
        return new URL();
    }

    /**
     * Create an instance of {@link TitledType }
     * 
     */
    public TitledType createTitledType() {
        return new TitledType();
    }

    /**
     * Create an instance of {@link Content }
     * 
     */
    public Content createContent() {
        return new Content();
    }

    /**
     * Create an instance of {@link Picture }
     * 
     */
    public Picture createPicture() {
        return new Picture();
    }

    /**
     * Create an instance of {@link PhoneNumber }
     * 
     */
    public PhoneNumber createPhoneNumber() {
        return new PhoneNumber();
    }

    /**
     * Create an instance of {@link TitledIDType }
     * 
     */
    public TitledIDType createTitledIDType() {
        return new TitledIDType();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RefereeManager }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "refereemanager")
    public JAXBElement<RefereeManager> createRefereemanager(RefereeManager value) {
        return new JAXBElement<RefereeManager>(_Refereemanager_QNAME, RefereeManager.class, null, value);
    }

}
