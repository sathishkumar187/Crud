package com.tlc.crm.sportsshop.api;

import com.tlc.commons.code.ErrorCode;
import com.tlc.crm.common.config.AuditEntry;
import com.tlc.crm.common.config.ConfigManager;

import com.tlc.crm.sportsshop.status.ProductErrorCodes;
import com.tlc.crm.sportsshop.model.Product;
import com.tlc.sql.SQLAccess;
import com.tlc.sql.api.DataContainer;
import com.tlc.sql.api.Row;
import com.tlc.sql.api.dml.*;
import com.tlc.sql.api.ds.OrgDataStore;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *     Manage the products.
 * </p>
 *
 * @author SathishKumarS
 */
public class ProductManager implements ConfigManager<Product> {

    private final Table table = Table.get("CricketKit");
    private final OrgDataStore orgDataStore = getOrgDataStore();

    /**
     * To get the instance of {@link ProductManager}
     */
    private static class Instance {
        private static final ProductManager INSTANCE = new ProductManager();
    }

    private ProductManager() {

    }

    /**
     * <p>
     *     Gets the {@link ProductManager} instance.
     * </p>
     *
     * @return ProductManager
     */
    public static ProductManager getInstance() {
        return Instance.INSTANCE;
    }

    /**
     * <p>
     *     Adds the product.
     * </p>
     *
     * @param model
     */
    @Override
    public void create(final Product model) {
        create(List.of(model));
    }

    /**
     * <p>
     *     Adds the products.
     * </p>
     *
     * @param models
     */
    @Override
    public void create(final Collection<Product> models) {
        models.forEach(model -> {
            final Row row = new Row(table);

            setColumns(row, model);
            orgDataStore.addRow(row);
            model.setId(row.getPKValue());
        });
    }

    /**
     * <p>
     *     Updates the product.
     * </p>
     *
     * @param model
     */
    @Override
    public void update(final Product model) {
        if (exists(model)) {
            update(List.of(model));
        } else {
            throw ErrorCode.get(ProductErrorCodes.INVALID_PRODUCT);
        }
    }

    /**
     *<p>
     *     Updates the products.
     *</p>
     *
     * @param models
     */
    @Override
    public void update(final Collection<Product> models) {
        final DataContainer dataContainer = DataContainer.create();

        models.forEach(model -> {
            if (exists(model)) {
                final Row row = new Row(table, model.id());

                setColumns(row, model);
                dataContainer.updateRow(row);
            }
        });
        orgDataStore.commitChanges(dataContainer);
    }

    /**
     * <p>
     *     Deletes the product.
     * </p>
     *
     * @param model
     */
    @Override
    public void delete(final Product model) {
        if (exists(model)) {
            delete(List.of(model));
        } else {
            throw ErrorCode.get(ProductErrorCodes.INVALID_PRODUCT);
        }
    }

    /**
     * <p>
     *     Deletes the products.
     * </p>
     *
     * @param models
     */
    @Override
    public void delete(final Collection<Product> models) {
        final Collection<Long> ids = new HashSet<>();

        models.forEach(model -> {
            if (exists(model)) {
                ids.add(model.id());
            }
        });
        orgDataStore.delete(table, ids);
    }

    /**
     * <p>
     *     Checks the product is exist or not.
     * </p>
     *
     * @param model
     * @return boolean
     */
    @Override
    public boolean exists(final Product model) {
        try {
            return partialGet(model.id()) != null;
        } catch (ErrorCode errorCode) {
            return false;
        }
    }

    /**
     * <p>
     *     Checks the products is exist or not.
     * </p>
     *
     * @param models
     * @return Boolean
     */
    @Override
    public Map<Long, Boolean> exists(final Collection<Product> models) {
        final Map<Long, Boolean> ExistedModels = new HashMap<>();

        models.forEach(model -> {
            ExistedModels.put(model.id(), exists(model));
        });
        return ExistedModels;
    }

    /**
     * <p>
     *     Checks the id is exists or not.
     * </p>
     *
     * @param id
     * @return boolean
     */
    private boolean exists(final Long id) {
        final Product product = new Product();

        product.setId(id);
        return exists(product);
    }

    /**
     * <p>
     *     Partially gets the product.
     * </p>
     *
     * @param id
     * @return Product
     */
    @Override
    public Product partialGet(final Long id) {
        final Collection<Column> columns = new HashSet<>();

        columns.add(table.getPKColumn());
        final DataContainer dataContainer = orgDataStore.get(table, new WhereClause(Criteria.eq(table.getPKColumn(), id)), columns);
        final Row row = dataContainer.getRow(table, id);

        if (row != null) {
            final Product product = new Product();

            product.setId(row.getPKValue());
            return product;
        } else {
            throw ErrorCode.get(ProductErrorCodes.PRODUCT_NOT_FOUND);
        }
    }

    /**
     * <p>
     *     Gets the product.
     * </p>
     *
     * @param id
     * @return Product
     */
    @Override
    public Product get(final Long id) {
        final List<Product> products = get(List.of(id)).stream().collect(Collectors.toList());

        return products.get(0);
    }

    /**
     * <p>
     *     Gets the products.
     * </p>
     *
     * @param ids
     * @return Products
     */
    @Override
    public Collection<Product> get(final Collection<Long> ids) {
        final Set<Row> rows = new HashSet<>();
        final List<Long> existedIds = new ArrayList<>();

        checkIdExists(ids, existedIds);

        if (existedIds.isEmpty()) {
            throw ErrorCode.get(ProductErrorCodes.PRODUCT_NOT_FOUND);
        }
        existedIds.forEach(id -> rows.add(orgDataStore.get(table, id)));
        return constructProducts(rows);
    }

    /**
     * <p>
     *     Audit entry.
     * </p>
     *
     * @param model
     * @return AuditEntry
     */
    @Override
    public AuditEntry auditEntry(final Product model) {
        return null;
    }

    /**
     * <p>
     *     Checks the ids exists or not and adds the existed ids in existedIds.
     * </p>
     *
     * @param ids
     * @param existedIds
     */
    private void checkIdExists(final Collection<Long> ids, final List<Long> existedIds) {
        ids.forEach(id ->
        {
            if (exists(id)) {
                existedIds.add(id);
            }
        });
    }

    /**
     * <p>
     *     Creates {@link OrgDataStore} object.
     * </p>
     *
     * @return OrgDataStore
     */
    private static OrgDataStore getOrgDataStore() {
        return SQLAccess.get().getOrgDataStore(1L);
    }

    /**
     * <p>
     *     Sets the columns in given row.
     * </p>
     *
     * @param row
     * @param model
     */
    private void setColumns(final Row row, final Product model) {
        row.set("NAME", model.getName());
        row.set("BRAND", model.getBrand());
        row.set("SIZE", model.getSize());
        row.set("PRICE", String.valueOf(model.getPrice()));
    }

    /**
     * <p>
     *     Constructs the products.
     * </p>
     *
     * @param rows
     * @return Products
     */
    private Collection<Product> constructProducts(final Set<Row> rows) {
        final Collection<Product> products = new HashSet<>();

        for (final Row row : rows) {
            final Product product = new Product();

            product.setId(row.getPKValue());
            product.setName(row.get("NAME"));
            product.setBrand(row.get("BRAND"));
            product.setSize(row.get("SIZE"));
            product.setPrice(Double.valueOf(row.get("PRICE")));

            products.add(product);
        }
        return products;
    }

}
