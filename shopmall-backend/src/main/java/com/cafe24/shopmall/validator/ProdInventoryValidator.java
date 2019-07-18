package com.cafe24.shopmall.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.shopmall.validator.constraints.ValidProdInventroy;
import com.cafe24.shopmall.vo.ProdInventoryVo;

public class ProdInventoryValidator implements ConstraintValidator<ValidProdInventroy, List<ProdInventoryVo>>{

	@Override
	public void initialize(ValidProdInventroy constraintAnnotation) {
	}

	@Override
	public boolean isValid(List<ProdInventoryVo> list, ConstraintValidatorContext context) {
		if(list.size()==0 || list == null) {
			return false;
		}
		return true;
	}

	

}
