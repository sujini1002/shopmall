package com.cafe24.shopmall.validator.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cafe24.shopmall.validator.ProdInventoryValidator;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy=ProdInventoryValidator.class) 
public @interface ValidProdInventroy {
	String message() default "Invalid ProdInventory";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
