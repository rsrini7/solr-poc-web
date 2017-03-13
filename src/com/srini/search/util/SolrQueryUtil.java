package com.srini.search.util;

import org.apache.solr.client.solrj.SolrQuery;

public class SolrQueryUtil {

	public static void setStringPartialQuery(SolrQuery query, String key, String value) {
		if (value != null) {
			value = value.trim();
			if (!"".equals(value)) {
				query.setQuery(key + ":" + "*"+value+"*");
			}
		}
	}
	
	public static void setStringStartsWithQuery(SolrQuery query, String key, String value) {
		if (value != null) {
			value = value.trim();
			if (!"".equals(value)) {
				query.setQuery(key + ":" +value+"*");
			}
		}
	}
	
	public static void setStringEqualsQuery(SolrQuery query, String key, String value) {
		if (value != null) {
			value = value.trim();
			if (!"".equals(value)) {
				query.setQuery(key + ":" +value+"~0");
			}
		}
	}
	
	public static void setNumberQuery(SolrQuery query, String key, Number value) {
		if (value != null && value.longValue() > 0) {
			query.setQuery(key + ":" + value);
		}
	}

	public static void setQueryPage(SolrQuery query, Integer page, Integer pageSize) {
		if (page == null || page <= 0) {
			page = 1;
		}
		if (pageSize == null || pageSize <= 0) {
			pageSize = 10;
		}
		Integer start = (page - 1) * pageSize;
		query.setStart(start);
		query.setRows(pageSize);
	}
	
}
