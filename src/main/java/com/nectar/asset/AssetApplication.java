package com.nectar.asset;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.nectar.asset.ServiceImpl.AssetServiceImpl;

@SpringBootApplication
@ComponentScan({ "com.nectar" })
public class AssetApplication implements CommandLineRunner{
	
	@Autowired
	AssetServiceImpl assetServiceImpl;
	
	public static void main(String[] args) {
		SpringApplication.run(AssetApplication.class, args);

	}
	
	
	
	@Override
	public void run(String... args) throws Exception {
		
		assetServiceImpl.getAssetLatest(Collections.singletonList("Emaar Square Building 3"),Collections.singletonList("FireElectricalPump"));
	}

}
