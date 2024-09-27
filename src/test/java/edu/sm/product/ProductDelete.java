package edu.sm.product;

import edu.sm.service.ProductService;

public class ProductDelete {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        int id = 18;
        try {
            boolean result = productService.remove(id);
            if (result) {
                System.out.println("상품 삭제 성공: " + id);
            } else {
                System.out.println("상품 삭제 실패: " + id);
            }
        } catch (Exception e) {
            System.out.println("상품 삭제 중 오류 발생:");
            e.printStackTrace();
        }
    }
}