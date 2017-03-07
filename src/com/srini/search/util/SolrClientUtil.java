package com.srini.search.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;

public class SolrClientUtil {

	private SolrClient solrClient = null;

	private String collection = null;

	public SolrClientUtil(SolrClient solrClient, String collection) {
		if (solrClient == null || collection == null) {
			throw new IllegalArgumentException("The solrClient or collection must not be null");
		}
		this.solrClient = solrClient;
		this.collection = collection;
	}


	public Map<String, Object> query(SolrQuery solrQuery, Class<?> beanClass) throws ServletException {
		Map<String, Object> result = new HashMap<String, Object>();
		List<?> list = null;
		try {
			QueryResponse queryResponse = solrClient.query(collection, solrQuery);
			list = queryResponse.getBeans(beanClass);
			result.put("numFound", queryResponse.getResults().getNumFound());
			result.put("datas", list);
		} catch (SolrServerException e) {
			e.printStackTrace();
			throw new ServletException("Unable to connect Solr");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	

	
}
