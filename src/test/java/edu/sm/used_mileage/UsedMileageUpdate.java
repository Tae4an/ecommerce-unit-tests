package edu.sm.used_mileage;

import edu.sm.dto.UsedMileage;
import edu.sm.service.CustomerService;
import edu.sm.service.UsedMileageService;

public class UsedMileageUpdate {
    public static void main(String[] args) {
        UsedMileageService service = new UsedMileageService();
        CustomerService customerService = new CustomerService();
        try {
            UsedMileage usedMileage = service.get(1);
            if (usedMileage != null) {
                int oldAmount = usedMileage.getAmount();
                usedMileage.setAmount(700);
                UsedMileage updatedUsedMileage = service.modify(usedMileage);
                System.out.println("--------------------------------------------------");
                System.out.printf("사용된 마일리지 ID %d가 수정되었습니다.%n", updatedUsedMileage.getUsedMileageId());
                System.out.printf("회원: %s%n", customerService.get(updatedUsedMileage.getCustId()).getName());
                System.out.printf("수정 전 사용 금액: %d%n", oldAmount);
                System.out.printf("수정 후 사용 금액: %d%n", updatedUsedMileage.getAmount());
                System.out.println("--------------------------------------------------");
            } else {
                System.out.println("사용된 마일리지를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("사용된 마일리지 수정 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
