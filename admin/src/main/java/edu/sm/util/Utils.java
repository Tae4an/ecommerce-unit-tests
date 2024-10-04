package edu.sm.util;

import edu.sm.dto.Address;
import edu.sm.dto.Category;
import edu.sm.dto.Customer;
import edu.sm.dto.Product;
import edu.sm.service.AddressService;
import edu.sm.service.CategoryService;
import edu.sm.service.CustomerService;
import edu.sm.service.ProductService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Utils {
    private static final CustomerService customerService = new CustomerService();
    private static final AddressService addressService = new AddressService();
    private static final ProductService productService = new ProductService();
    private static final CategoryService categoryService = new CategoryService();
    private static final Scanner scanner = new Scanner(System.in);


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
                case 1:
                    addCustomer();
                    break;
                case 2:
                    viewCustomers();
                    break;
                case 3:
                    updateCustomer();
                    break;
                case 4:
                    deleteCustomer();
                    break;
                case 5:
                    searchCustomers();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
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
                case 1:
                    addAddress();
                    break;
                case 2:
                    viewAddresses();
                    break;
                case 3:
                    updateAddress();
                    break;
                case 4:
                    deleteAddress();
                    break;
                case 5:
                    setDefaultAddress();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
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
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    toggleProductStatus();
                    break;
                case 5:
                    viewAllProducts();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
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
     * --------------------------- Address ---------------------------
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
                case 1:
                    addCategory();
                    break;
                case 2:
                    viewCategory();
                    break;
                case 3:
                    updateCategory();
                    break;
                case 4:
                    deleteCategory();
                    break;
                case 5:
                    viewAllCategories();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
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

}