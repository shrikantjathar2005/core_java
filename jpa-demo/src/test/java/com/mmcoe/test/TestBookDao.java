package com.mmcoe.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.mmcoe.dao.BookDao;
import com.mmcoe.entity.Book;

public class TestBookDao {

    private static BookDao dao;

    @BeforeAll
    public static void init() {

        dao = new BookDao();
    }

    @Test
    public void testSave() {

        Book b = new Book(
                101,
                "Core Java",
                "Cay Horstmann",
                799.0);

        assertNotNull(dao.save(b));
    }

    @Test
    public void testFind() {
        assertNotNull(dao.find(101));
    }

    @Test
    public void testList() {

        List<Book> books = dao.list();

        assertFalse(books.isEmpty());

        books.forEach(System.out::println);
    }

    @Test
    public void testDelete() {
        assertTrue(dao.delete(101));
    }
}