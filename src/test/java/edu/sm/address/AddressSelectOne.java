package edu.sm.address;

import edu.sm.dto.Address;
import edu.sm.service.AddressService;

public class AddressSelectOne {
    public static void main(String[] args) {
        AddressService addressService = new AddressService();
        int id = 1;
        try {
            Address address = addressService.get(id);
            if (address != null) {
                System.out.println("조회된 배송지 정보:");
                System.out.println("---------------------------------------------------------------------------------------------------------------");
                System.out.printf("%-5s %-10s %-15s %-20s %-20s %-10s %-15s\n",
                        "ID", "이름", "전화번호", "주소", "상세 주소", "우편번호", "요청사항");
                System.out.println("---------------------------------------------------------------------------------------------------------------");
                System.out.printf("%-5s %-10s %-15s %-20s %-20s %-10s %-15s\n",
                        address.getAddressId(),
                        address.getName(),
                        address.getPhone(),
                        address.getAddress(),
                        address.getAddressDetail(),
                        address.getZipCode(),
                        address.getRequest());
                System.out.println("---------------------------------------------------------------------------------------------------------------");
            } else {
                System.out.println("해당 ID의 배송지가 없습니다: " + id);
            }

        } catch (Exception e) {
            System.out.println("배송지 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}