package edu.sm.dao;

import edu.sm.dto.Order;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements Dao<Integer, Order> {

    @Override
    public Order insert(Order order, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(Sql.insertOrder, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, order.getCustId());
            pstmt.setInt(2, order.getProductCount());
            pstmt.setInt(3, order.getPrice());
            pstmt.setDate(4, new java.sql.Date(order.getOrderDate().getTime()));
            pstmt.setString(5, order.getName());
            pstmt.setString(6, order.getPhone());
            pstmt.setString(7, order.getAddress1());
            pstmt.setString(8, order.getAddress2());
            pstmt.setString(9, order.getZipCode());
            pstmt.setString(10, order.getRequest());
            pstmt.setString(11, order.getCard());
            pstmt.setInt(12, order.getUsedMileage());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("주문 생성 실패, 영향받은 행이 없습니다.");
            }

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                order.setOrderId(rs.getInt(1));
            } else {
                throw new SQLException("주문 생성 실패, ID를 가져올 수 없습니다.");
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return order;
    }

    @Override
    public Order update(Order order, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.editOrder);
            pstmt.setInt(1, order.getProductCount());
            pstmt.setInt(2, order.getPrice());
            pstmt.setString(3, order.getName());
            pstmt.setString(4, order.getPhone());
            pstmt.setString(5, order.getAddress1());
            pstmt.setString(6, order.getAddress2());
            pstmt.setString(7, order.getZipCode());
            pstmt.setString(8, order.getRequest());
            pstmt.setString(9, order.getCard());
            pstmt.setInt(10, order.getUsedMileage());
            pstmt.setInt(11, order.getOrderId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("주문 수정 실패, 해당 ID의 주문이 없습니다.");
            }
        } finally {
            if (pstmt != null) pstmt.close();
        }
        return order;
    }

    @Override
    public boolean delete(Integer id, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.deleteOrder);
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } finally {
            if (pstmt != null) pstmt.close();
        }
    }

    @Override
    public Order select(Integer id, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Order order = null;
        try {
            pstmt = conn.prepareStatement(Sql.searchOrders);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                order = new Order(
                    rs.getInt("order_id"),
                    rs.getInt("cust_id"),
                    rs.getInt("product_count"),
                    rs.getInt("price"),
                    rs.getDate("order_date"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("address1"),
                    rs.getString("address2"),
                    rs.getString("zip_code"),
                    rs.getString("request"),
                    rs.getString("card"),
                    rs.getInt("used_mileage")
                );
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return order;
    }

    @Override
    public List<Order> select(Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Order> orders = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(Sql.listOrders);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                orders.add(new Order(
                    rs.getInt("order_id"),
                    rs.getInt("cust_id"),
                    rs.getInt("product_count"),
                    rs.getInt("price"),
                    rs.getDate("order_date"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("address1"),
                    rs.getString("address2"),
                    rs.getString("zip_code"),
                    rs.getString("request"),
                    rs.getString("card"),
                    rs.getInt("used_mileage")
                ));
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return orders;
    }
}