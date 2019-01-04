package de.schulte.wicketcompact.categories;

import de.schulte.wicketcompact.EntityModel;
import de.schulte.wicketcompact.LoadingIndicatingAjaxSubmitLink;
import de.schulte.wicketcompact.SgFeedbackPanel;
import de.schulte.wicketcompact.entities.Category;
import de.schulte.wicketcompact.services.CategoryService;
import de.schulte.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.bean.validation.PropertyValidator;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

public class EditCategory extends Panel {

    private final Form form = new Form<Category>("form") {

        @Override
        protected void onSubmit() {
            super.onSubmit();
            ServiceRegistry.get(CategoryService.class).save(this.getModelObject());
        }
    };
    private final FeedbackPanel validationFeedback;

    public EditCategory(String id) {
        super(id);
        this.form.setModel(new CompoundPropertyModel<>(new EntityModel<>(CategoryService.class)));
        validationFeedback = new SgFeedbackPanel("validationFeedback");
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(validationFeedback);
        add(form);
        form.add(new TextField<String>("name").add(new PropertyValidator<>()));
        form.add(new TextField<String>("imageUrl").add(new PropertyValidator<>()));

        form.add(new LoadingIndicatingAjaxSubmitLink("save") {

            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                findParent(ModalWindow.class).close(target);
            }

            @Override
            protected void onError(AjaxRequestTarget target) {
                super.onError(target);
                target.add(EditCategory.this.validationFeedback);
            }

            @Override
            protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                super.updateAjaxAttributes(attributes);
                attributes.getAjaxCallListeners().add(new AjaxCallListener() {

                    @Override
                    public CharSequence getBeforeSendHandler(Component component) {
                        return String.format("$('#%s').attr('disabled', 'disabled');", getMarkupId());
                    }

                    @Override
                    public CharSequence getCompleteHandler(Component component) {
                        return String.format("$('#%s').removeAttr('disabled');", getMarkupId());
                    }
                });
            }
        });
    }

    EditCategory setCategory(Category category) {
        this.form.setModelObject(category);
        return this;
    }

}
