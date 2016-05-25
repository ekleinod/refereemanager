
package de.edgesoft.refereemanager.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ClubRelationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ClubRelationType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="member"/>
 *     &lt;enumeration value="referee"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ClubRelationType")
@XmlEnum
public enum ClubRelationType {

    @XmlEnumValue("member")
    MEMBER("member"),
    @XmlEnumValue("referee")
    REFEREE("referee");
    private final String value;

    ClubRelationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ClubRelationType fromValue(String v) {
        for (ClubRelationType c: ClubRelationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
