package org.aemadvance.aemadvance.core.models;

import java.util.List;

import org.aemadvance.aemadvance.core.beans.ContactUsBean;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

/**
 * @author Akash Rajput
 *
 */
public class ContactUsModel {

	@ChildResource
	private List<ContactUsBean> contactUs;

	public List<ContactUsBean> getContactUs() {
		return contactUs;
	}

}