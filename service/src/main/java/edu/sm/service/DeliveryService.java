package edu.sm.service;

import edu.sm.dao.DeliveryDao;
import edu.sm.dto.Delivery;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class DeliveryService implements MService<Integer, Delivery> {

    DeliveryDao dao;
    ConnectionPool cp;

    public DeliveryService() {
        dao = new DeliveryDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Delivery add(Delivery delivery) throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            delivery = dao.insert(delivery, con);
            System.out.println("New delivery created with ID: " + delivery.getDeliveryId());
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return delivery;
    }

    @Override
    public Delivery modify(Delivery delivery) throws Exception {
        Connection con = cp.getConnection();
        try {
            delivery = dao.update(delivery, con);
            System.out.println("Delivery updated: " + delivery.getDeliveryId());
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return delivery;
    }

    @Override
    public Boolean remove(Integer id) throws Exception {
        throw new UnsupportedOperationException("배송 정보 삭제 기능은 지원되지 않습니다.");
    }

    @Override
    public Delivery get(Integer id) throws Exception {
        Connection con = cp.getConnection();
        Delivery result = null;
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
    public List<Delivery> get() throws Exception {
        Connection con = cp.getConnection();
        List<Delivery> result = null;
        try {
            result = dao.select(con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    public List<Delivery> getByOrderId(Integer orderId) throws Exception {
        Connection con = cp.getConnection();
        List<Delivery> result = null;
        try {
            result = dao.select(con);
            result.removeIf(delivery -> !Objects.equals(delivery.getOrderId(), orderId));
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    public Delivery viewShippingStatus(Integer orderId) throws Exception {
        Connection con = cp.getConnection();
        Delivery result = null;
        try {
            result = dao.viewShippingStatus(orderId, con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    public Delivery editShippingInfo(Delivery delivery) throws Exception {
        Connection con = cp.getConnection();
        try {
            delivery = dao.editShippingInfo(delivery, con);
            System.out.println("Shipping info updated for delivery: " + delivery.getDeliveryId());
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return delivery;
    }
}