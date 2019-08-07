package com.cafe24.shopmall.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.shopmall.validator.constraints.ValidPhone;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String>{
	Pattern pattern = Pattern.compile("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$");

	@Override
	public void initialize(ValidPhone constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || "".contentEquals(value)|| value.length() == 0) {
			return false;
		}
		return pattern.matcher(value).matches();
	}

}
