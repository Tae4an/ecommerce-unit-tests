package edu.sm.service;


import edu.sm.dao.CustomerDao;
import edu.sm.dto.Customer;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerService implements MService<Integer, Customer> {

    CustomerDao dao;
    ConnectionPool cp;

    public CustomerService() {
        dao = new CustomerDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer add(Customer customer) throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            customer = dao.insert(customer, con);
            System.out.println("Send SMS to: " + customer.getPNumber());
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return customer;
    }

    @Override
    public Customer modify(Customer customer) throws Exception {
        Connection con = cp.getConnection();
        try {
            customer = dao.update(customer, con);
            System.out.println("Send SMS to: " + customer.getPNumber());
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return customer;
    }

    @Override
    public Boolean remove(Integer id) throws Exception {
        Connection con = cp.getConnection();
        Boolean result = false;
        try {
            result = dao.delete(id, con);
            System.out.println("Customer removed: " + id);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public Customer get(Integer id) throws Exception {
        Connection con = cp.getConnection();
        Customer result = null;
        try {
            result = dao.select(id, con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public List<Customer> get() throws Exception {
        Connection con = cp.getConnection();
        List<Customer> result = null;
        try {
            result = dao.select(con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }
    public List<Customer> searchMembers(String keyword) throws Exception {
        Connection con = cp.getConnection();
        List<Customer> result = null;
        try {
            result = dao.searchMembers(keyword, con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }
}