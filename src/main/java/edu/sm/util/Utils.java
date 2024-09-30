package edu.sm.util;

import edu.sm.dto.Address;
import edu.sm.dto.Customer;
import edu.sm.service.AddressService;
import edu.sm.service.CustomerService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public  class Utils {
    private static final CustomerService customerService = new CustomerService();
    private static final AddressService addressService = new AddressService();
    private static final Scanner scanner = new Scanner(System.in);

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

    public static void listCustomers() throws Exception {
        List<Customer> customers = customerService.get();
        System.out.println("고객 목록:");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    public static void searchCustomers() throws Exception {
        System.out.print("검색 키워드를 입력하세요: ");
        String keyword = scanner.nextLine();
        List<Customer> customers = customerService.searchMembers(keyword);
        System.out.println("검색 결과:");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    public static void updateCustomer(Customer customer) throws Exception {
        System.out.println("고객 정보 수정을 시작합니다.");
        System.out.print("새 사용자명 (건너뛰려면 Enter): ");
        String username = scanner.nextLine();
        if (!username.isEmpty()) customer.setUsername(username);

        System.out.print("새 비밀번호 (건너뛰려면 Enter): ");
        String password = scanner.nextLine();
        if (!password.isEmpty()) customer.setPw(password);

        System.out.print("새 이름 (건너뛰려면 Enter): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) customer.setName(name);

        System.out.print("새 전화번호 (건너뛰려면 Enter): ");
        String phoneNumber = scanner.nextLine();
        if (!phoneNumber.isEmpty()) customer.setPNumber(phoneNumber);

        customerService.modify(customer);
        System.out.println("고객 정보가 수정되었습니다.");
    }

    public static void deleteCustomer(Customer customer) throws Exception {
        System.out.println("정말로 계정을 삭제하시겠습니까? (y/n)");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            customerService.remove(customer.getCustId());
            System.out.println("계정이 삭제되었습니다.");
        } else {
            System.out.println("계정 삭제가 취소되었습니다.");
        }
    }
}