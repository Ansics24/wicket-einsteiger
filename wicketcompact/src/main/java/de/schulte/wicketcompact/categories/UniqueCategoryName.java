package de.schulte.wicketcompact.categories;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {UniqueCategoryNameValidator.class})
@Documented
public @interface UniqueCategoryName {

    String message() default "{unique.category.name}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
