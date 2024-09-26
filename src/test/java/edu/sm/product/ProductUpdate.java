package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

import java.util.Date;

public class ProductUpdate {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        Product product = Product.builder()
                .productId(1)
                .categoryId(1)
                .name("수정된 상품")
                .price(15000)
                .regDate(new Date())
                .description("수정된 상품 설명")
                .img1("updated1.jpg")
                .count(50)
                .isPublic(true)
                .build();
        try {
            Product updatedProduct = productService.modify(product);
            System.out.println("상품 정보 수정 성공: " + updatedProduct);
        } catch (Exception e) {
            System.out.println("상품 정보 수정 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
