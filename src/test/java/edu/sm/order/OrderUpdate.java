package edu.sm.order;

import edu.sm.dto.Order;
import edu.sm.service.OrderService;

public class OrderUpdate {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        try {
            Order order = orderService.get(1);
            if (order != null) {
                // 주문 정보 수정
                order.setProductCount(3);
                order.setPrice(30000);
                order.setPhone("010-9876-5432");

                Order updatedOrder = orderService.modify(order);
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("주문 정보 수정 성공:");
                // 수정된 주문 정보 출력
                System.out.printf("%-10s: %d\n", "주문 ID", updatedOrder.getOrderId());
                System.out.printf("%-10s: %d\n", "고객 ID", updatedOrder.getCustId());
                System.out.printf("%-10s: %d\n", "상품 개수", updatedOrder.getProductCount());
                System.out.printf("%-10s: %,d원\n", "수정 금액", updatedOrder.getPrice());
                System.out.printf("%-10s: %d개\n", "상품 수량", updatedOrder.getProductCount());
                System.out.printf("%-10s: %s\n", "전화번호", updatedOrder.getPhone());
                System.out.printf("%-10s: %s\n", "주문 날짜", updatedOrder.getOrderDate());
                System.out.printf("%-10s: %s, %s\n", "주소", updatedOrder.getAddress1(), updatedOrder.getAddress2());
                System.out.printf("%-10s: %s\n", "배송 요청", updatedOrder.getRequest());
                System.out.println("-------------------------------------------------------------------------------");
            } else {
                System.out.println("수정할 주문을 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("주문 정보 수정 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
