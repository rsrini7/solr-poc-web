package com.srini.search.util;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

public class SolrClientFactory {
	private static String serverUrl = "http://localhost:8983/solr";
	private static SolrClient httpSolrClient;

	private SolrClientFactory() {
	}

	public static SolrClient getSolrClient() {
		if (httpSolrClient == null) {
			httpSolrClient = new HttpSolrClient(serverUrl);
		}
		return httpSolrClient;
	}
}
