package com.cafe24.shopmall.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.shopmall.validator.constraints.ValidName;

public class NameValidator implements ConstraintValidator<ValidName, String> {
	Pattern pattern = Pattern.compile("^[\uAC00-\uD7A3xfe0-9a-zA-Z\\s]{1,20}$");

	@Override
	public void initialize(ValidName constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || "".contentEquals(value)|| value.length() == 0) {
			return false;
		}
		return pattern.matcher(value).matches();
	}

}
