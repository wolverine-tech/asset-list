package com.nectar.asset.ServiceImpl;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import com.nectar.honeycomb.solr.db.util.SolrUtil;
import com.nectar.thing.beans.ThingDataBean;


import static com.nectar.asset.dtos.SolrConstants.TYPE;
import static com.nectar.asset.dtos.SolrConstants.DATA_TIME;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service

public class AssetServiceImpl {
	
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Value("${honeycomb.asset.latest.solr.collection}")
	private String solrCollecton;

	@Value("${honeycomb.asset.solr.latest.fields}")
	private String assetLatestsolrFields;

	@Value("${honeycomb.asset.solr.rowCounts}")
	private Integer maxRows;
	
	
	
	public List<ThingDataBean> getAssetLatest(String path,String type) {
		Map<String, String> queryFields = new HashMap<String, String>();
		Map<String, String> filterFields = new HashMap<String, String>();

		SolrQuery solrQuery = new SolrQuery();

		List<String> sources = new ArrayList<String>();
		sources.add(type);

		setQueryFields(TYPE, sources, queryFields);
		solrQuery = setSolrQuery(queryFields, filterFields, null, null, null, maxRows, DATA_TIME, null,
				assetLatestsolrFields);


		return getAssetLatestResponse(solrQuery);
	}
	
	
	
	private Map<String, String> setQueryFields(String field, List<String> sources, Map<String, String> queryFields) {

		if (!CollectionUtils.isEmpty(sources)) {

			queryFields.put(field, field + sources.stream().collect(Collectors.joining("\"\"", ":(\"", "\")")));
		}
		return queryFields;

	}

	private Map<String, String> setQueryFields(String field, String source, Map<String, String> queryFields) {

		if (StringUtils.isNotEmpty(source)) {

			queryFields.put(field, field + ":\"" + source + "\"");
		}
		return queryFields;

	}
	
	
	private SolrQuery setSolrQuery(Map<String, String> queryFields, Map<String, String> filterFields,
			Map<String, String> paramFields, List<String> facetQuery, Integer start, Integer end, String sortField,
			ORDER sortOrder, String solrFields) {

		SolrQuery solrQuery = new SolrQuery();

		solrQuery.setFields(solrFields);
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

		if (null != facetQuery && facetQuery.size() > 0) {
			for (String query : facetQuery) {
				solrQuery.addFacetQuery(query);
			}
		}
		if (null != start) {
			solrQuery.setStart(start);
		} else {
			solrQuery.setStart(0);
		}
		if (null != end) {
			solrQuery.setRows(end);
		} else {
			solrQuery.setRows(maxRows);
		}
		if (null != sortOrder) {
			solrQuery.setSort(sortField, sortOrder);
		} else {
			solrQuery.setSort(sortField, ORDER.desc);
		}
		if (paramFields != null)
			for (String key : paramFields.keySet()) {
				solrQuery.setParam(key, paramFields.get(key));
			}
		return solrQuery;

	}
	
	public List<ThingDataBean> getAssetLatestResponse(SolrQuery solrQuery) {
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
