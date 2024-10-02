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
                printGuestMenu();
            } else if ("ADMIN".equals(loggedInCustomer.getRole())) {
                printAdminMenu();
            } else {
                printUserMenu();
            }

            int choice = Integer.parseInt(scanner.nextLine());

            try {
                if (loggedInCustomer == null) {
                    handleGuestChoice(choice);
                } else if ("ADMIN".equals(loggedInCustomer.getRole())) {
                    handleAdminChoice(choice);
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
        System.out.println("2. 기본 배송지 설정");
        System.out.println("3. 장바구니에 상품 추가");
        System.out.println("4. 장바구니 항목 조회");
        System.out.println("5. 위시리스트에 상품 추가");
        System.out.println("6. 리뷰 작성");
        System.out.println("7. 마일리지 확인");
        System.out.println("8. 배송 상태 확인");
        System.out.println("9. 로그아웃");
        System.out.println("10. 종료");
        System.out.print("선택: ");
    }

    private static void printAdminMenu() {
        System.out.println("\n1. 회원 목록 조회");
        System.out.println("2. 회원 검색");
        System.out.println("3. 장바구니 항목 조회");
        System.out.println("4. 게시글 삭제");
        System.out.println("5. 게시글 조회");
        System.out.println("6. 게시글 목록 조회");
        System.out.println("7. 배송 정보 수정");
        System.out.println("8. 배송 정보 조회");
        System.out.println("9. 배송 목록 조회");
        System.out.println("10. 주문별 배송 조회");
        System.out.println("11. 마일리지 수정");
        System.out.println("12. 마일리지 삭제");
        System.out.println("13. 마일리지 조회");
        System.out.println("14. 마일리지 목록 조회");
        System.out.println("15. 마일리지 추가");
        System.out.println("16. 위시리스트 항목 수정");
        System.out.println("17. 위시리스트 항목 삭제");
        System.out.println("18. 위시리스트 항목 조회");
        System.out.println("19. 위시리스트 목록 조회");
        System.out.println("20. 로그아웃");
        System.out.println("21. 종료");
        System.out.print("선택: ");
    }

    private static void handleGuestChoice(int choice) throws Exception {
        switch (choice) {
            case 1 -> loggedInCustomer = Utils.registerCustomer();
            case 2 -> loggedInCustomer = Utils.login();
            case 3 -> {
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
            }
            default -> System.out.println("잘못된 선택입니다.");
        }
    }

    private static void handleUserChoice(int choice) throws Exception {
        switch (choice) {
            case 1 -> {
                Utils.deleteCustomer(loggedInCustomer);
                loggedInCustomer = null;
            }
            case 2 -> Utils.setDefaultAddress(loggedInCustomer.getCustId());
            case 3 -> Utils.addToCart(loggedInCustomer);
            case 4 -> Utils.listCartItems();
            case 5 -> Utils.addToWishList(loggedInCustomer);
            case 6 -> Utils.writeReview(loggedInCustomer);
            case 7 -> Utils.checkMileage(loggedInCustomer);
            case 8 -> Utils.viewDeliveryStatus(loggedInCustomer);
            case 9 -> {
                System.out.println("로그아웃 되었습니다.");
                loggedInCustomer = null;
            }
            case 10 -> {
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
            }
            default -> System.out.println("잘못된 선택입니다.");
        }
    }

    private static void handleAdminChoice(int choice) throws Exception {
        switch (choice) {
            case 1 -> Utils.listCustomers();
            case 2 -> Utils.searchCustomers();
            case 3 -> Utils.listCartItems();
            case 4 -> Utils.deleteBoard();
            case 5 -> Utils.getBoard();
            case 6 -> Utils.listBoards();
            case 7 -> Utils.updateDelivery();
            case 8 -> Utils.getDelivery();
            case 9 -> Utils.listDeliveries();
            case 10 -> Utils.getDeliveriesByOrderId();
            case 11 -> Utils.updateMileage();
            case 12 -> Utils.removeMileage();
            case 13 -> Utils.getMileage();
            case 14 -> Utils.listMileages();
            case 15 -> Utils.addMileagePoints();
            case 16 -> Utils.updateWish();
            case 17 -> Utils.removeFromWishList();
            case 18 -> Utils.getWishItem();
            case 19 -> Utils.listWishItems();
            case 20 -> {
                System.out.println("로그아웃 되었습니다.");
                loggedInCustomer = null;
            }
            case 21 -> {
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
            }
            default -> System.out.println("잘못된 선택입니다.");
        }
    }
}