package edu.sm.util;

import edu.sm.dto.*;
import edu.sm.service.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Utils {
    private static final CustomerService customerService = new CustomerService();
    private static final AddressService addressService = new AddressService();
    private static final BoardService boardService = new BoardService();
    private static final CartService cartService = new CartService();
    private static final DeliveryService deliveryService = new DeliveryService();
    private static final MileageService mileageService = new MileageService();
    private static final WishService wishService = new WishService();
    private static final Scanner scanner = new Scanner(System.in);
    private static Customer loggedInCustomer = null;

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
                scanner.close();
                System.exit(0);
            default:
                System.out.println("잘못된 선택입니다.");
        }
    }

    public static Customer registerCustomer() throws Exception {
        System.out.println("회원가입을 시작합니다.");
        System.out.print("사용자명: ");
        String username = scanner.nextLine();
        System.out.print("비밀번호: ");
        String password = scanner.nextLine();
        System.out.print("이름: ");
        String name = scanner.nextLine();
        System.out.print("전화번호: ");
        String phoneNumber = scanner.nextLine();

        Customer customer = Customer.builder()
                .username(username)
                .pw(password)
                .name(name)
                .pNumber(phoneNumber)
                .signUpDate(new Date())
                .role("CUST")
                .build();

        customer = customerService.add(customer);
        System.out.println("회원가입이 완료되었습니다. 고객 ID: " + customer.getCustId());

        // 배송지 입력
        Address address = registerAddress(customer.getCustId());
        System.out.println("기본 배송지가 등록되었습니다. 주소 ID: " + address.getAddressId());

        // 마일리지 초기화
        Mileage mileage = Mileage.builder()
                .custId(customer.getCustId())
                .balance(0)
                .build();
        mileageService.add(mileage);

        return customer;
    }

    public static Customer login() throws Exception {
        System.out.println("로그인을 시작합니다.");
        System.out.print("사용자명: ");
        String username = scanner.nextLine();
        System.out.print("비밀번호: ");
        String password = scanner.nextLine();

        List<Customer> customers = customerService.get();
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username) && customer.getPw().equals(password)) {
                System.out.println("로그인 성공!");
                return customer;
            }
        }
        System.out.println("로그인 실패. 사용자명 또는 비밀번호가 잘못되었습니다.");
        return null;
    }
    public static void deleteCustomer(Customer customer) throws Exception {
        customerService.remove(customer.getCustId());
        System.out.println("고객 계정이 삭제되었습니다.");
    }

    public static void listCustomers() throws Exception {
        List<Customer> customers = customerService.get();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    public static void searchCustomers() throws Exception {
        System.out.print("검색 키워드를 입력하세요: ");
        String keyword = scanner.nextLine();
        List<Customer> customers = customerService.searchMembers(keyword);
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
    private static Address registerAddress(Integer custId) throws Exception {
        System.out.println("배송지를 등록합니다.");
        System.out.print("받는 사람 이름: ");
        String name = scanner.nextLine();
        System.out.print("전화번호: ");
        String phone = scanner.nextLine();
        System.out.print("주소: ");
        String address = scanner.nextLine();
        System.out.print("상세주소: ");
        String addressDetail = scanner.nextLine();
        System.out.print("우편번호: ");
        String zipCode = scanner.nextLine();
        System.out.print("요청사항: ");
        String request = scanner.nextLine();

        Address newAddress = Address.builder()
                .custId(custId)
                .name(name)
                .phone(phone)
                .address(address)
                .addressDetail(addressDetail)
                .zipCode(zipCode)
                .request(request)
                .def("Y")  // 첫 번째 주소이므로 기본 주소로 설정
                .build();

        return addressService.add(newAddress);
    }

    public static void setDefaultAddress(Integer custId) throws Exception {
        System.out.print("기본 배송지로 설정할 주소 ID를 입력하세요: ");
        Integer addressId = Integer.parseInt(scanner.nextLine());
        addressService.setDefaultAddress(addressId, custId);
        System.out.println("기본 배송지가 설정되었습니다.");
    }


    public static void addToCart(Customer customer) throws Exception {
        System.out.print("상품 ID를 입력하세요: ");
        Integer productId = Integer.parseInt(scanner.nextLine());
        System.out.print("수량을 입력하세요: ");
        Integer count = Integer.parseInt(scanner.nextLine());

        Cart cart = Cart.builder()
                .custId(customer.getCustId())
                .productId(productId)
                .count(count)
                .regDate(new Date())
                .build();

        cartService.add(cart);
        System.out.println("장바구니에 상품이 추가되었습니다.");
    }

    public static void updateCart() throws Exception {
        System.out.print("수정할 장바구니 항목 ID를 입력하세요: ");
        Integer cartId = Integer.parseInt(scanner.nextLine());
        Cart cart = cartService.get(cartId);
        // 수정할 정보 입력 받기
        cartService.modify(cart);
        System.out.println("장바구니 항목이 수정되었습니다.");
    }
    public static void removeFromCart() throws Exception {
        System.out.print("삭제할 장바구니 항목 ID를 입력하세요: ");
        Integer cartId = Integer.parseInt(scanner.nextLine());
        cartService.remove(cartId);
        System.out.println("장바구니 항목이 삭제되었습니다.");
    }

    public static void getCartItem() throws Exception {
        System.out.print("조회할 장바구니 항목 ID를 입력하세요: ");
        Integer cartId = Integer.parseInt(scanner.nextLine());
        Cart cart = cartService.get(cartId);
        System.out.println(cart);
    }

    public static void listCartItems() throws Exception {
        List<Cart> cartItems = cartService.get();
        for (Cart cart : cartItems) {
            System.out.println(cart);
        }
    }
    public static void addToWishList(Customer customer) throws Exception {
        System.out.print("위시리스트에 추가할 상품 ID를 입력하세요: ");
        Integer productId = Integer.parseInt(scanner.nextLine());

        Wish wish = Wish.builder()
                .custId(customer.getCustId())
                .productId(productId)
                .regDate(new Date())
                .build();

        wishService.add(wish);
        System.out.println("위시리스트에 상품이 추가되었습니다.");
    }

    public static void writeReview(Customer customer) throws Exception {
        System.out.print("리뷰를 작성할 상품 ID를 입력하세요: ");
        Integer productId = Integer.parseInt(scanner.nextLine());
        System.out.print("제목: ");
        String title = scanner.nextLine();
        System.out.print("내용: ");
        String content = scanner.nextLine();
        System.out.print("평점 (1-5): ");
        Integer rate = Integer.parseInt(scanner.nextLine());

        Board review = Board.builder()
                .custId(customer.getCustId())
                .productId(productId)
                .ntype("REVIEW")
                .title(title)
                .regDate(new Date())
                .content(content)
                .rate(rate)
                .build();

        boardService.add(review);
        System.out.println("리뷰가 작성되었습니다.");
    }
    public static void deleteBoard() throws Exception {
        System.out.print("삭제할 게시글 ID를 입력하세요: ");
        Integer boardId = Integer.parseInt(scanner.nextLine());
        boardService.remove(boardId);
        System.out.println("게시글이 삭제되었습니다.");
    }

    public static void getBoard() throws Exception {
        System.out.print("조회할 게시글 ID를 입력하세요: ");
        Integer boardId = Integer.parseInt(scanner.nextLine());
        Board board = boardService.get(boardId);
        System.out.println(board);
    }

    public static void listBoards() throws Exception {
        List<Board> boards = boardService.get();
        for (Board board : boards) {
            System.out.println(board);
        }
    }
    public static void checkMileage(Customer customer) throws Exception {
        Mileage mileage = mileageService.get(customer.getCustId());
        System.out.println("현재 마일리지: " + mileage.getBalance());
    }

    public static void viewDeliveryStatus(Customer customer) throws Exception {
        System.out.print("배송 상태를 확인할 주문 ID를 입력하세요: ");
        Integer orderId = Integer.parseInt(scanner.nextLine());
        Delivery delivery = deliveryService.viewShippingStatus(orderId);
        if (delivery != null) {
            System.out.println("배송 상태: " + delivery.getStatus());
        } else {
            System.out.println("해당 주문에 대한 배송 정보를 찾을 수 없습니다.");
        }
    }
    public static void updateDelivery() throws Exception {
        System.out.print("수정할 배송 ID를 입력하세요: ");
        Integer deliveryId = Integer.parseInt(scanner.nextLine());
        Delivery delivery = deliveryService.get(deliveryId);
        // 수정할 정보 입력 받기
        deliveryService.modify(delivery);
        System.out.println("배송 정보가 수정되었습니다.");
    }

    public static void getDelivery() throws Exception {
        System.out.print("조회할 배송 ID를 입력하세요: ");
        Integer deliveryId = Integer.parseInt(scanner.nextLine());
        Delivery delivery = deliveryService.get(deliveryId);
        System.out.println(delivery);
    }

    public static void listDeliveries() throws Exception {
        List<Delivery> deliveries = deliveryService.get();
        for (Delivery delivery : deliveries) {
            System.out.println(delivery);
        }
    }

    public static void getDeliveriesByOrderId() throws Exception {
        System.out.print("조회할 주문 ID를 입력하세요: ");
        Integer orderId = Integer.parseInt(scanner.nextLine());
        List<Delivery> deliveries = deliveryService.getByOrderId(orderId);
        for (Delivery delivery : deliveries) {
            System.out.println(delivery);
        }
    }

    public static void viewShippingStatus() throws Exception {
        System.out.print("배송 상태를 확인할 주문 ID를 입력하세요: ");
        Integer orderId = Integer.parseInt(scanner.nextLine());
        Delivery delivery = deliveryService.viewShippingStatus(orderId);
        System.out.println("배송 상태: " + delivery.getStatus());
    }

    public static void editShippingInfo() throws Exception {
        System.out.print("수정할 배송 ID를 입력하세요: ");
        Integer deliveryId = Integer.parseInt(scanner.nextLine());
        Delivery delivery = deliveryService.get(deliveryId);
        // 수정할 정보 입력 받기
        deliveryService.editShippingInfo(delivery);
        System.out.println("배송 정보가 수정되었습니다.");
    }
    public static void updateMileage() throws Exception {
        System.out.print("수정할 마일리지 ID를 입력하세요: ");
        Integer mileageId = Integer.parseInt(scanner.nextLine());
        Mileage mileage = mileageService.get(mileageId);
        // 수정할 정보 입력 받기
        mileageService.modify(mileage);
        System.out.println("마일리지가 수정되었습니다.");
    }

    public static void removeMileage() throws Exception {
        System.out.print("삭제할 마일리지 ID를 입력하세요: ");
        Integer mileageId = Integer.parseInt(scanner.nextLine());
        mileageService.remove(mileageId);
        System.out.println("마일리지가 삭제되었습니다.");
    }

    public static void getMileage() throws Exception {
        System.out.print("조회할 마일리지 ID를 입력하세요: ");
        Integer mileageId = Integer.parseInt(scanner.nextLine());
        Mileage mileage = mileageService.get(mileageId);
        System.out.println(mileage);
    }

    public static void listMileages() throws Exception {
        List<Mileage> mileages = mileageService.get();
        for (Mileage mileage : mileages) {
            System.out.println(mileage);
        }
    }

    public static void addMileagePoints() throws Exception {
        System.out.print("마일리지를 추가할 고객 ID를 입력하세요: ");
        Integer custId = Integer.parseInt(scanner.nextLine());
        System.out.print("추가할 마일리지 포인트를 입력하세요: ");
        Integer points = Integer.parseInt(scanner.nextLine());
        Mileage updatedMileage = mileageService.addMileagePoints(custId, points);
        System.out.println("마일리지가 추가되었습니다. 현재 잔액: " + updatedMileage.getBalance());
    }
    public static void updateWish() throws Exception {
        System.out.print("수정할 위시리스트 항목 ID를 입력하세요: ");
        Integer wishId = Integer.parseInt(scanner.nextLine());
        Wish wish = wishService.get(wishId);
        // 수정할 정보 입력 받기
        wishService.modify(wish);
        System.out.println("위시리스트 항목이 수정되었습니다.");
    }

    public static void removeFromWishList() throws Exception {
        System.out.print("삭제할 위시리스트 항목 ID를 입력하세요: ");
        Integer wishId = Integer.parseInt(scanner.nextLine());
        wishService.remove(wishId);
        System.out.println("위시리스트 항목이 삭제되었습니다.");
    }

    public static void getWishItem() throws Exception {
        System.out.print("조회할 위시리스트 항목 ID를 입력하세요: ");
        Integer wishId = Integer.parseInt(scanner.nextLine());
        Wish wish = wishService.get(wishId);
        System.out.println(wish);
    }

    public static void listWishItems() throws Exception {
        List<Wish> wishItems = wishService.get();
        for (Wish wish : wishItems) {
            System.out.println(wish);
        }
    }
}