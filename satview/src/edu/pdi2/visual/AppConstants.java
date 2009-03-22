package edu.pdi2.visual;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class AppConstants {
	private static final String BUNDLE_NAME = "edu.pdi2.visual.constants"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private AppConstants() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
