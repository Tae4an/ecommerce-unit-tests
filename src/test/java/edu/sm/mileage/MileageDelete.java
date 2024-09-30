package edu.sm.mileage;

import edu.sm.service.CustomerService;
import edu.sm.service.MileageService;
import edu.sm.dto.Mileage;

public class MileageDelete {
    public static void main(String[] args) {
        MileageService service = new MileageService();
        CustomerService customerService = new CustomerService();
        try {
            int mileageId = 1;
            Mileage mileage = service.get(mileageId);
            Boolean isDeleted = service.remove(mileageId);
            System.out.println("--------------------------------------------------");
            if (isDeleted) {
                if (mileage != null) {
                    System.out.printf("마일리지 ID %d가 삭제되었습니다. (회원: %s)%n", mileageId, customerService.get(mileage.getCustId()).getName());
                } else {
                    System.out.printf("마일리지 ID %d가 삭제되었습니다. (회원 정보를 찾을 수 없음)%n", mileageId);
                }
            } else {
                System.out.printf("마일리지 ID %d 삭제에 실패하였습니다.%n", mileageId);
            }
            System.out.println("--------------------------------------------------");
        } catch (Exception e) {
            System.out.println("마일리지 삭제 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
