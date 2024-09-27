package edu.sm.order;

import edu.sm.dto.Order;
import edu.sm.service.OrderService;

public class OrderSelectOne {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        int id = 1;  // 조회하려는 주문의 ID
        try {
            Order order = orderService.get(id);
            System.out.println("-------------------------------------------------------------------------------");
            if (order != null) {
                // 주문 정보 출력
                System.out.println("조회된 주문 정보:");
                System.out.printf("%-10s: %d\n", "주문 ID", order.getOrderId());
                System.out.printf("%-10s: %d\n", "고객 ID", order.getCustId());
                System.out.printf("%-10s: %d\n", "상품 개수", order.getProductCount());
                System.out.printf("%-10s: %,d원\n", "총 금액", order.getPrice());
                System.out.printf("%-10s: %s\n", "주문 날짜", order.getOrderDate());
                System.out.printf("%-10s: %s\n", "이름", order.getName());
                System.out.printf("%-10s: %s\n", "전화번호", order.getPhone());
                System.out.printf("%-10s: %s, %s\n", "주소", order.getAddress1(), order.getAddress2());
                System.out.printf("%-10s: %s\n", "배송 요청", order.getRequest());
            } else {
                System.out.println("해당 ID의 주문이 없습니다: " + id);
            }
            System.out.println("-------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("주문 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
