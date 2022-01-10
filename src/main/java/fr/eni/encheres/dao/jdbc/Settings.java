package fr.eni.encheres.dao.jdbc;


import java.util.Properties;

/**
 * Cette classe charge le fichier de conf settings.properties
 * et permet de récupérer les valeurs contenues dedans
 * @author jperrinet
 *
 */
public class Settings {

	private static Properties properties;
	
	/*
	 * bloc exécuté la 1ere fois (et uniquement la 1ere fois) que l'on appelle la classe Settings
	 * (soit par instanciation, soit par m�thode static, soit variable/constante static)
	 */
	static {
		try {
			properties = new Properties();
			properties.load(Settings.class.getResourceAsStream("settings.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}






