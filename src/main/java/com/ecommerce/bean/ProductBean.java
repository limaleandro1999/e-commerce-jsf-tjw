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
//    void createProductGet() {
//        String name =  req.getParameter("name");
//        String description =  req.getParameter("description");
//        Long price = Long.parseLong(req.getParameter("price"));
//        String imageUrl = req.getParameter("imageUrl");
//
//        Product product = new Product();
//        product.setName(name);
//        product.setDescription(description);
//        product.setPrice(price);
//        product.setImageUrl(imageUrl);
//
//        ProductDAO productDAO = new ProductDAO();
//        productDAO.createProduct(product);
//
//        res.sendRedirect("home");
//    }

