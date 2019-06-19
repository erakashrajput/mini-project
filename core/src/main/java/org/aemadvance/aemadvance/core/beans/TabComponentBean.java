package org.aemadvance.aemadvance.core.beans;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class)

/**
 * @author Akash Rajput
 *
 */
public class TabComponentBean {

	@ValueMapValue
	private String heading;

	public String getHeading() {
		return heading;
	}

}
