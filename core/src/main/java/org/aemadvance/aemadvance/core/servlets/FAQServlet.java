package org.aemadvance.aemadvance.core.servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.jcr.Node;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;

@Component(service = Servlet.class, property = {
		Constants.SERVICE_DESCRIPTION + "=Servlet for getting FAQ data based on Category",
		"sling.servlet.methods=" + HttpConstants.METHOD_POST, "sling.servlet.paths=" + "/bin/faqServlet" })

/**
 * @author Akash Rajput
 *
 */
public class FAQServlet extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(FAQServlet.class);

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		try {
			String faqData = "";
			String faqCategory = request.getParameter("faqCategory");

			Resource rootPageResource = request.getResourceResolver().getResource("/content/aemadvance/en/faqs");
			if (rootPageResource != null) {
				Page rootPage = rootPageResource.adaptTo(Page.class);
				if (rootPage != null) {
					Iterator<Page> childPages = rootPage.listChildren();
					while (childPages.hasNext()) {
						Page currentPage = childPages.next();
						Resource currentPageResource = request.getResourceResolver().getResource(currentPage.getPath());

						if (currentPageResource != null) {
							Node currentPageNode = currentPageResource.adaptTo(Node.class);

							if (currentPageNode != null && currentPageNode.getNode("jcr:content")
									.getProperty("cq:template").getValue().getString()
									.equals("/conf/aemadvance/settings/wcm/templates/faq-page-template")) {

								Node faqNodeRoot = currentPageNode.getNode("jcr:content/root");
								Iterator<Node> faqNodes = faqNodeRoot.getNodes();

								while (faqNodes.hasNext()) {
									Node node = faqNodes.next();
									if (node.getProperty("category").getValue().getString().equals(faqCategory)) {
										faqData += "<li><p><b>" + node.getProperty("question").getValue().getString()
												+ "</b></p><p>" + node.getProperty("answer").getValue().getString()
												+ "</p></li>";

									}

								}

							}

						}

					}
					response.getWriter().write(faqData);
				}
			}

		} catch (Exception e) {
			logger.error("{0} {1}", "error", e);
		}
	}
}
