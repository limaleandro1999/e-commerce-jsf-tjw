package com.ecommerce.dao;

import com.ecommerce.model.User;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class UserDAO extends BasicDAO{
    public void createUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public User findByEmail(String email) {
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE email = :email", User.class);
            query.setParameter("email", email);
            User user = query.getSingleResult();

            return user;
        } catch (NoResultException e) {
            return null;
        }
    }

    public User findById(Long userId) {
        User user = em.find(User.class, userId);
        return user;
    }
}
