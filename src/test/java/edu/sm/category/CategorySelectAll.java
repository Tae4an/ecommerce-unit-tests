package edu.sm.category;

import edu.sm.dto.Category;
import edu.sm.service.CategoryService;

import java.util.List;

public class CategorySelectAll {
    public static void main(String[] args) {
        CategoryService service = new CategoryService();

        try {
            List<Category> allCategories = service.get();
            
            System.out.println("--------------------------------------------------");
            System.out.println("모든 카테고리 목록:");
            if (allCategories.isEmpty()) {
                System.out.println("등록된 카테고리가 없습니다.");
            } else {
                for (Category category : allCategories) {
                    System.out.printf("카테고리 ID: %d | 이름: %s | 상위 카테고리 ID: %d%n",
                            category.getCategoryId(), category.getName(), category.getCategoryId2());
                }
            }
            System.out.println("--------------------------------------------------");
        } catch (Exception e) {
            System.out.println("모든 카테고리 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
