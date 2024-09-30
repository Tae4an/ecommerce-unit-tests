package edu.sm.service;

import edu.sm.dao.MileageDao;
import edu.sm.dto.Mileage;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MileageService implements MService<Integer, Mileage> {

    MileageDao dao;
    ConnectionPool cp;

    public MileageService() {
        dao = new MileageDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mileage add(Mileage mileage) throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            mileage = dao.insert(mileage, con);
            System.out.println("New mileage created with ID: " + mileage.getMileageId());
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return mileage;
    }

    @Override
    public Mileage modify(Mileage mileage) throws Exception {
        Connection con = cp.getConnection();
        try {
            mileage = dao.update(mileage, con);
            System.out.println("Mileage updated: " + mileage.getMileageId());
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return mileage;
    }

    @Override
    public Boolean remove(Integer id) throws Exception {
        Connection con = cp.getConnection();
        Boolean result = false;
        try {
            result = dao.delete(id, con);
            System.out.println("Mileage removed: " + id);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public Mileage get(Integer id) throws Exception {
        Connection con = cp.getConnection();
        Mileage result = null;
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
    public List<Mileage> get() throws Exception {
        Connection con = cp.getConnection();
        List<Mileage> result = null;
        try {
            result = dao.select(con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    public Mileage addMileagePoints(Integer custId, Integer points) throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            Mileage mileage = dao.selectByCustId(custId, con);
            if (mileage == null) {
                throw new Exception("Customer mileage not found");
            }
            mileage.setBalance(mileage.getBalance() + points);
            mileage = dao.update(mileage, con);
            con.commit();
            return mileage;
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
    }
}