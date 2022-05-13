package ir.fathi.taskmanagement.customValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileNumberValidator implements ConstraintValidator<MobileNumber,String> {

    @Override
    public boolean isValid(String mobileNumber, ConstraintValidatorContext constraintValidatorContext) {
        return !mobileNumber.matches("(\\+98|09|0098)[0-9]");
    }
}
