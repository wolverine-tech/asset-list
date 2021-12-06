package com.nectar.asset.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nectar.asset.ServiceImpl.AssetServiceImpl;
import com.nectar.asset.dtos.AssetLatestDTO;
import com.nectar.asset.dtos.RoyalJellyDTO;
import com.nectar.thing.beans.ThingDataBean;



@RestController
@RequestMapping(value = "/assets", produces = { MediaType.APPLICATION_JSON_VALUE })
public class AssetController {
	
	@Autowired
	AssetServiceImpl assetServiceImpl;

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@PostMapping(value = "details", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public List<ThingDataBean> fetchLatestAssetsDetailsSolr(@RequestParam String path,@RequestParam String type) {

		LOGGER.debug("******* started fetch asset latest details from solr  ********");
		List<ThingDataBean> response = assetServiceImpl.getAssetLatest(path,type);
		LOGGER.debug("******* completed fetch asset latest details from solr  ********");
		return response;
	}

}
