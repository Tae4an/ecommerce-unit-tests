package edu.sm.service;

import edu.sm.dao.StatisticsAnalysisDao;
import edu.sm.dto.*;
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StatisticsAnalysisService {

    StatisticsAnalysisDao dao;
    ConnectionPool cp;

    public StatisticsAnalysisService() {
        dao = new StatisticsAnalysisDao();

        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SalesStatistics> viewSalesStatistics() throws Exception {
        Connection con = cp.getConnection();
        try {
            return dao.viewSalesStatistics(con);
        } finally {
            cp.releaseConnection(con);
        }
    }

    public List<CustomerAnalysis> analyzeCustomers() throws Exception {
        Connection con = cp.getConnection();
        try {
            return dao.analyzeCustomers(con);
        } finally {
            cp.releaseConnection(con);
        }
    }

    public List<ProductAnalysis> analyzeProducts() throws Exception {
        Connection con = cp.getConnection();
        try {
            return dao.analyzeProducts(con);
        } finally {
            cp.releaseConnection(con);
        }
    }


    public void performCustomerAnalysis() throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            dao.clearCustomerAnalysis(con);
            dao.performCustomerAnalysis(con);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
    }

    public void performProductAnalysis() throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            dao.clearProductAnalysis(con);
            dao.performProductAnalysis(con);
            con.commit();
            System.out.println("상품 분석이 완료되었습니다.");
        } catch (SQLException e) {
            con.rollback();
            System.out.println("상품 분석 중 오류가 발생했습니다.");
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
    }

    public void performSalesStatistics() throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            dao.clearSalesStatistics(con);
            dao.performSalesStatistics(con);
            con.commit();
            System.out.println("매출 통계 분석이 완료되었습니다.");
        } catch (SQLException e) {
            con.rollback();
            System.out.println("매출 통계 분석 중 오류가 발생했습니다.");
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
    }

    public List<SalesStatistics> getDailySalesStatistics() throws Exception {
        Connection con = cp.getConnection();
        try {
            return dao.getDailySalesStatistics(con);
        } finally {
            cp.releaseConnection(con);
        }
    }

    public List<MonthlySalesStatistics> getMonthlySalesStatistics() throws Exception {
        Connection con = cp.getConnection();
        try {
            return dao.getMonthlySalesStatistics(con);
        } finally {
            cp.releaseConnection(con);
        }
    }

    public List<YearlySalesStatistics> getYearlySalesStatistics() throws Exception {
        Connection con = cp.getConnection();
        try {
            return dao.getYearlySalesStatistics(con);
        } finally {
            cp.releaseConnection(con);
        }
    }
    public void performDailySalesStatistics() throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            dao.clearDailySalesStatistics(con);
            dao.performDailySalesStatistics(con);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
    }

    public void performMonthlySalesStatistics() throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            dao.clearMonthlySalesStatistics(con);
            dao.performMonthlySalesStatistics(con);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
    }

    public void performYearlySalesStatistics() throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            dao.clearYearlySalesStatistics(con);
            dao.performYearlySalesStatistics(con);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
    }



}