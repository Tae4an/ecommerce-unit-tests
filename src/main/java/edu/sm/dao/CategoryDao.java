package edu.sm.dao;

import edu.sm.dto.Category;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.*;
import java.util.List;

public class CategoryDao implements Dao<Integer, Category> {

    @Override
    public Category insert(Category category, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(Sql.addCategory, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, category.getName());
            pstmt.setInt(2, category.getCategoryId2());  // parent_id에 해당

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("카테고리 생성 실패, 영향받은 행이 없습니다.");
            }

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                category.setCategoryId(rs.getInt(1));
            } else {
                throw new SQLException("카테고리 생성 실패, ID를 가져올 수 없습니다.");
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return category;
    }

    @Override
    public Category update(Category category, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.editCategory);
            pstmt.setString(1, category.getName());
            pstmt.setInt(2, category.getCategoryId2());
            pstmt.setInt(3, category.getCategoryId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("카테고리 수정 실패, 해당 ID의 카테고리가 없습니다.");
            }
        } finally {
            if (pstmt != null) pstmt.close();
        }
        return category;
    }

    @Override
    public boolean delete(Integer id, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.deleteCategory);
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            System.out.println("Affected rows: " + affectedRows);  // 로깅 추가
            return affectedRows > 0;
        } finally {
            if (pstmt != null) pstmt.close();
        }
    }

    @Override
    public Category select(Integer integer, Connection conn) throws Exception {
        return null;
    }

    @Override
    public List<Category> select(Connection conn) throws Exception {
        return List.of();
    }
}
