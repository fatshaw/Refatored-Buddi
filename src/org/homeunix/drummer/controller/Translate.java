/*
 * Created on May 8, 2006 by wyatt
 */
package org.homeunix.drummer.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.homeunix.drummer.Buddi;
import org.homeunix.drummer.Const;
import org.homeunix.thecave.moss.jar.JarLoader;
import org.homeunix.thecave.moss.util.Log;


public class Translate {
	private final Properties translations = new Properties();
	
	/**
	 * Returns a singleton instance of the Translate class.
	 * @return
	 */
	public static Translate getInstance() {
		return SingletonHolder.instance;
	}
	
	private static class SingletonHolder {
		private static Translate instance = new Translate();		
	}
	
	private Translate(){}
	
	/**
	 * We try to load up to three different language files, in the following order:
	 * <ol>
	 * <li>English.lang - this is the base languauge, and if a term is not 
	 * 		defined elsewhere, we default to the values here.</li>
	 * <li>Specified language, without locale.  For instance, if you try
	 * 		to load Espanol_(MX), we first try to load Espanol.  This
	 * 		is to allow localizations to draw on a base translation in 
	 * 		the same language.</li>
	 * <li>The specified language, with locale.</li>
	 * <p>
	 * This gives us extreme flexibility and maintainability when 
	 * creating locales - to change a single term, you only have to 
	 * create a .lang file with that one term changed.
	 * 
	 * @param language
	 * @return
	 */
	public Translate loadLanguage(String language){
		//English
		String englishFileName = Buddi.getWorkingDir() + File.separator + Const.LANGUAGE_FOLDER + File.separator + "English" + Const.LANGUAGE_EXTENSION;
		String englishResource = "/" + "English" + Const.LANGUAGE_EXTENSION;
		
		//Base Language (e.g., Espanol)
		String baseFileName = Buddi.getWorkingDir() + File.separator + Const.LANGUAGE_FOLDER + File.separator + language.replaceAll("_\\(.*\\)$", "") + Const.LANGUAGE_EXTENSION;
		String baseResource = "/" + language.replaceAll("_\\(.*\\)$", "") + Const.LANGUAGE_EXTENSION;

		//Localized Language (e.g., Espanol_(MX))
		String localizedFileName = Buddi.getWorkingDir() + File.separator + Const.LANGUAGE_FOLDER + File.separator + language + Const.LANGUAGE_EXTENSION;
		String localizedResource = "/" + language + Const.LANGUAGE_EXTENSION;
		
		try{
			if (Const.DEVEL) Log.info("Trying to load language: " + localizedFileName);

			//Set up the files
			File englishFile, baseFile, localizedFile;
			englishFile = new File(englishFileName);
			baseFile = new File(baseFileName);
			localizedFile = new File(localizedFileName);
			
			//Load English
			if (englishFile.exists()){
				if (new BufferedInputStream(new FileInputStream(englishFile)) != null)
					translations.load(new BufferedInputStream(new FileInputStream(englishFile)));
			}
			else {
				if (this.getClass().getResourceAsStream(englishResource) != null)
					translations.load(this.getClass().getResourceAsStream(englishResource));
			}
			
			//Load Base Language
			if (baseFile.exists()){
				if (new BufferedInputStream(new FileInputStream(baseFile)) != null)
					translations.load(new BufferedInputStream(new FileInputStream(baseFile)));
			}
			else {
				if (this.getClass().getResourceAsStream(baseResource) != null)
					translations.load(this.getClass().getResourceAsStream(baseResource));
			}

			//Load Localized Language
			if (localizedFile.exists()){
				if (new BufferedInputStream(new FileInputStream(localizedFile)) != null)
					translations.load(new BufferedInputStream(new FileInputStream(localizedFile)));
			}
			else {
				if (this.getClass().getResourceAsStream(localizedResource) != null)
					translations.load(this.getClass().getResourceAsStream(localizedResource));
			}

		}
		catch(IOException ioe){
			JOptionPane.showMessageDialog(
					null, 
					"Error loading language file.  Please check that\nyour Languages directory exists, and contains at least one language file.",
					"Error Loading Language File",
					JOptionPane.ERROR_MESSAGE
			);
		}
		
		//Set the locale
		String localeLanguage = translations.getProperty(TranslateKeys.LOCALE_LANGUAGE_CODE.toString());
		String localeCountry = translations.getProperty(TranslateKeys.LOCALE_COUNTRY_CODE.toString());
		
		if (localeLanguage == null) localeLanguage = "";
		if (localeCountry == null) localeCountry = "";
		Locale.setDefault(new Locale(localeLanguage, localeCountry));
		
		return this;
	}
	
	public Translate loadPluginLanguages(File jarFile, String language){
		//English
		String englishResource = "/" + "English" + Const.LANGUAGE_EXTENSION;
		
		//Base Language (e.g., Espanol)
		String baseResource = "/" + language.replaceAll("_\\(.*\\)$", "") + Const.LANGUAGE_EXTENSION;

		//Localized Language (e.g., Espanol_(MX))
		String localizedResource = "/" + language + Const.LANGUAGE_EXTENSION;
		
		try{
			if (Const.DEVEL) Log.info("Trying to load language: " + localizedResource);

			//Load English
			if (JarLoader.getResourceAsStream(jarFile, englishResource) != null)
				translations.load(JarLoader.getResourceAsStream(jarFile, englishResource));
			else
				if (Const.DEVEL) Log.info("Problem loading " + englishResource + " from " + jarFile.getAbsolutePath());
			
			//Load Base Language
			if (JarLoader.getResourceAsStream(jarFile, baseResource) != null)
				translations.load(JarLoader.getResourceAsStream(jarFile, baseResource));
			else
				if (Const.DEVEL) Log.info("Problem loading " + baseResource + " from " + jarFile.getAbsolutePath());

			//Load Localized Language
			if (JarLoader.getResourceAsStream(jarFile, localizedResource) != null)
				translations.load(JarLoader.getResourceAsStream(jarFile, localizedResource));
			else
				if (Const.DEVEL) Log.info("Problem loading " + localizedResource + " from " + jarFile.getAbsolutePath());
		}
		catch(IOException ioe){
			JOptionPane.showMessageDialog(
					null, 
					"Error loading language from Jar.",
					"Error Loading Language File",
					JOptionPane.ERROR_MESSAGE
			);
		}
		
		//Yes, I know that this is slow, but we want the user-defined 
		// languages to override the plugin languages, to give users 
		// the ability to change the strings for the plugins as well.  
		// If we always load the base languages (which includes the 
		// user-modified languages from Languages folder as well), 
		// then we are guaranteed to always have this happen.
		loadLanguage(language);
		
		return this;
	}

	/**
	 * Returns the translation, based on the given string.
	 * @param key The key to translate
	 * @return The translation in currently loaded language
	 */
	public String get(String key){
		if (key == null){
			Log.warning("Null translation key: " + key);
			return key;
		}
		String ret = translations.getProperty(key);
		if (ret == null)
			return key;
		
		return ret;
	}
	
	/**
	 * Returns the translation, based on the given TranslateKey.
	 * @param key The key to translate
	 * @return The translation in currently loaded language
	 */
	public String get(TranslateKeys key){
		String ret = translations.getProperty(key.toString());
		if (ret == null)
			return key.toString();
		return ret;
	}
//	
//	public Set<String> getUnusedKeys() {
//		existingKeys.removeAll(usedKeys);
//		
//		return existingKeys;
//	}
}
