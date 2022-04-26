package com.tlc.crm.sportsshop.validation;

import com.tlc.commons.code.ErrorCode;
import com.tlc.crm.sportsshop.status.ProductErrorCodes;
import com.tlc.crm.sportsshop.model.Product;
import com.tlc.validator.ModelValidator;
import com.tlc.validator.ValidatorAccess;

/**
 * <p>
 *     Validate the input objects.
 * </p>
 *
 * @author SathishKumarS
 */
public class Validator {

    private static final ModelValidator modelValidator = ValidatorAccess.get();

    /**
     * <p>
     *     Validates the object.
     * </p>
     *
     * @param product
     * @param groups
     */
    public static void validate(final Product product, final Class... groups) {

        if (!modelValidator.isValid(product, groups)) {
            throw ErrorCode.get(ProductErrorCodes.VALIDATION_FAILED);
        }
    }
}
