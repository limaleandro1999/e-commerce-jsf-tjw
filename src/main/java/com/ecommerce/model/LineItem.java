package com.ecommerce.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class LineItem {
    @ManyToOne
    private Product product;
    private Integer quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LineItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public LineItem() {

    }
}
