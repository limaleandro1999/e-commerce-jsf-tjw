package com.ecommerce.bean;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "productBean", eager = true)
@ApplicationScoped
public class ProductBean implements Serializable {
    List<Product> products;

    public ProductBean() {
        this.fetchProducts();
    }

    public void fetchProducts() {
        ProductDAO productDAO = new ProductDAO();
        this.products = productDAO.getProductList();
    }

    public List<Product> getProducts() {
        return this.products;
    }
}

