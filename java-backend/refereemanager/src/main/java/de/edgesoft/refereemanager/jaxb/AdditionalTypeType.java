
package de.edgesoft.refereemanager.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AdditionalTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AdditionalTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="pre"/>
 *     &lt;enumeration value="alpha"/>
 *     &lt;enumeration value="beta"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AdditionalTypeType")
@XmlEnum
public enum AdditionalTypeType {

    @XmlEnumValue("pre")
    PRE("pre"),
    @XmlEnumValue("alpha")
    ALPHA("alpha"),
    @XmlEnumValue("beta")
    BETA("beta");
    private final String value;

    AdditionalTypeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AdditionalTypeType fromValue(String v) {
        for (AdditionalTypeType c: AdditionalTypeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
