package de.schulte.wicketcompact;

import de.schulte.wicketcompact.articles.ArticlesPage;
import de.schulte.wicketcompact.categories.CategoriesPage;
import de.schulte.wicketcompact.tables.TablesPage;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class MenuTest {

    private WicketTester tester;

    @Before
    public void setUp() {
        this.tester = new WicketTester(new WicketApplication() {

            @Override
            protected void setUpRequestCycleListeners() {
                // no requestcycle listeners in tests
            }
        });
    }

    @Test
    public void navigation() {
        tester.startPage(HomePage.class);
        tester.assertRenderedPage(HomePage.class);

        tester.clickLink("header:navbar:categories");
        tester.assertRenderedPage(CategoriesPage.class);

        tester.clickLink("header:navbar:articles");
        tester.assertRenderedPage(ArticlesPage.class);

        tester.clickLink("header:navbar:tables");
        tester.assertRenderedPage(TablesPage.class);
    }
}
