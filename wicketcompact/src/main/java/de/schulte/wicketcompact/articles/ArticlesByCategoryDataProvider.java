package de.schulte.wicketcompact.articles;

import de.schulte.wicketcompact.EntityModel;
import de.schulte.wicketcompact.entities.Article;
import de.schulte.wicketcompact.entities.Category;
import de.schulte.wicketcompact.services.ArticleService;
import de.schulte.wicketcompact.services.CategoryService;
import de.schulte.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import java.util.Iterator;

public class ArticlesByCategoryDataProvider extends SortableDataProvider<Article, Void> {

    private final EntityModel<Category, CategoryService> categoryModel;

    public ArticlesByCategoryDataProvider(final Category category) {
        this.categoryModel = new EntityModel(category, CategoryService.class);
    }

    @Override
    public Iterator<? extends Article> iterator(long first, long count) {
        return ServiceRegistry.get(ArticleService.class).listValidByCategory(categoryModel.getObject()).iterator();
    }

    @Override
    public long size() {
        return ServiceRegistry.get(ArticleService.class).listAll().size();
    }

    @Override
    public IModel<Article> model(Article article) {
        return new CompoundPropertyModel<>(new EntityModel<>(article, ArticleService.class));
    }
}
