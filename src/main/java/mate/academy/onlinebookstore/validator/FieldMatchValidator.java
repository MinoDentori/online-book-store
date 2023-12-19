package mate.academy.onlinebookstore.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mate.academy.onlinebookstore.dto.user.UserRegistrationRequestDto;
import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator implements
        ConstraintValidator<FieldMatch, UserRegistrationRequestDto> {
    private String field;
    private String fieldMatch;

    public void initialize(FieldMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(UserRegistrationRequestDto requestDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        Object fieldValue = new BeanWrapperImpl(requestDto)
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(requestDto)
                .getPropertyValue(fieldMatch);

        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        } else {
            return fieldMatchValue == null;
        }
    }
}
