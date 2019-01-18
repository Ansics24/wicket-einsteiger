package de.schulte.wicketcompact;

import de.schulte.wicketcompact.articles.ArticlesPage;
import de.schulte.wicketcompact.categories.CategoriesPage;
import de.schulte.wicketcompact.tables.TablesPage;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class MenuTest extends BasePageWithoutLoginTest {

    @Before
    public void startHomePage() {
        tester.startPage(HomePage.class);
        tester.assertRenderedPage(HomePage.class);
    }

    @Test
    public void navigation() {

        tester.clickLink("header:navbar:categories");
        tester.assertRenderedPage(CategoriesPage.class);

        tester.clickLink("header:navbar:articles");
        tester.assertRenderedPage(ArticlesPage.class);

        tester.clickLink("header:navbar:tables");
        tester.assertRenderedPage(TablesPage.class);
    }

    @Test
    public void linkToActivePageDisabled_OthersEnabled() {
        tester.assertVisible("header:navbar:dashboard");
        tester.assertDisabled("header:navbar:dashboard");

        tester.assertVisible("header:navbar:articles");
        tester.assertEnabled("header:navbar:articles");
    }
}
