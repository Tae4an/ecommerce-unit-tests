package edu.sm.statistics_analysis;

import edu.sm.dto.YearlySalesStatistics;
import edu.sm.service.StatisticsAnalysisService;

import java.util.List;

public class YearlySalesStatisticsTest {
    public static void main(String[] args) {
        StatisticsAnalysisService service = new StatisticsAnalysisService();
        try {
            service.performYearlySalesStatistics();
            System.out.println("연도별 매출 통계 결과:");
            System.out.println("-------------------------------------------------------------------");

            List<YearlySalesStatistics> yearlyStats = service.getYearlySalesStatistics();
            for (YearlySalesStatistics stat : yearlyStats) {
                System.out.printf("연도: %d, 총 매출: %s원, 주문 수: %d, 평균 주문 금액: %s원\n",
                        stat.getYear(),
                        stat.getTotalSales(),
                        stat.getOrderCount(),
                        stat.getAvgOrderValue());
            }
            System.out.println("-------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("연도별 매출 통계 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
