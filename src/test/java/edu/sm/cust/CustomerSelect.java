package edu.sm.cust;

import edu.sm.dto.Customer;
import edu.sm.service.CustomerService;

import java.util.List;

public class CustomerSelect {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        List<Customer> customers = null;
        try {
            customers = customerService.get();
            System.out.println("전체 고객 목록:");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        } catch (Exception e) {
            System.out.println("고객 목록 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}