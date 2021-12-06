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

public class PathDTO {

	private String name;
	private RoyalJellyDTO entity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RoyalJellyDTO getEntity() {
		return entity;
	}

	public void setEntity(RoyalJellyDTO entity) {
		this.entity = entity;
	}

}
