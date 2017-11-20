package com.towerswatson.rto.dpo.services._2010._01;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import com.towerswatson.rto.dpo.types._2010._01.PofrResponse2DataContract;
import com.towerswatson.rto.smf.types.PofInformationCollectionDataContract;
import com.towerswatson.rto.smf.types.PofrInformationCollectionDataContract;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the
 * com.towerswatson.rto.dpo.services._2010._01 package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _PofResponse2PofCollection_QNAME = new QName(
			"http://towerswatson.com/rto/dpo/services/2010/01", "PofCollection");
	private final static QName _PofResponse2MetaData_QNAME = new QName(
			"http://towerswatson.com/rto/dpo/services/2010/01", "MetaData");
	private final static QName _PofRequestUsingKeyKeyName_QNAME = new QName(
			"http://towerswatson.com/rto/dpo/services/2010/01", "KeyName");
	private final static QName _PofRequestUsingKeyPofrCollection_QNAME = new QName(
			"http://towerswatson.com/rto/dpo/services/2010/01",
			"PofrCollection");
	private final static QName _PofRequestUsingKeyKeyRequestTime_QNAME = new QName(
			"http://towerswatson.com/rto/dpo/services/2010/01",
			"KeyRequestTime");
	private final static QName _PofResponseErrorCode_QNAME = new QName(
			"http://towerswatson.com/rto/dpo/services/2010/01", "ErrorCode");
	private final static QName _PofResponseErrorMessage_QNAME = new QName(
			"http://towerswatson.com/rto/dpo/services/2010/01", "ErrorMessage");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package:
	 * com.towerswatson.rto.dpo.services._2010._01
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link PofResponse2 }
	 * 
	 */
	public PofResponse2 createPofResponse2() {
		return new PofResponse2();
	}

	/**
	 * Create an instance of {@link PofRequestUsingMasterSet }
	 * 
	 */
	public PofRequestUsingMasterSet createPofRequestUsingMasterSet() {
		return new PofRequestUsingMasterSet();
	}

	/**
	 * Create an instance of {@link PofRequest }
	 * 
	 */
	public PofRequest createPofRequest() {
		return new PofRequest();
	}

	/**
	 * Create an instance of {@link PofRequestUsingKey }
	 * 
	 */
	public PofRequestUsingKey createPofRequestUsingKey() {
		return new PofRequestUsingKey();
	}

	/**
	 * Create an instance of {@link PofResponse }
	 * 
	 */
	public PofResponse createPofResponse() {
		return new PofResponse();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link PofInformationCollectionDataContract }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/dpo/services/2010/01", name = "PofCollection", scope = PofResponse2.class)
	public JAXBElement<PofInformationCollectionDataContract> createPofResponse2PofCollection(
			PofInformationCollectionDataContract value) {
		return new JAXBElement<PofInformationCollectionDataContract>(
				_PofResponse2PofCollection_QNAME,
				PofInformationCollectionDataContract.class, PofResponse2.class,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link PofrResponse2DataContract }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/dpo/services/2010/01", name = "MetaData", scope = PofResponse2.class)
	public JAXBElement<PofrResponse2DataContract> createPofResponse2MetaData(
			PofrResponse2DataContract value) {
		return new JAXBElement<PofrResponse2DataContract>(
				_PofResponse2MetaData_QNAME, PofrResponse2DataContract.class,
				PofResponse2.class, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/dpo/services/2010/01", name = "KeyName", scope = PofRequestUsingKey.class)
	public JAXBElement<String> createPofRequestUsingKeyKeyName(String value) {
		return new JAXBElement<String>(_PofRequestUsingKeyKeyName_QNAME,
				String.class, PofRequestUsingKey.class, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link PofrInformationCollectionDataContract }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/dpo/services/2010/01", name = "PofrCollection", scope = PofRequestUsingKey.class)
	public JAXBElement<PofrInformationCollectionDataContract> createPofRequestUsingKeyPofrCollection(
			PofrInformationCollectionDataContract value) {
		return new JAXBElement<PofrInformationCollectionDataContract>(
				_PofRequestUsingKeyPofrCollection_QNAME,
				PofrInformationCollectionDataContract.class,
				PofRequestUsingKey.class, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link XMLGregorianCalendar }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/dpo/services/2010/01", name = "KeyRequestTime", scope = PofRequestUsingKey.class)
	public JAXBElement<XMLGregorianCalendar> createPofRequestUsingKeyKeyRequestTime(
			XMLGregorianCalendar value) {
		return new JAXBElement<XMLGregorianCalendar>(
				_PofRequestUsingKeyKeyRequestTime_QNAME,
				XMLGregorianCalendar.class, PofRequestUsingKey.class, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link PofrInformationCollectionDataContract }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/dpo/services/2010/01", name = "PofrCollection", scope = PofRequestUsingMasterSet.class)
	public JAXBElement<PofrInformationCollectionDataContract> createPofRequestUsingMasterSetPofrCollection(
			PofrInformationCollectionDataContract value) {
		return new JAXBElement<PofrInformationCollectionDataContract>(
				_PofRequestUsingKeyPofrCollection_QNAME,
				PofrInformationCollectionDataContract.class,
				PofRequestUsingMasterSet.class, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link PofrInformationCollectionDataContract }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/dpo/services/2010/01", name = "PofrCollection", scope = PofRequest.class)
	public JAXBElement<PofrInformationCollectionDataContract> createPofRequestPofrCollection(
			PofrInformationCollectionDataContract value) {
		return new JAXBElement<PofrInformationCollectionDataContract>(
				_PofRequestUsingKeyPofrCollection_QNAME,
				PofrInformationCollectionDataContract.class, PofRequest.class,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/dpo/services/2010/01", name = "ErrorCode", scope = PofResponse.class)
	public JAXBElement<String> createPofResponseErrorCode(String value) {
		return new JAXBElement<String>(_PofResponseErrorCode_QNAME,
				String.class, PofResponse.class, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link PofInformationCollectionDataContract }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/dpo/services/2010/01", name = "PofCollection", scope = PofResponse.class)
	public JAXBElement<PofInformationCollectionDataContract> createPofResponsePofCollection(
			PofInformationCollectionDataContract value) {
		return new JAXBElement<PofInformationCollectionDataContract>(
				_PofResponse2PofCollection_QNAME,
				PofInformationCollectionDataContract.class, PofResponse.class,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/dpo/services/2010/01", name = "ErrorMessage", scope = PofResponse.class)
	public JAXBElement<String> createPofResponseErrorMessage(String value) {
		return new JAXBElement<String>(_PofResponseErrorMessage_QNAME,
				String.class, PofResponse.class, value);
	}

}
