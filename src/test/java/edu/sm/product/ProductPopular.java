package edu.sm.product;


import edu.sm.dto.Product;
import edu.sm.service.ProductService;

import java.util.List;

public class ProductPopular {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        try {
            List<Product> popularProducts = productService.getPopularProducts();
            System.out.println("인기 상품 목록 (상위 10개):");
            for (Product product : popularProducts) {
                System.out.println(product);
            }
        } catch (Exception e) {
            System.out.println("인기 상품 목록 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
