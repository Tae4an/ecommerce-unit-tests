package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

import java.util.Scanner;

public class ProductStatusChange {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        Scanner scanner = new Scanner(System.in);

        try {
            // 상태를 변경할 상품 ID 입력
            System.out.print("상태를 변경할 상품의 ID를 입력하세요: ");
            int productId = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            // 현재 상품 정보 조회
            Product product = productService.get(productId);
            if (product == null) {
                System.out.println("해당 ID의 상품을 찾을 수 없습니다.");
                return;
            }

            // 상품 상태 변경
            boolean result = productService.toggleProductStatus(productId);

            if (result) {
                // 변경 후 상품 정보 다시 조회
                Product updatedProduct = productService.get(productId);

                System.out.println("┌─────────────────────────────────────────────");
                System.out.println("│ 상품 상태 변경 성공");
                System.out.println("├─────────────────────────────────────────────");
                System.out.println("│ 상품 ID: " + updatedProduct.getProductId());
                System.out.println("│ 상품명: " + updatedProduct.getName());
                System.out.println("│ 변경된 상태: " + (updatedProduct.isPublic() ? "공개" : "비공개"));
                System.out.println("└─────────────────────────────────────────────");
            } else {
                System.out.println("┌─────────────────────────────────────────────");
                System.out.println("│ 상품 상태 변경 실패");
                System.out.println("├─────────────────────────────────────────────");
                System.out.println("│ 상태 변경에 실패했습니다.");
                System.out.println("└─────────────────────────────────────────────");
            }

        } catch (Exception e) {
            System.out.println("┌─────────────────────────────────────────────");
            System.out.println("│ 상품 상태 변경 실패");
            System.out.println("├─────────────────────────────────────────────");
            System.out.println("│ 오류 메시지: " + e.getMessage());
            System.out.println("└─────────────────────────────────────────────");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}