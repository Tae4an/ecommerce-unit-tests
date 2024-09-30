package edu.sm.statistics_analysis;

import edu.sm.service.StatisticsAnalysisService;

import java.util.List;

public class ProductAnalysis {
    public static void main(String[] args) {
        StatisticsAnalysisService service = new StatisticsAnalysisService();

        try {

            service.performProductAnalysis();

            // 분석 결과 조회
            List<edu.sm.dto.ProductAnalysis> productAnalyses = service.analyzeProducts();
            System.out.println("상품 분석 실행결과:");
            System.out.println("-------------------------------------------------------------------");

            for (edu.sm.dto.ProductAnalysis analysis : productAnalyses) {
                // 한 줄로 출력
                System.out.printf("상품 분석 ID: %d, 상품 ID: %d, 판매 수: %d, 총 수익: %s원, 평균 평점: %.2f\n",
                        analysis.getAnalysisId(),
                        analysis.getProductId(),
                        analysis.getSalesCount(),
                        analysis.getTotalRevenue(),
                        analysis.getAvgRating());
            }
            System.out.println("-------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("상품 분석 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
