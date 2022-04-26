package com.tlc.crm.sportsshop.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *     Size annotation.
 * </p>
 *
 * @author SathishKumarS
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SizeValidator.class)
public @interface Size {

    String message() default "validator_error_invalid_size";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
