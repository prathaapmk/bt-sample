package com.bt.sample.core.servlets;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.day.cq.dam.api.Asset;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component(service = { Servlet.class },
property = {
		"sling.servlet.paths=/bin/getassets",
		"sling.servlet.methods=" +HttpConstants.METHOD_GET,
		"sling.servlet.extensions=json"
		})
public class GetDAMAssetsDataServlet extends SlingSafeMethodsServlet {

	@Reference
	QueryBuilder queryBuilder;
	
	 protected void doGet( SlingHttpServletRequest request,
	             SlingHttpServletResponse response) throws ServletException,
	            IOException {
		 
		 try {
		      response.setContentType("application/json");
		      response.setCharacterEncoding("UTF-8");
		      Writer writer = response.getWriter();
		  
		      final Resource resource = request.getResource();
				 
			 	List list = new ArrayList();
			 	
			 	Map<String, String> queryMap = new  HashMap<String,String>();
			 	
			 	queryMap.put("path","/content/dam/bt-sample-project");
			 queryMap.put("type","dam:Asset");
			 	queryMap.put("p.limit","-1");
			 //	queryMap.put("1_property", "dc:description");
			// 	queryMap.put("1_property.operation", "exists");
			 	ResourceResolver resourceResolver = request.getResourceResolver();
			 	Session session = resourceResolver.adaptTo(Session.class);
			 	Query query = queryBuilder.createQuery(PredicateGroup.create(queryMap), session);
			 	
			 	/*****
			 	 * 
			 	 *  Sample Example for SQL 2 Query
			 	 *  String queryString = "SELECT page.* FROM [cq:Page] AS page "
                    + "INNER JOIN [cq:PageContent] AS jcrContentNode ON ISCHILDNODE(jcrContentNode, page) "
                    + "WHERE ISDESCENDANTNODE(page, '/content/we-retail') "
                    + "AND jcrContentNode.[cq:lastModified] <= CAST('2023-01-01T00:00:00.000+00:00' AS DATE)";

			        // Create the query object
			        QueryManager queryManager = session.getWorkspace().getQueryManager();
			        Query query = queryManager.createQuery(queryString, Query.JCR_SQL2);
			
			        // Execute the query
			        QueryResult result = query.execute();
			 	 * 
			 	 * 
			 	 * 
			 	 */
			 	
			 	SearchResult searchresults = query.getResult();
			 	Map assetData = new HashMap();
			 	for (Hit hit : searchresults.getHits()) {
			        try {
			        	List allAssetProps = new ArrayList();
						String path = hit.getPath();
						Resource assetConvert = resourceResolver.getResource(path);
						Asset asset = assetConvert.adaptTo(Asset.class);
						String name = asset.getName();
						String description = asset.getMetadataValueFromJcr("dc:description") !=null?asset.getMetadataValueFromJcr("dc:description"):StringUtils.EMPTY;
						String title = asset.getMetadataValueFromJcr("dc:title") != null ?asset.getMetadataValueFromJcr("dc:title") :StringUtils.EMPTY;
						allAssetProps.add(name);
						allAssetProps.add(title);
						allAssetProps.add(description);
						assetData.put(path, allAssetProps);
						
					} catch (RepositoryException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 	}
			 	list.add(assetData);
		      String serialized = (new ObjectMapper()).writeValueAsString(list);
		     
		      writer.write(serialized);
		    } catch (IOException ioe) {
		    	
		    } 
		 
	    }
	
}
