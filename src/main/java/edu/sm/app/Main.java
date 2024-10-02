package edu.sm;

import edu.sm.dto.Customer;
import edu.sm.util.Utils;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Customer loggedInCustomer = null;

    public static void main(String[] args) {
        while (true) {
            if (loggedInCustomer == null) {
                printGuestMenu();
            } else {
                printUserMenu();
            }

            int choice = Integer.parseInt(scanner.nextLine());

            try {
                if (loggedInCustomer == null) {
                    handleGuestChoice(choice);
                } else {
                    handleUserChoice(choice);
                }
            } catch (Exception e) {
                System.out.println("오류 발생: " + e.getMessage());
            }
        }
    }

    private static void printGuestMenu() {
        System.out.println("\n1. 회원가입");
        System.out.println("2. 로그인");
        System.out.println("3. 종료");
        System.out.print("선택: ");
    }

    private static void printUserMenu() {
        System.out.println("\n1. 회원 탈퇴");
        System.out.println("2. 회원 목록 조회");
        System.out.println("3. 회원 검색");
        System.out.println("4. 기본 배송지 설정");
        System.out.println("5. 장바구니에 상품 추가");
        System.out.println("6. 장바구니 항목 조회");
        System.out.println("7. 위시리스트에 상품 추가");
        System.out.println("8. 리뷰 작성");
        System.out.println("9. 마일리지 확인");
        System.out.println("10. 배송 상태 확인");
        System.out.println("11. 로그아웃");
        System.out.println("12. 종료");
        System.out.print("선택: ");
    }

    private static void handleGuestChoice(int choice) throws Exception {
        switch (choice) {
            case 1:
                loggedInCustomer = Utils.registerCustomer();
                break;
            case 2:
                loggedInCustomer = Utils.login();
                break;
            case 3:
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
            default:
                System.out.println("잘못된 선택입니다.");
        }
    }

    private static void handleUserChoice(int choice) throws Exception {
        switch (choice) {
            case 1:
                Utils.deleteCustomer(loggedInCustomer);
                loggedInCustomer = null;
                break;
            case 2:
                Utils.listCustomers();
                break;
            case 3:
                Utils.searchCustomers();
                break;
            case 4:
                Utils.setDefaultAddress(loggedInCustomer.getCustId());
                break;
            case 5:
                Utils.addToCart(loggedInCustomer);
                break;
            case 6:
                Utils.listCartItems();
                break;
            case 7:
                Utils.addToWishList(loggedInCustomer);
                break;
            case 8:
                Utils.writeReview(loggedInCustomer);
                break;
            case 9:
                Utils.checkMileage(loggedInCustomer);
                break;
            case 10:
                Utils.viewDeliveryStatus(loggedInCustomer);
                break;
            case 11:
                System.out.println("로그아웃 되었습니다.");
                loggedInCustomer = null;
                break;
            case 12:
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
            default:
                System.out.println("잘못된 선택입니다.");
        }
    }
}