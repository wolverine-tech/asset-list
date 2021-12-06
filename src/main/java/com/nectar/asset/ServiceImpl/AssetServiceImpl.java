package com.nectar.asset.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
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
public class AssetServiceImpl {

	public final String SEARCH_TAG_IDS = "searchTagIds";
	public final String TYPE = "type";

	private Logger LOGGER = LoggerFactory.getLogger(AssetServiceImpl.class);

	@Value("${honeycomb.asset.latest.solr.collection}")
	private String solrCollecton;

	@Value("${honeycomb.asset.solr.latest.fields}")
	private String assetLatestsolrFields;

	@Autowired
	SolrUtil solrUtil;

	public List<ThingDataBean> getAssetLatest(List<String> path, List<String> type) {
		Map<String, String> queryFields = new HashMap<String, String>();
		Map<String, String> filterFields = new HashMap<String, String>();
		setQueryFields(SEARCH_TAG_IDS, path, queryFields);
		setFilterFields(TYPE, type, filterFields);
		SolrQuery solrQuery = new SolrQuery();

		solrQuery = setSolrQuery(queryFields, filterFields);

		return getAssetLatestResponse(solrQuery);
	}

	private Map<String, String> setFilterFields(String field, List<String> sources, Map<String, String> filterFields) {

		if (!CollectionUtils.isEmpty(sources)) {

			filterFields.put(field, field + sources.stream().collect(Collectors.joining("\"\"", ":(\"", "\")")));
		}
		return filterFields;

	}

	private Map<String, String> setQueryFields(String field, List<String> sources, Map<String, String> queryFields) {

		if (!CollectionUtils.isEmpty(sources)) {

			queryFields.put(field, field + sources.stream().collect(Collectors.joining("\"\"", ":(\"", "\")")));
		}
		return queryFields;

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
		List<ThingDataBean> assetLatest = null;
		if (response.getResults().size() > 0) {
			Gson gson = new Gson();
			final SolrDocumentList documents = response.getResults();

			String json = gson.toJson(response.getResults());
		
			List<ThingDataBean> assetFilterList = new ArrayList<ThingDataBean>();
			try {
				assetFilterList = new ObjectMapper().readValue(json, new TypeReference<List<ThingDataBean>>() {
				});
			} catch (Exception e) {
				LOGGER.error("Error in mapping");
			}
		}

		else {
			LOGGER.warn("No response forund from solr");
		}
		return assetFilterList;

	}

}
