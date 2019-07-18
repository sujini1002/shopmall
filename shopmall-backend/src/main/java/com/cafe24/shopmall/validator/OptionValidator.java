package com.cafe24.shopmall.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.shopmall.validator.constraints.ValidOption;
import com.cafe24.shopmall.vo.OptionVo;

public class OptionValidator implements ConstraintValidator<ValidOption, List<OptionVo>>{

	@Override
	public void initialize(ValidOption constraintAnnotation) {
	}

	@Override
	public boolean isValid(List<OptionVo> list, ConstraintValidatorContext context) {
		if(list.size()==0 || list == null) {
			return false;
		}
		return true;
	}

	

}
