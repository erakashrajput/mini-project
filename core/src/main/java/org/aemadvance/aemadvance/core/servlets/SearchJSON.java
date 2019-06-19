package org.aemadvance.aemadvance.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

@Component(service = Servlet.class, property = {
		Constants.SERVICE_DESCRIPTION + "= This servlet searches the jcr using query builder.",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/aemadvance/searchJSON" })

/**
 * @author Akash Rajput
 *
 */
public class SearchJSON extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SearchJSON.class);

	@Reference
	private transient QueryBuilder builder;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws IOException, ServletException {
		Session session;

		response.setContentType("application/json");
		String pathForSearch = request.getParameter("path");

		try {

			ResourceResolver resourceResolver = request.getResourceResolver();
			session = resourceResolver.adaptTo(Session.class);
			if (session != null) {

				String fulltextSearchTerm = request.getParameter("searchKey");
				// create query description as hash map
				Map<String, String> map = new HashMap<>();

				map.put("path", pathForSearch);
				map.put("type", "cq:Page");
				map.put("group.p.or", "true"); // combine this group with OR
				map.put("group.1_fulltext", fulltextSearchTerm);
				map.put("group.1_fulltext.relPath", "jcr:content");
				map.put("group.2_fulltext", fulltextSearchTerm);
				map.put("group.2_fulltext.relPath", "jcr:content/@cq:tags");
				map.put("group.3_fulltext", fulltextSearchTerm);
				map.put("group.3_fulltext.relPath", "jcr:title");

				// can be done in map or with Query methods
				map.put("p.offset", "0"); // same as query.setStart(0) below
				map.put("p.limit", "50"); // same as query.setHitsPerPage(20)
											// below

				Query query = builder.createQuery(PredicateGroup.create(map), session);
				query.setStart(0);
				query.setHitsPerPage(50);

				SearchResult result = query.getResult();
				long totalMatches = result.getTotalMatches();
				JsonArray resultsList = new JsonArray();
				// iterating over the results
				for (Hit hit : result.getHits()) {
					String path = hit.getPath();
					String title = hit.getTitle();
					JsonObject temp = new JsonObject();
					temp.addProperty("path", path);
					temp.addProperty("title", title);
					resultsList.add(temp);
				}
				JsonObject resultJsonObject = new JsonObject();
				resultJsonObject.addProperty("totalMatches", totalMatches);
				resultJsonObject.add("results", resultsList);
				String resultJsonData = resultJsonObject.toString();
				response.getWriter().write(resultJsonData);
				// close the session

				session.logout();
			}
		} catch (Exception e) {
			logger.error("Exception: ", e);
		}
	}
}
