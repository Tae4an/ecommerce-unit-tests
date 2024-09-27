package edu.sm.cust;

import edu.sm.dto.Customer;
import edu.sm.service.CustomerService;

import java.util.List;
import java.util.Scanner;

public class CustomerSearch {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        Scanner scanner = new Scanner(System.in);

        System.out.print("검색할 회원 정보를 입력하세요(아이디 or 이름 or 핸드폰 번호): ");
        String keyword = scanner.nextLine();

        try {
            List<Customer> customers = customerService.searchMembers(keyword);
            if (!customers.isEmpty()) {
                System.out.println("검색된 회원 정보:");
                System.out.println("--------------------------------------------------------------------------");
                System.out.printf("%-10s", "Name :");
                for (Customer customer : customers) {
                    System.out.printf("%-10s\n",
                            customer.getName());
                }
                System.out.println("--------------------------------------------------------------------------");
            } else {
                System.out.println("검색 결과가 없습니다: " + keyword);
            }
        } catch (Exception e) {
            System.out.println("고객 검색 중 오류 발생:");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}