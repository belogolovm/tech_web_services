
package com.maxart.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for findPictures complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="findPictures">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="q" type="{http://service.maxart.com/}myRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findPictures", propOrder = {
    "q"
})
public class FindPictures {

    protected MyRequest q;

    /**
     * Gets the value of the q property.
     * 
     * @return
     *     possible object is
     *     {@link MyRequest }
     *     
     */
    public MyRequest getQ() {
        return q;
    }

    /**
     * Sets the value of the q property.
     * 
     * @param value
     *     allowed object is
     *     {@link MyRequest }
     *     
     */
    public void setQ(MyRequest value) {
        this.q = value;
    }

}
