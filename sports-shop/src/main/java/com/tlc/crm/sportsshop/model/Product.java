package com.tlc.crm.sportsshop.model;

import com.tlc.crm.sportsshop.validation.Brand;
import com.tlc.crm.sportsshop.validation.Name;
import com.tlc.crm.sportsshop.validation.Size;
import com.tlc.validator.TlcModel;
import com.tlc.validator.type.Group.Create;
import com.tlc.validator.type.Group.Update;

/**
 * <p>
 *     Object class.
 * </p>
 *
 * @author SathishKumarS
 */
public class Product implements TlcModel {

    private Long id;

    private Long orgId;

    private Object identity;

    @Name(groups = {Create.class, Update.class})
    private String name;

    @Brand(groups = {Create.class, Update.class})
    private String brand;

    @Size(groups = {Create.class, Update.class})
    private String size;

    private Double price;

    public Product() {

    }

    public Product(Long id, String name, String brand, String size, Double price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.size = size;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public void setIdentity(Object identity) {
        this.identity = identity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getSize() {
        return size;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Long orgId() {
        return orgId;
    }

    @Override
    public Object identity() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s", "Product \n    { id = ", id, ", name = '", name, '\'',
                ", brand = '", brand, '\'', ", size = '", size, '\'', ", price = ", price, '}');
    }
}
