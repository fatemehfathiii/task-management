package ir.fathi.taskmanagement.customValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileNumberValidator implements ConstraintValidator<MobileNumber,String> {

    @Override
    public boolean isValid(String mobileNumber, ConstraintValidatorContext constraintValidatorContext) {

        if (!mobileNumber.startsWith("09") || !mobileNumber.startsWith("0098") || mobileNumber.startsWith("+98")){
            return false;
        }

        return mobileNumber.trim().length() == 11 && !mobileNumber.matches("[0-9]");
    }
}
