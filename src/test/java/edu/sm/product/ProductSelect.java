package edu.sm.product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class ProductSelect {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        try {
            List<Product> products = productService.get();
            System.out.println("┌────┬──────────────┬────────┬──────┬──────────┬────┬──────────────┬────┐");
            System.out.println("│ ID │   상품명     │  가격  │카테고리│  등록일  │재고│    설명     │공개│");
            System.out.println("├────┼──────────────┼────────┼──────┼──────────┼────┼──────────────┼────┤");
            for (Product product : products) {
                System.out.printf("│%3d │%-12s│%8d│%6d│%-10s│%4d│%-12s│%3s│%n",
                        product.getProductId(),
                        truncate(product.getName(), 12),
                        product.getPrice(),
                        product.getCategoryId(),
                        formatDate(product.getRegDate()),
                        product.getCount(),
                        truncate(product.getDescription(), 12),
                        product.isPublic() ? "공개" : "비공개"
                );
            }
            System.out.println("└────┴──────────────┴────────┴──────┴──────────┴────┴──────────────┴────┘");
        } catch (Exception e) {
            System.out.println("상품 목록 조회 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String truncate(String str, int length) {
        if (str == null) return "";
        return (str.length() <= length) ? String.format("%-" + length + "s", str)
                : new String(str.substring(0, length-1).getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8) + "…";
    }

    private static String formatDate(java.util.Date date) {
        if (date == null) return "미등록    ";
        return new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}