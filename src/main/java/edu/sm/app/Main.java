package edu.sm.app;

import edu.sm.dto.Customer;
import edu.sm.util.Utils;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Customer loggedInCustomer = null;

    public static void main(String[] args) {
        while (true) {
            if (loggedInCustomer == null) {
                System.out.println("\n1. 회원가입");
                System.out.println("2. 로그인");
                System.out.println("3. 종료");
                System.out.print("선택: ");
            } else {
                System.out.println("\n1. 고객 목록 조회");
                System.out.println("2. 고객 검색");
                System.out.println("3. 내 정보 수정");
                System.out.println("4. 계정 삭제");
                System.out.println("5. 로그아웃");
                System.out.println("6. 종료");
                System.out.print("선택: ");
            }

            int choice = scanner.nextInt();

            try {
                if (loggedInCustomer == null) {
                    switch (choice) {
                        case 1:
                            loggedInCustomer = Utils.registerCustomer();
                            break;
                        case 2:
                            loggedInCustomer = Utils.login();
                            break;
                        case 3:
                            System.out.println("프로그램을 종료합니다.");
                            return;
                        default:
                            System.out.println("잘못된 선택입니다.");
                    }
                } else {
                    switch (choice) {
                        case 1:
                            Utils.listCustomers();
                            break;
                        case 2:
                            Utils.searchCustomers();
                            break;
                        case 3:
                            Utils.updateCustomer(loggedInCustomer);
                            break;
                        case 4:
                            Utils.deleteCustomer(loggedInCustomer);
                            loggedInCustomer = null;
                            break;
                        case 5:
                            System.out.println("로그아웃 되었습니다.");
                            loggedInCustomer = null;
                            break;
                        case 6:
                            System.out.println("프로그램을 종료합니다.");
                            return;
                        default:
                            System.out.println("잘못된 선택입니다.");
                    }
                }
            } catch (Exception e) {
                System.out.println("오류 발생: " + e.getMessage());
            }
        }
    }
}