package control;

import java.io.IOException;
import java.util.List;

import persistence.Page;
import persistence.PageController;
import util.PageContentGetterUtil;

public class Crawler {

	// That's where we go when we run out of links
	private static final String SOURCE_OF_LINKS = "http://www.reddit.com/r/all/";
	// Max number of pages that we load from the database at each time
	private static final int NUMBER_OF_PAGES_LOADED_AT_EACH_TIME = 100;

	public static void main(String[] args) {
		crawl();
	}

	private static void crawl() {
		List<Page> list = loadPageList();
		for (Page p : list)
			crawlOnePage(p);

		crawl();
	}

	/**
	 * Crawl one page to find links
	 * 
	 * @author Andre Macedo
	 * @param p
	 * @throws IOException
	 */
	private static void crawlOnePage(Page p) {
		List<String> links = null;
		try {
			links = PageContentGetterUtil.getPageLinks(p.getPageUrl());
			runThroughLinks(p, links);

		} catch (IOException e) {
		}

		p.setVisited(true);
		PageController.save(p);
	}

	/**
	 * Run through links persisting it if link is "crawlable"
	 * 
	 * @author Andre Macedo
	 * @param p
	 * @param links
	 */
	private static void runThroughLinks(Page p, List<String> links) {
		for (String link : links) {
			Page pageFromLink = new Page(p.getGenerationNumber() + 1, link, false);
			if (checkIfNewPageIsCrawlable(p, pageFromLink))
				PageController.save(pageFromLink);
		}
	}

	/**
	 * Load page list to continue crawling
	 * 
	 * @author Andre Macedo
	 * @return
	 */
	private static List<Page> loadPageList() {
		List<Page> list = PageController.getNextPagesToCrawl(NUMBER_OF_PAGES_LOADED_AT_EACH_TIME);
		if (list.size() == 0) {
			Page defaultSource = new Page(PageController.getLastGenerationNumber() + 1, SOURCE_OF_LINKS, false);
			defaultSource = PageController.save(defaultSource);
			list.add(defaultSource);
		}

		return list;
	}

	/**
	 * Need to check if the link that was found is from the same domain. If
	 * it is, I ignore it because I dont wan't to upset some websites.
	 * 
	 * @author Andre Macedo
	 * @param p
	 * @param link
	 */
	private static boolean checkIfNewPageIsCrawlable(Page p, Page pageFromLink) {
		if (p.getDomain().equals(pageFromLink.getDomain()) || PageController.getPageFromURL(pageFromLink.getPageUrl()) != null)
			return false;

		return true;
	}
}
