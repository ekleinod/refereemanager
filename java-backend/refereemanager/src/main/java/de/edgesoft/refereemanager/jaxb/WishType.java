
package de.edgesoft.refereemanager.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WishType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="WishType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="prefer"/>
 *     &lt;enumeration value="avoid"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "WishType")
@XmlEnum
public enum WishType {

    @XmlEnumValue("prefer")
    PREFER("prefer"),
    @XmlEnumValue("avoid")
    AVOID("avoid");
    private final String value;

    WishType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static WishType fromValue(String v) {
        for (WishType c: WishType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
