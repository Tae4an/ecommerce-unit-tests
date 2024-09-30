package edu.sm.order_detail;

import edu.sm.dto.OrderDetail;
import edu.sm.dto.Product;
import edu.sm.service.OrderDetailService;
import edu.sm.service.ProductService;

public class OrderDetailUpdate {
    public static void main(String[] args) {
        OrderDetailService orderDetailService = new OrderDetailService();
        ProductService productService = new ProductService();

        try {
            OrderDetail orderDetail = orderDetailService.get(2);
            if (orderDetail != null) {
                Product product = productService.get(orderDetail.getProductId());

                orderDetail.setCount(3);
                OrderDetail updatedOrderDetail = orderDetailService.modify(orderDetail);

                System.out.println("--------------------------------------------------");
                if (product != null) {
                    System.out.printf("주문 상세 수정 성공!\n상품 ID: %d\n상품 이름: %s\n수정된 주문 개수: %d\n",
                            product.getProductId(), product.getName(), updatedOrderDetail.getCount());
                } else {
                    System.out.println("수정된 주문 상세의 상품 정보를 찾을 수 없습니다.");
                }
                System.out.println("--------------------------------------------------");
            } else {
                System.out.println("수정할 주문 상세 정보를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("주문 상세 수정 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
