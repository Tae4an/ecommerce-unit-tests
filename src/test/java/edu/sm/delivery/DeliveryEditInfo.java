package edu.sm.delivery;

import edu.sm.dto.Delivery;
import edu.sm.service.DeliveryService;
import java.util.Date;
import java.util.Calendar;

public class DeliveryEditInfo {
    public static void main(String[] args) {
        DeliveryService deliveryService = new DeliveryService();

        // 수정할 배송 정보
        Delivery delivery = new Delivery();
        delivery.setDeliveryId(1);
        delivery.setOrderId(1);
        delivery.setStatus("배송완료");
        delivery.setTrackingNumber("1234567890");
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.SEPTEMBER, 25);
        delivery.setEstimatedDelivery(cal.getTime());
        delivery.setActualDelivery(new Date());

        try {
            Delivery updatedDelivery = deliveryService.editShippingInfo(delivery);

            // 예쁘게 출력
            System.out.println("배송 정보 수정 성공:");
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5s %-10s %-20s %-15s %-20s %-20s\n",
                    "ID", "주문 ID", "상태", "운송장 번호", "예상 배송일", "실제 배송일");
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5d %-10d %-20s %-15s %-20s %-20s\n",
                    updatedDelivery.getDeliveryId(),
                    updatedDelivery.getOrderId(),
                    updatedDelivery.getStatus(),
                    updatedDelivery.getTrackingNumber(),
                    updatedDelivery.getEstimatedDelivery() != null ? updatedDelivery.getEstimatedDelivery().toString() : "미정",
                    updatedDelivery.getActualDelivery().toString());
            System.out.println("------------------------------------------------------------------------------------------------------------------");

        } catch (Exception e) {
            System.out.println("배송 정보 수정 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
