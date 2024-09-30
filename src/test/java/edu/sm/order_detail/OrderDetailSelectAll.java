package edu.sm.order_detail;

import edu.sm.dto.OrderDetail;
import edu.sm.dto.Product;
import edu.sm.service.OrderDetailService;
import edu.sm.service.ProductService;

import java.util.List;

public class OrderDetailSelectAll {
    public static void main(String[] args) {
        OrderDetailService orderDetailService = new OrderDetailService();
        ProductService productService = new ProductService();

        try {
            List<OrderDetail> allOrderDetails = orderDetailService.get();
            System.out.println("--------------------------------------------------");
            System.out.println("모든 주문 상세 정보:");

            for (OrderDetail orderDetail : allOrderDetails) {
                // 각 주문 상세에 대한 상품 정보 조회
                Product product = productService.get(orderDetail.getProductId());

                // 출력
                System.out.println("--------------------------------------------------");
                System.out.printf("주문 상세 ID: %d%n", orderDetail.getOrderDetailId());
                System.out.printf("상품 ID: %d%n", orderDetail.getProductId());

                if (product != null) {
                    System.out.printf("상품 이름: %s%n", product.getName());
                } else {
                    System.out.println("상품 정보를 찾을 수 없습니다.");
                }

                System.out.printf("주문 개수: %d%n", orderDetail.getCount());
                System.out.printf("총 금액: %d원%n", orderDetail.getPrice());
            }
            System.out.println("--------------------------------------------------");
        } catch (Exception e) {
            System.out.println("모든 주문 상세 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
