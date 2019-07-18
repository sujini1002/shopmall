package com.cafe24.shopmall.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.shopmall.validator.constraints.ValidPostId;

public class PostIdValidator implements ConstraintValidator<ValidPostId, String>{
	Pattern pattern = Pattern.compile("^[0-9-]{0,7}$");

	@Override
	public void initialize(ValidPostId constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || "".contentEquals(value)|| value.length() == 0) {
			return true;
		}
		return pattern.matcher(value).matches();
	}

}
