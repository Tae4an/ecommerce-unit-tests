package edu.sm.address;

import edu.sm.dto.Address;
import edu.sm.service.AddressService;

public class AddressInsert {
    public static void main(String[] args) {
        AddressService addressService = new AddressService();
        Address address = new Address(null, 1, "최태산", "010-1234-5678", "서울시 강남구",
                                      "테헤란로 123", "06234", "부재시 경비실에 맡겨주세요", "N");
        try {
            Address addedAddress = addressService.add(address);
            System.out.println("배송지 등록 성공: " + addedAddress);
        } catch (Exception e) {
            System.out.println("배송지 등록 중 오류 발생:");
            e.printStackTrace();
        }
    }
}