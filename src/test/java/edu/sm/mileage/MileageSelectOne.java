package edu.sm.mileage;

import edu.sm.dto.Mileage;
import edu.sm.service.CustomerService;
import edu.sm.service.MileageService;

public class MileageSelectOne {
    public static void main(String[] args) {
        MileageService service = new MileageService();
        CustomerService customerService = new CustomerService();

        try {
            int mileageId = 1;
            Mileage mileage = service.get(mileageId);
            System.out.println("--------------------------------------------------");
            if (mileage != null) {
                System.out.printf("조회된 마일리지 정보:%n");
                System.out.printf("회원: %s%n", customerService.get(mileage.getCustId()).getName());
                System.out.printf("마일리지 ID: %d%n", mileage.getMileageId());
                System.out.printf("잔액: %d%n", mileage.getBalance());
            } else {
                System.out.println("해당 ID의 마일리지를 찾을 수 없습니다.");
            }
            System.out.println("--------------------------------------------------");
        } catch (Exception e) {
            System.out.println("마일리지 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
