package edu.sm.cust;


import edu.sm.dto.Customer;
import edu.sm.service.CustomerService;

public class CustomerSelectOne {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        int id = 1;
        Customer customer = null;
        try {
            customer = customerService.get(id);
            if (customer != null) {
                System.out.println("조회된 고객 정보:");
                System.out.println("--------------------------------------------------------------------------");
                System.out.printf("%-5s %-10s %-15s %-10s %-15s %-12s%n",
                        "ID", "Username", "Name", "Phone", "Sign-Up Date", "Password");
                System.out.println("--------------------------------------------------------------------------");
                System.out.printf("%-5d %-10s %-15s %-10s %-15s %-12s%n",
                        customer.getCustId(),
                        customer.getUsername(),
                        customer.getName(),
                        customer.getPNumber(),
                        customer.getSignUpDate(),
                        customer.getPw());
                System.out.println("--------------------------------------------------------------------------");
            } else {
                System.out.println("해당 ID의 고객이 없습니다: " + id);
            }
        } catch (Exception e) {
            System.out.println("고객 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}