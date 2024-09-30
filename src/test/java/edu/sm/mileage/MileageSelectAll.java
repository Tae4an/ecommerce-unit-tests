package edu.sm.mileage;

import edu.sm.dto.Mileage;
import edu.sm.service.CustomerService;
import edu.sm.service.MileageService;

import java.util.List;

public class MileageSelectAll {
    public static void main(String[] args) {
        MileageService service = new MileageService();
        CustomerService customerService = new CustomerService();

        try {
            List<Mileage> allMileages = service.get();
            System.out.println("--------------------------------------------------");
            System.out.println("모든 마일리지 목록:");
            if (allMileages.isEmpty()) {
                System.out.println("등록된 마일리지가 없습니다.");
            } else {
                for (Mileage mileage : allMileages) {
                    System.out.printf("회원: %s | 마일리지 ID: %d | 잔액: %d%n",
                            customerService.get(mileage.getCustId()).getName(), mileage.getMileageId(), mileage.getBalance());
                }
            }
            System.out.println("--------------------------------------------------");
        } catch (Exception e) {
            System.out.println("모든 마일리지 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
