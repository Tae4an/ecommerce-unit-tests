package edu.sm.payment;

import edu.sm.dto.Payment;
import edu.sm.service.PaymentService;

public class PaymentUpdate {
    public static void main(String[] args) {
        PaymentService service = new PaymentService();

        try {
            Payment payment = service.get(1);
            if (payment != null) {
                payment.setMethod("계좌 이체");
                Payment updatedPayment = service.modify(payment);
                System.out.println("--------------------------------------------------");
                System.out.println("결제가 수정되었습니다:");
                System.out.printf("결제 ID: %d%n", updatedPayment.getPaymentId());
                System.out.printf("주문 ID: %d%n", updatedPayment.getOrderId());
                System.out.printf("결제 금액: %d원%n", updatedPayment.getPrice());
                System.out.printf("수정된 결제 방법: %s%n", updatedPayment.getMethod());
                System.out.printf("결제일: %s%n", updatedPayment.getPayDate());
                System.out.println("--------------------------------------------------");
            } else {
                System.out.println("해당 ID의 결제를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("결제 수정 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
