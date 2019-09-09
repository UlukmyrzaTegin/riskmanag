package rts.service;

import java.util.Locale;
import java.util.ResourceBundle;

public class JaxWsProxyFactoryBeanAddress {

	private static ResourceBundle bundle = ResourceBundle.getBundle("data.JaxWsProxyFactoryBeanAddress");
	
	public static String getAddress() {
		return bundle.getString("address");
	}
}
