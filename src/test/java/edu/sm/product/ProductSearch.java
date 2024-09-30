package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

import java.text.SimpleDateFormat;

public class ProductSearch {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        int id = 17;
        try {
            Product product = productService.get(id);
            if (product != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                System.out.println("┌─────────────────────────────────────────────");
                System.out.println("│ 조회된 상품 정보");
                System.out.println("├─────────────────────────────────────────────");
                System.out.println("│ 상품 ID: " + product.getProductId());
                System.out.println("│ 상품명: " + product.getName());
                System.out.println("│ 가격: " + product.getPrice() + "원");
                System.out.println("│ 카테고리 ID: " + product.getCategoryId());
                System.out.println("│ 등록일: " + (product.getRegDate() != null ? sdf.format(product.getRegDate()) : "미등록"));
                System.out.println("│ 재고: " + product.getCount() + "개");
                System.out.println("│ 공개 여부: " + (product.isPublic() ? "공개" : "비공개"));
                System.out.println("└─────────────────────────────────────────────");
            } else {
                System.out.println("┌─────────────────────────────────────────────");
                System.out.println("│ 상품 조회 결과");
                System.out.println("├─────────────────────────────────────────────");
                System.out.println("│ 해당 ID의 상품이 없습니다: " + id);
                System.out.println("└─────────────────────────────────────────────");
            }
        } catch (Exception e) {
            System.out.println("┌─────────────────────────────────────────────");
            System.out.println("│ 오류 발생");
            System.out.println("├─────────────────────────────────────────────");
            System.out.println("│ 상품 조회 중 오류가 발생했습니다:");
            System.out.println("│ " + e.getMessage());
            System.out.println("└─────────────────────────────────────────────");
            e.printStackTrace();
        }
    }
}