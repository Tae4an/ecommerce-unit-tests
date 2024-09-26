package edu.sm.app;

import edu.sm.dto.Customer;
import edu.sm.service.CustomerService;

public class Main {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();

        try {
            // 고객 정보 등록
            Customer newCustomer = Customer.builder()
                    .username("xotks1234")
                    .pw("password123")
                    .name("TaeSan")
                    .pNumber("1234567890")
                    .build();
            Customer addedCustomer = customerService.add(newCustomer);
            System.out.println("Added customer: " + addedCustomer);

            // 고객 정보 수정
            addedCustomer.setName("TaeSan");
            Customer updatedCustomer = customerService.modify(addedCustomer);
            System.out.println("Updated customer: " + updatedCustomer);

            // 고객 정보 조회
            Customer retrievedCustomer = customerService.get(addedCustomer.getCustId());
            System.out.println("Retrieved customer: " + retrievedCustomer);

            // 고객 삭제
            Boolean removed = customerService.remove(addedCustomer.getCustId());
            System.out.println("Customer removed: " + removed);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}