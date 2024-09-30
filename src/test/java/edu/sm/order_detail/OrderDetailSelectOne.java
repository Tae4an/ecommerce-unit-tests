package edu.sm.order_detail;

import edu.sm.dto.OrderDetail;
import edu.sm.dto.Product;
import edu.sm.service.OrderDetailService;
import edu.sm.service.ProductService;

public class OrderDetailSelectOne {
    public static void main(String[] args) {
        OrderDetailService service = new OrderDetailService();
        ProductService productService = new ProductService(); // 상품 정보를 가져오기 위한 서비스

        try {
            int orderDetailId = 1;
            OrderDetail orderDetail = service.get(orderDetailId);
            if (orderDetail != null) {
                Product product = productService.get(orderDetail.getProductId());

                System.out.println("--------------------------------------------------");
                System.out.printf("주문 상세 ID: %d%n", orderDetail.getOrderDetailId());
                System.out.printf("상품 ID: %d%n", orderDetail.getProductId());
                if (product != null) {
                    System.out.printf("상품 이름: %s%n", product.getName());
                } else {
                    System.out.println("상품 정보를 찾을 수 없습니다.");
                }
                System.out.printf("상품 수량: %d%n", orderDetail.getCount());
                System.out.printf("총 금액: %d원%n", orderDetail.getPrice());
                System.out.println("--------------------------------------------------");
            } else {
                System.out.println("주문 상세를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("주문 상세 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
