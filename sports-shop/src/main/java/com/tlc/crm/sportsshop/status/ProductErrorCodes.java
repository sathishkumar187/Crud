package com.tlc.crm.sportsshop.status;

import com.tlc.commons.code.ErrorCodeGroup;
import com.tlc.commons.code.ErrorCodeProvider;

/**
 * <p>
 *     Product error codes.
 * </p>
 *
 * @author SathishKumarS
 */
public enum ProductErrorCodes implements ErrorCodeProvider {

    PRODUCT_NOT_FOUND(0x11),
    INVALID_PRODUCT(0x12),

    VALIDATION_FAILED(0x21),
    ;

    private final int code;

    ProductErrorCodes(int code) {
        this.code = ProductErrorCodeGroup.GROUP.getConvertedCode(code);
    }

    /**
     * {@inheritDoc}
     *
     * @return code.
     */
    @Override
    public int getCode() {
        return code;
    }

    /**
     * <p>
     *     Product error code group.
     * </p>
     */
    private static class ProductErrorCodeGroup implements ErrorCodeGroup {

        private static final ErrorCodeGroup GROUP = new ProductErrorCodeGroup();

        /**
         * {@inheritDoc}
         *
         * @return prefix
         */
        @Override
        public int getPrefix() {
            return 0x10;
        }
    }
}
