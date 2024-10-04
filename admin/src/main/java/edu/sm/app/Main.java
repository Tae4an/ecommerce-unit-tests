package edu.sm.app;

import edu.sm.util.Utils;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("관리자 애플리케이션에 오신 것을 환영합니다!");

        while (true) {
            try {
                displayMainMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        Utils.manageCustomers();
                        break;
                    case 2:
                        Utils.manageAddresses();
                        break;
                    case 3:
                        Utils.manageProducts();
                        break;
                    case 4:
                        Utils.manageCategories();
                        break;
                    case 5:
                        Utils.manageBoards();
                        break;
                    case 6:
                        System.out.println("프로그램을 종료합니다. 감사합니다!");
                        return;
                    default:
                        System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
                }
            } catch (Exception e) {
                System.out.println("오류가 발생했습니다: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n==============================");
        System.out.println("       관리자 애플리케이션       ");
        System.out.println("==============================");
        System.out.println("1. 회원 관리");
        System.out.println("2. 배송지 관리");
        System.out.println("3. 제품 관리");
        System.out.println("4. 카테고리 관리");
        System.out.println("5. 게시판 관리");
        System.out.println("6. 종료");
        System.out.print("메뉴를 선택하세요: ");
    }
}