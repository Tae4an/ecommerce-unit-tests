package edu.sm.category;

import edu.sm.dto.Category;
import edu.sm.service.CategoryService;

public class CategoryAdd {
    public static void main(String[] args) {
        CategoryService categoryService = new CategoryService();
        Category category = Category.builder()
                .name("키보드")
                .categoryId2(1) // 상위 카테고리 ID로 가정
                .build();

        try {
            Category addedCategory = categoryService.add(category);
            System.out.println("┌─────────────────────────────────────────────");
            System.out.println("│ 카테고리 등록 성공");
            System.out.println("├─────────────────────────────────────────────");
            System.out.println("│ 카테고리 ID: " + addedCategory.getCategoryId());
            System.out.println("│ 상위 카테고리 ID: " + addedCategory.getCategoryId2());
            System.out.println("│ 카테고리명: " + addedCategory.getName());
            System.out.println("└─────────────────────────────────────────────");
        } catch (Exception e) {
            System.out.println("┌─────────────────────────────────────────────");
            System.out.println("│ 카테고리 등록 실패");
            System.out.println("├─────────────────────────────────────────────");
            System.out.println("│ 오류 메시지: " + e.getMessage());
            System.out.println("└─────────────────────────────────────────────");
            e.printStackTrace();
        }
    }
}