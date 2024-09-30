package edu.sm.mileage;

import edu.sm.dto.Mileage;
import edu.sm.service.CustomerService;
import edu.sm.service.MileageService;

public class MileageAddPoints {
    public static void main(String[] args) {
        MileageService service = new MileageService();
        CustomerService customerService = new CustomerService();

        try {
            int custId = 1;
            int pointsToAdd = 500;

            System.out.println("--------------------------------------------------");
            System.out.printf("마일리지 추가 전 (%s의 정보):%n", customerService.get(custId).getName());
            Mileage beforeMileage = service.get(custId);
            if (beforeMileage != null) {
                System.out.printf("회원: %s%n", customerService.get(beforeMileage.getCustId()).getName());
                System.out.printf("현재 잔액: %d%n", beforeMileage.getBalance());
            } else {
                System.out.println("해당 고객의 마일리지 정보를 찾을 수 없습니다.");
            }
            System.out.println("--------------------------------------------------");

            Mileage updatedMileage = service.addMileagePoints(custId, pointsToAdd);

            System.out.printf("마일리지 추가 후 (%s의 정보):%n", customerService.get(custId).getName());
            System.out.printf("회원: %s%n",customerService.get(updatedMileage.getCustId()).getName());
            System.out.printf("추가된 포인트: %d%n", pointsToAdd);
            System.out.printf("새 잔액: %d%n", updatedMileage.getBalance());
            System.out.println("--------------------------------------------------");

        } catch (Exception e) {
            System.out.println("마일리지 추가 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
