package util;

import org.apache.commons.validator.routines.UrlValidator;

public class URLUtil {

	private static UrlValidator validator;

	static {
		String[] schemes = { "http", "https" };
		validator = new UrlValidator(schemes);
	}

	/**
	 * This method receives a link from a page and returns only the domain
	 * of that link
	 *
	 * @author Andre Macedo
	 * @param link
	 * @return link's domain
	 */
	public static String extractDomainFromLink(String link) {
		String domain = null;

		int indexEndOfProtocol = link.indexOf("//") + 2;
		int indexOfEndDomain = link.indexOf("/", indexEndOfProtocol);
		if (indexOfEndDomain > indexEndOfProtocol)
			domain = link.substring(indexEndOfProtocol, indexOfEndDomain);
		else
			domain = link.substring(indexEndOfProtocol);

		return domain.contains("www.") ? domain.replace("www.", "") : domain;
	}

	public static boolean isUrlValid(String url) {
		return validator.isValid(url);
	}

}
