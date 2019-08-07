package com.cafe24.shopmall.validator.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cafe24.shopmall.validator.EmailValidator;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy=EmailValidator.class)
public @interface ValidEmail {
	String message() default "exmaple@shopmall.com 형식으로 입력하시오.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
