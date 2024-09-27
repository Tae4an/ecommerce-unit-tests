package edu.sm.service;

import edu.sm.dao.OrderDao;
import edu.sm.dto.Order;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderService implements MService<Integer, Order> {

    OrderDao dao;
    ConnectionPool cp;

    public OrderService() {
        dao = new OrderDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order add(Order order) throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            order = dao.insert(order, con);
            System.out.println("New order created with ID: " + order.getOrderId());
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return order;
    }

    @Override
    public Order modify(Order order) throws Exception {
        Connection con = cp.getConnection();
        try {
            order = dao.update(order, con);
            System.out.println("Order updated: " + order.getOrderId());
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return order;
    }

    @Override
    public Boolean remove(Integer id) throws Exception {
        Connection con = cp.getConnection();
        Boolean result = false;
        try {
            result = dao.delete(id, con);
            System.out.println("Order removed: " + id);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public Order get(Integer id) throws Exception {
        Connection con = cp.getConnection();
        Order result = null;
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
    public List<Order> get() throws Exception {
        Connection con = cp.getConnection();
        List<Order> result = null;
        try {
            result = dao.select(con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }
}