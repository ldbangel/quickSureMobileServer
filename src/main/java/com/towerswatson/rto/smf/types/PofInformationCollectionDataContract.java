package com.towerswatson.rto.smf.types;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for PofInformationCollectionDataContract complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="PofInformationCollectionDataContract">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PofInformationDataContract" type="{http://towerswatson.com/rto/smf/types/2010/01}PofInformationDataContract" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PofInformationCollectionDataContract", propOrder = { "pofInformationDataContract" })
public class PofInformationCollectionDataContract {

	@XmlElement(name = "PofInformationDataContract", nillable = true)
	protected List<PofInformationDataContract> pofInformationDataContract;

	/**
	 * Gets the value of the pofInformationDataContract property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the pofInformationDataContract property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getPofInformationDataContract().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link PofInformationDataContract }
	 * 
	 * 
	 */
	public List<PofInformationDataContract> getPofInformationDataContract() {
		if (pofInformationDataContract == null) {
			pofInformationDataContract = new ArrayList<PofInformationDataContract>();
		}
		return this.pofInformationDataContract;
	}

}
