package com.towerswatson.rto.smf.types;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for MIDataCollectionDataContract complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="MIDataCollectionDataContract">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MIDataDataContract" type="{http://towerswatson.com/rto/smf/types/2010/01}MIDataDataContract" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MIDataCollectionDataContract", propOrder = { "miDataDataContract" })
public class MIDataCollectionDataContract {

	@XmlElement(name = "MIDataDataContract", nillable = true)
	protected List<MIDataDataContract> miDataDataContract;

	/**
	 * Gets the value of the miDataDataContract property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the miDataDataContract property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getMIDataDataContract().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link MIDataDataContract }
	 * 
	 * 
	 */
	public List<MIDataDataContract> getMIDataDataContract() {
		if (miDataDataContract == null) {
			miDataDataContract = new ArrayList<MIDataDataContract>();
		}
		return this.miDataDataContract;
	}

}
