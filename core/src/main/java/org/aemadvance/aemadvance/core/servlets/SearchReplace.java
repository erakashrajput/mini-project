package org.aemadvance.aemadvance.core.servlets;

import java.io.IOException;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
@Component(service = Servlet.class, property = {
		Constants.SERVICE_DESCRIPTION + "=Servlet for finding and replacing node values requested by user",
		"sling.servlet.methods=" + HttpConstants.METHOD_POST, "sling.servlet.paths=" + "/bin/searchReplace" })

/**
 * @author Akash Rajput
 *
 */
public class SearchReplace extends SlingAllMethodsServlet {

	private transient Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		ResourceResolver resourceResolver = request.getResourceResolver();
		String nodePath = request.getParameter("nodePath");
		String firstString = request.getParameter("firstString");
		String firstValue = request.getParameter("firstValue");
		String secondString = request.getParameter("secondString");
		String secondValue = request.getParameter("secondValue");
		String thirdString = request.getParameter("thirdString");
		String thirdValue = request.getParameter("thirdValue");

		Resource nodeResource = resourceResolver.getResource(nodePath);
		//Null check
		if (nodeResource != null) {
			Node currentNode = nodeResource.adaptTo(Node.class);
			if (currentNode != null) {
				try {
					// To check if the code is page or not.
					if (currentNode.getProperty("jcr:primaryType").getValue().getString().equals("cq:Page")) {
						
						Node pageContentNode = currentNode.getNode("jcr:content");
						pageContentNode.setProperty(firstString, firstValue);
						pageContentNode.setProperty(secondString, secondValue);
						pageContentNode.setProperty(thirdString, thirdValue);
						pageContentNode.getSession().save();
					} else {
						
						//Setting Properties
						currentNode.setProperty(firstString, firstValue);
						currentNode.setProperty(secondString, secondValue);
						currentNode.setProperty(thirdString, thirdValue);
						currentNode.getSession().save();
					}
				
					// Return the Replace Status
					response.getWriter().write("SUCCESS");

				} catch (Exception e) {
					log.error("{0} {1}", "error", e);

					// Return the Replace Status
					response.getWriter().write(e.toString());

				}

			}

		}

	}
}