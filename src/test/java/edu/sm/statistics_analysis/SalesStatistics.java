package edu.sm.statistics_analysis;

import edu.sm.service.StatisticsAnalysisService;

import java.util.List;

public class SalesStatistics {
    public static void main(String[] args) {
        StatisticsAnalysisService service = new StatisticsAnalysisService();

        try {
            service.performSalesStatistics();
            // 통계 결과 조회
            List<edu.sm.dto.SalesStatistics> salesStatistics = service.viewSalesStatistics();
            System.out.println("매출 통계 실행결과:");
            System.out.println("-------------------------------------------------------------------");

            for (edu.sm.dto.SalesStatistics statistics : salesStatistics) {
                // 매출 통계 정보를 한 줄로 출력
                System.out.printf("통계 ID: %d, 날짜: %s, 총 매출: %s원, 주문 수: %d, 평균 주문 금액: %s원\n",
                        statistics.getStatId(),
                        statistics.getDate(),
                        statistics.getTotalSales(),
                        statistics.getOrderCount(),
                        statistics.getAvgOrderValue());
            }
            System.out.println("-------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("매출 통계 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
