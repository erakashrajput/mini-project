package org.aemadvance.aemadvance.core.services;

import org.apache.sling.settings.SlingSettingsService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = OSGIService.class, configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = OSGIConfiguration.class)

/**
 * @author Akash Rajput
 *
 */
public class OSGIServiceImpl implements OSGIService {

	private OSGIConfiguration config;
	@Reference
	private SlingSettingsService settings;

	@Activate
	public void activate(OSGIConfiguration config) {
		this.config = config;

	}

	public String getEmailNotificationValue() {
		return config.emailNotificationValue();
	}

	public String getLoginPathValue() {
		return config.loginPathValue();
	}

	public String getEmailLocalizationValue() {
		return config.emailLocalizationValue();
	}

	public String getEmailLocalizedAssetsValue() {
		return config.emailLocalizedAssetsValue();
	}

	public String getEmailFailedLocalizationUploadValue() {
		return config.emailFailedLocalizationUploadValue();
	}

	public String[] getTechAspectMetadataFieldsValue() {
		return config.techAspectMetadataFieldsValue();
	}

	public String[] getMasterMetadataFieldsValue() {
		return config.masterMetadataFieldsValue();
	}

	public String getFilterPathValue() {
		return config.filterPathValue();
	}

}