package com.mmcoe.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.mmcoe.entity.Department;
import com.mmcoe.entity.Employee;

public class EmpDeptDao {

    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("MyJPA");
    }

    public Department saveDept(Department d) {

        EntityManager mgr = emf.createEntityManager();
        EntityTransaction txn = mgr.getTransaction();

        try {

            txn.begin();

            mgr.persist(d);

            txn.commit();

            return d;

        } catch (Exception e) {

            if (txn.isActive())
                txn.rollback();

            e.printStackTrace();
            return null;

        } finally {

            mgr.close();

        }
    }

    public Employee saveEmp(Employee e, int deptNo) {

        EntityManager mgr = emf.createEntityManager();
        EntityTransaction txn = mgr.getTransaction();

        try {

            txn.begin();

            Department d = mgr.find(Department.class, deptNo);

            if (d == null) {
                txn.rollback();
                return null;
            }

            e.setDept(d);

            mgr.persist(e);

            txn.commit();

            return e;

        } catch (Exception ex) {

            if (txn.isActive())
                txn.rollback();

            ex.printStackTrace();
            return null;

        } finally {

            mgr.close();

        }
    }

    public Employee findEmp(int empNo) {

        EntityManager mgr = emf.createEntityManager();

        try {
            return mgr.find(Employee.class, empNo);
        } finally {
            mgr.close();
        }
    }

    public Department findDept(int deptNo) {

        EntityManager mgr = emf.createEntityManager();

        try {
            return mgr.find(Department.class, deptNo);
        } finally {
            mgr.close();
        }
        
    }
    
    public boolean deleteDept(int deptNo) {

        EntityManager mgr = emf.createEntityManager();
        EntityTransaction txn = mgr.getTransaction();

        try {

            txn.begin();

            Department d = mgr.find(Department.class, deptNo);

            if (d == null) {
                txn.rollback();
                return false;
            }

            mgr.remove(d);

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
    
    public boolean deleteEmp(int empNo) {

        EntityManager mgr = emf.createEntityManager();
        EntityTransaction txn = mgr.getTransaction();

        try {

            txn.begin();

            Employee e = mgr.find(Employee.class, empNo);

            if (e == null) {
                txn.rollback();
                return false;
            }

            mgr.remove(e);

            txn.commit();

            return true;

        } catch (Exception ex) {

            if (txn.isActive())
                txn.rollback();

            ex.printStackTrace();

            return false;

        } finally {

            mgr.close();

        }
    }
}