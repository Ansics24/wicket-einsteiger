package de.schulte.wicketcompact.categories;

import de.schulte.wicketcompact.EntityModel;
import de.schulte.wicketcompact.ValidationErrorFeedbackPanel;
import de.schulte.wicketcompact.entities.Category;
import de.schulte.wicketcompact.services.CategoryService;
import de.schulte.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

public class EditCategory extends Panel {

    private final Form form = new Form<Category>("form") {

        @Override
        protected void onSubmit() {
            super.onSubmit();
            ServiceRegistry.get(CategoryService.class).save(this.getModelObject());
            setResponsePage(CategoriesPage.class);
        }
    };
    private final ValidationErrorFeedbackPanel validationFeedback;

    public EditCategory(String id) {
        super(id);
        this.form.setModel(new CompoundPropertyModel<>(new EntityModel<>(CategoryService.class)));
        validationFeedback = new ValidationErrorFeedbackPanel("validationFeedback");
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(validationFeedback);
        add(form);
        form.add(new TextField<String>("name").add(new PropertyValidator<>()));
        form.add(new TextField<String>("imageUrl").add(new PropertyValidator<>()));

        form.add(new AjaxSubmitLink("save") {

            @Override
            protected void onError(AjaxRequestTarget target) {
                super.onError(target);
                target.add(EditCategory.this.validationFeedback);
            }
        });
    }

    EditCategory setCategory(Category category) {
        this.form.setModelObject(category);
        return this;
    }

}
