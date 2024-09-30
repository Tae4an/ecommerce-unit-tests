package edu.sm.dao;

import edu.sm.dto.Mileage;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MileageDao implements Dao<Integer, Mileage> {

    @Override
    public Mileage insert(Mileage mileage, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(Sql.insertMileage, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, mileage.getCustId());
            pstmt.setInt(2, mileage.getBalance());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("마일리지 생성 실패, 영향받은 행이 없습니다.");
            }

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                mileage.setMileageId(rs.getInt(1));
            } else {
                throw new SQLException("마일리지 생성 실패, ID를 가져올 수 없습니다.");
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return mileage;
    }

    @Override
    public Mileage update(Mileage mileage, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.updateMileage);
            pstmt.setInt(1, mileage.getBalance());
            pstmt.setInt(2, mileage.getMileageId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("마일리지 수정 실패, 해당 ID의 마일리지가 없습니다.");
            }
        } finally {
            if (pstmt != null) pstmt.close();
        }
        return mileage;
    }

    @Override
    public boolean delete(Integer id, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.deleteMileage);
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } finally {
            if (pstmt != null) pstmt.close();
        }
    }

    @Override
    public Mileage select(Integer id, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Mileage mileage = null;
        try {
            pstmt = conn.prepareStatement(Sql.selectMileage);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                mileage = new Mileage(
                    rs.getInt("mileage_id"),
                    rs.getInt("cust_id"),
                    rs.getInt("balance")
                );
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return mileage;
    }

    @Override
    public List<Mileage> select(Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Mileage> mileages = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(Sql.selectAllMileages);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                mileages.add(new Mileage(
                    rs.getInt("mileage_id"),
                    rs.getInt("cust_id"),
                    rs.getInt("balance")
                ));
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return mileages;
    }
    public Mileage selectByCustId(Integer custId, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Mileage mileage = null;
        try {
            pstmt = conn.prepareStatement(Sql.selectMileageByCustId);
            pstmt.setInt(1, custId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                mileage = new Mileage(
                        rs.getInt("mileage_id"),
                        rs.getInt("cust_id"),
                        rs.getInt("balance")
                );
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return mileage;
    }
}