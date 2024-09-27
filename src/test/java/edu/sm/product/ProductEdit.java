package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

import java.util.Date;

public class ProductEdit {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        Product product = Product.builder()
                .productId(17)
                .categoryId(15)
                .name("종의기원")
                .price(13000)
                .regDate(new Date())
                .description("정유정 장편 소설")
                .img1("originofspecies.jpg")
                .count(10)
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
