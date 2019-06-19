package org.aemadvance.aemadvance.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "OSGi Service Configuration", description = "Service Configuration")

/**
 * @author Akash Rajput
 *
 */
public @interface OSGIConfiguration {

	@AttributeDefinition(name = "Email Template for Email Notification After Upload", description = "Email Template for Email Notification After Upload")
	String emailNotificationValue();

	@AttributeDefinition(name = "TechAspect Login Page Path", description = "TechAspect Login Page Path")
	String loginPathValue();

	@AttributeDefinition(name = "Email Template for Localization", description = "Email Template for Localization")
	String emailLocalizationValue();

	@AttributeDefinition(name = "Email Template after Uploading Localized Assets", description = "Email Template after Uploading Localized Assets")
	String emailLocalizedAssetsValue();

	@AttributeDefinition(name = "Email Template after failed Localization Upload", description = "Email Template after failed Localization Upload")
	String emailFailedLocalizationUploadValue();

	@AttributeDefinition(name = "Significant TechAspect metadata fields", description = "Significant TechAspect metadata fields")
	String[] techAspectMetadataFieldsValue();

	@AttributeDefinition(name = "Master Assets metadata fields", description = "Master Assets metadata fields")
	String[] masterMetadataFieldsValue();

	@AttributeDefinition(name = "TechAspect filter path", description = "TechAspect filter path")
	String filterPathValue();

}