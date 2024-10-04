package edu.sm.util;

import edu.sm.dto.*;
import edu.sm.service.*;

import java.text.SimpleDateFormat;
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
    private static final ProductService productService = new ProductService();



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

    public static Customer updateCustomerInfo(Customer customer) throws Exception {
        System.out.println("회원 정보를 변경하지 않으려면 엔터를 누르세요..");

        System.out.print("새로운 사용자명 (현재: " + customer.getUsername() + "): ");
        String newUsername = scanner.nextLine();
        if (!newUsername.isEmpty()) {
            customer.setUsername(newUsername);
        }

        System.out.print("새로운 비밀번호: ");
        String newPassword = scanner.nextLine();
        if (!newPassword.isEmpty()) {
            customer.setPw(newPassword);
        }

        System.out.print("새로운 이름 (현재: " + customer.getName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            customer.setName(newName);
        }

        System.out.print("새로운 전화번호 (현재: " + customer.getPNumber() + "): ");
        String newPhoneNumber = scanner.nextLine();
        if (!newPhoneNumber.isEmpty()) {
            customer.setPNumber(newPhoneNumber);
        }

        return customerService.modify(customer);
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
                .ntype("R")
                .title(title)
                .regDate(new Date())
                .content(content)
                .rate(rate)
                .build();

        boardService.add(review);
        System.out.println("리뷰가 작성되었습니다.");
    }

    public static void writeInquiry(Customer customer) throws Exception {
        System.out.print("문의할 상품 ID를 입력하세요: ");
        Integer productId = Integer.parseInt(scanner.nextLine());
        System.out.print("제목: ");
        String title = scanner.nextLine();
        System.out.print("내용: ");
        String content = scanner.nextLine();

        Board inquiry = Board.builder()
                .custId(customer.getCustId())
                .productId(productId)
                .ntype("Q")
                .title(title)
                .regDate(new Date())
                .content(content)
                .build();

        boardService.add(inquiry);
        System.out.println("문의가 등록되었습니다.");
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

    public static void removeFromWishList() throws Exception {
        System.out.print("삭제할 위시리스트 항목 ID를 입력하세요: ");
        Integer wishId = Integer.parseInt(scanner.nextLine());
        wishService.remove(wishId);
        System.out.println("위시리스트 항목이 삭제되었습니다.");
    }

    public static void listWishItems() throws Exception {
        List<Wish> wishItems = wishService.get();
        for (Wish wish : wishItems) {
            System.out.println(wish);
        }
    }

    public static void listProduct() throws Exception {
        List<Product> products = productService.get();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("\n=== 상품 목록 ===");

        // 헤더 출력
        System.out.println("ID | 상품명 | 가격 | 등록일 | 수량 | 공개 여부 | 카테고리 ID");
        System.out.println("------------------------------------------------------------");

        // 상품 정보 출력
        for (Product product : products) {
            System.out.printf("%d | %s | %,d원 | %s | %d | %s | %d%n",
                    product.getProductId(),
                    product.getName(),
                    product.getPrice(),
                    dateFormat.format(product.getRegDate()),
                    product.getCount(),
                    product.isPublic() ? "공개" : "비공개",
                    product.getCategoryId());
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("총 " + products.size() + "개의 상품이 있습니다.");
    }


    public static void getProduct() throws Exception {
        System.out.print("조회할 상품의 ID를 입력하세요: ");
        Integer productId = Integer.parseInt(scanner.nextLine());
        Product product = productService.get(productId);
        if (product != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            System.out.println("\n===== 상품 정보 =====");
            System.out.println("상품 ID: " + product.getProductId());
            System.out.println("카테고리 ID: " + product.getCategoryId());
            System.out.println("상품명: " + product.getName());
            System.out.println("가격: " + product.getPrice());
            System.out.println("등록일: " + dateFormat.format(product.getRegDate()));
            System.out.println("수량: " + product.getCount());
            System.out.println("공개 여부: " + (product.isPublic() ? "공개" : "비공개"));
            System.out.println("----------------------");
            System.out.println("설명: " + product.getDescription());
            System.out.println("----------------------");

            // 이미지 정보 출력
            boolean hasImages = false;
            if (product.getImg1() != null) { System.out.println("이미지 1: " + product.getImg1()); hasImages = true; }
            if (product.getImg2() != null) { System.out.println("이미지 2: " + product.getImg2()); hasImages = true; }
            if (product.getImg3() != null) { System.out.println("이미지 3: " + product.getImg3()); hasImages = true; }
            if (product.getImg4() != null) { System.out.println("이미지 4: " + product.getImg4()); hasImages = true; }
            if (product.getImg5() != null) { System.out.println("이미지 5: " + product.getImg5()); hasImages = true; }

            if (!hasImages) {
                System.out.println("등록된 이미지가 없습니다.");
            }
        } else {
            System.out.println("\n상품을 찾을 수 없습니다.");
        }
    }
}