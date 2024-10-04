package edu.sm.util;

import edu.sm.dto.*;
import edu.sm.service.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Utils {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CustomerService customerService = new CustomerService();
    private static final AddressService addressService = new AddressService();
    private static final ProductService productService = new ProductService();
    private static final CategoryService categoryService = new CategoryService();
    private static final BoardService boardService = new BoardService();
    private static final OrderService orderService = new OrderService();
    private static final OrderDetailService orderDetailService = new OrderDetailService();
    private static final PaymentService paymentService = new PaymentService();
    private static final StatisticsAnalysisService statisticsAnalysisService = new StatisticsAnalysisService();



    /**
     * --------------------------- Customer ---------------------------
     */

    public static void manageCustomers() throws Exception {
        while (true) {
            System.out.println("\n===== 회원 관리 =====");
            System.out.println("1. 회원 추가");
            System.out.println("2. 회원 조회");
            System.out.println("3. 회원 수정");
            System.out.println("4. 회원 삭제");
            System.out.println("5. 회원 검색");
            System.out.println("6. 뒤로 가기");
            System.out.print("선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> viewCustomers();
                case 3 -> updateCustomer();
                case 4 -> deleteCustomer();
                case 5 -> searchCustomers();
                case 6 -> {
                    return;
                }
                default -> System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private static void addCustomer() throws Exception {
        System.out.println("\n----- 회원 추가 -----");
        System.out.print("사용자명: ");
        String username = scanner.nextLine();
        System.out.print("비밀번호: ");
        String password = scanner.nextLine();
        System.out.print("이름: ");
        String name = scanner.nextLine();
        System.out.print("전화번호: ");
        String phone = scanner.nextLine();

        Customer customer = Customer.builder()
                .username(username)
                .pw(password)
                .name(name)
                .pNumber(phone)
                .role("USER")
                .build();

        Customer addedCustomer = customerService.add(customer);
        System.out.println("회원이 추가되었습니다. ID: " + addedCustomer.getCustId() +
                "Name: " + addedCustomer.getName());
    }

    private static void viewCustomers() throws Exception {
        System.out.println("\n----- 회원 조회 -----");
        List<Customer> customers = customerService.get();

        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("%-10s %-15s %-10s %-20s %-15s %-10s%n",
                "ID", "Username", "Name", "Phone Number", "Sign Up Date", "Role");
        System.out.println("----------------------------------------------------------------------------------");

        for (Customer customer : customers) {
            // null 값에 대해 기본 값 처리
            String pNumber = customer.getPNumber() != null ? customer.getPNumber() : "N/A";
            Date signUpDate = customer.getSignUpDate() != null ? customer.getSignUpDate() : new Date(); // 기본 값으로 현재 날짜
            String role = customer.getRole() != null ? customer.getRole() : "User"; // 기본 값

            System.out.printf("%-10d %-15s %-10s %-20s %-15tF %-10s%n",
                    customer.getCustId(),
                    customer.getUsername(),
                    customer.getName(),
                    pNumber,
                    signUpDate,
                    role
            );
        }

        System.out.println("----------------------------------------------------------------------------------");
    }

    private static void updateCustomer() throws Exception {
        System.out.println("\n----- 회원 수정 -----");
        System.out.print("수정할 회원 ID: ");
        int custId = scanner.nextInt();
        scanner.nextLine();

        Customer customer = customerService.get(custId);
        if (customer == null) {
            System.out.println("해당 ID의 회원이 없습니다.");
            return;
        }

        System.out.print("새 이름 (현재: " + customer.getName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            customer.setName(newName);
        }

        System.out.print("새 전화번호 (현재: " + customer.getPNumber() + "): ");
        String newPhone = scanner.nextLine();
        if (!newPhone.isEmpty()) {
            customer.setPNumber(newPhone);
        }

        customerService.modify(customer);
        System.out.println("회원 정보가 수정되었습니다.");
    }

    private static void deleteCustomer() throws Exception {
        System.out.println("\n----- 회원 삭제 -----");
        System.out.print("삭제할 회원 ID: ");
        int custId = scanner.nextInt();
        scanner.nextLine();

        if (customerService.remove(custId)) {
            System.out.println("회원이 삭제되었습니다.");
        } else {
            System.out.println("회원 삭제에 실패했습니다.");
        }
    }

    private static void searchCustomers() throws Exception {
        System.out.println("\n----- 회원 검색 -----");
        System.out.print("검색 키워드: ");
        String keyword = scanner.nextLine();

        List<Customer> searchResults = customerService.searchMembers(keyword);

        if (searchResults.isEmpty()) {
            System.out.println("검색 결과가 없습니다.");
        } else {
            System.out.println("----------------------------------------------------------------------------------");
            System.out.printf("%-10s %-15s %-10s %-20s %-15s %-10s%n",
                    "ID", "Username", "Name", "Phone Number", "Sign Up Date", "Role");
            System.out.println("----------------------------------------------------------------------------------");

            for (Customer customer : searchResults) {
                String pNumber = customer.getPNumber() != null ? customer.getPNumber() : "N/A";
                Date signUpDate = customer.getSignUpDate() != null ? customer.getSignUpDate() : new Date();
                String role = customer.getRole() != null ? customer.getRole() : "User";

                System.out.printf("%-10d %-15s %-10s %-20s %-15tF %-10s%n",
                        customer.getCustId(),
                        customer.getUsername(),
                        customer.getName(),
                        pNumber,
                        signUpDate,
                        role
                );
            }

            System.out.println("----------------------------------------------------------------------------------");
        }
    }


    /**
     * --------------------------- Address ---------------------------
     */

    public static void manageAddresses() throws Exception {
        while (true) {
            System.out.println("\n===== 배송지 관리 =====");
            System.out.println("1. 배송지 추가");
            System.out.println("2. 배송지 조회");
            System.out.println("3. 배송지 수정");
            System.out.println("4. 배송지 삭제");
            System.out.println("5. 기본 배송지 설정");
            System.out.println("6. 뒤로 가기");
            System.out.print("선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addAddress();
                case 2 -> viewAddresses();
                case 3 -> updateAddress();
                case 4 -> deleteAddress();
                case 5 -> setDefaultAddress();
                case 6 -> {
                    return;
                }
                default -> System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private static void addAddress() throws Exception {
        System.out.println("\n----- 배송지 추가 -----");
        System.out.print("회원 ID: ");
        int custId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("이름: ");
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
                .build();

        Address addedAddress = addressService.add(newAddress);
        System.out.println("배송지가 추가되었습니다. ID: " + addedAddress.getAddressId());
    }

    private static void viewAddresses() throws Exception {
        System.out.println("\n----- 배송지 조회 -----");
        List<Address> addresses = addressService.get();

        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-10s %-15s %-15s %-30s %-20s %-10s %-10s%n",
                "ID", "Cust ID", "Name", "Phone", "Address", "Detail", "ZipCode", "Default");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        for (Address address : addresses) {
            String name = address.getName() != null ? address.getName() : "N/A";
            String phone = address.getPhone() != null ? address.getPhone() : "N/A";
            String addr = address.getAddress() != null ? address.getAddress() : "N/A";
            String detail = address.getAddressDetail() != null ? address.getAddressDetail() : "N/A";
            String zipCode = address.getZipCode() != null ? address.getZipCode() : "N/A";
            String def = address.getDef() != null ? address.getDef() : "No";

            System.out.printf("%-10d %-10d %-15s %-15s %-30s %-20s %-10s %-10s%n",
                    address.getAddressId(),
                    address.getCustId(),
                    name,
                    phone,
                    addr,
                    detail,
                    zipCode,
                    def
            );
        }

        System.out.println("-----------------------------------------------------------------------------------------------------");
    }


    private static void updateAddress() throws Exception {
        System.out.println("\n----- 배송지 수정 -----");
        System.out.print("수정할 배송지 ID: ");
        int addressId = scanner.nextInt();
        scanner.nextLine();

        Address address = addressService.get(addressId);
        if (address == null) {
            System.out.println("해당 ID의 배송지가 없습니다.");
            return;
        }

        System.out.print("새 주소 (현재: " + address.getAddress() + "): ");
        String newAddress = scanner.nextLine();
        if (!newAddress.isEmpty()) {
            address.setAddress(newAddress);
        }

        System.out.print("새 상세주소 (현재: " + address.getAddressDetail() + "): ");
        String newAddressDetail = scanner.nextLine();
        if (!newAddressDetail.isEmpty()) {
            address.setAddressDetail(newAddressDetail);
        }

        addressService.modify(address);
        System.out.println("배송지 정보가 수정되었습니다.");
    }

    private static void deleteAddress() throws Exception {
        System.out.println("\n----- 배송지 삭제 -----");
        System.out.print("삭제할 배송지 ID: ");
        int addressId = scanner.nextInt();
        scanner.nextLine();
        if (addressService.remove(addressId)) {
            System.out.println("배송지가 삭제되었습니다.");
        } else {
            System.out.println("배송지 삭제에 실패했습니다.");
        }
    }

    private static void setDefaultAddress() throws Exception {
        System.out.println("\n----- 기본 배송지 설정 -----");
        System.out.print("회원 ID: ");
        int custId = scanner.nextInt();
        System.out.print("기본 배송지로 설정할 배송지 ID: ");
        int addressId = scanner.nextInt();
        scanner.nextLine();

        if (addressService.setDefaultAddress(addressId, custId)) {
            System.out.println("기본 배송지가 설정되었습니다.");
        } else {
            System.out.println("기본 배송지 설정에 실패했습니다.");
        }
    }

    /**
     * --------------------------- Product ---------------------------
     */

    public static void manageProducts() throws Exception {
        while (true) {
            System.out.println("\n===== 제품 관리 =====");
            System.out.println("1. 상품 추가");
            System.out.println("2. 상품 조회");
            System.out.println("3. 상품 수정");
            System.out.println("4. 상품 상태 변경");
            System.out.println("5. 모든 상품 조회");
            System.out.println("6. 뒤로 가기");
            System.out.print("선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> viewProduct();
                case 3 -> updateProduct();
                case 4 -> toggleProductStatus();
                case 5 -> viewAllProducts();
                case 6 -> {
                    return;
                }
                default -> System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private static void addProduct() throws Exception {
        System.out.println("\n----- 상품 추가 -----");
        System.out.print("카테고리 ID: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("상품명: ");
        String name = scanner.nextLine();
        System.out.print("가격: ");
        int price = scanner.nextInt();
        scanner.nextLine();
        System.out.print("설명: ");
        String description = scanner.nextLine();
        System.out.print("재고 수량: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        Product product = Product.builder()
                .categoryId(categoryId)
                .name(name)
                .price(price)
                .description(description)
                .count(count)
                .regDate(new Date())
                .isPublic(true)
                .build();

        Product addedProduct = productService.add(product);
        System.out.println("상품이 추가되었습니다. ID: " + addedProduct.getProductId());
    }

    private static void viewProduct() throws Exception {
        System.out.println("\n----- 상품 조회 -----");
        System.out.print("조회할 상품 ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        Product product = productService.get(productId);
        if (product != null) {
            // 제품 정보 상세 출력
            System.out.println("\n===== 상품 정보 =====");
            System.out.printf("상품 ID: %d%n", product.getProductId());
            System.out.printf("카테고리 ID: %d%n", product.getCategoryId());
            System.out.printf("상품 이름: %s%n", product.getName() != null ? product.getName() : "N/A");
            System.out.printf("가격: %,d원%n", product.getPrice());
            System.out.printf("등록일: %tF%n", product.getRegDate() != null ? product.getRegDate() : new Date());
            System.out.printf("설명: %s%n", product.getDescription() != null ? product.getDescription() : "N/A");
            System.out.printf("재고 수량: %d%n", product.getCount());
            System.out.printf("공개 여부: %s%n", product.isPublic() ? "Yes" : "No");
            if (product.getImg1() != null) {
                System.out.printf("이미지1: %s%n", product.getImg1());
            }
            if (product.getImg2() != null) {
                System.out.printf("이미지2: %s%n", product.getImg2());
            }
        } else {
            System.out.println("해당 ID의 상품이 없습니다.");
        }
    }


    private static void updateProduct() throws Exception {
        System.out.println("\n----- 상품 수정 -----");
        System.out.print("수정할 상품 ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        Product product = productService.get(productId);
        if (product == null) {
            System.out.println("해당 ID의 상품이 없습니다.");
            return;
        }

        System.out.print("새 상품명 (현재: " + product.getName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            product.setName(newName);
        }

        System.out.print("새 가격 (현재: " + product.getPrice() + "): ");
        String newPrice = scanner.nextLine();
        if (!newPrice.isEmpty()) {
            product.setPrice(Integer.parseInt(newPrice));
        }

        System.out.print("새 설명 (현재: " + product.getDescription() + "): ");
        String newDescription = scanner.nextLine();
        if (!newDescription.isEmpty()) {
            product.setDescription(newDescription);
        }

        System.out.print("새 재고 수량 (현재: " + product.getCount() + "): ");
        String newCount = scanner.nextLine();
        if (!newCount.isEmpty()) {
            product.setCount(Integer.parseInt(newCount));
        }

        productService.modify(product);
        System.out.println("상품 정보가 수정되었습니다.");
    }

    private static void toggleProductStatus() throws Exception {
        System.out.println("\n----- 상품 상태 변경 -----");
        System.out.print("상태를 변경할 상품 ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        if (productService.toggleProductStatus(productId)) {
            System.out.println("상품 상태가 변경되었습니다.");
        } else {
            System.out.println("상품 상태 변경에 실패했습니다.");
        }
    }

    private static void viewAllProducts() throws Exception {
        System.out.println("\n----- 모든 상품 조회 -----");
        List<Product> products = productService.get();

        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-15s %-25s %-10s %-15s %-10s %-10s%n",
                "ID", "Category", "Product Name", "Price", "Reg Date", "Count", "Public");
        System.out.println("-----------------------------------------------------------------------------------------------------------");

        for (Product product : products) {
            String name = product.getName() != null ? product.getName() : "N/A";
            Date regDate = product.getRegDate() != null ? product.getRegDate() : new Date();
            String isPublic = product.isPublic() ? "Yes" : "No";

            System.out.printf("%-10d %-15d %-25s %-10d %-15tF %-10d %-10s%n",
                    product.getProductId(),
                    product.getCategoryId(),
                    name,
                    product.getPrice(),
                    regDate,
                    product.getCount(),
                    isPublic
            );
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }


    /**
     * --------------------------- Category ---------------------------
     */

    public static void manageCategories() throws Exception {
        while (true) {
            System.out.println("\n===== 카테고리 관리 =====");
            System.out.println("1. 카테고리 추가");
            System.out.println("2. 카테고리 조회");
            System.out.println("3. 카테고리 수정");
            System.out.println("4. 카테고리 삭제");
            System.out.println("5. 모든 카테고리 조회");
            System.out.println("6. 뒤로 가기");
            System.out.print("선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addCategory();
                case 2 -> viewCategory();
                case 3 -> updateCategory();
                case 4 -> deleteCategory();
                case 5 -> viewAllCategories();
                case 6 -> {
                    return;
                }
                default -> System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private static void addCategory() throws Exception {
        System.out.println("\n----- 카테고리 추가 -----");
        System.out.print("상위 카테고리 ID (없으면 0): ");
        int categoryId2 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("카테고리명: ");
        String name = scanner.nextLine();

        Category category = Category.builder()
                .categoryId2(categoryId2)
                .name(name)
                .build();

        Category addedCategory = categoryService.add(category);
        System.out.println("카테고리가 추가되었습니다. ID: " + addedCategory.getCategoryId());
    }

    private static void viewCategory() throws Exception {
        System.out.println("\n----- 카테고리 조회 -----");
        System.out.print("조회할 카테고리 ID: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();

        Category category = categoryService.get(categoryId);
        if (category != null) {
            // 카테고리 상세 정보 출력
            System.out.println("\n===== 카테고리 정보 =====");
            System.out.printf("카테고리 ID: %d%n", category.getCategoryId());
            System.out.printf("서브 카테고리 ID: %d%n", category.getCategoryId2());
            System.out.printf("카테고리 이름: %s%n", category.getName() != null ? category.getName() : "N/A");
        } else {
            System.out.println("해당 ID의 카테고리가 없습니다.");
        }
    }


    private static void updateCategory() throws Exception {
        System.out.println("\n----- 카테고리 수정 -----");
        System.out.print("수정할 카테고리 ID: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();

        Category category = categoryService.get(categoryId);
        if (category == null) {
            System.out.println("해당 ID의 카테고리가 없습니다.");
            return;
        }

        System.out.print("새 상위 카테고리 ID (현재: " + category.getCategoryId2() + "): ");
        String newCategoryId2 = scanner.nextLine();
        if (!newCategoryId2.isEmpty()) {
            category.setCategoryId2(Integer.parseInt(newCategoryId2));
        }

        System.out.print("새 카테고리명 (현재: " + category.getName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            category.setName(newName);
        }

        categoryService.modify(category);
        System.out.println("카테고리 정보가 수정되었습니다.");
    }

    private static void deleteCategory() throws Exception {
        System.out.println("\n----- 카테고리 삭제 -----");
        System.out.print("삭제할 카테고리 ID: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();

        if (categoryService.remove(categoryId)) {
            System.out.println("카테고리가 삭제되었습니다.");
        } else {
            System.out.println("카테고리 삭제에 실패했습니다.");
        }
    }

    private static void viewAllCategories() throws Exception {
        System.out.println("\n----- 모든 카테고리 조회 -----");
        List<Category> categories = categoryService.get();

        System.out.println("--------------------------------------------------------");
        System.out.printf("%-15s %-15s %-30s%n", "Category ID", "SubCategory ID", "Category Name");
        System.out.println("--------------------------------------------------------");

        for (Category category : categories) {
            System.out.printf("%-15d %-15d %-30s%n",
                    category.getCategoryId(),
                    category.getCategoryId2(),
                    category.getName() != null ? category.getName() : "N/A"
            );
        }

        System.out.println("--------------------------------------------------------");
    }



    /**
     * --------------------------- Board ---------------------------
     */

    public static void manageBoards() throws Exception {
        while (true) {
            System.out.println("\n===== 게시판 관리 =====");
            System.out.println("1. 게시글 추가");
            System.out.println("2. 게시글 조회");
            System.out.println("3. 게시글 수정");
            System.out.println("4. 게시글 삭제");
            System.out.println("5. 모든 게시글 조회");
            System.out.println("6. 뒤로 가기");
            System.out.print("선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addBoard();
                case 2 -> viewBoard();
                case 3 -> updateBoard();
                case 4 -> deleteBoard();
                case 5 -> viewAllBoards();
                case 6 -> {
                    return;
                }
                default -> System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private static void addBoard() throws Exception {
        System.out.println("\n----- 게시글 추가 -----");
        System.out.print("고객 ID: ");
        int custId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("제품 ID (없으면 0): ");
        int productId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("게시글 유형 (notice/review): ");
        String ntype = scanner.nextLine();
        System.out.print("제목: ");
        String title = scanner.nextLine();
        System.out.print("내용: ");
        String content = scanner.nextLine();
        System.out.print("이미지 URL (없으면 엔터): ");
        String img = scanner.nextLine();
        System.out.print("평점 (1-5, 리뷰가 아니면 0): ");
        int rate = scanner.nextInt();
        scanner.nextLine();

        Board board = Board.builder()
                .custId(custId)
                .productId(productId == 0 ? null : productId)
                .ntype(ntype)
                .title(title)
                .regDate(new Date())
                .content(content)
                .img(img.isEmpty() ? null : img)
                .rate(rate)
                .build();

        Board addedBoard = boardService.add(board);
        System.out.println("게시글이 추가되었습니다. ID: " + addedBoard.getBoardId());
    }

    private static void viewBoard() throws Exception {
        System.out.println("\n----- 게시글 조회 -----");
        System.out.print("조회할 게시글 ID: ");
        int boardId = scanner.nextInt();
        scanner.nextLine();

        Board board = boardService.get(boardId);
        if (board != null) {
            // 게시글 상세 정보 출력
            System.out.println("\n===== 게시글 정보 =====");
            System.out.printf("게시글 ID: %d%n", board.getBoardId());
            System.out.printf("작성자 ID: %d%n", board.getCustId());
            System.out.printf("상품 ID: %d%n", board.getProductId() != null ? board.getProductId() : 0);
            System.out.printf("게시글 유형: %s%n", board.getNtype() != null ? board.getNtype() : "N/A");
            System.out.printf("제목: %s%n", board.getTitle() != null ? board.getTitle() : "N/A");
            System.out.printf("등록일: %tF%n", board.getRegDate() != null ? board.getRegDate() : new Date());
            System.out.printf("내용: %s%n", board.getContent() != null ? board.getContent() : "N/A");
            System.out.printf("이미지: %s%n", board.getImg() != null ? board.getImg() : "N/A");
            System.out.printf("평점: %d%n", board.getRate() != null ? board.getRate() : 0);
        } else {
            System.out.println("해당 ID의 게시글이 없습니다.");
        }
    }

    private static void updateBoard() throws Exception {
        System.out.println("\n----- 게시글 수정 -----");
        System.out.print("수정할 게시글 ID: ");
        int boardId = scanner.nextInt();
        scanner.nextLine();

        Board board = boardService.get(boardId);
        if (board == null) {
            System.out.println("해당 ID의 게시글이 없습니다.");
            return;
        }

        System.out.print("새 제목 (현재: " + board.getTitle() + "): ");
        String newTitle = scanner.nextLine();
        if (!newTitle.isEmpty()) {
            board.setTitle(newTitle);
        }

        System.out.print("새 내용 (현재: " + board.getContent() + "): ");
        String newContent = scanner.nextLine();
        if (!newContent.isEmpty()) {
            board.setContent(newContent);
        }

        System.out.print("새 이미지 URL (현재: " + board.getImg() + "): ");
        String newImg = scanner.nextLine();
        if (!newImg.isEmpty()) {
            board.setImg(newImg);
        }

        System.out.print("새 평점 (현재: " + board.getRate() + "): ");
        String newRate = scanner.nextLine();
        if (!newRate.isEmpty()) {
            board.setRate(Integer.parseInt(newRate));
        }

        boardService.modify(board);
        System.out.println("게시글 정보가 수정되었습니다.");
    }

    private static void deleteBoard() throws Exception {
        System.out.println("\n----- 게시글 삭제 -----");
        System.out.print("삭제할 게시글 ID: ");
        int boardId = scanner.nextInt();
        scanner.nextLine();

        if (boardService.remove(boardId)) {
            System.out.println("게시글이 삭제되었습니다.");
        } else {
            System.out.println("게시글 삭제에 실패했습니다.");
        }
    }

    private static void viewAllBoards() throws Exception {
        System.out.println("\n----- 모든 게시글 조회 -----");
        List<Board> boards = boardService.get();

        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-10s %-15s %-20s %-15s %-10s%n",
                "Board ID", "Cust ID", "Product ID", "Title", "Reg Date", "Rate");
        System.out.println("------------------------------------------------------------------------------------------------------------");

        for (Board board : boards) {
            System.out.printf("%-10d %-10d %-15d %-20s %-15tF %-10d%n",
                    board.getBoardId(),
                    board.getCustId(),
                    board.getProductId() != null ? board.getProductId() : 0,
                    board.getTitle() != null ? board.getTitle() : "N/A",
                    board.getRegDate() != null ? board.getRegDate() : new Date(),
                    board.getRate() != null ? board.getRate() : 0
            );
        }

        System.out.println("------------------------------------------------------------------------------------------------------------");
    }



    /**
     * --------------------------- Order && OrderDetail ---------------------------
     */

    public static void manageOrders() throws Exception {
        while (true) {
            System.out.println("\n===== 주문 관리 =====");
            System.out.println("1. 주문 추가");
            System.out.println("2. 주문 조회");
            System.out.println("3. 주문 수정");
            System.out.println("4. 주문 삭제");
            System.out.println("5. 모든 주문 조회");
            System.out.println("6. 주문 상세 관리");
            System.out.println("7. 뒤로 가기");
            System.out.print("선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addOrder();
                case 2 -> viewOrder();
                case 3 -> updateOrder();
                case 4 -> deleteOrder();
                case 5 -> viewAllOrders();
                case 6 -> manageOrderDetails();
                case 7 -> {
                    return;
                }
                default -> System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private static void addOrder() throws Exception {
        System.out.println("\n----- 주문 추가 -----");
        System.out.print("고객 ID: ");
        int custId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("제품 수량: ");
        int productCount = scanner.nextInt();
        scanner.nextLine();
        System.out.print("총 가격: ");
        int price = scanner.nextInt();
        scanner.nextLine();
        System.out.print("수령인 이름: ");
        String name = scanner.nextLine();
        System.out.print("연락처: ");
        String phone = scanner.nextLine();
        System.out.print("주소1: ");
        String address1 = scanner.nextLine();
        System.out.print("주소2: ");
        String address2 = scanner.nextLine();
        System.out.print("우편번호: ");
        String zipCode = scanner.nextLine();
        System.out.print("요청사항: ");
        String request = scanner.nextLine();
        System.out.print("카드 정보: ");
        String card = scanner.nextLine();
        System.out.print("사용 마일리지: ");
        int usedMileage = scanner.nextInt();
        scanner.nextLine();

        Order order = Order.builder()
                .custId(custId)
                .productCount(productCount)
                .price(price)
                .orderDate(new Date())
                .name(name)
                .phone(phone)
                .address1(address1)
                .address2(address2)
                .zipCode(zipCode)
                .request(request)
                .card(card)
                .usedMileage(usedMileage)
                .build();

        Order addedOrder = orderService.add(order);
        System.out.println("주문이 추가되었습니다. ID: " + addedOrder.getOrderId());
    }

    private static void viewOrder() throws Exception {
        System.out.println("\n----- 주문 조회 -----");
        System.out.print("조회할 주문 ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        Order order = orderService.get(orderId);
        if (order != null) {
            System.out.println("\n===== 주문 정보 =====");
            System.out.printf("주문 ID: %d%n", order.getOrderId());
            System.out.printf("고객 ID: %d%n", order.getCustId());
            System.out.printf("제품 개수: %d%n", order.getProductCount());
            System.out.printf("총 가격: %,d원%n", order.getPrice());
            System.out.printf("주문 날짜: %tF%n", order.getOrderDate() != null ? order.getOrderDate() : new Date());
            System.out.printf("받는 사람: %s%n", order.getName() != null ? order.getName() : "N/A");
            System.out.printf("전화번호: %s%n", order.getPhone() != null ? order.getPhone() : "N/A");
            System.out.printf("주소: %s, %s, %s%n", order.getAddress1(), order.getAddress2(), order.getZipCode());
            System.out.printf("요청사항: %s%n", order.getRequest() != null ? order.getRequest() : "N/A");
            System.out.printf("카드: %s%n", order.getCard() != null ? order.getCard() : "N/A");
            System.out.printf("사용한 마일리지: %d%n", order.getUsedMileage() != null ? order.getUsedMileage() : 0);
        } else {
            System.out.println("해당 ID의 주문이 없습니다.");
        }
    }


    private static void updateOrder() throws Exception {
        System.out.println("\n----- 주문 수정 -----");
        System.out.print("수정할 주문 ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        Order order = orderService.get(orderId);
        if (order == null) {
            System.out.println("해당 ID의 주문이 없습니다.");
            return;
        }

        System.out.print("새 수령인 이름 (현재: " + order.getName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            order.setName(newName);
        }

        System.out.print("새 연락처 (현재: " + order.getPhone() + "): ");
        String newPhone = scanner.nextLine();
        if (!newPhone.isEmpty()) {
            order.setPhone(newPhone);
        }

        orderService.modify(order);
        System.out.println("주문 정보가 수정되었습니다.");
    }

    private static void deleteOrder() throws Exception {
        System.out.println("\n----- 주문 삭제 -----");
        System.out.print("삭제할 주문 ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        if (orderService.remove(orderId)) {
            System.out.println("주문이 삭제되었습니다.");
        } else {
            System.out.println("주문 삭제에 실패했습니다.");
        }
    }

    private static void viewAllOrders() throws Exception {
        System.out.println("\n----- 모든 주문 조회 -----");
        List<Order> orders = orderService.get();

        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-10s %-15s %-10s %-15s %-10s %-15s%n",
                "Order ID", "Cust ID", "Product Count", "Price", "Order Date", "Name", "Phone");
        System.out.println("------------------------------------------------------------------------------------------------------------");

        for (Order order : orders) {
            System.out.printf("%-10d %-10d %-15d %,10d %-15tF %-10s %-15s%n",
                    order.getOrderId(),
                    order.getCustId(),
                    order.getProductCount(),
                    order.getPrice(),
                    order.getOrderDate() != null ? order.getOrderDate() : new Date(),
                    order.getName() != null ? order.getName() : "N/A",
                    order.getPhone() != null ? order.getPhone() : "N/A"
            );
        }

        System.out.println("------------------------------------------------------------------------------------------------------------");
    }


    private static void manageOrderDetails() throws Exception {
        while (true) {
            System.out.println("\n===== 주문 상세 관리 =====");
            System.out.println("1. 주문 상세 추가");
            System.out.println("2. 주문 상세 조회");
            System.out.println("3. 주문 상세 수정");
            System.out.println("4. 주문 상세 삭제");
            System.out.println("5. 모든 주문 상세 조회");
            System.out.println("6. 뒤로 가기");
            System.out.print("선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addOrderDetail();
                case 2 -> viewOrderDetail();
                case 3 -> updateOrderDetail();
                case 4 -> deleteOrderDetail();
                case 5 -> viewAllOrderDetails();
                case 6 -> {
                    return;
                }
                default -> System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private static void addOrderDetail() throws Exception {
        System.out.println("\n----- 주문 상세 추가 -----");
        System.out.print("제품 ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("주문 ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("가격: ");
        int price = scanner.nextInt();
        scanner.nextLine();
        System.out.print("수량: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        OrderDetail orderDetail = OrderDetail.builder()
                .productId(productId)
                .orderId(orderId)
                .price(price)
                .count(count)
                .build();

        OrderDetail addedOrderDetail = orderDetailService.add(orderDetail);
        System.out.println("주문 상세가 추가되었습니다. ID: " + addedOrderDetail.getOrderDetailId());
    }

    private static void viewOrderDetail() throws Exception {
        System.out.println("\n----- 주문 상세 조회 -----");
        System.out.print("조회할 주문 상세 ID: ");
        int orderDetailId = scanner.nextInt();
        scanner.nextLine();

        OrderDetail orderDetail = orderDetailService.get(orderDetailId);
        if (orderDetail != null) {
            System.out.println("\n===== 주문 상세 정보 =====");
            System.out.printf("주문 상세 ID: %d%n", orderDetail.getOrderDetailId());
            System.out.printf("제품 ID: %d%n", orderDetail.getProductId());
            System.out.printf("주문 ID: %d%n", orderDetail.getOrderId());
            System.out.printf("가격: %,d원%n", orderDetail.getPrice());
            System.out.printf("수량: %d%n", orderDetail.getCount());
        } else {
            System.out.println("해당 ID의 주문 상세가 없습니다.");
        }
    }


    private static void updateOrderDetail() throws Exception {
        System.out.println("\n----- 주문 상세 수정 -----");
        System.out.print("수정할 주문 상세 ID: ");
        int orderDetailId = scanner.nextInt();
        scanner.nextLine();

        OrderDetail orderDetail = orderDetailService.get(orderDetailId);
        if (orderDetail == null) {
            System.out.println("해당 ID의 주문 상세가 없습니다.");
            return;
        }

        System.out.print("새 가격 (현재: " + orderDetail.getPrice() + "): ");
        String newPrice = scanner.nextLine();
        if (!newPrice.isEmpty()) {
            orderDetail.setPrice(Integer.parseInt(newPrice));
        }

        System.out.print("새 수량 (현재: " + orderDetail.getCount() + "): ");
        String newCount = scanner.nextLine();
        if (!newCount.isEmpty()) {
            orderDetail.setCount(Integer.parseInt(newCount));
        }

        orderDetailService.modify(orderDetail);
        System.out.println("주문 상세 정보가 수정되었습니다.");
    }

    private static void deleteOrderDetail() throws Exception {
        System.out.println("\n----- 주문 상세 삭제 -----");
        System.out.print("삭제할 주문 상세 ID: ");
        int orderDetailId = scanner.nextInt();
        scanner.nextLine();

        if (orderDetailService.remove(orderDetailId)) {
            System.out.println("주문 상세가 삭제되었습니다.");
        } else {
            System.out.println("주문 상세 삭제에 실패했습니다.");
        }
    }

    private static void viewAllOrderDetails() throws Exception {
        System.out.println("\n----- 모든 주문 상세 조회 -----");
        List<OrderDetail> orderDetails = orderDetailService.get();

        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-15s %-15s %-10s %-10s%n",
                "Order Detail ID", "Product ID", "Order ID", "Price", "Count");
        System.out.println("----------------------------------------------------------------------------------------");

        for (OrderDetail orderDetail : orderDetails) {
            System.out.printf("%-15d %-15d %-15d %,10d %-10d%n",
                    orderDetail.getOrderDetailId(),
                    orderDetail.getProductId(),
                    orderDetail.getOrderId(),
                    orderDetail.getPrice(),
                    orderDetail.getCount()
            );
        }

        System.out.println("----------------------------------------------------------------------------------------");
    }



    /**
     * --------------------------- Payment ---------------------------
     */

    public static void managePayments() throws Exception {
        while (true) {
            System.out.println("\n===== 결제 관리 =====");
            System.out.println("1. 결제 추가");
            System.out.println("2. 결제 조회");
            System.out.println("3. 결제 수정");
            System.out.println("4. 결제 삭제");
            System.out.println("5. 모든 결제 조회");
            System.out.println("6. 뒤로 가기");
            System.out.print("선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addPayment();
                case 2 -> viewPayment();
                case 3 -> updatePayment();
                case 4 -> deletePayment();
                case 5 -> viewAllPayments();
                case 6 -> {
                    return;
                }
                default -> System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private static void addPayment() throws Exception {
        System.out.println("\n----- 결제 추가 -----");
        System.out.print("주문 ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("결제 금액: ");
        int price = scanner.nextInt();
        scanner.nextLine();
        System.out.print("결제 방법: ");
        String method = scanner.nextLine();

        Payment payment = Payment.builder()
                .orderId(orderId)
                .price(price)
                .method(method)
                .payDate(new Date())
                .build();

        Payment addedPayment = paymentService.add(payment);
        System.out.println("결제가 추가되었습니다. ID: " + addedPayment.getPaymentId());
    }

    private static void viewPayment() throws Exception {
        System.out.println("\n----- 결제 조회 -----");
        System.out.print("조회할 결제 ID: ");
        int paymentId = scanner.nextInt();
        scanner.nextLine();

        Payment payment = paymentService.get(paymentId);
        if (payment != null) {
            System.out.println("\n===== 결제 정보 =====");
            System.out.printf("결제 ID: %d%n", payment.getPaymentId());
            System.out.printf("주문 ID: %d%n", payment.getOrderId());
            System.out.printf("금액: %,d원%n", payment.getPrice());
            System.out.printf("결제 수단: %s%n", payment.getMethod() != null ? payment.getMethod() : "N/A");
            System.out.printf("결제 날짜: %tF%n", payment.getPayDate() != null ? payment.getPayDate() : new Date());
        } else {
            System.out.println("해당 ID의 결제가 없습니다.");
        }
    }


    private static void updatePayment() throws Exception {
        System.out.println("\n----- 결제 수정 -----");
        System.out.print("수정할 결제 ID: ");
        int paymentId = scanner.nextInt();
        scanner.nextLine();

        Payment payment = paymentService.get(paymentId);
        if (payment == null) {
            System.out.println("해당 ID의 결제가 없습니다.");
            return;
        }

        System.out.print("새 결제 금액 (현재: " + payment.getPrice() + "): ");
        String newPrice = scanner.nextLine();
        if (!newPrice.isEmpty()) {
            payment.setPrice(Integer.parseInt(newPrice));
        }

        System.out.print("새 결제 방법 (현재: " + payment.getMethod() + "): ");
        String newMethod = scanner.nextLine();
        if (!newMethod.isEmpty()) {
            payment.setMethod(newMethod);
        }

        paymentService.modify(payment);
        System.out.println("결제 정보가 수정되었습니다.");
    }

    private static void deletePayment() throws Exception {
        System.out.println("\n----- 결제 삭제 -----");
        System.out.print("삭제할 결제 ID: ");
        int paymentId = scanner.nextInt();
        scanner.nextLine();

        if (paymentService.remove(paymentId)) {
            System.out.println("결제가 삭제되었습니다.");
        } else {
            System.out.println("결제 삭제에 실패했습니다.");
        }
    }

    private static void viewAllPayments() throws Exception {
        System.out.println("\n----- 모든 결제 조회 -----");
        List<Payment> payments = paymentService.get();

        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-10s %-10s %-15s %-10s %-15s%n",
                "Payment ID", "Order ID", "Price", "Method", "Pay Date");
        System.out.println("--------------------------------------------------------------------------------");

        for (Payment payment : payments) {
            System.out.printf("%-10d %-10d %,15d %-10s %-15tF%n",
                    payment.getPaymentId(),
                    payment.getOrderId(),
                    payment.getPrice(),
                    payment.getMethod() != null ? payment.getMethod() : "N/A",
                    payment.getPayDate() != null ? payment.getPayDate() : new Date()
            );
        }

        System.out.println("--------------------------------------------------------------------------------");
    }

    /**
     * --------------------------- Statistics Analysis ---------------------------
     */

    public static void manageStatisticsAnalysis() throws Exception {
        while (true) {
            System.out.println("\n===== 통계 분석 관리 =====");
            System.out.println("1. 고객 분석 수행");
            System.out.println("2. 상품 분석 수행");
            System.out.println("3. 일별 매출 통계 수행");
            System.out.println("4. 월별 매출 통계 수행");
            System.out.println("5. 연도별 매출 통계 수행");
            System.out.println("6. 고객 분석 결과 조회");
            System.out.println("7. 상품 분석 결과 조회");
            System.out.println("8. 일별 매출 통계 조회");
            System.out.println("9. 월별 매출 통계 조회");
            System.out.println("10. 연도별 매출 통계 조회");
            System.out.println("11. 뒤로 가기");
            System.out.print("선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> performCustomerAnalysis();
                case 2 -> performProductAnalysis();
                case 3 -> performDailySalesStatistics();
                case 4 -> performMonthlySalesStatistics();
                case 5 -> performYearlySalesStatistics();
                case 6 -> viewCustomerAnalysis();
                case 7 -> viewProductAnalysis();
                case 8 -> viewDailySalesStatistics();
                case 9 -> viewMonthlySalesStatistics();
                case 10 -> viewYearlySalesStatistics();
                case 11 -> {
                    return;
                }
                default -> System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private static void performCustomerAnalysis() throws Exception {
        statisticsAnalysisService.performCustomerAnalysis();
        System.out.println("고객 분석이 완료되었습니다.");
    }

    private static void performProductAnalysis() throws Exception {
        statisticsAnalysisService.performProductAnalysis();
        System.out.println("상품 분석이 완료되었습니다.");
    }

    private static void performDailySalesStatistics() throws Exception {
        statisticsAnalysisService.performDailySalesStatistics();
        System.out.println("일별 매출 통계 분석이 완료되었습니다.");
    }

    private static void performMonthlySalesStatistics() throws Exception {
        statisticsAnalysisService.performMonthlySalesStatistics();
        System.out.println("월별 매출 통계 분석이 완료되었습니다.");
    }

    private static void performYearlySalesStatistics() throws Exception {
        statisticsAnalysisService.performYearlySalesStatistics();
        System.out.println("연도별 매출 통계 분석이 완료되었습니다.");
    }

    private static void viewCustomerAnalysis() throws Exception {
        List<CustomerAnalysis> analyses = statisticsAnalysisService.analyzeCustomers();
        for (CustomerAnalysis analysis : analyses) {
            System.out.println(analysis);
        }
    }

    private static void viewProductAnalysis() throws Exception {
        List<ProductAnalysis> analyses = statisticsAnalysisService.analyzeProducts();
        for (ProductAnalysis analysis : analyses) {
            System.out.println(analysis);
        }
    }

    private static void viewDailySalesStatistics() throws Exception {
        List<SalesStatistics> statistics = statisticsAnalysisService.getDailySalesStatistics();
        for (SalesStatistics stat : statistics) {
            System.out.println(stat);
        }
    }

    private static void viewMonthlySalesStatistics() throws Exception {
        List<MonthlySalesStatistics> statistics = statisticsAnalysisService.getMonthlySalesStatistics();
        for (MonthlySalesStatistics stat : statistics) {
            System.out.println(stat);
        }
    }

    private static void viewYearlySalesStatistics() throws Exception {
        List<YearlySalesStatistics> statistics = statisticsAnalysisService.getYearlySalesStatistics();
        for (YearlySalesStatistics stat : statistics) {
            System.out.println(stat);
        }
    }

}