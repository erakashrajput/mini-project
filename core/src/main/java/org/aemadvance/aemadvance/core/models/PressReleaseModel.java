package org.aemadvance.aemadvance.core.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.jcr.Node;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Source;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;

@Model(adaptables = Resource.class ,defaultInjectionStrategy= DefaultInjectionStrategy.OPTIONAL)

/**
 * @author Akash Rajput
 *
 */
public class PressReleaseModel {

	protected final  Logger logger = LoggerFactory.getLogger(this.getClass());
	@Inject
	@Source("sling-object")
	private ResourceResolver resourceResolver;

	@ValueMapValue
	private String parentNode;

	public String getParentNode() {
		return parentNode;
	}

	public List<Map<String, String>> getPressReleases() {
		List<Map<String, String>> pressReleaseList = new ArrayList<>();
		try {
			Resource rootPageResource = resourceResolver.getResource(parentNode);
			if (rootPageResource != null) {
				Page rootPage = rootPageResource.adaptTo(Page.class);
				if (rootPage != null) {
					Iterator<Page> childPages = rootPage.listChildren();
					while (childPages.hasNext()) {
						Map<String, String> map = new HashMap<>();
						Page currentPage = childPages.next();
						Resource currentPageResource = resourceResolver.getResource(currentPage.getPath());
						if (currentPageResource != null) {

							Node currentPageNode = currentPageResource.adaptTo(Node.class);
							if (currentPageNode != null) {
								Node pressReleaseNode = currentPageNode.getNode("jcr:content");
								map.put("pageTitle", pressReleaseNode.getProperty("jcr:title").getValue().getString());
								map.put("releaseDate", (new SimpleDateFormat("dd/MM/yyyy").format(pressReleaseNode
										.getProperty("pressReleaseDate").getValue().getDate().getTime())));
								map.put("pageURL", "http://localhost:4502" + currentPage.getPath() + ".html");

								pressReleaseList.add(map);

							}

						}

					}
				}
			}
		} catch (Exception e) {

			logger.error("{0} {1}", "error", e);
		}
		return pressReleaseList;
	}
}
