package edu.sm.cust;

import edu.sm.dto.Customer;
import edu.sm.service.CustomerService;

import java.util.Date;

public class CustomerUpdate {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        Customer customer = Customer.builder()
                .custId(6) // 변경하고 싶은 유저의 아이디(PK)
                .username("user6") // 새로운 유저 아이디
                .pw("pass112") // 새로운 패스워드 (아래 위와 동)
                .name("김철수")
                .pNumber("010-2121-3211")
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