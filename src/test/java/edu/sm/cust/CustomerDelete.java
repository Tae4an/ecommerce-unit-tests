package edu.sm.cust;

import edu.sm.service.CustomerService;

public class CustomerDelete {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        int id = 88; // 가정: 데이터베이스에서 custId는 정수형
        try {
            boolean result = customerService.remove(id);
            if (result) {
                System.out.println("고객 삭제 성공: " + id);
            } else {
                System.out.println("고객 삭제 실패: " + id);
            }
        } catch (Exception e) {
            System.out.println("고객 삭제 중 오류 발생:");
            e.printStackTrace();
        }
    }
}