package com.towerswatson.rto.dpo.services._2010._01;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.towerswatson.rto.smf.types.PofrInformationCollectionDataContract;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Time" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="PofrCollection" type="{http://towerswatson.com/rto/smf/types/2010/01}PofrInformationCollectionDataContract" minOccurs="0"/>
 *         &lt;element name="MasterSetId" type="{http://schemas.microsoft.com/2003/10/Serialization/}guid" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "time", "pofrCollection", "masterSetId" })
@XmlRootElement(name = "PofRequestUsingMasterSet")
public class PofRequestUsingMasterSet {

	@XmlElement(name = "Time")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar time;
	@XmlElementRef(name = "PofrCollection", namespace = "http://towerswatson.com/rto/dpo/services/2010/01", type = JAXBElement.class)
	protected JAXBElement<PofrInformationCollectionDataContract> pofrCollection;
	@XmlElement(name = "MasterSetId")
	protected String masterSetId;

	/**
	 * Gets the value of the time property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getTime() {
		return time;
	}

	/**
	 * Sets the value of the time property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setTime(XMLGregorianCalendar value) {
		this.time = value;
	}

	/**
	 * Gets the value of the pofrCollection property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}
	 *         {@link PofrInformationCollectionDataContract }{@code >}
	 * 
	 */
	public JAXBElement<PofrInformationCollectionDataContract> getPofrCollection() {
		return pofrCollection;
	}

	/**
	 * Sets the value of the pofrCollection property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}
	 *            {@link PofrInformationCollectionDataContract }{@code >}
	 * 
	 */
	public void setPofrCollection(
			JAXBElement<PofrInformationCollectionDataContract> value) {
		this.pofrCollection = ((JAXBElement<PofrInformationCollectionDataContract>) value);
	}

	/**
	 * Gets the value of the masterSetId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMasterSetId() {
		return masterSetId;
	}

	/**
	 * Sets the value of the masterSetId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMasterSetId(String value) {
		this.masterSetId = value;
	}

}
