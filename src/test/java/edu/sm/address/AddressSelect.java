package edu.sm.address;

import edu.sm.dto.Address;
import edu.sm.service.AddressService;
import java.util.List;

public class AddressSelect {
    public static void main(String[] args) {
        AddressService addressService = new AddressService();
        try {
            List<Address> addresses = addressService.get();
            System.out.println("전체 배송지 목록:");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5s %-10s %-15s %-20s %-30s %-10s %-15s\n",
                    "ID", "이름", "전화번호", "주소", "상세 주소", "우편번호", "요청사항");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");

            for (Address address : addresses) {
                System.out.printf("%-5d %-10s %-15s %-20s %-30s %-10s %-15s\n",
                        address.getAddressId(),
                        address.getName(),
                        address.getPhone(),
                        address.getAddress(),
                        address.getAddressDetail(),
                        address.getZipCode(),
                        address.getRequest());
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");

        } catch (Exception e) {
            System.out.println("배송지 목록 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}