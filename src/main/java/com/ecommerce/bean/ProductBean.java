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
    public List<Product> getProducts() {
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.getProductList();
        return products;
    }
}

