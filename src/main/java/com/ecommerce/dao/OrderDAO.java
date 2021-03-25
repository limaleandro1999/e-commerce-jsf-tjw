package com.ecommerce.dao;

import com.ecommerce.model.Order;

import javax.persistence.TypedQuery;
import java.util.List;

public class OrderDAO extends BasicDAO {
    public void createOrder(Order order) {
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
    }

    public List<Order> getUserOrders(Long userId) {
        TypedQuery<Order> query = em.createQuery("SELECT o FROM e_order o WHERE o.user.id = :userId", Order.class);
        query.setParameter("userId", userId);
        List<Order> orderList = query.getResultList();
        return orderList;
    }
}
