package com.towerswatson.rto.dpo.services._2010._01;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.towerswatson.rto.dpo.types._2010._01.PofrResponse2DataContract;
import com.towerswatson.rto.smf.types.PofInformationCollectionDataContract;

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
 *         &lt;element name="MetaData" type="{http://towerswatson.com/rto/dpo/types/2010/01}PofrResponse2DataContract" minOccurs="0"/>
 *         &lt;element name="PofCollection" type="{http://towerswatson.com/rto/smf/types/2010/01}PofInformationCollectionDataContract" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "metaData", "pofCollection" })
@XmlRootElement(name = "PofResponse2")
public class PofResponse2 {

	@XmlElementRef(name = "MetaData", namespace = "http://towerswatson.com/rto/dpo/services/2010/01", type = JAXBElement.class)
	protected JAXBElement<PofrResponse2DataContract> metaData;
	@XmlElementRef(name = "PofCollection", namespace = "http://towerswatson.com/rto/dpo/services/2010/01", type = JAXBElement.class)
	protected JAXBElement<PofInformationCollectionDataContract> pofCollection;

	/**
	 * Gets the value of the metaData property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}
	 *         {@link PofrResponse2DataContract }{@code >}
	 * 
	 */
	public JAXBElement<PofrResponse2DataContract> getMetaData() {
		return metaData;
	}

	/**
	 * Sets the value of the metaData property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}
	 *            {@link PofrResponse2DataContract }{@code >}
	 * 
	 */
	public void setMetaData(JAXBElement<PofrResponse2DataContract> value) {
		this.metaData = ((JAXBElement<PofrResponse2DataContract>) value);
	}

	/**
	 * Gets the value of the pofCollection property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}
	 *         {@link PofInformationCollectionDataContract }{@code >}
	 * 
	 */
	public JAXBElement<PofInformationCollectionDataContract> getPofCollection() {
		return pofCollection;
	}

	/**
	 * Sets the value of the pofCollection property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}
	 *            {@link PofInformationCollectionDataContract }{@code >}
	 * 
	 */
	public void setPofCollection(
			JAXBElement<PofInformationCollectionDataContract> value) {
		this.pofCollection = ((JAXBElement<PofInformationCollectionDataContract>) value);
	}

}
