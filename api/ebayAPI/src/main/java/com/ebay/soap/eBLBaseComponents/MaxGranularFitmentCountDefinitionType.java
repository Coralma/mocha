
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * 
 * 				Defines the total number of fine grained item compatibilities that can be applied
 * 				to a listing.
 * 				<br><br>
 * 				When you list with parts compatibility, using only the high-level compatibility
 * 				search names, such as Year, Make, and Model, the fitment applies to the various
 * 				unspecified lower-level compatiblity search names and values, such as Trim and
 * 				Engine, as well. This means that specifying a single coarsely defined item
 * 				compatibility may result in multiple fitments applying to the listing at the lowest
 * 				level of granularity. Up to 300 (or whatever maximum is indicated by
 * 				MaxItemCompatibility) coarse parts compatiblities can be specified for a given
 * 				listing.
 * 				<br><br>
 * 				Alternatively, you can explicitly specify up to 1000 (or whatever maximum is
 * 				indicated by MaxGranularFitmentCount) parts compatibilities at the lowest level of
 * 				granularity. That is, if you specify your parts compatibilities using all of the
 * 				supported compatiblity search names (e.g., Year, Make, Model, Trim, and Engine),
 * 				you can specify up to 1000 compatibilities.
 * 			
 * 
 * <p>Java class for MaxGranularFitmentCountDefinitionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MaxGranularFitmentCountDefinitionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;any/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MaxGranularFitmentCountDefinitionType", propOrder = {
    "any"
})
public class MaxGranularFitmentCountDefinitionType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link Object }
     *     {@link Element }
     *     
     */
    public Object[] getAny() {
        if (this.any == null) {
            return new Object[ 0 ] ;
        }
        return ((Object[]) this.any.toArray(new Object[this.any.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link Object }
     *     {@link Element }
     *     
     */
    public Object getAny(int idx) {
        if (this.any == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.any.get(idx);
    }

    public int getAnyLength() {
        if (this.any == null) {
            return  0;
        }
        return this.any.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link Object }
     *     {@link Element }
     *     
     */
    public void setAny(Object[] values) {
        this._getAny().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.any.add(values[i]);
        }
    }

    protected List<Object> _getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return any;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     {@link Element }
     *     
     */
    public Object setAny(int idx, Object value) {
        return this.any.set(idx, value);
    }

}
