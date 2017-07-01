package util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class PageContentGetterUtil {

	/**
	 * Get all links from a page url
	 * 
	 * @author andre
	 * @param url
	 * @return String Array containing the links from the page
	 * @throws IOException
	 */
	public static String[] getPageLinks(String url) throws IOException {

		Document doc = getPageDocument(url);
		Elements linkElements = extractPageLinks(doc);

		return createLinkArray(linkElements);
	}

	/**
	 * Get object org.jsoup.nodes.Document from a Page URL
	 * 
	 * @author andre
	 * @param pageUrl
	 * @return a org.jsoup.nodes.Document object
	 * @throws IOException
	 */
	private static Document getPageDocument(String url) throws IOException {
		return Jsoup.connect(url).userAgent(UserAgentUtil.getRandomUserAgent()).timeout(10000).get();
	}

	/**
	 * Extract links from the Document as org.jsoup.select.Elements
	 * 
	 * @author andre
	 * @param document
	 * @return a org.jsoup.select.Elements of all links on the document
	 */
	private static Elements extractPageLinks(Document document) {
		return document.select("a[href]");
	}

	/**
	 * Create a link array from Elements
	 * 
	 * @author andre
	 * @param elements
	 * @return String array containing links
	 */
	private static String[] createLinkArray(Elements elements) {
		int length = elements.size();
		String links[] = new String[length];

		for (int i = 0; i < length; i++)
			links[i] = elements.get(i).attr("abs:href");

		return links;
	}

}
