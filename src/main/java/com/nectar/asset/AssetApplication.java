package com.nectar.asset;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nectar.asset.ServiceImpl.AssetServiceImpl;

@SpringBootApplication
public class AssetApplication {
	
//	@Autowired
//	AssetServiceImpl assetServiceImpl;
	
	public static void main(String[] args) {
		SpringApplication.run(AssetApplication.class, args);
		
		AssetServiceImpl assetServiceImpl = new AssetServiceImpl();
		
		assetServiceImpl.getAssetLatest(Collections.singletonList("Emaar Square Building 3"),Collections.singletonList("FireElectricalPump"));
	}

}
