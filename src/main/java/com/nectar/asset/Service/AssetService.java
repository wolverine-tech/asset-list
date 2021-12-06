package com.nectar.asset.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nectar.honeycomb.solr.db.util.SolrUtil;
import com.nectar.thing.beans.ThingDataBean;

@Service
public class AssetService {

	public final String SEARCH_TAG_IDS = "searchTagIds";
	public final String TYPE = "type";

	private Logger LOGGER = LoggerFactory.getLogger(AssetService.class);

	@Value("${honeycomb.asset.latest.solr.collection}")
	private String solrCollecton;

	@Value("${honeycomb.asset.solr.latest.fields}")
	private String assetLatestsolrFields;

	@Autowired
	SolrUtil solrUtil;

	public List<ThingDataBean> getAssetLatest(List<String> path, List<String> type) {
		Map<String, String> queryFields = new HashMap<String, String>();
		Map<String, String> filterFields = new HashMap<String, String>();
		setFilterFieldsAndQueryFields(SEARCH_TAG_IDS, path, queryFields);
		setFilterFieldsAndQueryFields(TYPE, type, filterFields);
		SolrQuery solrQuery = new SolrQuery();

		solrQuery = setSolrQuery(queryFields, filterFields);

		return getAssetLatestResponse(solrQuery);
	}

	private Map<String, String> setFilterFieldsAndQueryFields(String field, List<String> sources, Map<String, String> filterFieldsOrqueryFields) {

		if (!CollectionUtils.isEmpty(sources)) {

			filterFieldsOrqueryFields.put(field, field + sources.stream().collect(Collectors.joining("\"\"", ":(\"", "\")")));
		}
		return filterFieldsOrqueryFields;

	}


	private SolrQuery setSolrQuery(Map<String, String> queryFields, Map<String, String> filterFields) {

		SolrQuery solrQuery = new SolrQuery();

		solrQuery.setFields(assetLatestsolrFields);
		if (null != queryFields) {

			for (String key : queryFields.keySet()) {
				solrQuery.setQuery(queryFields.get(key));
			}
		} else {
			solrQuery.setQuery(":*:");
		}
		if (null != filterFields) {
			for (String key : filterFields.keySet()) {
				solrQuery.addFilterQuery(filterFields.get(key));
			}
		}
		solrQuery.setStart(0);
		solrQuery.setRows(Integer.MAX_VALUE);

		return solrQuery;

	}

	public List<ThingDataBean> getAssetLatestResponse(SolrQuery solrQuery) {
		LOGGER.debug("solr query :{}", solrQuery.toString());
		QueryResponse response = solrUtil.querySolr(solrCollecton, solrQuery);
		List<ThingDataBean> assetLatest = new ArrayList<>();
		if (response.getResults().size() > 0) {
			Gson gson = new Gson();
			String json = gson.toJson(response.getResults());
			try {
				assetLatest = new ObjectMapper().readValue(json, new TypeReference<List<ThingDataBean>>() {
				});
			} catch (Exception e) {
				LOGGER.error("Error in mapping");
			}
		}

		else {
			LOGGER.warn("No response forund from solr");
		}
		return assetLatest;

	}

}
