package edu.sm.cust;

import edu.sm.dto.Customer;
import edu.sm.service.CustomerService;

import java.util.List;

public class CustomerListMembers {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        List<Customer> customers = null;
        try {
            customers = customerService.get();
            System.out.println("전체 고객 목록:");
            System.out.println("------------------------------------------------------------------------");
            System.out.printf("%-5s %-10s %-15s %-10s %-15s %-12s%n",
                    "ID", "Username", "Name", "Phone", "Sign-Up Date", "Password");
            System.out.println("------------------------------------------------------------------------");
            for (Customer customer : customers) {
                System.out.printf("%-5d %-10s %-15s %-10s %-15s %-12s%n",
                        customer.getCustId(),
                        customer.getUsername(),
                        customer.getName(),
                        customer.getPNumber(),
                        customer.getSignUpDate(),
                        customer.getPw());
            }
            System.out.println("------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("고객 목록 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}