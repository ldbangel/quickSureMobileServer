package com.towerswatson.rto.smf.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.towerswatson.rto.smf.types._2010._01
 * package.
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

	private final static QName _DataType_QNAME = new QName(
			"http://towerswatson.com/rto/smf/types/2010/01", "DataType");
	private final static QName _MIDataCollectionDataContract_QNAME = new QName(
			"http://towerswatson.com/rto/smf/types/2010/01",
			"MIDataCollectionDataContract");
	private final static QName _PofrInformationCollectionDataContract_QNAME = new QName(
			"http://towerswatson.com/rto/smf/types/2010/01",
			"PofrInformationCollectionDataContract");
	private final static QName _PofMetadataDataContract_QNAME = new QName(
			"http://towerswatson.com/rto/smf/types/2010/01",
			"PofMetadataDataContract");
	private final static QName _PofInformationDataContract_QNAME = new QName(
			"http://towerswatson.com/rto/smf/types/2010/01",
			"PofInformationDataContract");
	private final static QName _PofrInformationDataContract_QNAME = new QName(
			"http://towerswatson.com/rto/smf/types/2010/01",
			"PofrInformationDataContract");
	private final static QName _MIDataDataContract_QNAME = new QName(
			"http://towerswatson.com/rto/smf/types/2010/01",
			"MIDataDataContract");
	private final static QName _PofInformationCollectionDataContract_QNAME = new QName(
			"http://towerswatson.com/rto/smf/types/2010/01",
			"PofInformationCollectionDataContract");
	private final static QName _PofInformationDataContractErrorMessage_QNAME = new QName(
			"http://towerswatson.com/rto/smf/types/2010/01", "ErrorMessage");
	private final static QName _PofInformationDataContractPof_QNAME = new QName(
			"http://towerswatson.com/rto/smf/types/2010/01", "Pof");
	private final static QName _PofInformationDataContractMIDataCollection_QNAME = new QName(
			"http://towerswatson.com/rto/smf/types/2010/01", "MIDataCollection");
	private final static QName _PofInformationDataContractPofMetadata_QNAME = new QName(
			"http://towerswatson.com/rto/smf/types/2010/01", "PofMetadata");
	private final static QName _PofInformationDataContractErrorCode_QNAME = new QName(
			"http://towerswatson.com/rto/smf/types/2010/01", "ErrorCode");
	private final static QName _PofMetadataDataContractKeyStartTime_QNAME = new QName(
			"http://towerswatson.com/rto/smf/types/2010/01", "KeyStartTime");
	private final static QName _PofMetadataDataContractKeyName_QNAME = new QName(
			"http://towerswatson.com/rto/smf/types/2010/01", "KeyName");
	private final static QName _PofMetadataDataContractRequestedMasterSetId_QNAME = new QName(
			"http://towerswatson.com/rto/smf/types/2010/01",
			"RequestedMasterSetId");
	private final static QName _PofMetadataDataContractScheduleAliasOfId_QNAME = new QName(
			"http://towerswatson.com/rto/smf/types/2010/01",
			"ScheduleAliasOfId");
	private final static QName _PofMetadataDataContractModelRevisionId_QNAME = new QName(
			"http://towerswatson.com/rto/smf/types/2010/01", "ModelRevisionId");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package:
	 * com.towerswatson.rto.smf.types._2010._01
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link PofrInformationDataContract }
	 * 
	 */
	public PofrInformationDataContract createPofrInformationDataContract() {
		return new PofrInformationDataContract();
	}

	/**
	 * Create an instance of {@link PofrInformationCollectionDataContract }
	 * 
	 */
	public PofrInformationCollectionDataContract createPofrInformationCollectionDataContract() {
		return new PofrInformationCollectionDataContract();
	}

	/**
	 * Create an instance of {@link MIDataCollectionDataContract }
	 * 
	 */
	public MIDataCollectionDataContract createMIDataCollectionDataContract() {
		return new MIDataCollectionDataContract();
	}

	/**
	 * Create an instance of {@link PofInformationDataContract }
	 * 
	 */
	public PofInformationDataContract createPofInformationDataContract() {
		return new PofInformationDataContract();
	}

	/**
	 * Create an instance of {@link MIDataDataContract }
	 * 
	 */
	public MIDataDataContract createMIDataDataContract() {
		return new MIDataDataContract();
	}

	/**
	 * Create an instance of {@link PofInformationCollectionDataContract }
	 * 
	 */
	public PofInformationCollectionDataContract createPofInformationCollectionDataContract() {
		return new PofInformationCollectionDataContract();
	}

	/**
	 * Create an instance of {@link PofMetadataDataContract }
	 * 
	 */
	public PofMetadataDataContract createPofMetadataDataContract() {
		return new PofMetadataDataContract();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link DataType }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/smf/types/2010/01", name = "DataType")
	public JAXBElement<DataType> createDataType(DataType value) {
		return new JAXBElement<DataType>(_DataType_QNAME, DataType.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link MIDataCollectionDataContract }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/smf/types/2010/01", name = "MIDataCollectionDataContract")
	public JAXBElement<MIDataCollectionDataContract> createMIDataCollectionDataContract(
			MIDataCollectionDataContract value) {
		return new JAXBElement<MIDataCollectionDataContract>(
				_MIDataCollectionDataContract_QNAME,
				MIDataCollectionDataContract.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link PofrInformationCollectionDataContract }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/smf/types/2010/01", name = "PofrInformationCollectionDataContract")
	public JAXBElement<PofrInformationCollectionDataContract> createPofrInformationCollectionDataContract(
			PofrInformationCollectionDataContract value) {
		return new JAXBElement<PofrInformationCollectionDataContract>(
				_PofrInformationCollectionDataContract_QNAME,
				PofrInformationCollectionDataContract.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link PofMetadataDataContract }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/smf/types/2010/01", name = "PofMetadataDataContract")
	public JAXBElement<PofMetadataDataContract> createPofMetadataDataContract(
			PofMetadataDataContract value) {
		return new JAXBElement<PofMetadataDataContract>(
				_PofMetadataDataContract_QNAME, PofMetadataDataContract.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link PofInformationDataContract }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/smf/types/2010/01", name = "PofInformationDataContract")
	public JAXBElement<PofInformationDataContract> createPofInformationDataContract(
			PofInformationDataContract value) {
		return new JAXBElement<PofInformationDataContract>(
				_PofInformationDataContract_QNAME,
				PofInformationDataContract.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link PofrInformationDataContract }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/smf/types/2010/01", name = "PofrInformationDataContract")
	public JAXBElement<PofrInformationDataContract> createPofrInformationDataContract(
			PofrInformationDataContract value) {
		return new JAXBElement<PofrInformationDataContract>(
				_PofrInformationDataContract_QNAME,
				PofrInformationDataContract.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link MIDataDataContract }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/smf/types/2010/01", name = "MIDataDataContract")
	public JAXBElement<MIDataDataContract> createMIDataDataContract(
			MIDataDataContract value) {
		return new JAXBElement<MIDataDataContract>(_MIDataDataContract_QNAME,
				MIDataDataContract.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link PofInformationCollectionDataContract }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/smf/types/2010/01", name = "PofInformationCollectionDataContract")
	public JAXBElement<PofInformationCollectionDataContract> createPofInformationCollectionDataContract(
			PofInformationCollectionDataContract value) {
		return new JAXBElement<PofInformationCollectionDataContract>(
				_PofInformationCollectionDataContract_QNAME,
				PofInformationCollectionDataContract.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/smf/types/2010/01", name = "ErrorMessage", scope = PofInformationDataContract.class)
	public JAXBElement<String> createPofInformationDataContractErrorMessage(
			String value) {
		return new JAXBElement<String>(
				_PofInformationDataContractErrorMessage_QNAME, String.class,
				PofInformationDataContract.class, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/smf/types/2010/01", name = "Pof", scope = PofInformationDataContract.class)
	public JAXBElement<String> createPofInformationDataContractPof(String value) {
		return new JAXBElement<String>(_PofInformationDataContractPof_QNAME,
				String.class, PofInformationDataContract.class, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link MIDataCollectionDataContract }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/smf/types/2010/01", name = "MIDataCollection", scope = PofInformationDataContract.class)
	public JAXBElement<MIDataCollectionDataContract> createPofInformationDataContractMIDataCollection(
			MIDataCollectionDataContract value) {
		return new JAXBElement<MIDataCollectionDataContract>(
				_PofInformationDataContractMIDataCollection_QNAME,
				MIDataCollectionDataContract.class,
				PofInformationDataContract.class, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link PofMetadataDataContract }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/smf/types/2010/01", name = "PofMetadata", scope = PofInformationDataContract.class)
	public JAXBElement<PofMetadataDataContract> createPofInformationDataContractPofMetadata(
			PofMetadataDataContract value) {
		return new JAXBElement<PofMetadataDataContract>(
				_PofInformationDataContractPofMetadata_QNAME,
				PofMetadataDataContract.class,
				PofInformationDataContract.class, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/smf/types/2010/01", name = "ErrorCode", scope = PofInformationDataContract.class)
	public JAXBElement<String> createPofInformationDataContractErrorCode(
			String value) {
		return new JAXBElement<String>(
				_PofInformationDataContractErrorCode_QNAME, String.class,
				PofInformationDataContract.class, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link XMLGregorianCalendar }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/smf/types/2010/01", name = "KeyStartTime", scope = PofMetadataDataContract.class)
	public JAXBElement<XMLGregorianCalendar> createPofMetadataDataContractKeyStartTime(
			XMLGregorianCalendar value) {
		return new JAXBElement<XMLGregorianCalendar>(
				_PofMetadataDataContractKeyStartTime_QNAME,
				XMLGregorianCalendar.class, PofMetadataDataContract.class,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/smf/types/2010/01", name = "KeyName", scope = PofMetadataDataContract.class)
	public JAXBElement<String> createPofMetadataDataContractKeyName(String value) {
		return new JAXBElement<String>(_PofMetadataDataContractKeyName_QNAME,
				String.class, PofMetadataDataContract.class, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/smf/types/2010/01", name = "RequestedMasterSetId", scope = PofMetadataDataContract.class)
	public JAXBElement<String> createPofMetadataDataContractRequestedMasterSetId(
			String value) {
		return new JAXBElement<String>(
				_PofMetadataDataContractRequestedMasterSetId_QNAME,
				String.class, PofMetadataDataContract.class, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/smf/types/2010/01", name = "ScheduleAliasOfId", scope = PofMetadataDataContract.class)
	public JAXBElement<String> createPofMetadataDataContractScheduleAliasOfId(
			String value) {
		return new JAXBElement<String>(
				_PofMetadataDataContractScheduleAliasOfId_QNAME, String.class,
				PofMetadataDataContract.class, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/smf/types/2010/01", name = "ModelRevisionId", scope = PofMetadataDataContract.class)
	public JAXBElement<String> createPofMetadataDataContractModelRevisionId(
			String value) {
		return new JAXBElement<String>(
				_PofMetadataDataContractModelRevisionId_QNAME, String.class,
				PofMetadataDataContract.class, value);
	}

}
