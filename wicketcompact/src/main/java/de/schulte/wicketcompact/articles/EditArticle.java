package de.schulte.wicketcompact.articles;

import de.schulte.wicketcompact.EntityModel;
import de.schulte.wicketcompact.ValidationErrorFeedbackPanel;
import de.schulte.wicketcompact.entities.Article;
import de.schulte.wicketcompact.services.ArticleService;
import de.schulte.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.RangeValidator;

import java.time.LocalDate;

public class EditArticle extends Panel {

    private final Form<Article> form = new Form<Article>("form") {

        @Override
        protected void onSubmit() {
            super.onSubmit();
            ServiceRegistry.get(ArticleService.class).save(form.getModelObject());
            setResponsePage(ArticlesPage.class);
        }
    };

    private final TextField<String> nameField;

    private final MarkupContainer image;

    private final FormComponent<String> imageUrl;

    private final ExternalLink helpLink;

    public EditArticle(String id) {
        super(id);
        form.setModel(new CompoundPropertyModel<>(new EntityModel<>(ArticleService.class)));
        this.helpLink = new ExternalLink("help", new Model<String>() {

            @Override
            public String getObject() {
                return "https://de.wikipedia.org/wiki/" + EditArticle.this.nameField.getModelObject();
            }
        });
        this.helpLink.setOutputMarkupPlaceholderTag(true);
        this.nameField = new TextField<>("name");
        this.nameField.add(new AjaxFormComponentUpdatingBehavior("change") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.add(helpLink);
            }
        });
        this.image = new WebMarkupContainer("image");
        this.image.add(new AttributeModifier("src", new Model<String>() {
            @Override
            public String getObject() {
                return imageUrl.getModelObject();
            }
        }));
        this.imageUrl = new TextField<String>("imageUrl").add(new PropertyValidator<>()).setLabel(Model.of("Bild-URL"));
        this.imageUrl.add(new AjaxFormComponentUpdatingBehavior("change") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.add(image);
            }
        });
        this.image.setOutputMarkupPlaceholderTag(true);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        initializeForm();
        this.form.add(this.helpLink);
    }

    private void initializeForm() {
        add(form);
        add(new ValidationErrorFeedbackPanel("validationFeedback"));
        form.add(nameField.add(new PropertyValidator<>()));
        form.add(new DropDownChoice<>("category", new CategoryListModel(), new ChoiceRenderer<>("name", "id")).add(new PropertyValidator<>()));
        form.add(new TextArea<String>("description").add(new PropertyValidator<>()).setLabel(Model.of("Beschreibung")));
        form.add(new TextField<>("price").add(new PropertyValidator<>()).setLabel(Model.of("Preis")));
        form.add(new TextField<>("validFrom").setLabel(Model.of("Gültig ab")).add(RangeValidator.maximum(LocalDate.now().plusMonths(3))));
        form.add(new TextField<>("validTo").setLabel(Model.of("Gültig bis")).add(RangeValidator.minimum(LocalDate.now().plusDays(1))));
        form.add(this.imageUrl);
        form.add(this.image);
    }

    EditArticle setArticle(Article article) {
        this.form.setModelObject(article);
        return this;
    }

}
