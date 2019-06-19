package org.aemadvance.aemadvance.core.services;

/**
 * @author Akash Rajput
 *
 */
public interface OSGIService {
	String getLoginPathValue();

	String getEmailLocalizationValue();

	String getEmailLocalizedAssetsValue();

	String getEmailFailedLocalizationUploadValue();

	String[] getTechAspectMetadataFieldsValue();

	String[] getMasterMetadataFieldsValue();

	String getFilterPathValue();

}
