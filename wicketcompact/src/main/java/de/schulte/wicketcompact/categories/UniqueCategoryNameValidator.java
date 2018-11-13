package de.schulte.wicketcompact.categories;

import de.schulte.wicketcompact.entities.Category;
import de.schulte.wicketcompact.services.CategoryService;
import de.schulte.wicketcompact.services.ServiceRegistry;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCategoryNameValidator implements ConstraintValidator<UniqueCategoryName, String> {

    @Override
    public void initialize(UniqueCategoryName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String givenCategoryName, ConstraintValidatorContext constraintValidatorContext) {
        for (Category category : ServiceRegistry.get(CategoryService.class).listAll()) {
            if (category.getName().equals(givenCategoryName)) {
                return false;
            }
        }
        return true;
    }

}
