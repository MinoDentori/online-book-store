package mate.academy.onlinebookstore.validator;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static mate.academy.onlinebookstore.util.ConstraintsMessages.VALUES_DONT_MATCH;

@Constraint(validatedBy = FieldMatchValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldMatch {
    String message() default VALUES_DONT_MATCH;

    String field();

    String fieldMatch();
}
