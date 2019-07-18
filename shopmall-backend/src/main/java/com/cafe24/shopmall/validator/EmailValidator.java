package com.cafe24.shopmall.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.shopmall.validator.constraints.ValidEmail;

public class EmailValidator implements ConstraintValidator<ValidEmail, String>{
	private Pattern pattern = Pattern.compile("^[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+$");
	
	@Override
	public void initialize(ValidEmail constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || "".contentEquals(value)|| value.length() == 0) {
			return false;
		}
		
		return pattern.matcher(value).matches();
	}

}
