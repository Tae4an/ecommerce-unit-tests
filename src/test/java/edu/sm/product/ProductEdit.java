package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

import java.util.Date;

public class ProductEdit {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        Product product = Product.builder()
                .productId(18)
                .categoryId(2)
                .name("아이폰 12 미니")
                .price(1000)
                .regDate(new Date())
                .description("애플 스마트폰")
                .img1("iphone12mini.jpg")
                .count(10)
                .isPublic(true)
                .build();

        try {
            Product updatedProduct = productService.modify(product);
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│           상품 정보 수정 성공                │");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("│ 상품 ID: " + updatedProduct.getProductId());
            System.out.println("│ 카테고리 ID: " + updatedProduct.getCategoryId());
            System.out.println("│ 상품명: " + updatedProduct.getName());
            System.out.println("│ 가격: " + updatedProduct.getPrice() + "원");
            System.out.println("│ 등록일: " + updatedProduct.getRegDate());
            System.out.println("│ 설명: " + updatedProduct.getDescription());
            System.out.println("│ 이미지: " + updatedProduct.getImg1());
            System.out.println("│ 수량: " + updatedProduct.getCount() + "개");
            System.out.println("│ 공개 여부: " + (updatedProduct.isPublic() ? "공개" : "비공개"));
            System.out.println("└─────────────────────────────────────────────┘");
        } catch (Exception e) {
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│         상품 정보 수정 중 오류 발생          │");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("│ 오류 메시지: " + e.getMessage());
            System.out.println("└─────────────────────────────────────────────┘");
            e.printStackTrace();
        }
    }
}