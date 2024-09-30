package edu.sm.used_mileage;

import edu.sm.dto.UsedMileage;
import edu.sm.service.CustomerService;
import edu.sm.service.UsedMileageService;

public class UsedMileageDelete {
    public static void main(String[] args) {
        UsedMileageService service = new UsedMileageService();
        CustomerService customerService = new CustomerService();

        try {
            int usedMileageId = 1;
            UsedMileage usedMileage = service.get(usedMileageId);

            if (usedMileage != null) {
                Boolean isDeleted = service.remove(usedMileageId);
                System.out.println("--------------------------------------------------");
                if (isDeleted) {
                    System.out.printf("사용된 마일리지 ID %d가 삭제되었습니다(회원: %s).%n", usedMileageId, customerService.get(usedMileage.getCustId()).getName());
                } else {
                    System.out.printf("사용된 마일리지 ID %d 삭제에 실패하였습니다.%n", usedMileageId);
                }
                System.out.println("--------------------------------------------------");
            } else {
                System.out.printf("사용된 마일리지 ID %d를 찾을 수 없습니다.%n", usedMileageId);
            }
        } catch (Exception e) {
            System.out.println("사용된 마일리지 삭제 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
