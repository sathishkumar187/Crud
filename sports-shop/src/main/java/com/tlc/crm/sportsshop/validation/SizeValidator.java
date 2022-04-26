package com.tlc.crm.sportsshop.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * <p>
 *     Size validator.
 * </p>
 *
 * @author SathishKumarS
 */
public class SizeValidator implements ConstraintValidator<Size, String> {

    /**
     * {@inheritDoc}
     *
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("(?i)s|m|l");
    }
}
