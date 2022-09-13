package ir.fathi.taskmanagement.customValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumValidator implements ConstraintValidator<ValidateEnum,String> {

    Set<String> ALLOWED_VALUES;

    public static Set<String> getNamesSet(Class<? extends Enum<?>> e){
        Enum<?>[] enums=e.getEnumConstants();
        return Arrays.stream(enums).map(Enum::name).collect(Collectors.toSet());
    }

    @Override
    public void initialize(ValidateEnum constraintAnnotation) {
        Class<? extends Enum<?>> enumSelected = constraintAnnotation.targetClassType();
        ALLOWED_VALUES=getNamesSet(enumSelected);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return ALLOWED_VALUES.contains(value);
    }
}
