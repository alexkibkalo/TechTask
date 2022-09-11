package com.ua.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = AccountStatusValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccountStatusConstraint {

    String message() default "Account is CLOSED. To perform any modified operation, account need to be OPEN";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}