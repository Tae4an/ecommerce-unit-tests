package edu.sm.category;

import edu.sm.dto.Category;
import edu.sm.service.CategoryService;

public class CategorySelectOne {
    public static void main(String[] args) {
        CategoryService service = new CategoryService();

        try {
            int categoryId = 1; // 조회할 카테고리 ID
            Category category = service.get(categoryId);
            
            System.out.println("--------------------------------------------------");
            System.out.println("카테고리 단일 조회 결과:");
            if (category == null) {
                System.out.println("해당 ID의 카테고리가 존재하지 않습니다.");
            } else {
                System.out.printf("카테고리 ID: %d | 이름: %s | 상위 카테고리 ID: %d%n",
                        category.getCategoryId(), category.getName(), category.getCategoryId2());
            }
            System.out.println("--------------------------------------------------");
        } catch (Exception e) {
            System.out.println("카테고리 단일 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}