package com.ecommerce.dao;

import com.ecommerce.model.Cart;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;

import javax.persistence.TypedQuery;
import java.util.List;

public class CartDAO extends BasicDAO {
    public void addItem(Long productId, Long userId) {
        TypedQuery<Cart> query = em.createQuery("SELECT c FROM Cart c WHERE c.product.id = :productId AND c.user.id = :userId", Cart.class);
        query.setParameter("productId", productId);
        query.setParameter("userId", userId);
        List<Cart> cartList = query.getResultList();

        Product product = em.find(Product.class, productId);
        User user = em.find(User.class, userId);
        Cart cart;

        if (cartList.isEmpty()) {
            cart = new Cart();
            cart.setProduct(product);
            cart.setUser(user);
            cart.setQuantity(1);
            em.getTransaction().begin();
        } else {
            cart = cartList.get(0);
            em.getTransaction().begin();
            cart.setQuantity(cart.getQuantity() + 1);
        }
        em.persist(cart);
        em.getTransaction().commit();
    }

    public List<Cart> getCartItems(Long userId) {
        TypedQuery<Cart> query = em.createQuery("SELECT c FROM Cart c WHERE c.user.id = :userId", Cart.class);
        query.setParameter("userId", userId);
        List<Cart> cartList = query.getResultList();

        return cartList;
    }

    public void deleteUserCart(Long userId) {
        TypedQuery<Cart> query = em.createQuery("SELECT c FROM Cart c WHERE c.user.id = :userId", Cart.class);
        query.setParameter("userId", userId);
        List<Cart> cartItems = query.getResultList();

        for (Cart cartItem: cartItems) {
            em.getTransaction().begin();
            em.remove(cartItem);
            em.getTransaction().commit();
        }
    }
}
