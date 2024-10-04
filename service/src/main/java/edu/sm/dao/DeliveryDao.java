package edu.sm.dao;

import edu.sm.dto.Delivery;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDao implements Dao<Integer, Delivery> {

    @Override
    public Delivery insert(Delivery delivery, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(Sql.insertDelivery, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, delivery.getOrderId());
            pstmt.setString(2, delivery.getStatus());
            pstmt.setString(3, delivery.getTrackingNumber());
            pstmt.setTimestamp(4, new Timestamp(delivery.getEstimatedDelivery().getTime()));
            pstmt.setTimestamp(5, delivery.getActualDelivery() != null ? new Timestamp(delivery.getActualDelivery().getTime()) : null);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("배송 정보 생성 실패, 영향받은 행이 없습니다.");
            }

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                delivery.setDeliveryId(rs.getInt(1));
            } else {
                throw new SQLException("배송 정보 생성 실패, ID를 가져올 수 없습니다.");
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return delivery;
    }

    @Override
    public Delivery update(Delivery delivery, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.editShippingInfo);
            pstmt.setString(1, delivery.getStatus());
            pstmt.setString(2, delivery.getTrackingNumber());
            pstmt.setTimestamp(3, new Timestamp(delivery.getEstimatedDelivery().getTime()));
            pstmt.setTimestamp(4, delivery.getActualDelivery() != null ? new Timestamp(delivery.getActualDelivery().getTime()) : null);
            pstmt.setInt(5, delivery.getDeliveryId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("배송 정보 수정 실패, 해당 ID의 배송이 없습니다.");
            }
        } finally {
            if (pstmt != null) pstmt.close();
        }
        return delivery;
    }

    @Override
    public boolean delete(Integer id, Connection conn) throws Exception {
        // 배송 정보 삭제 쿼리가 없으므로 구현하지 않았습니다.
        throw new UnsupportedOperationException("배송 정보 삭제 기능은 지원되지 않습니다.");
    }

    @Override
    public Delivery select(Integer id, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Delivery delivery = null;
        try {
            pstmt = conn.prepareStatement(Sql.viewShippingStatus);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                delivery = new Delivery(
                        rs.getInt("delivery_id"),
                        rs.getInt("order_id"),
                        rs.getString("status"),
                        rs.getString("tracking_number"),
                        rs.getTimestamp("estimated_delivery"),
                        rs.getTimestamp("actual_delivery")
                );
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return delivery;
    }

    @Override
    public List<Delivery> select(Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Delivery> deliveries = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(Sql.listDelivery);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                deliveries.add(new Delivery(
                        rs.getInt("delivery_id"),
                        rs.getInt("order_id"),
                        rs.getString("status"),
                        rs.getString("tracking_number"),
                        rs.getTimestamp("estimated_delivery"),
                        rs.getTimestamp("actual_delivery")
                ));
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return deliveries;
    }
    public Delivery viewShippingStatus(Integer orderId, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Delivery delivery = null;
        try {
            pstmt = conn.prepareStatement(Sql.viewShippingStatus);
            pstmt.setInt(1, orderId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                delivery = new Delivery(
                        rs.getInt("delivery_id"),
                        rs.getInt("order_id"),
                        rs.getString("status"),
                        rs.getString("tracking_number"),
                        rs.getTimestamp("estimated_delivery"),
                        rs.getTimestamp("actual_delivery")
                );
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return delivery;
    }

    public Delivery editShippingInfo(Delivery delivery, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.editShippingInfo);
            pstmt.setString(1, delivery.getStatus());
            pstmt.setString(2, delivery.getTrackingNumber());
            pstmt.setTimestamp(3, new Timestamp(delivery.getEstimatedDelivery().getTime()));
            pstmt.setTimestamp(4, delivery.getActualDelivery() != null ? new Timestamp(delivery.getActualDelivery().getTime()) : null);
            pstmt.setInt(5, delivery.getDeliveryId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("배송 정보 수정 실패, 해당 ID의 배송이 없습니다.");
            }
        } finally {
            if (pstmt != null) pstmt.close();
        }
        return delivery;
    }
}