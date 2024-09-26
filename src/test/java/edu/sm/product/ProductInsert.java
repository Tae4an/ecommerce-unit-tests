package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

import java.util.Date;
import java.util.List;

public class ProductInsert {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        Product product = Product.builder()
                .categoryId(1)
                .name("테스트 상품")
                .price(10000)
                .regDate(new Date())
                .description("테스트 상품 설명")
                .img1("test1.jpg")
                .count(100)
                .isPublic(true)
                .build();
        try {
            Product addedProduct = productService.add(product);
            System.out.println("상품 등록 성공: " + addedProduct);
        } catch (Exception e) {
            System.out.println("상품 등록 중 오류 발생:");
            e.printStackTrace();
        }
    }
}