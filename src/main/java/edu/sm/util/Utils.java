package edu.sm.util;

import edu.sm.dto.*;
import edu.sm.service.*;

import java.sql.SQLOutput;
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

    public static void addProduct() throws Exception {
        System.out.println("상품 추가를 시작합니다.");
        System.out.println("카테고리 ID: ");
        Integer categoryId = Integer.parseInt(scanner.nextLine());
        System.out.println("상품명: ");
        String name = scanner.nextLine();
        System.out.println("가격: ");
        Integer price = Integer.parseInt(scanner.nextLine());
        String[] images = new String[5];
        for (int i = 0; i < 5; i++) {
            System.out.print("이미지 " + (i + 1) + " 경로 (엔터 시 건너뛰기): ");
            String imagePath = scanner.nextLine().trim();
            if (imagePath.isEmpty()) {
                break;
            }
            images[i] = imagePath;
        }
        System.out.println("설명: ");
        String description = scanner.nextLine();
        System.out.println("수량: ");
        Integer count = Integer.parseInt(scanner.nextLine());

        Product product = Product.builder()
                .categoryId(categoryId)
                .name(name)
                .price(price)
                .regDate(new Date())
                .description(description)
                .img1(images[0])
                .img2(images[1])
                .img3(images[2])
                .img4(images[3])
                .img5(images[4])
                .count(count)
                .isPublic(true)
                .build();

        product = productService.add(product);
        System.out.println("상품이 추가되었습니다. 상품 ID: " + product.getProductId());
    }

    public static void updateProduct() throws Exception {
        System.out.println("수정할 상품의 ID를 입력하세요: ");
        Integer productId = Integer.parseInt(scanner.nextLine());
        Product product = productService.get(productId);
        if (product == null) {
            System.out.println("해당 ID의 상품을 찾을 수 없습니다.");
            return;
        }

        System.out.print("새 상품명 (엔터 시 변경 없음): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) product.setName(name);

        System.out.print("새 가격 (엔터 시 변경 없음): ");
        String priceStr = scanner.nextLine();
        if (!priceStr.isEmpty()) product.setPrice(Integer.parseInt(priceStr));

        System.out.print("새 설명 (엔터 시 변경 없음): ");
        String description = scanner.nextLine();
        if (!description.isEmpty()) product.setDescription(description);

        String[] images = new String[5];
        for (int i = 0; i < 5; i++) {
            System.out.print("새 이미지 " + (i + 1) + " 경로 (엔터 시 변경 없음): ");
            String imagePath = scanner.nextLine().trim();
            if (!imagePath.isEmpty()) {
                images[i] = imagePath;
            }
        }

        if (!images[0].isEmpty()) product.setImg1(images[0]);
        if (!images[1].isEmpty()) product.setImg2(images[1]);
        if (!images[2].isEmpty()) product.setImg3(images[2]);
        if (!images[3].isEmpty()) product.setImg4(images[3]);
        if (!images[4].isEmpty()) product.setImg5(images[4]);

        System.out.print("새 수량 (엔터 시 변경 없음): ");
        String countStr = scanner.nextLine();
        if (!countStr.isEmpty()) product.setCount(Integer.parseInt(countStr));

        product = productService.modify(product);
        System.out.println("상품 정보가 수정되었습니다.");
    }
    public static void listProduct() throws Exception {
        List<Product> products = productService.get();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String format = "| %-4s | %-20s | %10s | %-10s | %5s | %-4s | %-8s |\n";
        System.out.println("+------+----------------------+------------+------------+-------+------+----------+");
        System.out.format(format, "ID", "상품명", "가격", "등록일", "수량", "공개", "카테고리");
        System.out.println("+------+----------------------+------------+------------+-------+------+----------+");

        for (Product product : products) {
            String name = product.getName().length() > 20 ? product.getName().substring(0, 17) + "..." : product.getName();
            System.out.format(format,
                    product.getProductId(),
                    name,
                    String.format("%,d", product.getPrice()),
                    dateFormat.format(product.getRegDate()),
                    product.getCount(),
                    product.isPublic() ? "O" : "X",
                    product.getCategoryId());
        }
        System.out.println("+------+----------------------+------------+------------+-------+------+----------+");
    }
    public static void getProduct() throws Exception {
        System.out.print("조회할 상품의 ID를 입력하세요: ");
        Integer productId = Integer.parseInt(scanner.nextLine());
        Product product = productService.get(productId);
        if (product != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("상품 ID: " + product.getProductId());
            System.out.println("카테고리 ID: " + product.getCategoryId());
            System.out.println("상품명: " + product.getName());
            System.out.println("가격: " + product.getPrice());
            System.out.println("등록일: " + dateFormat.format(product.getRegDate()));
            System.out.println("설명: " + product.getDescription());
            System.out.println("수량: " + product.getCount());
            System.out.println("공개 여부: " + (product.isPublic() ? "공개" : "비공개"));

            if (product.getImg1() != null) System.out.println("이미지 1: " + product.getImg1());
            if (product.getImg2() != null) System.out.println("이미지 2: " + product.getImg2());
            if (product.getImg3() != null) System.out.println("이미지 3: " + product.getImg3());
            if (product.getImg4() != null) System.out.println("이미지 4: " + product.getImg4());
            if (product.getImg5() != null) System.out.println("이미지 5: " + product.getImg5());
        } else {
            System.out.println("해당 ID의 상품을 찾을 수 없습니다.");
        }
    }
    public static void toggleProductStatus() throws Exception {
        System.out.print("상태를 변경할 상품 ID를 입력하세요: ");
        Integer productId = Integer.parseInt(scanner.nextLine());
        Boolean result = productService.toggleProductStatus(productId);
        if (result) {
            System.out.println("상품 상태가 변경되었습니다.");
        } else {
            System.out.println("상품 상태 변경에 실패했습니다.");
        }
    }

}