package com.cafe24.shopmall.validator.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cafe24.shopmall.validator.ProdImgValidator;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy=ProdImgValidator.class)
public @interface ValidProdImg {
	String message() default "Invalid ProdImg";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
