package edu.sm.payment;

import edu.sm.service.PaymentService;

public class PaymentDelete {
    public static void main(String[] args) {
        PaymentService service = new PaymentService();

        try {
            int paymentId = 1;
            Boolean isDeleted = service.remove(paymentId);
            if (isDeleted) {
                System.out.printf("결제 ID %d가 성공적으로 삭제되었습니다.%n", paymentId);
            } else {
                System.out.printf("결제 ID %d를 삭제하는 데 실패했습니다.%n", paymentId);
            }
        } catch (Exception e) {
            System.out.println("결제 삭제 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
