
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ValueTypeCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ValueTypeCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Decimal"/>
 *     &lt;enumeration value="Text"/>
 *     &lt;enumeration value="ISBN"/>
 *     &lt;enumeration value="UPC"/>
 *     &lt;enumeration value="EAN"/>
 *     &lt;enumeration value="Date"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 * Note: Per JAXB standards, underscores are added to separate words in enumerations (e.g., PayPal becomes PAY_PAL).
 */
@XmlType(name = "ValueTypeCodeType")
@XmlEnum
public enum ValueTypeCodeType {


    /**
     * 
     * 						A number with a possible decimal point, e.g. -3.14159.
     * 						(Item Specifics don't distinguish between float and double.)
     * 					
     * 
     */
    @XmlEnumValue("Decimal")
    DECIMAL("Decimal"),

    /**
     * 
     * 						Free-form text. This is the default. 
     * 						Max length 50.
     * 					
     * 
     */
    @XmlEnumValue("Text")
    TEXT("Text"),

    /**
     * 
     * 						International Standard Book Number (ISBN).
     * 						ISBNs can contain either 10 or 13 characters.
     * 						Max length 13.
     * 					
     * 
     */
    ISBN("ISBN"),

    /**
     * 
     * 						Universal Product Code. Max length 12.
     * 					
     * 
     */
    UPC("UPC"),

    /**
     * 
     * 						European Article Number. Max length 13.
     * 					
     * 
     */
    EAN("EAN"),

    /**
     * 
     * 						A Date value, which can assume one of these formats:
     * 						YYYYMMDD, YYYYMM, or YYYY. See ValueFormat for the 
     * 						recommended format to use in listings.
     * 					
     * 
     */
    @XmlEnumValue("Date")
    DATE("Date"),

    /**
     * 
     * 						Reserved for future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    ValueTypeCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ValueTypeCodeType fromValue(String v) {
        for (ValueTypeCodeType c: ValueTypeCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
