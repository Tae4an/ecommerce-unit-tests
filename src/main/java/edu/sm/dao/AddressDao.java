package edu.sm.dao;

import edu.sm.dto.Address;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDao implements Dao<Integer, Address> {

    @Override
    public Address insert(Address address, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(Sql.addAddress, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, address.getCustId());
            pstmt.setString(2, address.getName());
            pstmt.setString(3, address.getPhone());
            pstmt.setString(4, address.getAddress());
            pstmt.setString(5, address.getAddressDetail());
            pstmt.setString(6, address.getZipCode());
            pstmt.setString(7, address.getRequest());
            pstmt.setString(8, address.getDef());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("배송지 추가 실패, 영향받은 행이 없습니다.");
            }

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                address.setAddressId(rs.getInt(1));
            } else {
                throw new SQLException("배송지 추가 실패, ID를 가져올 수 없습니다.");
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return address;
    }

    @Override
    public Address update(Address address, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.editAddress);
            pstmt.setString(1, address.getName());
            pstmt.setString(2, address.getPhone());
            pstmt.setString(3, address.getAddress());
            pstmt.setString(4, address.getAddressDetail());
            pstmt.setString(5, address.getZipCode());
            pstmt.setString(6, address.getRequest());
            pstmt.setInt(7, address.getAddressId());
            pstmt.setInt(8, address.getCustId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("배송지 수정 실패, 해당 ID의 배송지가 없습니다.");
            }
        } finally {
            if (pstmt != null) pstmt.close();
        }
        return address;
    }

    @Override
    public boolean delete(Integer addressKey, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.deleteAddress);
            pstmt.setInt(1, addressKey);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } finally {
            if (pstmt != null) pstmt.close();
        }
    }

    @Override
    public Address select(Integer addressKey, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Address address = null;
        try {
            pstmt = conn.prepareStatement(Sql.listAddressesDetails);
            pstmt.setInt(1, addressKey);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                address = new Address(
                    rs.getInt("address_key"),
                    rs.getInt("cust_id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getString("address_detail"),
                    rs.getString("zip_code"),
                    rs.getString("request"),
                    rs.getString("def")
                );
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return address;
    }

    @Override
    public List<Address> select(Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Address> addresses = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(Sql.listAddresses);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                addresses.add(new Address(
                    rs.getInt("address_key"),
                    rs.getInt("cust_id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getString("address_detail"),
                    rs.getString("zip_code"),
                    rs.getString("request"),
                    rs.getString("def")
                ));
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return addresses;
    }

    public boolean setDefaultAddress(Integer addressKey, Integer custId, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.setDefaultAddress);
            pstmt.setInt(1, addressKey);
            pstmt.setInt(2, custId);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } finally {
            if (pstmt != null) pstmt.close();
        }
    }
}