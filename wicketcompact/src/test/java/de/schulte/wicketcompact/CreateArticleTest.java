package de.schulte.wicketcompact;

import de.schulte.wicketcompact.articles.ArticlesPage;
import de.schulte.wicketcompact.articles.CreateArticlePage;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Test;

public class CreateArticleTest extends BasePageWithoutLoginTest {

    @Override
    public void setUp() {
        super.setUp();
        tester.startPage(CreateArticlePage.class);
        tester.assertRenderedPage(CreateArticlePage.class);
    }

    @Test
    public void createsArticle() {
        final FormTester formTester = tester.newFormTester("editArticle:form");
        formTester.setValue("name", "Mineralwasser");
        formTester.select("category", 2);
        formTester.setValue("description", "Ein Wasser mit Kohlensäure");
        formTester.setValue("price", "1,50");
        formTester.setValue("imageUrl", "https://images.freeimages.com/images/large-previews/16e/black-tea-1319625.jpg");
        formTester.submit();
        tester.assertRenderedPage(ArticlesPage.class);

        tester.assertModelValue("articles:8:name", "Mineralwasser");
    }
}
