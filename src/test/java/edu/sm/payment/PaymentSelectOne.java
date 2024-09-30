package edu.sm.payment;

import edu.sm.dto.Payment;
import edu.sm.service.PaymentService;

public class PaymentSelectOne {
    public static void main(String[] args) {
        PaymentService service = new PaymentService();

        try {
            int paymentId = 1;
            Payment payment = service.get(paymentId);
            if (payment != null) {
                System.out.println("--------------------------------------------------");
                System.out.println("조회된 결제 정보:");
                System.out.printf("결제 ID: %d%n", payment.getPaymentId());
                System.out.printf("주문 ID: %d%n", payment.getOrderId());
                System.out.printf("결제 금액: %d원%n", payment.getPrice());
                System.out.printf("결제 방법: %s%n", payment.getMethod());
                System.out.printf("결제일: %s%n", payment.getPayDate());
                System.out.println("--------------------------------------------------");
            } else {
                System.out.println("해당 ID의 결제 정보를 찾을 수 없습니다: " + paymentId);
            }
        } catch (Exception e) {
            System.out.println("결제 정보 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
