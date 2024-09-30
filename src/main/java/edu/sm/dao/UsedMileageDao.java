package edu.sm.dao;

import edu.sm.dto.UsedMileage;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsedMileageDao implements Dao<Integer, UsedMileage> {

    @Override
    public UsedMileage insert(UsedMileage usedMileage, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(Sql.insertUsedMileage, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, usedMileage.getCustId());
            pstmt.setDate(2, new java.sql.Date(usedMileage.getUsedDate().getTime()));
            pstmt.setInt(3, usedMileage.getAmount());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("사용된 마일리지 생성 실패, 영향받은 행이 없습니다.");
            }

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                usedMileage.setUsedMileageId(rs.getInt(1));
            } else {
                throw new SQLException("사용된 마일리지 생성 실패, ID를 가져올 수 없습니다.");
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return usedMileage;
    }

    @Override
    public UsedMileage update(UsedMileage usedMileage, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.updateUsedMileage);
            pstmt.setInt(1, usedMileage.getCustId());
            pstmt.setDate(2, new java.sql.Date(usedMileage.getUsedDate().getTime()));
            pstmt.setInt(3, usedMileage.getAmount());
            pstmt.setInt(4, usedMileage.getUsedMileageId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("사용된 마일리지 수정 실패, 해당 ID의 사용된 마일리지가 없습니다.");
            }
        } finally {
            if (pstmt != null) pstmt.close();
        }
        return usedMileage;
    }

    @Override
    public boolean delete(Integer id, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.deleteUsedMileage);
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } finally {
            if (pstmt != null) pstmt.close();
        }
    }

    @Override
    public UsedMileage select(Integer id, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UsedMileage usedMileage = null;
        try {
            pstmt = conn.prepareStatement(Sql.selectUsedMileage);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                usedMileage = new UsedMileage(
                    rs.getInt("used_mileage_id"),
                    rs.getInt("cust_id"),
                    rs.getDate("used_date"),
                    rs.getInt("amount")
                );
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return usedMileage;
    }

    @Override
    public List<UsedMileage> select(Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<UsedMileage> usedMileages = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(Sql.selectAllUsedMileages);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                usedMileages.add(new UsedMileage(
                    rs.getInt("used_mileage_id"),
                    rs.getInt("cust_id"),
                    rs.getDate("used_date"),
                    rs.getInt("amount")
                ));
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return usedMileages;
    }
}