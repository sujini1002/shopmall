package com.cafe24.shopmall.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.cafe24.shopmall.repository.CategoryDAO;
import com.cafe24.shopmall.validator.constraints.ValidCheckCategoryNo;

public class CheckCategoryNoValidator implements ConstraintValidator<ValidCheckCategoryNo, Integer>{
	
	@Autowired
	private CategoryDAO categoryDao;
	
	@Override
	public void initialize(ValidCheckCategoryNo constraintAnnotation) {
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		
		if(value == null) {
			return true;
		}
		
		return categoryDao.isExistTopNo(value);
	}

}
