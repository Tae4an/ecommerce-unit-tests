package edu.sm.address;

import edu.sm.service.AddressService;

import java.util.Scanner;

public class AddressDelete {
    public static void main(String[] args) {
        AddressService addressService = new AddressService();
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("삭제할 배송지의 아이디를 입력해주세요: ");
            int id = scanner.nextInt();
            System.out.println("삭제할 배송지 : " + addressService.get(id).getName() +"-"+ addressService.get(id).getAddress());
            boolean result = addressService.remove(id);
            if (result) {
                System.out.println("\n배송지 삭제 성공: " + id);
            } else {
                System.out.println("배송지 삭제 실패: " + id);
            }
        } catch (Exception e) {
            System.out.println("배송지 삭제 중 오류 발생:");
            e.printStackTrace();
        }
    }
}