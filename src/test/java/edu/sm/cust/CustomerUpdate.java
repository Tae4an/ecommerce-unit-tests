package edu.sm.cust;

import edu.sm.dto.Customer;
import edu.sm.service.CustomerService;

import java.util.Date;

public class CustomerUpdate {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        Customer customer = Customer.builder()
                .custId(88)
                .username("id88")
                .pw("pwd888")
                .name("홍말자")
                .pNumber("01087654321")
                .signUpDate(new Date())
                .build();
        try {
            Customer updatedCustomer = customerService.modify(customer);
            System.out.println("고객 정보 수정 성공: " + updatedCustomer);
        } catch (Exception e) {
            System.out.println("고객 정보 수정 중 오류 발생:");
            e.printStackTrace();
        }
    }
}