package edu.sm.payment;

import edu.sm.dto.Payment;
import edu.sm.service.PaymentService;

import java.util.List;

public class PaymentSelectAll {
    public static void main(String[] args) {
        PaymentService service = new PaymentService();

        try {
            List<Payment> allPayments = service.get();
            System.out.println("--------------------------------------------------");
            System.out.println("전체 결제 목록:");
            for (Payment payment : allPayments) {
                System.out.printf("결제 ID: %d | 주문 ID: %d | 결제 금액: %d원 | 결제 방법: %s | 결제일: %s%n",
                        payment.getPaymentId(), payment.getOrderId(), payment.getPrice(),
                        payment.getMethod(), payment.getPayDate());
            }
            System.out.println("--------------------------------------------------");
        } catch (Exception e) {
            System.out.println("결제 목록 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
