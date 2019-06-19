package org.aemadvance.aemadvance.core.models;

import java.util.List;

import org.aemadvance.aemadvance.core.beans.MultifieldComponentBean;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

/**
 * @author Akash Rajput
 *
 */
public class MultifieldComponentModel {
	@ChildResource
	private List<MultifieldComponentBean> multifieldcomponent;

	public List<MultifieldComponentBean> getMultifieldcomponent() {
		return multifieldcomponent;
	}

}
