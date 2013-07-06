
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RefundOptionsCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RefundOptionsCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="MoneyBack"/>
 *     &lt;enumeration value="Exchange"/>
 *     &lt;enumeration value="MerchandiseCredit"/>
 *     &lt;enumeration value="MoneyBackOrExchange"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 * Note: Per JAXB standards, underscores are added to separate words in enumerations (e.g., PayPal becomes PAY_PAL).
 */
@XmlType(name = "RefundOptionsCodeType")
@XmlEnum
public enum RefundOptionsCodeType {


    /**
     * 
     * 						The seller will refund the basic listing (or transaction) cost, excluding
     * 						shipping and other costs.
     * 						Use ReturnPolicy.Description to explain how the seller will handle the refund
     * 						(such as whether the refund will occur via PayPal).
     * 					
     * 
     */
    @XmlEnumValue("MoneyBack")
    MONEY_BACK("MoneyBack"),

    /**
     * 
     * 						This value is deprecated on the US site, and all listings attempting to use this
     * 						value will be blocked.
     * 						<br><br>
     * 						The seller will exchange the returned item for another item. Use
     * 						ReturnPolicy.Description to explain how this will occur (such as whether the seller will
     * 						send an identical item in place of the returned item).
     * 					
     * 
     */
    @XmlEnumValue("Exchange")
    EXCHANGE("Exchange"),

    /**
     * 
     * 						This value is deprecated on the US site, and all listings attempting to use this
     * 						value will be blocked. 
     * 						<br><br> 
     * 						The seller will give the buyer a credit toward the purchase of another item.
     * 						Use ReturnPolicy.Description to explain how the buyer can redeem this credit.
     * 					
     * 
     */
    @XmlEnumValue("MerchandiseCredit")
    MERCHANDISE_CREDIT("MerchandiseCredit"),

    /**
     * 
     * 						The seller will either issue the buyer a refund for the cost of the item (excluding
     * 						shipping and other costs), or the seller will exchange the returned item for another
     * 						identical/similar item. 
     * 						<br><br> 
     * 						Use ReturnPolicy.Description to explain how the seller will handle the refund (such as
     * 						whether the refund will occur via PayPal), or how the exchange will occur (such as
     * 						whether the seller will send an identical item in place of the returned item).
     * 					
     * 
     */
    @XmlEnumValue("MoneyBackOrExchange")
    MONEY_BACK_OR_EXCHANGE("MoneyBackOrExchange"),

    /**
     * 
     * 						Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    RefundOptionsCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RefundOptionsCodeType fromValue(String v) {
        for (RefundOptionsCodeType c: RefundOptionsCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
