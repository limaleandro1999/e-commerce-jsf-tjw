package com.ecommerce.bean;

import com.ecommerce.dao.CartDAO;
import com.ecommerce.helpers.SessionContext;
import com.ecommerce.model.Cart;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@ManagedBean(name = "cartBean", eager = true)
@ApplicationScoped
public class CartBean {
    public String addItem(Long productId) throws IOException {
        SessionContext session = SessionContext.getInstance();
        Long userId = (Long) session.getAttribute("userId");

        if(userId == null) {
            return "home";
        } else {
            CartDAO cartDAO = new CartDAO();
            cartDAO.addItem(productId, userId);
            return "cart";
        }
    }

    public List<Cart> getCart() {
        SessionContext session = SessionContext.getInstance();
        Long userId = (Long) session.getAttribute("userId");
        CartDAO cartDAO = new CartDAO();
        List<Cart> cart = cartDAO.getCartItems(userId);
        return cart;
    }

    public Integer getCartQuantity() {
        SessionContext session = SessionContext.getInstance();
        Long userId = (Long) session.getAttribute("userId");
        CartDAO cartDAO = new CartDAO();
        List<Cart> cart = cartDAO.getCartItems(userId);
        Integer cartItemsNumber = cart.stream().reduce(0, (partialCartQuantityResult, cart1) -> partialCartQuantityResult + cart1.getQuantity(), Integer::sum);
        return cartItemsNumber;
    }

    public Integer getCartAmount() {
        SessionContext session = SessionContext.getInstance();
        Long userId = (Long) session.getAttribute("userId");
        CartDAO cartDAO = new CartDAO();
        List<Cart> cart = cartDAO.getCartItems(userId);
        Integer cartTotalAmount = cart.stream().reduce(0, (partialCartQuantityResult, cart1) -> Math.toIntExact(partialCartQuantityResult + (cart1.getQuantity() * cart1.getProduct().getPrice())), Integer::sum);
        return cartTotalAmount;
    }
}
