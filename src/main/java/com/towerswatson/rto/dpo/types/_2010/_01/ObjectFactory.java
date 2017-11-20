package com.towerswatson.rto.dpo.types._2010._01;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.towerswatson.rto.dpo.types._2010._01
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

	private final static QName _PofrResponse2DataContract_QNAME = new QName(
			"http://towerswatson.com/rto/dpo/types/2010/01",
			"PofrResponse2DataContract");
	private final static QName _SevereFaultContract_QNAME = new QName(
			"http://towerswatson.com/rto/dpo/types/2010/01",
			"SevereFaultContract");
	private final static QName _ConfigurationFaultContract_QNAME = new QName(
			"http://towerswatson.com/rto/dpo/types/2010/01",
			"ConfigurationFaultContract");
	private final static QName _PofrResponse2DataContractDiagnostics_QNAME = new QName(
			"http://towerswatson.com/rto/dpo/types/2010/01", "Diagnostics");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package:
	 * com.towerswatson.rto.dpo.types._2010._01
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link SevereFaultContract }
	 * 
	 */
	public SevereFaultContract createSevereFaultContract() {
		return new SevereFaultContract();
	}

	/**
	 * Create an instance of {@link PofrResponse2DataContract }
	 * 
	 */
	public PofrResponse2DataContract createPofrResponse2DataContract() {
		return new PofrResponse2DataContract();
	}

	/**
	 * Create an instance of {@link ConfigurationFaultContract }
	 * 
	 */
	public ConfigurationFaultContract createConfigurationFaultContract() {
		return new ConfigurationFaultContract();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link PofrResponse2DataContract }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/dpo/types/2010/01", name = "PofrResponse2DataContract")
	public JAXBElement<PofrResponse2DataContract> createPofrResponse2DataContract(
			PofrResponse2DataContract value) {
		return new JAXBElement<PofrResponse2DataContract>(
				_PofrResponse2DataContract_QNAME,
				PofrResponse2DataContract.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SevereFaultContract }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/dpo/types/2010/01", name = "SevereFaultContract")
	public JAXBElement<SevereFaultContract> createSevereFaultContract(
			SevereFaultContract value) {
		return new JAXBElement<SevereFaultContract>(_SevereFaultContract_QNAME,
				SevereFaultContract.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ConfigurationFaultContract }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/dpo/types/2010/01", name = "ConfigurationFaultContract")
	public JAXBElement<ConfigurationFaultContract> createConfigurationFaultContract(
			ConfigurationFaultContract value) {
		return new JAXBElement<ConfigurationFaultContract>(
				_ConfigurationFaultContract_QNAME,
				ConfigurationFaultContract.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://towerswatson.com/rto/dpo/types/2010/01", name = "Diagnostics", scope = PofrResponse2DataContract.class)
	public JAXBElement<String> createPofrResponse2DataContractDiagnostics(
			String value) {
		return new JAXBElement<String>(
				_PofrResponse2DataContractDiagnostics_QNAME, String.class,
				PofrResponse2DataContract.class, value);
	}

}
