package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

public class ProductDetails {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        try {
            int productId = 1; // 테스트할 상품 ID
            Product product = productService.showProductDetails(productId);
            if (product != null) {
                System.out.println("상품 상세 정보:");
                System.out.println("ID: " + product.getProductId());
                System.out.println("이름: " + product.getName());
                System.out.println("가격: " + product.getPrice());
                System.out.println("설명: " + product.getDescription());
                // 기타 필요한 정보 출력
            } else {
                System.out.println("해당 ID의 상품을 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("상품 상세 정보 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
