package edu.sm.dao;

import edu.sm.dto.Product;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements Dao<Integer, Product> {

    @Override
    public Product insert(Product product, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.insertProduct, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, product.getCategoryId());
            pstmt.setString(2, product.getName());
            pstmt.setInt(3, product.getPrice());
            pstmt.setDate(4, new java.sql.Date(product.getRegDate().getTime()));
            pstmt.setString(5, product.getDescription());
            pstmt.setString(6, product.getImg1());
            pstmt.setString(7, product.getImg2());
            pstmt.setString(8, product.getImg3());
            pstmt.setString(9, product.getImg4());
            pstmt.setString(10, product.getImg5());
            pstmt.setInt(11, product.getCount());
            pstmt.setBoolean(12, product.isPublic());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                product.setProductId(rs.getInt(1));
            }
        } catch (Exception e) {
            throw new Exception("상품 등록 중 오류 발생: " + e.getMessage());
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return product;
    }

    @Override
    public Product update(Product product, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.updateProduct);
            pstmt.setInt(1, product.getCategoryId());
            pstmt.setString(2, product.getName());
            pstmt.setInt(3, product.getPrice());
            pstmt.setString(4, product.getDescription());
            pstmt.setString(5, product.getImg1());
            pstmt.setString(6, product.getImg2());
            pstmt.setString(7, product.getImg3());
            pstmt.setString(8, product.getImg4());
            pstmt.setString(9, product.getImg5());
            pstmt.setInt(10, product.getCount());
            pstmt.setBoolean(11, product.isPublic());
            pstmt.setInt(12, product.getProductId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception("상품 정보 수정 중 오류 발생: " + e.getMessage());
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return product;
    }

    @Override
    public boolean delete(Integer id, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.deleteProduct);
            pstmt.setInt(1, id);
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            throw new Exception("상품 삭제 중 오류 발생: " + e.getMessage());
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    @Override
    public Product select(Integer id, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Product product = null;
        try {
            pstmt = conn.prepareStatement(Sql.selectOneProduct);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                product = createProductFromResultSet(rs);
            }
        } catch (Exception e) {
            throw new Exception("상품 조회 중 오류 발생: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return product;
    }

    @Override
    public List<Product> select(Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(Sql.selectProduct);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(createProductFromResultSet(rs));
            }
        } catch (Exception e) {
            throw new Exception("전체 상품 조회 중 오류 발생: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return list;
    }

    private Product createProductFromResultSet(ResultSet rs) throws SQLException {
        return Product.builder()
                .productId(rs.getInt("product_id"))
                .categoryId(rs.getInt("category_id"))
                .name(rs.getString("name"))
                .price(rs.getInt("price"))
                .regDate(rs.getDate("reg_date"))
                .description(rs.getString("description"))
                .img1(rs.getString("img1"))
                .img2(rs.getString("img2"))
                .img3(rs.getString("img3"))
                .img4(rs.getString("img4"))
                .img5(rs.getString("img5"))
                .count(rs.getInt("count"))
                .isPublic(rs.getBoolean("is_public"))
                .build();
    }
}