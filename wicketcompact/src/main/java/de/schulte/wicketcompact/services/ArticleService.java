package de.schulte.wicketcompact.services;

import de.schulte.wicketcompact.entities.Article;
import de.schulte.wicketcompact.entities.Category;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleService extends BaseService<Article> {

    public ArticleService() {
        final CategoryService categoryService = ServiceRegistry.get(CategoryService.class);

        final Article cappuccino = new Article(categoryService.get(1L), "Cappuccino", "https://images.freeimages.com/images/large-previews/a79/cappuccino-1497220.jpg",
                new BigDecimal("3.20"), "Italienische Kaffeespezialität, die aus einem Espresso und heißem Milchschaum zubereitet wird.");

        save(cappuccino);
    }

    public List<Article> listByCategory(final Category category) {
        return listAll().stream().filter(article -> article.getCategory().equals(category)).collect(Collectors.toList());
    }

}
