package com.cafe24.shopmall.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.cafe24.shopmall.repository.MemberDAO;
import com.cafe24.shopmall.validator.constraints.ValidCheckMemberNo;

public class CheckMemberNoValidator implements ConstraintValidator<ValidCheckMemberNo, Long>{
	
	@Autowired
	private MemberDAO memberDao;
	
	@Override
	public void initialize(ValidCheckMemberNo constraintAnnotation) {
	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		
		if(value == null) {
			return true;
		}
		
		Long no = Long.valueOf(String.valueOf(value));
		
		return memberDao.isExistMemberNo(no);
	}

}
