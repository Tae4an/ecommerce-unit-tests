package edu.sm.statistics_analysis;

import edu.sm.service.CustomerService;
import edu.sm.service.StatisticsAnalysisService;

import java.util.List;

public class CustomerAnalysis {
    public static void main(String[] args) {
        StatisticsAnalysisService service = new StatisticsAnalysisService();
        CustomerService customerService = new CustomerService();
        try {
            service.performCustomerAnalysis();
            // 분석 결과 조회
            List<edu.sm.dto.CustomerAnalysis> customerAnalyses = service.analyzeCustomers();
            System.out.println("고객 분석 결과:");

            for (edu.sm.dto.CustomerAnalysis analysis : customerAnalyses) {
                System.out.println("-------------------------------------");
                System.out.println("분석 ID: " + analysis.getAnalysisId());
                System.out.println("고객: " +  customerService.get(analysis.getCustId()).getName());
                System.out.println("총 주문 수: " + analysis.getTotalOrders());
                System.out.println("총 지출 금액: " + analysis.getTotalSpent() + "원");
                System.out.println("선호 카테고리 ID: " + analysis.getFavoriteCategoryId());
            }
            System.out.println("-------------------------------------\n");
        } catch (Exception e) {
            System.out.println("고객 분석 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
