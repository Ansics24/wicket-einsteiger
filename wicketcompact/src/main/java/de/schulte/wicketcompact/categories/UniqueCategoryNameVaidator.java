package de.schulte.wicketcompact.categories;

import de.schulte.wicketcompact.entities.Category;
import de.schulte.wicketcompact.services.CategoryService;
import de.schulte.wicketcompact.services.ServiceRegistry;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCategoryNameVaidator implements ConstraintValidator<UniqueCategoryName, Category> {

    @Override
    public void initialize(UniqueCategoryName constraintAnnotation) {

    }

    @Override
    public boolean isValid(Category givenCategory, ConstraintValidatorContext constraintValidatorContext) {
        for (Category category : ServiceRegistry.get(CategoryService.class).listAll()) {
            if(category.getName().equals(givenCategory.getName())) {
                return false;
            }
        }
        return true;
    }

}
