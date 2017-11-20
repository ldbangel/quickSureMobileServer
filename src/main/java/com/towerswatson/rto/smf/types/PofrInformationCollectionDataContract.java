package com.towerswatson.rto.smf.types;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for PofrInformationCollectionDataContract complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="PofrInformationCollectionDataContract">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PofrInformationDataContract" type="{http://towerswatson.com/rto/smf/types/2010/01}PofrInformationDataContract" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PofrInformationCollectionDataContract", propOrder = { "pofrInformationDataContract" })
public class PofrInformationCollectionDataContract {

	@XmlElement(name = "PofrInformationDataContract", nillable = true)
	protected List<PofrInformationDataContract> pofrInformationDataContract;

	/**
	 * Gets the value of the pofrInformationDataContract property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the pofrInformationDataContract property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getPofrInformationDataContract().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link PofrInformationDataContract }
	 * 
	 * 
	 */
	public List<PofrInformationDataContract> getPofrInformationDataContract() {
		if (pofrInformationDataContract == null) {
			pofrInformationDataContract = new ArrayList<PofrInformationDataContract>();
		}
		return this.pofrInformationDataContract;
	}

}
