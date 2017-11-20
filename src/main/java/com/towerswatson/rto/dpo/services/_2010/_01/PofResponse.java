package com.towerswatson.rto.dpo.services._2010._01;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

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
 *         &lt;element name="ErrorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ErrorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "", propOrder = { "errorCode", "errorMessage", "pofCollection" })
@XmlRootElement(name = "PofResponse")
public class PofResponse {

	@XmlElementRef(name = "ErrorCode", namespace = "http://towerswatson.com/rto/dpo/services/2010/01", type = JAXBElement.class)
	protected JAXBElement<String> errorCode;
	@XmlElementRef(name = "ErrorMessage", namespace = "http://towerswatson.com/rto/dpo/services/2010/01", type = JAXBElement.class)
	protected JAXBElement<String> errorMessage;
	@XmlElementRef(name = "PofCollection", namespace = "http://towerswatson.com/rto/dpo/services/2010/01", type = JAXBElement.class)
	protected JAXBElement<PofInformationCollectionDataContract> pofCollection;

	/**
	 * Gets the value of the errorCode property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the value of the errorCode property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setErrorCode(JAXBElement<String> value) {
		this.errorCode = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the errorMessage property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Sets the value of the errorMessage property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setErrorMessage(JAXBElement<String> value) {
		this.errorMessage = ((JAXBElement<String>) value);
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
