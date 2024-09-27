package edu.sm.address;

import edu.sm.dto.Address;
import edu.sm.service.AddressService;

public class AddressUpdate {
    public static void main(String[] args) {
        AddressService addressService = new AddressService();
        Address address = new Address(1, 1, "김철수", "010-9876-5432", "서울시 서초구",
                "서초대로 456", "06543", "문 앞에 놓아주세요", "N");
        try {
            Address updatedAddress = addressService.modify(address);

            System.out.println("배송지 정보 수정 성공:");
            System.out.println("-------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5s %-10s %-15s %-20s %-20s %-10s %-15s\n",
                    "ID", "이름", "전화번호", "주소", "상세 주소", "우편번호", "요청사항");
            System.out.println("------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5s %-10s %-15s %-20s %-20s %-10s %-15s\n",
                    updatedAddress.getAddressId(),
                    updatedAddress.getName(),
                    updatedAddress.getPhone(),
                    updatedAddress.getAddress(),
                    updatedAddress.getAddressDetail(),
                    updatedAddress.getZipCode(),
                    updatedAddress.getRequest());
            System.out.println("-------------------------------------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("배송지 정보 수정 중 오류 발생:");
            e.printStackTrace();
        }
    }

}