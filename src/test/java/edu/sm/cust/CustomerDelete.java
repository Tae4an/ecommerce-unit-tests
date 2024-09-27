package edu.sm.cust;

import edu.sm.service.CustomerService;

public class CustomerDelete {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        int id = 6;
        try {
            System.out.println( "삭제할 고객 이름 : "+ customerService.get(id).getName());
            boolean result = customerService.remove(id);
            if (result) {
                System.out.println("고객 삭제 성공: " + id );
            } else {
                System.out.println("고객 삭제 실패: " + id);
            }
        } catch (Exception e) {
            System.out.println("고객 삭제 중 오류 발생:");
            e.printStackTrace();
        }
    }
}