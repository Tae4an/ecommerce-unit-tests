package edu.sm.order;

import edu.sm.dto.Order;
import edu.sm.service.OrderService;

import java.util.List;

public class OrderSelectAll {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        try {
            // 전체 주문 목록 조회
            List<Order> orders = orderService.get();
            System.out.println("전체 주문 목록:");

            // 테이블 헤더 출력
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5s %-10s %-10s %-10s %-13s %-10s %-15s %-35s %-20s\n",
                    "ID", "고객 ID", "상품 개수", "총 금액", "주문 날짜", "이름", "전화번호", "주소", "배송 요청");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");

            // 주문 목록 출력
            for (Order order : orders) {
                System.out.printf("%-5d %-10d %-15d %-10d %-15s %-10s %-15s %-30s %-20s\n",
                        order.getOrderId(),
                        order.getCustId(),
                        order.getProductCount(),
                        order.getPrice(),
                        order.getOrderDate().toString(),
                        order.getName(),
                        order.getPhone(),
                        order.getAddress1() + ", " + order.getAddress2(),
                        order.getRequest());
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("주문 목록 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
