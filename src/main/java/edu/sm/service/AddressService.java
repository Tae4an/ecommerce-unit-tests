package edu.sm.service;

import edu.sm.dao.AddressDao;
import edu.sm.dto.Address;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddressService implements MService<Integer, Address> {

    AddressDao dao;
    ConnectionPool cp;

    public AddressService() {
        dao = new AddressDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Address add(Address address) throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            address = dao.insert(address, con);
            System.out.println("New address added with ID: " + address.getAddressId());
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return address;
    }

    @Override
    public Address modify(Address address) throws Exception {
        Connection con = cp.getConnection();
        try {
            address = dao.update(address, con);
            System.out.println("Address updated: " + address.getAddressId());
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return address;
    }

    @Override
    public Boolean remove(Integer addressKey) throws Exception {
        Connection con = cp.getConnection();
        Boolean result = false;
        try {
            result = dao.delete(addressKey, con);
            System.out.println("Address removed: " + addressKey);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public Address get(Integer addressKey) throws Exception {
        Connection con = cp.getConnection();
        Address result = null;
        try {
            result = dao.select(addressKey, con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public List<Address> get() throws Exception {
        Connection con = cp.getConnection();
        List<Address> result = null;
        try {
            result = dao.select(con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    public boolean setDefaultAddress(Integer addressKey, Integer custId) throws Exception {
        Connection con = cp.getConnection();
        boolean result = false;
        try {
            result = dao.setDefaultAddress(addressKey, custId, con);
            System.out.println("Default address set: " + addressKey);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }
}