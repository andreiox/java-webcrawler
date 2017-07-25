package persistence;

import org.junit.Assert;
import org.junit.Test;

public class PageTest {

	@Test
	public void CRUDTest() {
		Page page = new Page();
		page.setGenerationNumber(1);
		page.setPageUrl("https://github.com/andreiox/java-webcrawler");
		page.setVisited(false);

		page = PageController.save(page);
		Assert.assertEquals(page.getId() != 0, true);

		page = PageController.getById(page.getId());
		Assert.assertEquals(page.getId() != 0, true);

		page.setVisited(true);
		page = PageController.save(page);
		Assert.assertEquals(page.isVisited(), true);

		Assert.assertEquals(PageController.remove(page), true);
	}
}
