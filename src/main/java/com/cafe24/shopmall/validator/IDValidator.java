package com.cafe24.shopmall.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.shopmall.validator.constraints.ValidID;


public class IDValidator implements ConstraintValidator<ValidID, String> {
	private Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]{6,15}$");
	
	@Override
	public void initialize(ValidID constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(value == null || "".contentEquals(value)|| value.length() == 0) {
			return false;
		}
		
		return pattern.matcher(value).matches();
	}
	
}
