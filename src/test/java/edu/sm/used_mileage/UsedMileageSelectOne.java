package edu.sm.used_mileage;

import edu.sm.dto.UsedMileage;
import edu.sm.service.CustomerService;
import edu.sm.service.UsedMileageService;

public class UsedMileageSelectOne {
    public static void main(String[] args) {
        UsedMileageService service = new UsedMileageService();
        CustomerService customerService = new CustomerService();
        try {
            int usedMileageId = 1;
            UsedMileage usedMileage = service.get(usedMileageId);
            System.out.println("--------------------------------------------------");
            if (usedMileage != null) {
                System.out.println("조회된 사용된 마일리지 정보:");
                System.out.printf("사용된 마일리지 ID: %d%n", usedMileage.getUsedMileageId());
                System.out.printf("회원: %s%n", customerService.get(usedMileage.getCustId()).getName());
                System.out.printf("사용 날짜: %s%n", usedMileage.getUsedDate());
                System.out.printf("사용 금액: %d%n", usedMileage.getAmount());
            } else {
                System.out.println("해당 ID의 사용된 마일리지를 찾을 수 없습니다.");
            }
            System.out.println("--------------------------------------------------");
        } catch (Exception e) {
            System.out.println("사용된 마일리지 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
