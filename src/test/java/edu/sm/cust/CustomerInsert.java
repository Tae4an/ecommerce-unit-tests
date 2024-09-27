package edu.sm.cust;

import edu.sm.dto.Customer;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.service.CustomerService;

import java.util.Date;

public class CustomerInsert {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        Customer customer = Customer.builder()
                .username("user6")
                .pw("pass992")
                .name("신승민")
                .pNumber("010-3201-4202")
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