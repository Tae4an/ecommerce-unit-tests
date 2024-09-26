package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

import java.util.List;

public class ProductSelect {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        try {
            List<Product> products = productService.get();
            System.out.println("전체 상품 목록:");
            for (Product product : products) {
                System.out.println(product);
            }
        } catch (Exception e) {
            System.out.println("상품 목록 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}