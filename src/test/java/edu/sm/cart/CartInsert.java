package edu.sm.cart;

import edu.sm.dto.Cart;
import edu.sm.dto.Customer;
import edu.sm.dto.Product;
import edu.sm.service.CartService;
import edu.sm.service.CustomerService;
import edu.sm.service.ProductService;

import java.util.Date;

public class CartInsert {
    public static void main(String[] args) {
        CartService cartService = new CartService();
        ProductService productService = new ProductService();
        CustomerService customerService = new CustomerService(); // 회원 정보를 가져오기 위한 서비스

        Cart cart = new Cart(null, 1, 1, 2, new Date());

        try {
            cartService.add(cart);

            Product product = productService.get(cart.getProductId());

            Customer customer = customerService.get(cart.getCustId());

            if (product != null && customer != null) {
                System.out.printf("회원 [%s]님의 장바구니에 [%s] 상품 (%d개) 추가 성공\n", customer.getName(), product.getName(), cart.getCount());
            } else {
                System.out.println("상품 또는 회원 정보를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("장바구니 항목 추가 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
