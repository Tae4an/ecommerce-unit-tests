package edu.sm.cust;

import edu.sm.dto.Customer;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.service.CustomerService;

import java.util.Date;

public class CustomerInsert {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        Customer customer = Customer.builder()
                .username("id111")
                .pw("pwd88")
                .name("taesan")
                .pNumber("01012345678")
                .signUpDate(new Date())
                .build();
        try {
            Customer addedCustomer = customerService.add(customer);
            System.out.println("고객 등록 성공: " + addedCustomer);
        } catch (DuplicatedIdException e) {
            System.out.println("ID가 중복되었습니다");
        } catch (Exception e) {
            System.out.println("시스템 장애");
            e.printStackTrace();
        }
    }
}