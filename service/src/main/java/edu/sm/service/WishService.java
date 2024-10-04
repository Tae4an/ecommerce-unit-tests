package edu.sm.service;

import edu.sm.dao.WishDao;
import edu.sm.dto.Wish;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class WishService implements MService<Integer, Wish> {

    WishDao dao;
    ConnectionPool cp;

    public WishService() {
        dao = new WishDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Wish add(Wish wish) throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            wish = dao.insert(wish, con);
            System.out.println("New wish created with ID: " + wish.getWishId());
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return wish;
    }

    @Override
    public Wish modify(Wish wish) throws Exception {
        Connection con = cp.getConnection();
        try {
            wish = dao.update(wish, con);
            System.out.println("Wish updated: " + wish.getWishId());
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return wish;
    }

    @Override
    public Boolean remove(Integer id) throws Exception {
        Connection con = cp.getConnection();
        Boolean result = false;
        try {
            result = dao.delete(id, con);
            System.out.println("Wish removed: " + id);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public Wish get(Integer id) throws Exception {
        Connection con = cp.getConnection();
        Wish result = null;
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
    public List<Wish> get() throws Exception {
        Connection con = cp.getConnection();
        List<Wish> result = null;
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