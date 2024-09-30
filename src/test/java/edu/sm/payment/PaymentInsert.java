package edu.sm.payment;

import edu.sm.dto.Payment;
import edu.sm.service.PaymentService;

import java.util.Date;

public class PaymentInsert {
    public static void main(String[] args) {
        PaymentService service = new PaymentService();

        try {
            Payment newPayment = new Payment(null, 1, 50000, "Credit Card", new Date());
            Payment addedPayment = service.add(newPayment);
            System.out.println("--------------------------------------------------");
            System.out.println("새로운 결제가 추가되었습니다:");
            System.out.printf("결제 ID: %d%n", addedPayment.getPaymentId());
            System.out.printf("주문 ID: %d%n", addedPayment.getOrderId());
            System.out.printf("결제 금액: %d원%n", addedPayment.getPrice());
            System.out.printf("결제 방법: %s%n", addedPayment.getMethod());
            System.out.printf("결제일: %s%n", addedPayment.getPayDate());
            System.out.println("--------------------------------------------------");
        } catch (Exception e) {
            System.out.println("결제 추가 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
