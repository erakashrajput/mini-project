package org.aemadvance.aemadvance.core.beans;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

/**
 * @author Akash Rajput
 *
 */
public class ContactUsBean {

	@ValueMapValue
	private String emails;
	@ValueMapValue
	private String name;
	@ValueMapValue
	private String number;

	public String getEmails() {
		return emails;
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

}
