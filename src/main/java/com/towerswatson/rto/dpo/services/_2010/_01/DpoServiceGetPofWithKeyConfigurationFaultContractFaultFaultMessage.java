package com.towerswatson.rto.dpo.services._2010._01;

import javax.xml.ws.WebFault;

import com.towerswatson.rto.dpo.types._2010._01.ConfigurationFaultContract;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * 
 */
@WebFault(name = "ConfigurationFaultContract", targetNamespace = "http://towerswatson.com/rto/dpo/types/2010/01")
public class DpoServiceGetPofWithKeyConfigurationFaultContractFaultFaultMessage
		extends Exception {

	/**
	 * Java type that goes as soapenv:Fault detail element.
	 * 
	 */
	private ConfigurationFaultContract faultInfo;

	/**
	 * 
	 * @param message
	 * @param faultInfo
	 */
	public DpoServiceGetPofWithKeyConfigurationFaultContractFaultFaultMessage(
			String message, ConfigurationFaultContract faultInfo) {
		super(message);
		this.faultInfo = faultInfo;
	}

	/**
	 * 
	 * @param message
	 * @param faultInfo
	 * @param cause
	 */
	public DpoServiceGetPofWithKeyConfigurationFaultContractFaultFaultMessage(
			String message, ConfigurationFaultContract faultInfo,
			Throwable cause) {
		super(message, cause);
		this.faultInfo = faultInfo;
	}

	/**
	 * 
	 * @return returns fault bean:
	 *         com.towerswatson.rto.dpo.types._2010._01.ConfigurationFaultContract
	 */
	public ConfigurationFaultContract getFaultInfo() {
		return faultInfo;
	}

}
