package util;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Andre Macedo
 *
 *         I know thats probably not a Unit Test but I'm new at it, still.
 */
public class PageContentGetterUtilTest {

	@Test
	public void getPageDocumentTest() throws IOException {
		String url = "http://www.ledr.com/colours/white.htm";
		List<String> pageLinks = PageContentGetterUtil.getPageLinks(url);

		Assert.assertEquals(pageLinks.get(0), "https://www.ledr.com/colours/multi.htm");
	}
}
