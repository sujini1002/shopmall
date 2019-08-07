package com.cafe24.shopmall.validator.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cafe24.shopmall.validator.PasswordValidator;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy=PasswordValidator.class)
public @interface ValidPassword {
	String message() default "영문 대소문자 숫자 특수문자를 하나씩 포함하여 8~16글자";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
