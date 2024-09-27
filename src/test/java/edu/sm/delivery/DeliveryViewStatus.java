package edu.sm.delivery;

import edu.sm.dto.Delivery;
import edu.sm.service.DeliveryService;

public class DeliveryViewStatus {
    public static void main(String[] args) {
        DeliveryService deliveryService = new DeliveryService();
        int orderId = 1;

        try {
            Delivery delivery = deliveryService.viewShippingStatus(orderId);
            if (delivery != null) {
                System.out.println("조회된 배송 현황:");
                System.out.println("-------------------------------------------------------------------------------------------------------------");
                System.out.printf("%-5s %-10s %-10s %-15s %-20s  %-20s\n",
                        "ID", "주문 ID", "상태", "운송장 번호", "예상 배송일", "실제 배송일");
                System.out.println("-------------------------------------------------------------------------------------------------------------");
                System.out.printf("%-5d %-10d %-10s %-15s %-20s  %-20s\n",
                        delivery.getDeliveryId(),
                        delivery.getOrderId(),
                        delivery.getStatus(),
                        delivery.getTrackingNumber(),
                        delivery.getEstimatedDelivery() != null ? delivery.getEstimatedDelivery().toString() : "미정",
                        delivery.getActualDelivery() != null ? delivery.getActualDelivery().toString() : "미정");
                System.out.println("-------------------------------------------------------------------------------------------------------------");
            } else {
                System.out.println("해당 주문 ID의 배송 정보가 없습니다: " + orderId);
            }
        } catch (Exception e) {
            System.out.println("배송 현황 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
