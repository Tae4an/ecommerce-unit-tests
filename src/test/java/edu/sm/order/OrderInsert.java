package edu.sm.order;

import edu.sm.dto.Order;
import edu.sm.service.OrderService;

import java.util.Date;

public class OrderInsert {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();

        // 새로운 주문 정보 생성
        Order order = new Order(
                null, // 주문 ID는 자동 생성되므로 null
                1, // 고객 ID
                2, // 상품 ID
                20000, // 총 금액
                new Date(), // 주문 날짜 (현재 시간)
                "최태산", // 고객 이름
                "010-1234-5678", // 전화번호
                "서울시 강남구", // 주소1
                "아파트 101동 1004호", // 주소2
                "12345", // 우편번호
                "문 앞에 놓아주세요", // 배송 요청 사항
                "1234-5678-9012-3456", // 카드 번호
                1000 // 배송비
        );

        try {
            // 주문 추가
            Order addedOrder = orderService.add(order);

            // 주문 성공 출력
            System.out.println("주문 등록 성공:");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5s %-10s %-10s %-15s %-30s %-10s %-15s %-30s %-20s\n",
                    "ID", "고객 ID", "상품 개수", "금액", "주문 날짜", "이름", "전화번호", "주소", "배송 요청");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5d %-12d %-10d %-10d %-40s %-10s %-15s %-25s %-25s\n",
                    addedOrder.getOrderId(),
                    addedOrder.getCustId(),
                    addedOrder.getProductCount(),
                    addedOrder.getPrice(),
                    addedOrder.getOrderDate().toString(),
                    addedOrder.getName(),
                    addedOrder.getPhone(),
                    addedOrder.getAddress1() + ", " + addedOrder.getAddress2(),
                    addedOrder.getRequest());
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("주문 등록 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
