package edu.sm.cart;

import edu.sm.dto.Cart;
import edu.sm.dto.Customer;
import edu.sm.dto.Product;
import edu.sm.service.CartService;
import edu.sm.service.CustomerService;
import edu.sm.service.ProductService;

public class CartDelete {
    public static void main(String[] args) {
        CartService cartService = new CartService();
        ProductService productService = new ProductService();
        CustomerService customerService = new CustomerService();

        int cartId = 2;

        try {
            Cart cart = cartService.get(cartId);

            if (cart != null) {
                Product product = productService.get(cart.getProductId());
                Customer customer = customerService.get(cart.getCustId());
                boolean result = cartService.remove(cartId);

                if (result) {
                    if (product != null && customer != null) {
                        System.out.printf("회원 [%s]님의 장바구니에서 [%s] 상품 (%d개) 삭제 성공\n",
                                customer.getName(), product.getName(), cart.getCount());
                    } else {
                        System.out.println("상품 또는 회원 정보를 찾을 수 없습니다.");
                    }
                } else {
                    System.out.printf("회원 [%s]님의 장바구니 항목 삭제 실패: %d\n", customer.getName(), cartId);
                }
            } else {
                System.out.println("삭제할 장바구니 항목을 찾을 수 없습니다: " + cartId);
            }
        } catch (Exception e) {
            System.out.println("장바구니 항목 삭제 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
