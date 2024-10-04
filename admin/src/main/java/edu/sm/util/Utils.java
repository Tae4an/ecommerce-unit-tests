package edu.sm.util;

import edu.sm.dto.Address;
import edu.sm.dto.Customer;
import edu.sm.service.AddressService;
import edu.sm.service.CustomerService;

import java.util.List;
import java.util.Scanner;

public class Utils {
    private static final CustomerService customerService = new CustomerService();
    private static final AddressService addressService = new AddressService();
    private static final Scanner scanner = new Scanner(System.in);

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
            scanner.nextLine(); // 버퍼 비우기

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
        System.out.println("회원이 추가되었습니다. ID: " + addedCustomer.getCustId());
    }

    private static void viewCustomers() throws Exception {
        System.out.println("\n----- 회원 조회 -----");
        List<Customer> customers = customerService.get();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
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
            for (Customer customer : searchResults) {
                System.out.println(customer);
            }
        }
    }

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
            scanner.nextLine(); // 버퍼 비우기

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
        for (Address address : addresses) {
            System.out.println(address);
        }
    }

    private static void updateAddress() throws Exception {
        System.out.println("\n----- 배송지 수정 -----");
        System.out.print("수정할 배송지 ID: ");
        int addressId = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

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
}