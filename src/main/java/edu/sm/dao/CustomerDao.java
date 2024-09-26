package edu.sm.dao;



import edu.sm.dto.Customer;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CustomerDao implements Dao<Integer, Customer> {

    @Override
    public Customer insert(Customer customer, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.insertCustomer, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, customer.getUsername());
            pstmt.setString(2, customer.getPw());
            pstmt.setString(3, customer.getName());
            pstmt.setString(4, customer.getPNumber());
            pstmt.setDate(5, new java.sql.Date(customer.getSignUpDate().getTime()));

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                customer.setCustId(rs.getInt(1));
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicatedIdException("EX0001");
        } catch (Exception e) {
            throw new Exception("고객 등록 중 오류 발생: " + e.getMessage());
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return customer;
    }

    @Override
    public Customer update(Customer customer, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.updateCustomer);
            pstmt.setString(1, customer.getPw());
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getPNumber());
            pstmt.setInt(4, customer.getCustId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception("고객 정보 수정 중 오류 발생: " + e.getMessage());
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return customer;
    }

    @Override
    public boolean delete(Integer id, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.deleteCustomer);
            pstmt.setInt(1, id);
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            throw new Exception("고객 삭제 중 오류 발생: " + e.getMessage());
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    @Override
    public Customer select(Integer id, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Customer customer = null;
        try {
            pstmt = conn.prepareStatement(Sql.selectOneCustomer);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                customer = new Customer(
                        rs.getInt("cust_id"),
                        rs.getString("username"),
                        rs.getString("pw"),
                        rs.getString("name"),
                        rs.getString("p_number"),
                        rs.getDate("signup_date")
                );
            }
        } catch (Exception e) {
            throw new Exception("고객 조회 중 오류 발생: " + e.getMessage());
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
        List<Customer> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(Sql.selectCustomer);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("cust_id"),
                        rs.getString("username"),
                        rs.getString("pw"),
                        rs.getString("name"),
                        rs.getString("p_number"),
                        rs.getDate("signup_date")
                );
                list.add(customer);
            }
        } catch (Exception e) {
            throw new Exception("전체 고객 조회 중 오류 발생: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return list;
    }
}