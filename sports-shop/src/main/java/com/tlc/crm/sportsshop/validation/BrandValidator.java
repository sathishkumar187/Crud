package com.tlc.crm.sportsshop.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * <p>
 *     Brand validator.
 * </p>
 *
 * @author SathishKumarS
 */
public class BrandValidator implements ConstraintValidator<Brand, String> {

    /**
     * {@inheritDoc}
     *
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("(?i)ss|sg|mrf|rbk|nike");
    }
}
