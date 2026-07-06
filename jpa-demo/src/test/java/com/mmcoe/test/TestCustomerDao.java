package com.mmcoe.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.mmcoe.dao.CustomerDao;
import com.mmcoe.entity.Customer;

public class TestCustomerDao {

    private static CustomerDao dao;

    @BeforeAll
    public static void init() {
        dao = new CustomerDao();
    }

    @Test
    public void testSave() {

        Customer c = new Customer();
        c.setCustName("Shrikant");
        c.setCreditLimit(50000);

        Customer saved = dao.save(c);

        assertNotNull(saved);

        System.out.println(saved);
    }

    @Test
    public void testFind() {

        Customer c = dao.find(1);   // Change to an existing ID if needed

        assertNotNull(c);

        System.out.println(c);
    }

    @Test
    public void testList() {

        List<Customer> customers = dao.list();

        assertFalse(customers.isEmpty());

        customers.forEach(System.out::println);
    }

    @Test
    public void testDelete() {

        assertTrue(dao.delete(1));  // Change to an existing ID if needed
    }
}