/*******************************************************************************
 * Copyright (c) 2021 Nectar IT
 *
 * Contributors:
 *
 * 1. brucedotbanner
 * 2. titanthanos
 * 3. natasharomanoff
 *    
 * Since Dec 2020  
 *******************************************************************************/


package com.nectar.asset.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PointLatestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String unit;
	private String unitSymbol;
	private Object data;
	private String pointName;
	private String pointId;
	private String dataType;
	private String displayName;
	private String type;
	private String status;
	private String pointAccessType;
	private String physicalQuantity;
	private String precedence;
	private String expression;
	private String thingName;
	private String thingType;
	private String thingDomain;
	private String thingIdentifier;

	public String getThingName() {
		return thingName;
	}

	public void setThingName(String thingName) {
		this.thingName = thingName;
	}

	public String getThingType() {
		return thingType;
	}

	public void setThingType(String thingType) {
		this.thingType = thingType;
	}

	public String getThingDomain() {
		return thingDomain;
	}

	public void setThingDomain(String thingDomain) {
		this.thingDomain = thingDomain;
	}

	public String getThingIdentifier() {
		return thingIdentifier;
	}

	public void setThingIdentifier(String thingIdentifier) {
		this.thingIdentifier = thingIdentifier;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	public String getPointId() {
		return pointId;
	}

	public void setPointId(String pointId) {
		this.pointId = pointId;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPointAccessType() {
		return pointAccessType;
	}

	public void setPointAccessType(String pointAccessType) {
		this.pointAccessType = pointAccessType;
	}

	public String getPhysicalQuantity() {
		return physicalQuantity;
	}

	public void setPhysicalQuantity(String physicalQuantity) {
		this.physicalQuantity = physicalQuantity;
	}

	public String getPrecedence() {
		return precedence;
	}

	public void setPrecedence(String precedence) {
		this.precedence = precedence;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getUnitSymbol() {
		return unitSymbol;
	}

	public void setUnitSymbol(String unitSymbol) {
		this.unitSymbol = unitSymbol;
	}

}
