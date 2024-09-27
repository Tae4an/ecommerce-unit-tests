package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

public class ProductSearch {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        int id = 4;
        try {
            Product product = productService.get(id);
            if (product != null) {
                System.out.println("조회된 상품 정보: " + product);
            } else {
                System.out.println("해당 ID의 상품이 없습니다: " + id);
            }
        } catch (Exception e) {
            System.out.println("상품 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}