package com.tlc.crm.sportsshop.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * <p>
 *     Name validator.
 * </p>
 *
 * @author SathishKumarS
 */
public class NameValidator implements ConstraintValidator<Name, String> {

    /**
     * {@inheritDoc}
     *
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("(?i)bat|ball|stump|gloves|helmet");
    }
}
