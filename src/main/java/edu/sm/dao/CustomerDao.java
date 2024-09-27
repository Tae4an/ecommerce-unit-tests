package edu.sm.dao;

import edu.sm.dto.Customer;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements Dao<Integer, Customer> {

    @Override
    public Customer insert(Customer customer, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(Sql.insertCustomer, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, customer.getUsername());
            pstmt.setString(2, customer.getPw());
            pstmt.setString(3, customer.getName());
            pstmt.setString(4, customer.getPNumber());
            pstmt.setDate(5, new java.sql.Date(customer.getSignUpDate().getTime()));
            pstmt.setString(6,"CUST");

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("고객 생성 실패, 영향받은 행이 없습니다.");
            }

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                customer.setCustId(rs.getInt(1));
            } else {
                throw new SQLException("고객 생성 실패, ID를 가져올 수 없습니다.");
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return customer;
    }

    @Override
    public Customer update(Customer customer, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.editMemberInfo);
            pstmt.setString(1, customer.getUsername());
            pstmt.setString(2, customer.getPw());
            pstmt.setString(3, customer.getName());
            pstmt.setString(4, customer.getPNumber());
            pstmt.setInt(5, customer.getCustId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("고객 정보 수정 실패, 해당 ID의 고객이 없습니다.");
            }
        } finally {
            if (pstmt != null) pstmt.close();
        }
        return customer;
    }

    @Override
    public boolean delete(Integer id, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.deleteMember);
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } finally {
            if (pstmt != null) pstmt.close();
        }
    }

    @Override
    public Customer select(Integer id, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Customer customer = null;
        try {
            pstmt = conn.prepareStatement(Sql.viewMemberDetails);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                customer = new Customer(
                        rs.getInt("cust_id"),
                        rs.getString("username"),
                        rs.getString("pw"),
                        rs.getString("name"),
                        rs.getString("p_number"),
                        rs.getDate("signup_date"),
                        rs.getString("role")
                );
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return customer;
    }

    @Override
    public List<Customer> select(Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Customer> customers = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(Sql.listMembers);
            pstmt.setInt(1, 10); // 예시로 10개씩 조회
            pstmt.setInt(2, 0);  // 첫 페이지
            rs = pstmt.executeQuery();
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("cust_id"),
                        rs.getString("username"),
                        rs.getString("pw"),
                        rs.getString("name"),
                        rs.getString("p_number"),
                        rs.getDate("signup_date"),
                        rs.getString("role")
                ));
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return customers;
    }

    public List<Customer> searchMembers(String keyword, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Customer> customers = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(Sql.searchMembers);
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            pstmt.setString(3, "%" + keyword + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("cust_id"),
                        rs.getString("username"),
                        rs.getString("pw"),
                        rs.getString("name"),
                        rs.getString("p_number"),
                        rs.getDate("signup_date"),
                        rs.getString("role")
                ));
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return customers;
    }
}
