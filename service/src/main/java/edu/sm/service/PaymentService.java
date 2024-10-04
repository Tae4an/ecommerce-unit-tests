package edu.sm.service;

import edu.sm.dao.PaymentDao;
import edu.sm.dto.Payment;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PaymentService implements MService<Integer, Payment> {

    PaymentDao dao;
    ConnectionPool cp;

    public PaymentService() {
        dao = new PaymentDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Payment add(Payment payment) throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            payment = dao.insert(payment, con);
            System.out.println("New payment created with ID: " + payment.getPaymentId());
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return payment;
    }

    @Override
    public Payment modify(Payment payment) throws Exception {
        Connection con = cp.getConnection();
        try {
            payment = dao.update(payment, con);
            System.out.println("Payment updated: " + payment.getPaymentId());
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return payment;
    }

    @Override
    public Boolean remove(Integer id) throws Exception {
        Connection con = cp.getConnection();
        Boolean result = false;
        try {
            result = dao.delete(id, con);
            System.out.println("Payment removed: " + id);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public Payment get(Integer id) throws Exception {
        Connection con = cp.getConnection();
        Payment result = null;
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
    public List<Payment> get() throws Exception {
        Connection con = cp.getConnection();
        List<Payment> result = null;
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