package edu.sm.statistics_analysis;

import edu.sm.dto.SalesStatistics;
import edu.sm.service.StatisticsAnalysisService;

import java.text.SimpleDateFormat;
import java.util.List;

public class DailySalesStatistics {
    public static void main(String[] args) {
        StatisticsAnalysisService service = new StatisticsAnalysisService();
        try {

            service.performDailySalesStatistics();

            List<SalesStatistics> dailyStats = service.getDailySalesStatistics();
            System.out.println("일별 매출 통계 실행 결과:");
            // 날짜 형식을 지정하기 위한 SimpleDateFormat 객체 생성
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("-------------------------------------------------------------");
            for (SalesStatistics stat : dailyStats) {

                System.out.printf("날짜: %s, 총 매출: %s원, 총 주문 수: %d, 평균 주문 금액: %s원\n",
                        dateFormat.format(stat.getDate()),
                        stat.getTotalSales(),
                        stat.getOrderCount(),
                        stat.getAvgOrderValue());
            }
            System.out.println("-------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("일별 매출 통계 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
