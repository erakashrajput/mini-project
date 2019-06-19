package org.aemadvance.aemadvance.core.beans;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class)

/**
 * @author Akash Rajput
 *
 */
public class DynamicFieldsComponentBean {
	
	@ValueMapValue
	private String name;
	@ValueMapValue
	private String designation;
	@ValueMapValue
	private String profileImage;
	@ValueMapValue
	private String addressLineOne;
	@ValueMapValue
	private String addressLineTwo;
	@ValueMapValue
	private String city;
	@ValueMapValue
	private String state;
	@ValueMapValue
	private String countries;
	@ValueMapValue
	private String phoneNumber;
	@ValueMapValue
	private String faxNumber;

	public String getName() {
		return name;
	}

	public String getDesignation() {
		return designation;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public String getAddressLineOne() {
		return addressLineOne;
	}

	public String getAddressLineTwo() {
		return addressLineTwo;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountries() {
		return countries;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

}
