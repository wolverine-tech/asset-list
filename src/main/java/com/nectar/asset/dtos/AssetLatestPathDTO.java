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
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AssetLatestPathDTO extends AssetLatestDTO {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private List<PathDTO> path;

	public List<PathDTO> getPath() {

		List<PathDTO> paths = new ArrayList<PathDTO>();

		ObjectMapper mapper = new ObjectMapper();
		String thingTagpath = this.thingTagPath;
		if (StringUtils.isNotBlank(thingTagpath)) {
			try {
				List<SourceTagPathDTO> sourceTagList = mapper.readValue(this.thingTagPath,
						new TypeReference<List<SourceTagPathDTO>>() {
						});

				Optional<SourceTagPathDTO> community = sourceTagList.stream()
						.filter(p -> p.getParentType().equalsIgnoreCase("Community")).findFirst();
				getPathDTO(community, paths);
				Optional<SourceTagPathDTO> subCommunity = sourceTagList.stream()
						.filter(p -> p.getParentType().equalsIgnoreCase("SiteGroup")).findFirst();
				getPathDTO(subCommunity, paths);
				Optional<SourceTagPathDTO> site = sourceTagList.stream()
						.filter(p -> p.getParentType().equalsIgnoreCase("Site")).findFirst();
				getPathDTO(site, paths);
				List<SourceTagPathDTO> spaces = sourceTagList.stream()
						.filter(p -> p.getParentType().equalsIgnoreCase("Space")).collect(Collectors.toList());
				getMultiplePathDTO(spaces, paths);

				Optional<SourceTagPathDTO> equipment = sourceTagList.stream()
						.filter(p -> p.getParentType().equalsIgnoreCase("Equipment")).findFirst();
				getPathDTO(equipment, paths);

			} catch (IOException e) {
				LOGGER.error("Error while data conersion {} ", this.thingTagPath, e);
			}
		}
		this.path = paths;
		this.thingTagPath = null;
		return paths;
	}

	private List<PathDTO> getPathDTO(Optional<SourceTagPathDTO> entity, List<PathDTO> pathList) {
		if (entity.isPresent()) {
			RoyalJellyDTO equipData = new RoyalJellyDTO();
			HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("domain", entity.get().getDomain());
			data.put("name", entity.get().getName());
			data.put("identifier", entity.get().getTopic());
			data.put("parentType", entity.get().getParentType());
			equipData.setType(entity.get().getType());
			equipData.setData(data);
			PathDTO path5 = new PathDTO();
			path5.setName(entity.get().getName());
			path5.setEntity(equipData);
			pathList.add(path5);
		}
		return pathList;

	}

	private List<PathDTO> getMultiplePathDTO(List<SourceTagPathDTO> entity, List<PathDTO> pathList) {

		if (!CollectionUtils.isEmpty(entity)) {

			for (SourceTagPathDTO space : entity) {

				RoyalJellyDTO equipData = new RoyalJellyDTO();
				HashMap<String, Object> data = new HashMap<String, Object>();
				data.put("domain", space.getDomain());
				data.put("name", space.getName());
				data.put("identifier", space.getTopic());
				data.put("parentType", space.getParentType());
				equipData.setType(space.getType());
				equipData.setData(data);
				PathDTO path5 = new PathDTO();
				path5.setName(space.getName());
				path5.setEntity(equipData);
				pathList.add(path5);
			}
		}

		return pathList;

	}

}
