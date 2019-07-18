package com.cafe24.shopmall.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.shopmall.validator.constraints.ValidProdImg;
import com.cafe24.shopmall.vo.ProdImgVo;

public class ProdImgValidator implements ConstraintValidator<ValidProdImg, List<ProdImgVo>>{

	@Override
	public void initialize(ValidProdImg constraintAnnotation) {
	}

	@Override
	public boolean isValid(List<ProdImgVo> list, ConstraintValidatorContext context) {
		if(list.size()==0 || list == null) {
			return false;
		}
		return true;
	}

}
