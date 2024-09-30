package edu.sm.dao;

import edu.sm.dto.Wish;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WishDao implements Dao<Integer, Wish> {

    @Override
    public Wish insert(Wish wish, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(Sql.insertWish, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, wish.getCustId());
            pstmt.setInt(2, wish.getProductId());
            pstmt.setDate(3, new java.sql.Date(wish.getRegDate().getTime()));

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("찜 생성 실패, 영향받은 행이 없습니다.");
            }

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                wish.setWishId(rs.getInt(1));
            } else {
                throw new SQLException("찜 생성 실패, ID를 가져올 수 없습니다.");
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return wish;
    }

    @Override
    public Wish update(Wish wish, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.updateWish);
            pstmt.setInt(1, wish.getCustId());
            pstmt.setInt(2, wish.getProductId());
            pstmt.setDate(3, new java.sql.Date(wish.getRegDate().getTime()));
            pstmt.setInt(4, wish.getWishId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("찜 수정 실패, 해당 ID의 찜이 없습니다.");
            }
        } finally {
            if (pstmt != null) pstmt.close();
        }
        return wish;
    }

    @Override
    public boolean delete(Integer id, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.deleteWish);
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } finally {
            if (pstmt != null) pstmt.close();
        }
    }

    @Override
    public Wish select(Integer id, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Wish wish = null;
        try {
            pstmt = conn.prepareStatement(Sql.selectWish);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                wish = new Wish(
                    rs.getInt("wish_id"),
                    rs.getInt("cust_id"),
                    rs.getInt("product_id"),
                    rs.getDate("reg_date")
                );
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return wish;
    }

    @Override
    public List<Wish> select(Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Wish> wishes = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(Sql.selectAllWishes);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                wishes.add(new Wish(
                    rs.getInt("wish_id"),
                    rs.getInt("cust_id"),
                    rs.getInt("product_id"),
                    rs.getDate("reg_date")
                ));
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return wishes;
    }
}