package edu.sm.dao;

import edu.sm.dto.OrderDetail;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDao implements Dao<Integer, OrderDetail> {

    @Override
    public OrderDetail insert(OrderDetail orderDetail, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(Sql.insertOrderDetail, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, orderDetail.getProductId());
            pstmt.setInt(2, orderDetail.getOrderId());
            pstmt.setInt(3, orderDetail.getPrice());
            pstmt.setInt(4, orderDetail.getCount());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("주문 상세 생성 실패, 영향받은 행이 없습니다.");
            }

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                orderDetail.setOrderDetailId(rs.getInt(1));
            } else {
                throw new SQLException("주문 상세 생성 실패, ID를 가져올 수 없습니다.");
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return orderDetail;
    }

    @Override
    public OrderDetail update(OrderDetail orderDetail, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.updateOrderDetail);
            pstmt.setInt(1, orderDetail.getPrice());
            pstmt.setInt(2, orderDetail.getCount());
            pstmt.setInt(3, orderDetail.getOrderDetailId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("주문 상세 수정 실패, 해당 ID의 주문 상세가 없습니다.");
            }
        } finally {
            if (pstmt != null) pstmt.close();
        }
        return orderDetail;
    }

    @Override
    public boolean delete(Integer id, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.deleteOrderDetail);
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } finally {
            if (pstmt != null) pstmt.close();
        }
    }

    @Override
    public OrderDetail select(Integer id, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        OrderDetail orderDetail = null;
        try {
            pstmt = conn.prepareStatement(Sql.selectOrderDetail);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                orderDetail = new OrderDetail(
                    rs.getInt("order_detail_id"),
                    rs.getInt("product_id"),
                    rs.getInt("order_id"),
                    rs.getInt("price"),
                    rs.getInt("count")
                );
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return orderDetail;
    }

    @Override
    public List<OrderDetail> select(Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<OrderDetail> orderDetails = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(Sql.selectAllOrderDetails);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                orderDetails.add(new OrderDetail(
                    rs.getInt("order_detail_id"),
                    rs.getInt("product_id"),
                    rs.getInt("order_id"),
                    rs.getInt("price"),
                    rs.getInt("count")
                ));
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return orderDetails;
    }
}