package com.cafe24.shopmall.validator.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cafe24.shopmall.validator.IDValidator;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy=IDValidator.class)
public @interface ValidID {
	String message() default "영문자와 숫자를 조합하여 6~15 입력 또는 중복된 아이디입니다.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
