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
public class MultifieldComponentBean {

	@ValueMapValue
	private String linkText;
	@ValueMapValue
	private String linkURL;

	public String getLinkText() {
		return linkText;
	}

	public String getLinkURL() {
		return linkURL;
	}

}
