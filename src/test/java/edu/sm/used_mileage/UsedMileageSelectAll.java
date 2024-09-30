package edu.sm.used_mileage;

import edu.sm.dto.UsedMileage;
import edu.sm.service.CustomerService;
import edu.sm.service.UsedMileageService;

import java.util.List;

public class UsedMileageSelectAll {
    public static void main(String[] args) {
        UsedMileageService service = new UsedMileageService();
        CustomerService customerService = new CustomerService();

        try {
            List<UsedMileage> allUsedMileages = service.get();
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("사용된 마일리지 전체 목록:");
            System.out.println("-------------------------------------------------------------------------");
            for (UsedMileage usedMileage : allUsedMileages) {
                System.out.printf("사용된 마일리지 ID: %d | 회원: %s | 사용 날짜: %s | 사용 금액: %d%n",
                        usedMileage.getUsedMileageId(),
                        customerService.get(usedMileage.getCustId()).getName(),
                        usedMileage.getUsedDate(),
                        usedMileage.getAmount());
            }
            System.out.println("-------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("사용된 마일리지 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
