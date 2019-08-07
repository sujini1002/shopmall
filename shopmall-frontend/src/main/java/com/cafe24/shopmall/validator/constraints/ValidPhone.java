package com.cafe24.shopmall.validator.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cafe24.shopmall.validator.PhoneValidator;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy=PhoneValidator.class)
public @interface ValidPhone {
	String message() default "01[0,6,7,8,9]-0000[000]-0000 형식으로 입력";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
