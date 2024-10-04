package edu.sm.dao;

import edu.sm.dto.Payment;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDao implements Dao<Integer, Payment> {

    @Override
    public Payment insert(Payment payment, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(Sql.insertPayment, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, payment.getOrderId());
            pstmt.setInt(2, payment.getPrice());
            pstmt.setString(3, payment.getMethod());
            pstmt.setDate(4, new java.sql.Date(payment.getPayDate().getTime()));

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("결제 생성 실패, 영향받은 행이 없습니다.");
            }

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                payment.setPaymentId(rs.getInt(1));
            } else {
                throw new SQLException("결제 생성 실패, ID를 가져올 수 없습니다.");
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return payment;
    }

    @Override
    public Payment update(Payment payment, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.updatePayment);
            pstmt.setInt(1, payment.getPrice());
            pstmt.setString(2, payment.getMethod());
            pstmt.setDate(3, new java.sql.Date(payment.getPayDate().getTime()));
            pstmt.setInt(4, payment.getPaymentId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("결제 수정 실패, 해당 ID의 결제가 없습니다.");
            }
        } finally {
            if (pstmt != null) pstmt.close();
        }
        return payment;
    }

    @Override
    public boolean delete(Integer id, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.deletePayment);
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } finally {
            if (pstmt != null) pstmt.close();
        }
    }

    @Override
    public Payment select(Integer id, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Payment payment = null;
        try {
            pstmt = conn.prepareStatement(Sql.selectPayment);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                payment = new Payment(
                    rs.getInt("payment_id"),
                    rs.getInt("order_id"),
                    rs.getInt("price"),
                    rs.getString("method"),
                    rs.getDate("pay_date")
                );
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return payment;
    }

    @Override
    public List<Payment> select(Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Payment> payments = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(Sql.selectAllPayments);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                payments.add(new Payment(
                    rs.getInt("payment_id"),
                    rs.getInt("order_id"),
                    rs.getInt("price"),
                    rs.getString("method"),
                    rs.getDate("pay_date")
                ));
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return payments;
    }
}