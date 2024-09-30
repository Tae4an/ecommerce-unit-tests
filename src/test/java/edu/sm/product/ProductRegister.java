package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductRegister {
    public static void main(String[] args) {
        ProductService productService = new ProductService();

        Product product = Product.builder()
                .categoryId(2)
                .name("아이폰 12 미니")
                .price(400000)
                .regDate(new Date())
                .description("애플 스마트폰")
                .img1("iphone12mini.jpg")
                .count(1)
                .isPublic(true)
                .build();
        try {
            Product addedProduct = productService.add(product);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("┌─────────────────────────────────────────────");
            System.out.println("│ 상품 등록 성공");
            System.out.println("├─────────────────────────────────────────────");
            System.out.println("│ 상품 ID: " + addedProduct.getProductId());
            System.out.println("│ 상품명: " + addedProduct.getName());
            System.out.println("│ 가격: " + addedProduct.getPrice() + "원");
            System.out.println("│ 카테고리 ID: " + addedProduct.getCategoryId());
            System.out.println("│ 등록일: " + sdf.format(addedProduct.getRegDate()) );
            System.out.println("│ 설명: " + addedProduct.getDescription());
            System.out.println("│ 이미지: " + addedProduct.getImg1());
            System.out.println("│ 재고: " + addedProduct.getCount() + "개");
            System.out.println("│ 공개 여부: " + (addedProduct.isPublic() ? "공개" : "비공개"));
            System.out.println("└─────────────────────────────────────────────");
        } catch (Exception e) {
            System.out.println("┌─────────────────────────────────────────────");
            System.out.println("│ 상품 등록 실패");
            System.out.println("├─────────────────────────────────────────────");
            System.out.println("│ 오류 메시지: " + e.getMessage());
            System.out.println("└─────────────────────────────────────────────");
            e.printStackTrace();
        }
    }
}