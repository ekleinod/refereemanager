
package de.edgesoft.refereemanager.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter3
    extends XmlAdapter<String, Integer>
{


    public Integer unmarshal(String value) {
        return (javax.xml.bind.DatatypeConverter.parseInt(value));
    }

    public String marshal(Integer value) {
        if (value == null) {
            return null;
        }
        return (javax.xml.bind.DatatypeConverter.printInt(value));
    }

}
