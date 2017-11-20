package com.towerswatson.rto.smf.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for PofrInformationDataContract complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="PofrInformationDataContract">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Pofr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PofrInformationDataContract", propOrder = { "pofr" })
public class PofrInformationDataContract {

	@XmlElement(name = "Pofr", required = true, nillable = true)
	protected String pofr;

	/**
	 * Gets the value of the pofr property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPofr() {
		return pofr;
	}

	/**
	 * Sets the value of the pofr property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPofr(String value) {
		this.pofr = value;
	}

}
