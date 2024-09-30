package edu.sm.cart;

import edu.sm.dto.Cart;
import edu.sm.dto.Customer;
import edu.sm.dto.Product;
import edu.sm.service.CartService;
import edu.sm.service.CustomerService;
import edu.sm.service.ProductService;

import java.util.List;

public class CartSelect {
    public static void main(String[] args) {
        CartService cartService = new CartService();
        ProductService productService = new ProductService();
        CustomerService customerService = new CustomerService(); // 고객 정보를 조회하기 위한 서비스

        try {
            List<Cart> carts = cartService.get();
            System.out.println("전체 장바구니 항목 목록:");
            System.out.println("---------------------------------------------------------------------------------------------------------------");
            for (Cart cart : carts) {
                Product product = productService.get(cart.getProductId());
                Customer customer = customerService.get(cart.getCustId());
                if (product != null && customer != null) {
                    System.out.printf("회원명: [%s], 상품명: [%s], 수량: %d개\n", customer.getName(), product.getName(), cart.getCount());
                } else if (customer == null) {
                    System.out.printf("회원 ID: [%d] - 회원 정보를 찾을 수 없습니다.\n", cart.getCustId());
                } else {
                    System.out.printf("상품 ID: [%d] - 상품 정보를 찾을 수 없습니다.\n", cart.getProductId());
                }
            }
            System.out.println("---------------------------------------------------------------------------------------------------------------");

        } catch (Exception e) {
            System.out.println("장바구니 항목 목록 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
