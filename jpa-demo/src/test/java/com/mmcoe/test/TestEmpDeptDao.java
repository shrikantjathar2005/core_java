package com.mmcoe.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.mmcoe.dao.EmpDeptDao;
import com.mmcoe.entity.Department;
import com.mmcoe.entity.Employee;

public class TestEmpDeptDao {

    private static EmpDeptDao dao;

    @BeforeAll
    public static void init() {
        dao = new EmpDeptDao();
    }


    @Test
    public void testSaveDept() {

        Department d = new Department(50, "Marketing", "Pune");

        assertNotNull(dao.saveDept(d));
    }

    @Test
    public void testSaveEmp() {

        Employee e = new Employee(111, "Rohit", 6500);

        assertNotNull(dao.saveEmp(e, 20));
    }

    @Test
    public void testFindDept() {

        Department d = dao.findDept(20);

        assertNotNull(d);
    }

    @Test
    public void testFindEmp() {

        Employee e = dao.findEmp(101);

        assertNotNull(e);
    }
    }
