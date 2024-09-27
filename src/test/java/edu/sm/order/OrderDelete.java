package edu.sm.order;

import edu.sm.service.OrderService;

public class OrderDelete {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        int id = 6; // 삭제할 주문의 ID
        try {
            boolean result = orderService.remove(id);
            System.out.println("-------------------------------------------------------------------------------");
            if (result) {
                System.out.println("주문 삭제 성공: 주문 ID " + id + "이(가) 성공적으로 삭제되었습니다.");
            } else {
                System.out.println("주문 삭제 실패: 주문 ID " + id + "이(가) 존재하지 않거나 이미 삭제되었습니다.");
            }
            System.out.println("-------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("주문 삭제 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
