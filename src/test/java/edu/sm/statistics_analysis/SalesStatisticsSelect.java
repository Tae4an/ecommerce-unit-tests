package edu.sm.statistics_analysis;

import edu.sm.dto.SalesStatistics;
import edu.sm.service.StatisticsAnalysisService;

import java.text.SimpleDateFormat;
import java.util.List;

public class SalesStatisticsSelect {
    public static void main(String[] args) {
        StatisticsAnalysisService service = new StatisticsAnalysisService();

        try {
            List<SalesStatistics> salesStats = service.viewSalesStatistics();
            System.out.println("매출 통계 조회결과:");
            System.out.println("-------------------------------------------------------------------");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            for (SalesStatistics stat : salesStats) {
                System.out.printf("통계 ID: %d, 날짜: %s, 총 매출: %s원, 주문 수: %d, 평균 주문 금액: %s원\n",
                        stat.getStatId(),
                        dateFormat.format(stat.getDate()),  // 날짜 포맷팅
                        stat.getTotalSales(),
                        stat.getOrderCount(),
                        stat.getAvgOrderValue());
            }
            System.out.println("-------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("매출 통계 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
