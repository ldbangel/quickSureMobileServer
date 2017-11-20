package com.towerswatson.rto.smf.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for DataType.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="DataType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Undefined"/>
 *     &lt;enumeration value="Int32"/>
 *     &lt;enumeration value="Float"/>
 *     &lt;enumeration value="String"/>
 *     &lt;enumeration value="Boolean"/>
 *     &lt;enumeration value="DateTime"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DataType")
@XmlEnum
public enum DataType {

	@XmlEnumValue("Undefined")
	UNDEFINED("Undefined"), @XmlEnumValue("Int32")
	INT_32("Int32"), @XmlEnumValue("Float")
	FLOAT("Float"), @XmlEnumValue("String")
	STRING("String"), @XmlEnumValue("Boolean")
	BOOLEAN("Boolean"), @XmlEnumValue("DateTime")
	DATE_TIME("DateTime");
	private final String value;

	DataType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static DataType fromValue(String v) {
		for (DataType c : DataType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}
