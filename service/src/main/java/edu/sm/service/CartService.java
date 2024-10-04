package edu.sm.service;

import edu.sm.dao.CartDao;
import edu.sm.dto.Cart;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CartService implements MService<Integer, Cart> {

    CartDao dao;
    ConnectionPool cp;

    public CartService() {
        dao = new CartDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Cart add(Cart cart) throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            cart = dao.insert(cart, con);
            System.out.println("New cart item added with ID: " + cart.getCartId());
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return cart;
    }

    @Override
    public Cart modify(Cart cart) throws Exception {
        Connection con = cp.getConnection();
        try {
            cart = dao.update(cart, con);
            System.out.println("Cart item updated: " + cart.getCartId());
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return cart;
    }

    @Override
    public Boolean remove(Integer id) throws Exception {
        Connection con = cp.getConnection();
        Boolean result = false;
        try {
            result = dao.delete(id, con);
            System.out.println("Cart item removed: " + id);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public Cart get(Integer id) throws Exception {
        Connection con = cp.getConnection();
        Cart result = null;
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
    public List<Cart> get() throws Exception {
        Connection con = cp.getConnection();
        List<Cart> result = null;
        try {
            result = dao.select(con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }
}