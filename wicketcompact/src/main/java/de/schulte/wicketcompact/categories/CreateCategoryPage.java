package de.schulte.wicketcompact.categories;

import de.schulte.wicketcompact.BaseWebPage;
import de.schulte.wicketcompact.entities.Category;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class CreateCategoryPage extends BaseWebPage {

    public CreateCategoryPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        final EditCategory editCategory = new EditCategory("editCategory");
        add(editCategory.setCategory(new Category()));
    }

}
