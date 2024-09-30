package edu.sm.statistics_analysis;

import edu.sm.dto.ProductAnalysis;
import edu.sm.service.StatisticsAnalysisService;

import java.util.List;

public class ProductAnalysisSelect {
    public static void main(String[] args) {
        StatisticsAnalysisService service = new StatisticsAnalysisService();

        try {
            // 상품 분석 결과 조회
            List<ProductAnalysis> productAnalyses = service.analyzeProducts();
            System.out.println("상품 분석 조회 결과:");
            System.out.println("-------------------------------------------------------------------");

            for (ProductAnalysis analysis : productAnalyses) {
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
