package edu.sm.service;

import edu.sm.dao.ProductDao;
import edu.sm.dto.Product;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductService implements MService<Integer, Product> {

    ProductDao dao;
    ConnectionPool cp;

    public ProductService() {
        dao = new ProductDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product add(Product product) throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            product = dao.insert(product, con);
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return product;
    }

    @Override
    public Product modify(Product product) throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            product = dao.update(product, con);
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return product;
    }

    @Override
    public Boolean remove(Integer id) throws Exception {
        Connection con = cp.getConnection();
        Boolean result = false;
        try {
            con.setAutoCommit(false);
            result = dao.delete(id, con);
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public Product get(Integer id) throws Exception {
        Connection con = cp.getConnection();
        Product result = null;
        try {
            result = dao.select(id, con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public List<Product> get() throws Exception {
        Connection con = cp.getConnection();
        List<Product> result = null;
        try {
            result = dao.select(con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    public List<Product> getPopularProducts() throws Exception {
        Connection con = cp.getConnection();
        List<Product> result = null;
        try {
            result = dao.getPopularProducts(con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    public Product showProductDetails(Integer productId) throws Exception {
        Connection con = cp.getConnection();
        Product result = null;
        try {
            result = dao.showProductDetails(productId, con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }


}