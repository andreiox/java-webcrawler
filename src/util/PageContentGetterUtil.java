package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	public static List<String> getPageLinks(String url) throws IOException {

		Document doc = getPageDocument(url);
		Elements linkElements = extractPageLinks(doc);

		return createLinkList(linkElements);
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
	 * Create a link list from Elements
	 * 
	 * @author andre
	 * @param elements
	 * @return String List containing links
	 */
	private static List<String> createLinkList(Elements elements) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < elements.size(); i++) {
			String link = elements.get(i).attr("abs:href");
			if (URLUtil.isUrlValid(link))
				list.add(link);
		}

		return list;
	}

}
