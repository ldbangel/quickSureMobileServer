package com.towerswatson.rto.smf.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for PofMetadataDataContract complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="PofMetadataDataContract">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ScheduleId" type="{http://schemas.microsoft.com/2003/10/Serialization/}guid"/>
 *         &lt;element name="MasterSetId" type="{http://schemas.microsoft.com/2003/10/Serialization/}guid"/>
 *         &lt;element name="StrategyIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ConfigSetId" type="{http://schemas.microsoft.com/2003/10/Serialization/}guid"/>
 *         &lt;element name="ModelRevisionId" type="{http://schemas.microsoft.com/2003/10/Serialization/}guid" minOccurs="0"/>
 *         &lt;element name="RequestedMasterSetId" type="{http://schemas.microsoft.com/2003/10/Serialization/}guid" minOccurs="0"/>
 *         &lt;element name="ScheduleAliasOfId" type="{http://schemas.microsoft.com/2003/10/Serialization/}guid" minOccurs="0"/>
 *         &lt;element name="KeyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="KeyStartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PofMetadataDataContract", propOrder = { "scheduleId",
		"masterSetId", "strategyIndex", "configSetId", "modelRevisionId",
		"requestedMasterSetId", "scheduleAliasOfId", "keyName", "keyStartTime" })
public class PofMetadataDataContract {

	@XmlElement(name = "ScheduleId", required = true)
	protected String scheduleId;
	@XmlElement(name = "MasterSetId", required = true)
	protected String masterSetId;
	@XmlElement(name = "StrategyIndex", required = true, type = Integer.class, nillable = true)
	protected Integer strategyIndex;
	@XmlElement(name = "ConfigSetId", required = true, nillable = true)
	protected String configSetId;
	@XmlElementRef(name = "ModelRevisionId", namespace = "http://towerswatson.com/rto/smf/types/2010/01", type = JAXBElement.class)
	protected JAXBElement<String> modelRevisionId;
	@XmlElementRef(name = "RequestedMasterSetId", namespace = "http://towerswatson.com/rto/smf/types/2010/01", type = JAXBElement.class)
	protected JAXBElement<String> requestedMasterSetId;
	@XmlElementRef(name = "ScheduleAliasOfId", namespace = "http://towerswatson.com/rto/smf/types/2010/01", type = JAXBElement.class)
	protected JAXBElement<String> scheduleAliasOfId;
	@XmlElementRef(name = "KeyName", namespace = "http://towerswatson.com/rto/smf/types/2010/01", type = JAXBElement.class)
	protected JAXBElement<String> keyName;
	@XmlElementRef(name = "KeyStartTime", namespace = "http://towerswatson.com/rto/smf/types/2010/01", type = JAXBElement.class)
	protected JAXBElement<XMLGregorianCalendar> keyStartTime;

	/**
	 * Gets the value of the scheduleId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getScheduleId() {
		return scheduleId;
	}

	/**
	 * Sets the value of the scheduleId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setScheduleId(String value) {
		this.scheduleId = value;
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

	/**
	 * Gets the value of the strategyIndex property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getStrategyIndex() {
		return strategyIndex;
	}

	/**
	 * Sets the value of the strategyIndex property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setStrategyIndex(Integer value) {
		this.strategyIndex = value;
	}

	/**
	 * Gets the value of the configSetId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConfigSetId() {
		return configSetId;
	}

	/**
	 * Sets the value of the configSetId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConfigSetId(String value) {
		this.configSetId = value;
	}

	/**
	 * Gets the value of the modelRevisionId property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getModelRevisionId() {
		return modelRevisionId;
	}

	/**
	 * Sets the value of the modelRevisionId property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setModelRevisionId(JAXBElement<String> value) {
		this.modelRevisionId = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the requestedMasterSetId property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getRequestedMasterSetId() {
		return requestedMasterSetId;
	}

	/**
	 * Sets the value of the requestedMasterSetId property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setRequestedMasterSetId(JAXBElement<String> value) {
		this.requestedMasterSetId = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the scheduleAliasOfId property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getScheduleAliasOfId() {
		return scheduleAliasOfId;
	}

	/**
	 * Sets the value of the scheduleAliasOfId property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setScheduleAliasOfId(JAXBElement<String> value) {
		this.scheduleAliasOfId = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the keyName property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link String }
	 *         {@code >}
	 * 
	 */
	public JAXBElement<String> getKeyName() {
		return keyName;
	}

	/**
	 * Sets the value of the keyName property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link String }
	 *            {@code >}
	 * 
	 */
	public void setKeyName(JAXBElement<String> value) {
		this.keyName = ((JAXBElement<String>) value);
	}

	/**
	 * Gets the value of the keyStartTime property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}
	 *         {@link XMLGregorianCalendar }{@code >}
	 * 
	 */
	public JAXBElement<XMLGregorianCalendar> getKeyStartTime() {
		return keyStartTime;
	}

	/**
	 * Sets the value of the keyStartTime property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}
	 *            {@link XMLGregorianCalendar }{@code >}
	 * 
	 */
	public void setKeyStartTime(JAXBElement<XMLGregorianCalendar> value) {
		this.keyStartTime = ((JAXBElement<XMLGregorianCalendar>) value);
	}

}
