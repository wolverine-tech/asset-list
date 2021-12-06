package com.nectar.asset.ServiceImpl;



import static com.nectar.asset.dtos.SolrConstants.TYPE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.nectar.honeycomb.solr.db.util.SolrUtil;
import com.nectar.thing.beans.ThingDataBean;

@Service

public class AssetServiceImpl {
	
	public static final String SEARCH_TAG_IDS = "searchTagIds";
	
	private static Logger LOGGER = LoggerFactory.getLogger(AssetServiceImpl.class);
	
	@Value("${honeycomb.asset.latest.solr.collection}")
	private static String solrCollecton;

	@Value("${honeycomb.asset.solr.latest.fields}")
	private static String assetLatestsolrFields;


	
	
	
	public static List<ThingDataBean> getAssetLatest(List<String> path,List<String> type) {
		Map<String, String> queryFields = new HashMap<String, String>();
		Map<String, String> filterFields = new HashMap<String, String>();
		setQueryFields(SEARCH_TAG_IDS, path, queryFields);
		setFilterFields(TYPE,type,filterFields);
		SolrQuery solrQuery = new SolrQuery();

		solrQuery = setSolrQuery(queryFields, filterFields);
		

		return getAssetLatestResponse(solrQuery);
	}
	
	private static Map<String, String> setFilterFields(String field, List<String> sources,
			Map<String, String> filterFields) {

		if (!CollectionUtils.isEmpty(sources)) {

			filterFields.put(field, field + sources.stream().collect(Collectors.joining("\"\"", ":(\"", "\")")));
		}
		return filterFields;

	}
	
	private static  Map<String, String> setQueryFields(String field, List<String> sources, Map<String, String> queryFields) {

		if (!CollectionUtils.isEmpty(sources)) {

			queryFields.put(field, field + sources.stream().collect(Collectors.joining("\"\"", ":(\"", "\")")));
		}
		return queryFields;

	}

	
	private static SolrQuery setSolrQuery(Map<String, String> queryFields, Map<String, String> filterFields) {

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
	
	public static List<ThingDataBean> getAssetLatestResponse(SolrQuery solrQuery) {
		LOGGER.debug("solr query :{}", solrQuery.toString());
		QueryResponse response = SolrUtil.querySolr(solrCollecton, solrQuery);
		List<ThingDataBean> assetLatest = null;
		if (response.getResults().size() > 0) {
			try {
				assetLatest = response.getBeans(ThingDataBean.class);
			} catch (Exception e) {
				LOGGER.error("Error in mapping {}", e);
				return null;
			}
		}

		else {
			LOGGER.warn("No response forund from solr");
		}
		return assetLatest;

	}

}
