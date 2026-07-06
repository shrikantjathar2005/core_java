package com.mmcoe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.mmcoe.entity.Customer;

public class CustomerDao {

    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("MyJPA");
    }

    public Customer save(Customer c) {

        EntityManager mgr = emf.createEntityManager();
        EntityTransaction txn = mgr.getTransaction();

        try {
            txn.begin();
            mgr.persist(c);
            txn.commit();
            return c;
        } catch (Exception e) {
            if (txn.isActive())
                txn.rollback();
            e.printStackTrace();
            return null;
        } finally {
            mgr.close();
        }
    }

    public Customer find(int id) {

        EntityManager mgr = emf.createEntityManager();

        try {
            return mgr.find(Customer.class, id);
        } finally {
            mgr.close();
        }
    }

    public List<Customer> list() {

        EntityManager mgr = emf.createEntityManager();

        try {
            return mgr.createQuery("FROM Customer", Customer.class)
                    .getResultList();
        } finally {
            mgr.close();
        }
    }

    public boolean delete(int id) {

        EntityManager mgr = emf.createEntityManager();
        EntityTransaction txn = mgr.getTransaction();

        try {

            txn.begin();

            Customer c = mgr.find(Customer.class, id);

            if (c == null) {
                txn.rollback();
                return false;
            }

            mgr.remove(c);

            txn.commit();

            return true;

        } catch (Exception e) {

            if (txn.isActive())
                txn.rollback();

            e.printStackTrace();
            return false;

        } finally {
            mgr.close();
        }
    }
}