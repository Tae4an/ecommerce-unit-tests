package edu.sm.order_detail;

import edu.sm.dto.OrderDetail;
import edu.sm.dto.Product;
import edu.sm.service.OrderDetailService;
import edu.sm.service.ProductService;

public class OrderDetailDelete {
    public static void main(String[] args) {
        OrderDetailService orderDetailService = new OrderDetailService();
        ProductService productService = new ProductService();

        try {
            int orderDetailId = 1;

            OrderDetail orderDetail = orderDetailService.get(orderDetailId);
            boolean isDeleted = orderDetailService.remove(orderDetailId);

            System.out.println("--------------------------------------------------");
            if (isDeleted) {
                if (orderDetail != null) {
                    Product product = productService.get(orderDetail.getProductId());
                    if (product != null) {
                        System.out.printf("주문 상세 삭제 성공!\n상품 ID: %d\n상품 이름: %s\n주문 개수: %d\n",
                                product.getProductId(), product.getName(), orderDetail.getCount());
                    } else {
                        System.out.println("삭제된 주문 상세의 상품 정보를 찾을 수 없습니다.");
                    }
                } else {
                    System.out.println("삭제할 주문 상세 정보를 찾을 수 없습니다.");
                }
            } else {
                System.out.println("주문 상세 삭제 실패: " + orderDetailId);
            }
            System.out.println("--------------------------------------------------");
        } catch (Exception e) {
            System.out.println("주문 상세 삭제 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
