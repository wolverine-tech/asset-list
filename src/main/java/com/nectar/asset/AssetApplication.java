package com.nectar.asset;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nectar.asset.ServiceImpl.AssetServiceImpl;
import com.nectar.thing.beans.ThingDataBean;

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
		
		List<ThingDataBean> thingdatabean = assetServiceImpl.getAssetLatest(Collections.singletonList("b3bbc402-33fb-4894-ae80-c6d865ed30c2"),Collections.singletonList("FireElectricalPump"));
		System.out.println(new ObjectMapper().writeValueAsString(thingdatabean));
	}

}
