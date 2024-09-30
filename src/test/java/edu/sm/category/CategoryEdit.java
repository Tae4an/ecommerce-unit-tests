package edu.sm.category;

import edu.sm.dto.Category;
import edu.sm.service.CategoryService;

import java.util.Scanner;

public class CategoryEdit {
    public static void main(String[] args) {
        CategoryService categoryService = new CategoryService();
        Scanner scanner = new Scanner(System.in);

        try {
            // 수정할 카테고리 ID 입력
            System.out.print("수정할 카테고리의 ID를 입력하세요: ");
            int categoryId = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            // 새로운 정보 입력
            System.out.print("새로운 카테고리 이름을 입력하세요: ");
            String newName = scanner.nextLine();

            System.out.print("새로운 상위 카테고리 ID를 입력하세요: ");
            int newCategoryId2 = scanner.nextInt();

            // 카테고리 객체 생성
            Category category = new Category();
            category.setCategoryId(categoryId);
            category.setName(newName);
            category.setCategoryId2(newCategoryId2);

            // 카테고리 업데이트
            Category updatedCategory = categoryService.modify(category);

            // 결과 출력
            System.out.println("┌─────────────────────────────────────────────");
            System.out.println("│ 카테고리 수정 성공");
            System.out.println("├─────────────────────────────────────────────");
            System.out.println("│ 카테고리 ID: " + updatedCategory.getCategoryId());
            System.out.println("│ 카테고리명: " + updatedCategory.getName());
            System.out.println("│ 상위 카테고리 ID: " + updatedCategory.getCategoryId2());
            System.out.println("└─────────────────────────────────────────────");

        } catch (Exception e) {
            System.out.println("┌─────────────────────────────────────────────");
            System.out.println("│ 카테고리 수정 실패");
            System.out.println("├─────────────────────────────────────────────");
            System.out.println("│ 오류 메시지: " + e.getMessage());
            System.out.println("└─────────────────────────────────────────────");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}