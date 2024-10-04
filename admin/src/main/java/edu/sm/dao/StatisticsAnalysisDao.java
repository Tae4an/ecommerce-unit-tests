package edu.sm.dao;

import edu.sm.dto.*;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticsAnalysisDao {

    public List<SalesStatistics> viewSalesStatistics(Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<SalesStatistics> statistics = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(Sql.selectViewSalesStatistics);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SalesStatistics stat = new SalesStatistics(
                        rs.getInt("stat_id"),
                        rs.getDate("date"),
                        rs.getBigDecimal("total_sales"),
                        rs.getInt("order_count"),
                        rs.getBigDecimal("avg_order_value")
                );
                statistics.add(stat);
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return statistics;
    }

    public List<CustomerAnalysis> analyzeCustomers(Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<CustomerAnalysis> analyses = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(Sql.selectAnalyzeCustomers);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CustomerAnalysis analysis = new CustomerAnalysis(
                        rs.getInt("analysis_id"),
                        rs.getInt("cust_id"),
                        rs.getInt("total_orders"),
                        rs.getBigDecimal("total_spent"),
                        rs.getInt("favorite_category_id")
                );
                analyses.add(analysis);
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return analyses;
    }

    public List<ProductAnalysis> analyzeProducts(Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ProductAnalysis> analyses = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(Sql.selectAnalyzeProducts);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ProductAnalysis analysis = new ProductAnalysis(
                        rs.getInt("analysis_id"),
                        rs.getInt("product_id"),
                        rs.getInt("sales_count"),
                        rs.getBigDecimal("total_revenue"),
                        rs.getBigDecimal("avg_rate")
                );
                analyses.add(analysis);
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return analyses;
    }
    public void performProductAnalysis(Connection conn) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(Sql.performProductAnalysis)) {
            pstmt.executeUpdate();
        }
    }

    public void performCustomerAnalysis(Connection conn) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(Sql.performCustomerAnalysis)) {
            pstmt.executeUpdate();
        }
    }

    public void performSalesStatistics(Connection conn) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(Sql.performSalesStatistics)) {
            pstmt.executeUpdate();
        }
    }

    public void clearProductAnalysis(Connection conn) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM product_analysis")) {
            pstmt.executeUpdate();
        }
    }
    public void clearSalesStatistics(Connection conn) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM sales_statistics")) {
            pstmt.executeUpdate();
        }
    }
    public void clearCustomerAnalysis(Connection conn) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM customer_analysis")) {
            pstmt.executeUpdate();
        }
    }


    public void performDailySalesStatistics(Connection conn) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(Sql.performDailySalesStatistics)) {
            pstmt.executeUpdate();
        }
    }

    public void performMonthlySalesStatistics(Connection conn) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(Sql.performMonthlySalesStatistics)) {
            pstmt.executeUpdate();
        }
    }

    public void performYearlySalesStatistics(Connection conn) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(Sql.performYearlySalesStatistics)) {
            pstmt.executeUpdate();
        }
    }

    public List<MonthlySalesStatistics> getMonthlySalesStatistics(Connection conn) throws SQLException {
        List<MonthlySalesStatistics> statistics = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM monthly_sales_statistics");
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                MonthlySalesStatistics stat = new MonthlySalesStatistics(
                        rs.getString("month"),
                        rs.getBigDecimal("total_sales"),
                        rs.getInt("order_count"),
                        rs.getBigDecimal("avg_order_value")
                );
                statistics.add(stat);
            }
        }
        return statistics;
    }

    public List<YearlySalesStatistics> getYearlySalesStatistics(Connection conn) throws SQLException {
        List<YearlySalesStatistics> statistics = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM yearly_sales_statistics");
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                YearlySalesStatistics stat = new YearlySalesStatistics(
                        rs.getInt("year"),
                        rs.getBigDecimal("total_sales"),
                        rs.getInt("order_count"),
                        rs.getBigDecimal("avg_order_value")
                );
                statistics.add(stat);
            }
        }
        return statistics;
    }

    public void clearDailySalesStatistics(Connection conn) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM sales_statistics")) {
            pstmt.executeUpdate();
        }
    }


    public List<SalesStatistics> getDailySalesStatistics(Connection conn) throws SQLException {
        List<SalesStatistics> statistics = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM sales_statistics");
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                SalesStatistics stat = new SalesStatistics(
                        rs.getInt("stat_id"),
                        rs.getDate("date"),
                        rs.getBigDecimal("total_sales"),
                        rs.getInt("order_count"),
                        rs.getBigDecimal("avg_order_value")
                );
                statistics.add(stat);
            }
        }
        return statistics;
    }

    public void clearMonthlySalesStatistics(Connection conn) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM monthly_sales_statistics")) {
            pstmt.executeUpdate();
        }
    }
    public void clearYearlySalesStatistics(Connection conn) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM yearly_sales_statistics")) {
            pstmt.executeUpdate();
        }
    }

}