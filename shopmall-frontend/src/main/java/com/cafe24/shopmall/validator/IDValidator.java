package com.cafe24.shopmall.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.cafe24.shopmall.provider.MemberProvider;
import com.cafe24.shopmall.validator.constraints.ValidID;


public class IDValidator implements ConstraintValidator<ValidID, String> {
	private Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]{6,15}$");
	
	@Autowired
	private MemberProvider MemberProvider;
	
	@Override
	public void initialize(ValidID constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(value == null || "".contentEquals(value)|| value.length() == 0) {
			return false;
		}
		
		return pattern.matcher(value).matches() && MemberProvider.checkId(value)==false;
	}
	
}
