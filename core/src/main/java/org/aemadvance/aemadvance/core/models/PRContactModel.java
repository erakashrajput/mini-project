package org.aemadvance.aemadvance.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.Node;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Source;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class)

/**
 * @author Akash Rajput
 *
 */
public class PRContactModel {

	@Inject
	@Source("sling-object")
	private ResourceResolver resourceResolver;

	@ValueMapValue
	private String prContact;

	public String getPrContact() {
		return prContact;
	}

	public String company;
	public String name;

	public String getCompany() {
		return "Hello" + company;
	}

	public String title;
	public String phone;
	public String fax;
	public String email;
	public String alternateEmail;

	@PostConstruct
	public void init() {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		try {
			Resource currentPageResource = resourceResolver.getResource(prContact);
			if (currentPageResource != null) {
				Node currentPageNode = currentPageResource.adaptTo(Node.class);
				if (currentPageNode != null) {

					Node prContactNode = currentPageNode.getNode("jcr:content");

					company = prContactNode.getProperty("company").getValue().getString();
					name = prContactNode.getProperty("name").getValue().getString();
					title = prContactNode.getProperty("title").getValue().getString();
					phone = prContactNode.getProperty("phone").getValue().getString();
					fax = prContactNode.getProperty("fax").getValue().getString();
					email = prContactNode.getProperty("email").getValue().getString();

					alternateEmail = prContactNode.getProperty("alternateEmail").getValue().getString() != null
							? prContactNode.getProperty("alternateEmail").getValue().getString() : null;

				}

			}

		} catch (Exception e) {

			logger.error("{0} {1}", "error", e);

		}
	}
}
