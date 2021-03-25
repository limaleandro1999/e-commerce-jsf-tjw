package com.ecommerce.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BasicDAO {
    protected EntityManager em;

    public BasicDAO() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("e-commerce");
        this.em = factory.createEntityManager();
    }
}
