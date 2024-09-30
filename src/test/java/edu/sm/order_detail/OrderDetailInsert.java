package edu.sm.order_detail;

import edu.sm.dto.OrderDetail;
import edu.sm.dto.Product;
import edu.sm.service.OrderDetailService;
import edu.sm.service.ProductService;

public class OrderDetailInsert {
    public static void main(String[] args) {
        OrderDetailService orderDetailService = new OrderDetailService();
        ProductService productService = new ProductService();

        try {
            OrderDetail newOrderDetail = new OrderDetail(null, 1, 1, 10000, 2);
            OrderDetail addedOrderDetail = orderDetailService.add(newOrderDetail);

            Product product = productService.get(addedOrderDetail.getProductId());

            System.out.println("--------------------------------------------------");
            System.out.println("주문 상세 추가 성공");
            if (product != null) {
                System.out.printf("상품 ID: %d%n", product.getProductId());
                System.out.printf("상품 이름: %s%n", product.getName());
            } else {
                System.out.println("상품 정보를 찾을 수 없습니다.");
            }
            System.out.printf("주문 개수: %d%n", addedOrderDetail.getCount());
            System.out.printf("총 금액: %d원%n", addedOrderDetail.getPrice());
            System.out.println("--------------------------------------------------");
        } catch (Exception e) {
            System.out.println("주문 상세 추가 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
