package com.tlc.crm.sportsshop.action;

import com.tlc.commons.json.Json;
import com.tlc.commons.json.JsonArray;
import com.tlc.commons.json.JsonObject;

import com.tlc.crm.common.action.secure.CrmConfigAction;
import com.tlc.crm.common.config.ConfigManager;

import com.tlc.crm.sportsshop.model.Product;
import com.tlc.crm.sportsshop.api.ProductManager;
import com.tlc.crm.sportsshop.validation.Validator;

import com.tlc.validator.type.Group.Create;
import com.tlc.validator.type.Group.Update;

import com.tlc.web.WebAction;

import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>
 *     Manages the {@link Product}
 * </p>
 *
 * @author SathishKumarS
 */
@WebAction(path = "/product/mgmt")
public class ProductManagement extends CrmConfigAction<Product> {

    /**
     * <p>
     *     Gets the {@link ProductManager} object.
     * </p>
     *
     * @return ConfigManager
     */
    @Override
    public ConfigManager getConfigManager() {
        return ProductManager.getInstance();
    }

    /**
     * <p>
     *     Constructs the {@link Product} from {@link JsonObject}
     * </p>
     *
     * @param jsonObject
     * @param type
     * @return Product
     */
    @Override
    public Product construct(final JsonObject jsonObject, final String type) {
        final Long id = jsonObject.optLong("id", 0);
        final String name = jsonObject.optString("name", null);
        final String brand = jsonObject.optString("brand", null);
        final String size = jsonObject.optString("size", null);
        final Double price = jsonObject.optDouble("price", 0);

        final Product product = new Product(id, name, brand, size, price);

        validate(product, type);
        return product;
    }

    /**
     * <p>
     *     Constructs the {@link JsonObject} from {@link Product}
     * </p>
     *
     * @param model
     * @param responseJson
     */
    @Override
    protected void constructResponse(final Product model, final JsonObject responseJson) {
        responseJson.put("id", model.id());
        responseJson.put("name", model.getName());
        responseJson.put("brand", model.getBrand());
        responseJson.put("size", model.getSize());
        responseJson.put("price", model.getPrice());
    }

    /**
     * constructs the response json object.
     *
     * @param models
     * @param responeJson
     */
    @Override
    protected void constructResponse(final Collection<Product> models, final JsonObject responeJson) {
        final int[] slno = {1};
        models.forEach(model -> {
            final JsonObject jsonObject = Json.object();

            constructResponse(model, jsonObject);
            responeJson.put("model " + slno[0], jsonObject);
            slno[0]++;
        });
    }

    /**
     * <p>
     *     Constructs {@link Collection<Product>} from {@link JsonArray}
     * </p>
     *
     * @param modelsArray
     * @return products
     */
    @Override
    protected Collection<Product> construct(final JsonArray modelsArray, final String type) {
        final Collection<Product> products = new ArrayList<>();

        for (int i = 0; i < modelsArray.size(); i++) {
            final JsonObject jsonObject = modelsArray.getJsonObject(i);
            final Product product = construct(jsonObject, type);

            products.add(product);
        }
        return products;
    }

    /**
     * <p>
     *     Gets the ids from {@link JsonArray}.
     * </p>
     *
     * @param modelsArray
     * @return ids
     */
    @Override
    protected Collection<Long> getIds(final JsonArray modelsArray) {
        final Collection<Long> ids = new ArrayList<>();

        for (int i = 0; i < modelsArray.size(); i++) {
            ids.add(modelsArray.getLong(i));
        }
        return ids;
    }

    /**
     * <p>
     *     Validates the product.
     * </p>
     *
     * @param product
     * @param type
     */
    private void validate(final Product product, final String type) {

        if ("create".equals(type)) {
            Validator.validate(product, Create.class);
        } else if ("update".equals(type)) {
            Validator.validate(product, Update.class);
        }
    }
}
