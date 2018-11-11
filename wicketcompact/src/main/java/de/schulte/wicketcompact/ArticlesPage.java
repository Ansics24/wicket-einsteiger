package de.schulte.wicketcompact;

import de.schulte.wicketcompact.entities.Article;
import de.schulte.wicketcompact.services.ArticleService;
import de.schulte.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.UrlValidator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.FormatStyle;

public class ArticlesPage extends BaseEntitiesPage {

    private final DataView<Article> articles;

    private final Form<Article> form = new Form<Article>("form") {

        @Override
        protected void onSubmit() {
            super.onSubmit();
            form.setVisible(false);
            ServiceRegistry.get(ArticleService.class).save(form.getModelObject());
        }
    };

    public ArticlesPage(PageParameters parameters) {
        super(parameters);
        IDataProvider<Article> dataProvider = new ArticlesDataProvider();
        this.articles = new DataView<Article>("articles", dataProvider) {

            @Override
            protected void populateItem(Item<Article> item) {
                final Article article = item.getModelObject();
                item.setModel(new CompoundPropertyModel<>(item.getModel()));
                item.add(new Label("name"));
                item.add(new Label("category.name"));
                item.add(new Label("description"));
                item.add(new Label("price"));
                item.add(new Label("validFrom"));
                item.add(new Label("validTo"));
                final AttributeAppender srcAppender = new AttributeAppender("src", new PropertyModel<>(new EntityModel<>(article, ArticleService.class), "imageUrl"));
                item.add(new WebMarkupContainer("image").add(srcAppender));
            }
        };
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(this.articles);
        add(new Link<String>("newArticle") {

            @Override
            public void onClick() {
                form.setVisible(true);
                form.setModelObject(new Article());
            }
        });
        initializeForm();
    }

    private void initializeForm() {
        form.setVisible(false);
        add(form);
        add(new FeedbackPanel("feedback"));
        form.setModel(new CompoundPropertyModel<>(new EntityModel<>(ArticleService.class)));
        form.add(new TextField<String>("name").setRequired(true));
        form.add(new TextArea<String>("description").setRequired(true));
        form.add(new TextField<>("price").setRequired(true).add(new RangeValidator<>(BigDecimal.ZERO, new BigDecimal("20"))));
        form.add(new LocalDateTextField("validFrom", FormatStyle.SHORT).add(new RangeValidator<>(LocalDate.now(), LocalDate.now().plusDays(365))));
        form.add(new LocalDateTextField("validTo", FormatStyle.SHORT).add(new RangeValidator<>(LocalDate.now().plusDays(1), LocalDate.MAX)));
        form.add(new TextField<String>("imageUrl").setRequired(true).add(new UrlValidator()));
    }

    @Override
    protected IPageable getPageable() {
        return this.articles;
    }
}
