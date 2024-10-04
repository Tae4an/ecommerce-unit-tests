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
                handleGuestMenu();
            } else {
                handleUserMenu();
            }
        }
    }

    private static void handleGuestMenu() {
        while (true) {
            System.out.println("\n=== 게스트 메뉴 ===");
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("3. 종료");
            System.out.print("선택: ");

            int choice = getChoice(3);
            try {
                switch (choice) {
                    case 1 -> loggedInCustomer = Utils.registerCustomer();
                    case 2 -> loggedInCustomer = Utils.login();
                    case 3 -> {
                        System.out.println("프로그램을 종료합니다.");
                        System.exit(0);
                    }
                }
                if (loggedInCustomer != null) return;
            } catch (Exception e) {
                System.out.println("오류 발생: " + e.getMessage());
            }
        }
    }

    private static void handleUserMenu() {
        while (true) {
            System.out.println("\n=== 사용자 메뉴 ===");
            System.out.println("1. 마이 페이지");
            System.out.println("2. 상품 검색");
            System.out.println("3. 장바구니");
            System.out.println("4. 위시리스트");
            System.out.println("5. 상품 리뷰");
            System.out.println("6. 문의 등록");
            System.out.println("7. 로그아웃");
            System.out.println("8. 종료");
            System.out.print("선택: ");

            int choice = getChoice(6);
            try {
                switch (choice) {
                    case 1 -> handleMyPageMenu();
                    case 2 -> handleProductMenu();
                    case 3 -> handleCartMenu();
                    case 4 -> handleWishlistMenu();
                    case 5 -> Utils.writeReview(loggedInCustomer);
                    case 6 -> Utils.writeInquiry(loggedInCustomer);
                    case 7 -> {
                        System.out.println("로그아웃 되었습니다.");
                        loggedInCustomer = null;
                        return;
                    }
                    case 8 -> {
                        System.out.println("프로그램을 종료합니다.");
                        System.exit(0);
                    }
                }
            } catch (Exception e) {
                System.out.println("오류 발생: " + e.getMessage());
            }
        }
    }

    private static void handleMyPageMenu() throws Exception {
        while (true) {
            System.out.println("\n=== 마이 페이지 ===");
            System.out.println("1. 회원 정보 조회");
            System.out.println("2. 회원 정보 수정");
            System.out.println("3. 회원 탈퇴");
            System.out.println("4. 기본 배송지 설정");
            System.out.println("5. 마일리지 확인");
            System.out.println("6. 배송 상태 확인");
            System.out.println("7. 이전 메뉴로");
            System.out.print("선택: ");

            int choice = getChoice(7);
            switch (choice) {
                case 1 -> System.out.println(loggedInCustomer);
                case 2 -> {
                    loggedInCustomer = Utils.updateCustomerInfo(loggedInCustomer);
                    System.out.println("회원 정보가 수정되었습니다.");                }
                case 3 -> {
                    Utils.deleteCustomer(loggedInCustomer);
                    loggedInCustomer = null;
                    return;
                }
                case 4 -> Utils.setDefaultAddress(loggedInCustomer.getCustId());
                case 5 -> Utils.checkMileage(loggedInCustomer);
                case 6 -> Utils.viewDeliveryStatus(loggedInCustomer);
                case 7 -> {
                    return;
                }
            }
        }
    }


    private static void handleProductMenu() throws Exception {
        while (true) {
            System.out.println("\n=== 상품 ===");
            System.out.println("1. 상품 목록 조회");
            System.out.println("2. 상품 상세 검색");
            System.out.println("3. 이전 메뉴로");
            System.out.print("선택: ");

            int choice = getChoice(5);
            switch (choice) {
                case 1 -> Utils.listProduct();
                case 2 -> Utils.getProduct();
                case 3 -> {
                    return;
                }
            }
        }
    }

    private static void handleCartMenu() throws Exception {
        while (true) {
            System.out.println("\n=== 장바구니 ===");
            System.out.println("1. 장바구니에 상품 추가");
            System.out.println("2. 장바구니 항목 조회");
            System.out.println("3. 장바구니 항목 수정");
            System.out.println("4. 장바구니 항목 삭제");
            System.out.println("5. 이전 메뉴로");
            System.out.print("선택: ");

            int choice = getChoice(5);
            switch (choice) {
                case 1 -> Utils.addToCart(loggedInCustomer);
                case 2 -> Utils.listCartItems();
                case 3 -> Utils.updateCart();
                case 4 -> Utils.removeFromCart();
                case 5 -> {
                    return;
                }
            }
        }
    }

    private static void handleWishlistMenu() throws Exception {
        while (true) {
            System.out.println("\n=== 위시리스트 ===");
            System.out.println("1. 위시리스트에 상품 추가");
            System.out.println("2. 위시리스트 항목 조회");
            System.out.println("3. 위시리스트 항목 삭제");
            System.out.println("4. 이전 메뉴로");
            System.out.print("선택: ");

            int choice = getChoice(4);
            switch (choice) {
                case 1 -> Utils.addToWishList(loggedInCustomer);
                case 2 -> Utils.listWishItems();
                case 3 -> Utils.removeFromWishList();
                case 4 -> {
                    return;
                }
            }
        }
    }

    private static int getChoice(int maxChoice) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= maxChoice) {
                    return choice;
                } else {
                    System.out.println("잘못된 선택입니다. 1부터 " + maxChoice + " 사이의 숫자를 입력하세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
            }
        }
    }
}