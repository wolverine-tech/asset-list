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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.beans.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AssetLatestDTO {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	private  ObjectMapper mapper =new ObjectMapper();
	@Field("identifier")
	private String identifier;
	@Field("clientDomain")
	private String clientDomain;
	@Field("clientName")
	private String clientName;
	@Field("domain")
	private String domain;
	@Field("id")
	private String id;
	@Field("name")
	private String name;
	@Field("displayName")
	private String displayName;
	@Field("make")
	private String make;
	@Field("model")
	private String model;
	@Field("type")
	private String type;
	@Field("operationStatus")
	private String operationStatus;
	@Field("communicationStatus")
	private String communicationStatus;
	@Field("criticalAlarm")
	private boolean criticalAlarm;
	@Field("lowAlarm")
	private boolean lowAlarm;
	@Field("mediumAlarm")
	private boolean mediumAlarm;
	@Field("highAlarm")
	private boolean highAlarm;
	@Field("warningAlarm")
	private boolean warningAlarm;
	private Set<String> category;
	@Field("serviceDue")
	private boolean serviceDue;
	@Field("documentExpire")
	private boolean documentExpire;
	private List<String> documentExpiryTypes;
	private Long lastCommunicated;
	@Field("createdOn")
	private Long createdOn;
	@Field("dataTime")
	private Long dataTime;
	private List<PointLatestDTO> points;
	@Field("location")
	private String location;
	private Object pointsJson;
	private String ownersJson;
	@Field("thingCode")
	private String thingCode;
	@Field("reason")
	private String reason;
	private boolean isOvertime;
	@Field("recent")
	private boolean recent;
	@Field("sourceId")
	private String sourceId;
	private List<OwnerDTO> owners;
	@Field("underMaintenance")
	private boolean underMaintenance;
	@Field("thingTagPath")
	protected String thingTagPath;
	@Field("annotations")
	private List<String> annotations;
	@Field("premiseNumber")
	private String premiseNumber;
	@Field("contractAccountNumber")
	private String contractAccountNumber;
	@Field("serialNumber")
	private String serialNumber;
	@Field("status")
	private String status;
	
	public List<String> getAnnotations() {
		return annotations;
	}
	public void setAnnotations(List<String> annotations) {
		this.annotations = annotations;
	}

	public void addAnnotation(String annotation) {
		if(annotation != null && annotation.trim().length()>0) {
			if(CollectionUtils.isEmpty(this.annotations)) {
				this.annotations = new ArrayList<String>();
			}
			this.annotations.add(annotation);
		}
	}

	public List<OwnerDTO> getOwners() {
		return owners;
	}

	public void setOwners(List<OwnerDTO> owners) {
		this.owners = owners;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getClientDomain() {
		return clientDomain;
	}

	public void setClientDomain(String clientDomain) {
		this.clientDomain = clientDomain;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}

	public String getCommunicationStatus() {
		return communicationStatus;
	}

	public void setCommunicationStatus(String communicationStatus) {
		this.communicationStatus = communicationStatus;
	}

	public boolean isCriticalAlarm() {
		return criticalAlarm;
	}

	public void setCriticalAlarm(boolean criticalAlarm) {
		this.criticalAlarm = criticalAlarm;
	}

	public boolean isLowAlarm() {
		return lowAlarm;
	}

	public void setLowAlarm(boolean lowAlarm) {
		this.lowAlarm = lowAlarm;
	}

	public boolean isMediumAlarm() {
		return mediumAlarm;
	}

	public void setMediumAlarm(boolean mediumAlarm) {
		this.mediumAlarm = mediumAlarm;
	}

	public boolean isHighAlarm() {
		return highAlarm;
	}

	public void setHighAlarm(boolean highAlarm) {
		this.highAlarm = highAlarm;
	}

	public boolean isWarningAlarm() {
		return warningAlarm;
	}

	public void setWarningAlarm(boolean warningAlarm) {
		this.warningAlarm = warningAlarm;
	}

	public Set<String> getCategory() {
		return category;
	}

	public void setCategory(Set<String> category) {
		this.category = category;
	}

	public boolean isServiceDue() {
		return serviceDue;
	}

	public void setServiceDue(boolean serviceDue) {
		this.serviceDue = serviceDue;
	}

	public boolean isDocumentExpire() {
		return documentExpire;
	}

	public void setDocumentExpire(boolean documentExpire) {
		this.documentExpire = documentExpire;
	}
	public List<String> getDocumentExpiryTypes() {
		return documentExpiryTypes;
	}

	public void setDocumentExpiryTypes(List<String> documentExpiryTypes) {
		this.documentExpiryTypes = documentExpiryTypes;
	}

	public Long getLastCommunicated() {
		return lastCommunicated;
	}

	public void setLastCommunicated(Long lastCommunicated) {
		this.lastCommunicated = lastCommunicated;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public Long getDataTime() {
		return dataTime;
	}

	public void setDataTime(Long dataTime) {
		this.dataTime = dataTime;
	}

	public List<PointLatestDTO> getPoints() {
		return points;
	}

	public void setPoints(List<PointLatestDTO> points) {
		this.points = points;
	}

	public String getOwnersJson() {
		return ownersJson;
	}

	public String getThingTagPath() {
		return thingTagPath;
	}

	public void setThingTagPath(String thingTagPath) {
		this.thingTagPath = thingTagPath;
	}

	public String getThingCode() {
		return thingCode;
	}

	public void setThingCode(String thingCode) {
		this.thingCode = thingCode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public boolean isOvertime() {
		return isOvertime;
	}

	public void setOvertime(boolean isOvertime) {
		this.isOvertime = isOvertime;
	}

	public boolean isRecent() {
		return recent;
	}

	public void setRecent(boolean recent) {
		this.recent = recent;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	
	@Field("pointsJson")
	public void setPointsJson(List<String> pointsJson) {

		try {
			if (!CollectionUtils.isEmpty(pointsJson)) {
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				this.points = mapper.readValue(pointsJson.get(0),
						new TypeReference<List<PointLatestDTO>>() {
						});
			} else {
				LOGGER.info("Collection empty:");
				this.points = null;
			}
		} catch (Exception e) {
			this.points = null;
			LOGGER.info("Erro converting {} ",e);
		}

	}
	@Field("ownersJson")
	public void setOwnersJson(String ownersJson) {

		try {
			if (StringUtils.isNotEmpty(ownersJson)) {
				this.owners = mapper.readValue(ownersJson, new TypeReference<List<OwnerDTO>>() {
				});
			} else {
				this.owners = null;
			}
		} catch (IOException e) {
			this.owners = null;
			e.printStackTrace();
		}

	}

	public Object getPointsJson() {
		return pointsJson;
	}


	public boolean isUnderMaintenance() {
		return underMaintenance;
	}

	public void setUnderMaintenance(boolean underMaintenance) {
		this.underMaintenance = underMaintenance;
	}
	
	public String getPremiseNumber() {
		return premiseNumber;
	}
	public void setPremiseNumber(String premiseNumber) {
		this.premiseNumber = premiseNumber;
	}
	public String getContractAccountNumber() {
		return contractAccountNumber;
	}
	public void setContractAccountNumber(String contractAccountNumber) {
		this.contractAccountNumber = contractAccountNumber;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

}
