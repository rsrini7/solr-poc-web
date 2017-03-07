package com.srini.search.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;

import com.srini.search.bean.ClientInfo;
import com.srini.search.bean.ClientInfoBean;
import com.srini.search.util.SolrClientFactory;
import com.srini.search.util.SolrClientUtil;
import com.srini.search.util.SolrQueryUtil;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String collection = "kyc";
		SolrClient solrClient = SolrClientFactory.getSolrClient();
		
		SolrClientUtil clientUtil = new SolrClientUtil(solrClient, collection);
		
		SolrQuery solrQuery = new SolrQuery();
		try{
			Map<ClientInfo, String> params = getParams(request,response);
			String queryType = request.getParameter("querytype");
			if(queryType == null || queryType.trim().equals("")){
				queryType = "P";
			}else{
				queryType = queryType.toUpperCase();
			}
			int startPos = 0;
			int endPos = 10;
			try{
				startPos = Integer.parseInt(request.getParameter("start"));
				endPos = Integer.parseInt(request.getParameter("end"));
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			SolrQueryUtil.setQueryPage(solrQuery, startPos, endPos);
			
			if(QueryType.S.name().equals(queryType)){
				SolrQueryUtil.setStringStartsWithQuery(solrQuery, ClientInfo.FIRST_NAME.getName(), params.get(ClientInfo.FIRST_NAME));
				SolrQueryUtil.setStringStartsWithQuery(solrQuery, ClientInfo.LAST_NAME.getName(), params.get(ClientInfo.LAST_NAME));
				SolrQueryUtil.setStringStartsWithQuery(solrQuery, ClientInfo.MIDDLE_NAME.getName(), params.get(ClientInfo.MIDDLE_NAME));
				SolrQueryUtil.setStringStartsWithQuery(solrQuery, ClientInfo.CLIENT_TYPE.getName(), params.get(ClientInfo.CLIENT_TYPE));
				SolrQueryUtil.setStringStartsWithQuery(solrQuery, ClientInfo.ENTITY_NAME.getName(), params.get(ClientInfo.ENTITY_NAME));
				SolrQueryUtil.setStringStartsWithQuery(solrQuery, ClientInfo.COUNTRY.getName(), params.get(ClientInfo.COUNTRY));
				SolrQueryUtil.setStringStartsWithQuery(solrQuery, ClientInfo.REGION.getName(), params.get(ClientInfo.REGION));
				SolrQueryUtil.setStringStartsWithQuery(solrQuery, ClientInfo.DOB.getName(), params.get(ClientInfo.DOB));
				SolrQueryUtil.setStringStartsWithQuery(solrQuery, ClientInfo.ACCOUNT_NO.getName(), params.get(ClientInfo.ACCOUNT_NO));
				SolrQueryUtil.setStringStartsWithQuery(solrQuery, ClientInfo.BUSINESS_ID.getName(), params.get(ClientInfo.BUSINESS_ID));
				SolrQueryUtil.setStringStartsWithQuery(solrQuery, ClientInfo.GOVT_ID.getName(), params.get(ClientInfo.GOVT_ID));
				SolrQueryUtil.setStringStartsWithQuery(solrQuery, ClientInfo.SOURCE.getName(), params.get(ClientInfo.SOURCE));
			}else {
				SolrQueryUtil.setStringPartialQuery(solrQuery, ClientInfo.FIRST_NAME.getName(), params.get(ClientInfo.FIRST_NAME));
				SolrQueryUtil.setStringPartialQuery(solrQuery, ClientInfo.LAST_NAME.getName(), params.get(ClientInfo.LAST_NAME));
				SolrQueryUtil.setStringPartialQuery(solrQuery, ClientInfo.MIDDLE_NAME.getName(), params.get(ClientInfo.MIDDLE_NAME));
				SolrQueryUtil.setStringPartialQuery(solrQuery, ClientInfo.CLIENT_TYPE.getName(), params.get(ClientInfo.CLIENT_TYPE));
				SolrQueryUtil.setStringPartialQuery(solrQuery, ClientInfo.ENTITY_NAME.getName(), params.get(ClientInfo.ENTITY_NAME));
				SolrQueryUtil.setStringPartialQuery(solrQuery, ClientInfo.COUNTRY.getName(), params.get(ClientInfo.COUNTRY));
				SolrQueryUtil.setStringPartialQuery(solrQuery, ClientInfo.REGION.getName(), params.get(ClientInfo.REGION));
				SolrQueryUtil.setStringPartialQuery(solrQuery, ClientInfo.DOB.getName(), params.get(ClientInfo.DOB));
				SolrQueryUtil.setStringPartialQuery(solrQuery, ClientInfo.ACCOUNT_NO.getName(), params.get(ClientInfo.ACCOUNT_NO));
				SolrQueryUtil.setStringPartialQuery(solrQuery, ClientInfo.BUSINESS_ID.getName(), params.get(ClientInfo.BUSINESS_ID));
				SolrQueryUtil.setStringPartialQuery(solrQuery, ClientInfo.GOVT_ID.getName(), params.get(ClientInfo.GOVT_ID));
				SolrQueryUtil.setStringPartialQuery(solrQuery, ClientInfo.SOURCE.getName(), params.get(ClientInfo.SOURCE));
			}
			
			Map<String, Object> query = clientUtil.query(solrQuery, ClientInfoBean.class);
			@SuppressWarnings("unchecked")
			List<ClientInfoBean> list = (List<ClientInfoBean>)query.get("datas");
			for(ClientInfoBean info : list){
				response.getWriter().append("data: ").append(info.toString()).append("\n");
			}

			request.setAttribute("result", list);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Map<ClientInfo,String> getParams(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<ClientInfo,String> paramMap = new HashMap<>();
		
		Map<String, String[]> parameterMap = request.getParameterMap();
		for(Map.Entry<String, String[]> entry : parameterMap.entrySet()){
			try{
				if(!entry.getKey().equals("start") && !entry.getKey().equals("end")  && !entry.getKey().equals("querytype")){
					paramMap.put(ClientInfo.valueOf(entry.getKey().toUpperCase()), entry.getValue()[0]);
				}
			}catch (IllegalArgumentException e) {
				response.getWriter().append("-------------\n").append("param key not available: ").append(e.getMessage()).append("\n").append("-------------").append("\n\n");
			}
		}
		return paramMap;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	enum QueryType{
		P,E,S
	}

}
