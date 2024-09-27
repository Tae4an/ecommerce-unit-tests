package edu.sm.delivery;

import edu.sm.dto.Delivery;
import edu.sm.service.DeliveryService;
import java.util.Date;

public class DeliveryInsert {
    public static void main(String[] args) {
        DeliveryService deliveryService = new DeliveryService();
        
        // 새로운 배송 정보 생성
        Delivery delivery = new Delivery();
        delivery.setOrderId(1);
        delivery.setStatus("배송 준비중");
        delivery.setTrackingNumber("9876543210");
        delivery.setEstimatedDelivery(new Date(System.currentTimeMillis() + 86400000));  // 내일을 예상 배송 시간으로 설정
        
        try {
            Delivery addedDelivery = deliveryService.add(delivery);
            System.out.println("새로운 배송 정보 추가 성공: " + addedDelivery);
        } catch (Exception e) {
            System.out.println("배송 정보 추가 중 오류 발생:");
            e.printStackTrace();
        }
    }
}