package com.tlc.crm.sportsshop.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * <p>
 *     Brand annotation.
 * </p>
 *
 * @author SathishKumarS
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BrandValidator.class)
@Documented
public @interface Brand {

    String message() default "validator_error_invalid_brand";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
