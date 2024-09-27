package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

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
            System.out.println("상품 등록 성공: " + addedProduct);
        } catch (Exception e) {
            System.out.println("상품 등록 중 오류 발생:");
            e.printStackTrace();
        }
    }
}