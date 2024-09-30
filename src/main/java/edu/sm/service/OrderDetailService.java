package edu.sm.service;

import edu.sm.dao.OrderDetailDao;
import edu.sm.dto.OrderDetail;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailService implements MService<Integer, OrderDetail> {

    OrderDetailDao dao;
    ConnectionPool cp;

    public OrderDetailService() {
        dao = new OrderDetailDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderDetail add(OrderDetail orderDetail) throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            orderDetail = dao.insert(orderDetail, con);
            System.out.println("New order detail created with ID: " + orderDetail.getOrderDetailId());
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return orderDetail;
    }

    @Override
    public OrderDetail modify(OrderDetail orderDetail) throws Exception {
        Connection con = cp.getConnection();
        try {
            orderDetail = dao.update(orderDetail, con);
            System.out.println("Order detail updated: " + orderDetail.getOrderDetailId());
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return orderDetail;
    }

    @Override
    public Boolean remove(Integer id) throws Exception {
        Connection con = cp.getConnection();
        Boolean result = false;
        try {
            result = dao.delete(id, con);
            System.out.println("Order detail removed: " + id);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public OrderDetail get(Integer id) throws Exception {
        Connection con = cp.getConnection();
        OrderDetail result = null;
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
    public List<OrderDetail> get() throws Exception {
        Connection con = cp.getConnection();
        List<OrderDetail> result = null;
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