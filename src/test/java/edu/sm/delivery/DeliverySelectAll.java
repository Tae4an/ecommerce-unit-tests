package edu.sm.delivery;

import edu.sm.dto.Delivery;
import edu.sm.service.DeliveryService;
import java.util.List;

public class DeliverySelectAll {
    public static void main(String[] args) {
        DeliveryService deliveryService = new DeliveryService();

        try {
            List<Delivery> deliveries = deliveryService.get();
            System.out.println("모든 배송 정보 조회:");
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5s %-10s %-15s %-15s %-20s %-20s\n",
                    "ID", "주문 ID", "상태", "운송장 번호", "예상 배송일", "실제 배송일");
            System.out.println("------------------------------------------------------------------------------------------------------");

            for (Delivery delivery : deliveries) {
                System.out.printf("%-5d %-10d %-15s %-15s %-20s %-20s\n",
                        delivery.getDeliveryId(),
                        delivery.getOrderId(),
                        delivery.getStatus(),
                        delivery.getTrackingNumber(),
                        delivery.getEstimatedDelivery() != null ? delivery.getEstimatedDelivery().toString() : "미정",
                        delivery.getActualDelivery() != null ? delivery.getActualDelivery().toString() : "미정");
            }
            System.out.println("------------------------------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("배송 정보 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
