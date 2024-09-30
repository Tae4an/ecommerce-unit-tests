package edu.sm.mileage;

import edu.sm.dto.Mileage;
import edu.sm.service.CustomerService;
import edu.sm.service.MileageService;

public class MileageUpdate {
    public static void main(String[] args) {
        MileageService service = new MileageService();
        CustomerService customerService = new CustomerService();
        try {
            Mileage mileage = service.get(1);
            if (mileage != null) {
                mileage.setBalance(1500);
                Mileage updatedMileage = service.modify(mileage);
                System.out.println("--------------------------------------------------");
                System.out.printf("수정된 마일리지: %n");
                System.out.printf("회원: %s%n", customerService.get(updatedMileage.getCustId()).getName());
                System.out.printf("마일리지 ID: %d%n", updatedMileage.getMileageId());
                System.out.printf("새 잔액: %d%n", updatedMileage.getBalance());
                System.out.println("--------------------------------------------------");
            } else {
                System.out.println("마일리지 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("마일리지 수정 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
