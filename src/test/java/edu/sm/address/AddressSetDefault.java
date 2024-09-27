package edu.sm.address;

import edu.sm.service.AddressService;

public class AddressSetDefault {
    public static void main(String[] args) {
        AddressService addressService = new AddressService();
        int addressKey = 1;
        int custId = 1;
        try {
            boolean result = addressService.setDefaultAddress(addressKey, custId);
            if (result) {
                System.out.println("기본 배송지 설정 성공: " + addressService.get(custId).getName() + " - "+ addressService.get(addressKey).getAddress());
            } else {
                System.out.println("기본 배송지 설정 실패: " + addressKey);
            }
        } catch (Exception e) {
            System.out.println("기본 배송지 설정 중 오류 발생:");
            e.printStackTrace();
        }
    }
}