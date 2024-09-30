package edu.sm.statistics_analysis;

import edu.sm.dto.MonthlySalesStatistics;
import edu.sm.service.StatisticsAnalysisService;

import java.text.SimpleDateFormat;
import java.util.List;

public class MonthlySalesStatisticsTest {
    public static void main(String[] args) {
        StatisticsAnalysisService service = new StatisticsAnalysisService();
        try {
            service.performMonthlySalesStatistics();

            List<MonthlySalesStatistics> monthlyStats = service.getMonthlySalesStatistics();
            System.out.println("월별 매출 통계 실행결과:");
            System.out.println("=====================================");


            for (MonthlySalesStatistics stat : monthlyStats) {
                // 한 줄로 출력
                System.out.printf("월: %s, 총 매출: %s원, 총 주문 수: %d, 평균 주문 금액: %s원\n",
                        stat.getMonth(),
                        stat.getTotalSales(),
                        stat.getOrderCount(),
                        stat.getAvgOrderValue());
            }
            System.out.println("=====================================\n");
        } catch (Exception e) {
            System.out.println("월별 매출 통계 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
