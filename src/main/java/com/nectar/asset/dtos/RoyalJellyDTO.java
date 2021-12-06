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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "entity")
public class RoyalJellyDTO implements Serializable {

	private static final long serialVersionUID = -2049990530877806985L;

	private String entity;

	private String type;

	private Map<String, Object> data;

	public String getDomain() {
		if (data == null) {
			return null;
		} else {
			Object domain = data.get("domain");
			if (Objects.nonNull(domain)) {
				return data.get("domain").toString();
			} else {
				return null;
			}
		}
	}

	public void setDomain(String domain) {
		if (data == null) {
			data = new HashMap<String, Object>();
		}
		data.put("domain", domain);
	}

	public String getStatus() {
		if (data == null) {
			return null;
		} else {
			Object status = data.get("status");
			if (Objects.nonNull(status)) {
				return data.get("status").toString();
			} else {
				return null;
			}
		}
	}

	public void setStatus(String status) {
		if (data == null) {
			data = new HashMap<String, Object>();
		}
		data.put("status", status);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public String getIdentifier() {
		if (data == null) {
			return null;
		} else {
			Object identifier = data.get("identifier");
			if (Objects.nonNull(identifier)) {
				return data.get("identifier").toString();
			} else {
				return null;
			}
		}
	}

	public void setIdentifier(String identifier) {
		if (data == null) {
			data = new HashMap<String, Object>();
		}
		data.put("identifier", identifier);
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public Object getValue(String key) {
		if (data == null) {
			return null;
		} else {
			return data.get(key);
		}
	}

	public String getStringValue(String key) {
		if (data == null) {
			return null;
		} else {
			Object status = data.get(key);
			if (Objects.nonNull(status)) {
				return data.get(key).toString();
			} else {
				return null;
			}
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getIdentifier() == null) ? 0 : getIdentifier().hashCode());
		result = prime * result + ((getDomain() == null) ? 0 : getDomain().hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoyalJellyDTO other = (RoyalJellyDTO) obj;
		if (getIdentifier() == null) {
			if (other.getIdentifier() != null)
				return false;
		} else if (!getIdentifier().equals(other.getIdentifier()))
			return false;
		if (getDomain() == null) {
			if (other.getDomain() != null)
				return false;
		} else if (!getDomain().equals(other.getDomain()))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
