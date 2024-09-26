package edu.sm.cust;


import edu.sm.dto.Customer;
import edu.sm.service.CustomerService;

public class CustomerSelectOne {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        int id = 1; // 가정: 데이터베이스에서 custId는 정수형
        Customer customer = null;
        try {
            customer = customerService.get(id);
            if (customer != null) {
                System.out.println("조회된 고객 정보: " + customer);
            } else {
                System.out.println("해당 ID의 고객이 없습니다: " + id);
            }
        } catch (Exception e) {
            System.out.println("고객 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}