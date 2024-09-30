package edu.sm.used_mileage;

import edu.sm.dto.UsedMileage;
import edu.sm.service.CustomerService;
import edu.sm.service.UsedMileageService;

import java.util.Date;

public class UsedMileageInsert {
    public static void main(String[] args) {
        UsedMileageService service = new UsedMileageService();
        CustomerService customerService = new CustomerService();
        try {
            UsedMileage newUsedMileage = new UsedMileage(null, 1, new Date(), 500);
            UsedMileage addedUsedMileage = service.add(newUsedMileage);
            System.out.println("--------------------------------------------------");
            System.out.println("추가된 사용된 마일리지 정보:");
            System.out.printf("사용된 마일리지 ID: %d%n", addedUsedMileage.getUsedMileageId());
            System.out.printf("회원: %s%n", customerService.get(addedUsedMileage.getCustId()).getName());
            System.out.printf("사용 날짜: %s%n", addedUsedMileage.getUsedDate());
            System.out.printf("사용 금액: %d%n", addedUsedMileage.getAmount());
            System.out.println("--------------------------------------------------");
        } catch (Exception e) {
            System.out.println("사용된 마일리지 추가 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
