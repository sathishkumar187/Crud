package com.tlc.crm.sportsshop.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * <p>
 *     Name annotation.
 * </p>
 *
 * @author SathishKumarS
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameValidator.class)
@Documented
public @interface Name {

    String message() default "validator_error_invalid_name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
