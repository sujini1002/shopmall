package com.cafe24.shopmall.validator.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cafe24.shopmall.validator.PostIdValidator;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy=PostIdValidator.class)
public @interface ValidPostId {
	String message() default "숫자와 '-'만 이루어진 7자를 입력";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
