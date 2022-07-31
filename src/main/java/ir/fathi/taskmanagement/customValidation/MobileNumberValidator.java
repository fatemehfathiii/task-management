package ir.fathi.taskmanagement.customValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileNumberValidator implements ConstraintValidator<MobileNumber,String> {

    @Override
    public boolean isValid(String mobileNumber, ConstraintValidatorContext constraintValidatorContext) {

        if (mobileNumber==null){
            return true;
        }else{
            return Pattern.matches("\\+98|09|0098\\d{9}[0_9]", mobileNumber);
        }

    }
}

