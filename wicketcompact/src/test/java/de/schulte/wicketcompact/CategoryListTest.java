package de.schulte.wicketcompact;

import de.schulte.wicketcompact.categories.CategoriesPage;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Assert;
import org.junit.Test;

public class CategoryListTest extends BasePageWithoutLoginTest {

    @Override
    public void setUp() {
        super.setUp();
        tester.startPage(CategoriesPage.class);
        tester.assertRenderedPage(CategoriesPage.class);
    }

    @Test
    public void categoryImageRendered() {
        final String lastRenderedDocument = tester.getLastResponse().getDocument();
        final TagTester tagTester = TagTester.createTagByAttribute(lastRenderedDocument, "src", "https://images.freeimages.com/images/large-previews/9c2/coca-cola-1189757.jpg");

        Assert.assertNotNull(tagTester);
        Assert.assertEquals("img", tagTester.getName());
    }
}
