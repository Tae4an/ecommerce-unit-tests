package edu.sm.category;

import edu.sm.service.CategoryService;

import java.util.Scanner;

public class CategoryDelete {
    public static void main(String[] args) {
        CategoryService categoryService = new CategoryService();
        Scanner scanner = new Scanner(System.in);

        try {
            // 삭제할 카테고리 ID 입력
            System.out.print("삭제할 카테고리의 ID를 입력하세요: ");
            int categoryId = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            // 카테고리 삭제
            Boolean isDeleted = categoryService.remove(categoryId);

            // 결과 출력
            if (isDeleted) {
                System.out.println("┌─────────────────────────────────────────────");
                System.out.println("│ 카테고리 삭제 성공");
                System.out.println("├─────────────────────────────────────────────");
                System.out.println("│ 카테고리 ID " + categoryId + "가 성공적으로 삭제되었습니다.");
                System.out.println("└─────────────────────────────────────────────");
            } else {
                System.out.println("┌─────────────────────────────────────────────");
                System.out.println("│ 카테고리 삭제 실패");
                System.out.println("├─────────────────────────────────────────────");
                System.out.println("│ 카테고리 ID " + categoryId + "를 찾을 수 없거나 삭제할 수 없습니다.");
                System.out.println("└─────────────────────────────────────────────");
            }

        } catch (Exception e) {
            System.out.println("┌─────────────────────────────────────────────");
            System.out.println("│ 카테고리 삭제 실패");
            System.out.println("├─────────────────────────────────────────────");
            System.out.println("│ 오류 메시지: " + e.getMessage());
            System.out.println("└─────────────────────────────────────────────");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}