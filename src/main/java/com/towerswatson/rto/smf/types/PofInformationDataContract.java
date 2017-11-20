package com.towerswatson.rto.smf.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for PofInformationDataContract complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="PofInformationDataContract">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Pof" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ErrorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ErrorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MIDataCollection" type="{http://towerswatson.com/rto/smf/types/2010/01}MIDataCollectionDataContract" minOccurs="0"/>
 *         &lt;element name="PofMetadata" type="{http://towerswatson.com/rto/smf/types/2010/01}PofMetadataDataContract" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PofInformationDataContract", propOrder = { "pof", "errorCode",
		"errorMessage", "miDataCollection", "pofMetadata" })
public class PofInformationDataContract {

	@XmlElementRef(name = "Pof", namespace = "http://towerswatson.com/rto/smf/types/2010/01", type = JAXBElement.class)
	protected JAXBElement<String> pof;
	@XmlElementRef(name = "ErrorCode", namespace = "http://towerswatson.com/rto/smf/types/2010/01", type = JAXBElement.class)
	protected JAXBElement<String> errorCode;
	@XmlElementRef(name = "ErrorMessage", namespace = "http://towerswatson.com/rto/smf/types/2010/01", type = JAXBElement.class)
	protected JAXBElement<String> errorMessage;
	@XmlElementRef(name = "MIDataCollection", namespace = "http://towerswatson.com/rto/smf/types/2010/01", type = JAXBElement.class)
	protected JAXBElement<MIDataCollectionDataContract> miDataCollection;
	@XmlElementRef(name = "PofMetadata", namespace = "http://towerswatson.com/rto/smf/types/2010/01", type = JAXBElement.class)
	protected JAXBElement<PofMetadataDataContract> pofMetadata;

	/**
	 * Gets the value of the pof property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getPof() {
		return pof;
	}

	/**
	 * Sets the value of the pof property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setPof(JAXBElement<String> value) {
		this.pof = ((JAXBElement<String>) value);
	}

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
	 * Gets the value of the miDataCollection property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}
	 *         {@link MIDataCollectionDataContract }{@code >}
	 * 
	 */
	public JAXBElement<MIDataCollectionDataContract> getMIDataCollection() {
		return miDataCollection;
	}

	/**
	 * Sets the value of the miDataCollection property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}
	 *            {@link MIDataCollectionDataContract }{@code >}
	 * 
	 */
	public void setMIDataCollection(
			JAXBElement<MIDataCollectionDataContract> value) {
		this.miDataCollection = ((JAXBElement<MIDataCollectionDataContract>) value);
	}

	/**
	 * Gets the value of the pofMetadata property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}
	 *         {@link PofMetadataDataContract }{@code >}
	 * 
	 */
	public JAXBElement<PofMetadataDataContract> getPofMetadata() {
		return pofMetadata;
	}

	/**
	 * Sets the value of the pofMetadata property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}
	 *            {@link PofMetadataDataContract }{@code >}
	 * 
	 */
	public void setPofMetadata(JAXBElement<PofMetadataDataContract> value) {
		this.pofMetadata = ((JAXBElement<PofMetadataDataContract>) value);
	}

}
