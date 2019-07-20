package com.cafe24.shopmall.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.shopmall.validator.constraints.ValidList;

public class ListValidator implements ConstraintValidator<ValidList, List<? extends Object>>{

	@Override
	public void initialize(ValidList constraintAnnotation) {
	}

	@Override
	public boolean isValid(List<? extends Object> value, ConstraintValidatorContext context) {
		if(value == null || value.size() == 0) {
			return false;
		}
		return true;
	}

}
