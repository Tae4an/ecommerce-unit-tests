package edu.sm.cart;

import edu.sm.dto.Cart;
import edu.sm.dto.Customer;
import edu.sm.dto.Product;
import edu.sm.service.CartService;
import edu.sm.service.CustomerService;
import edu.sm.service.ProductService;

public class CartUpdate {
    public static void main(String[] args) {
        CartService cartService = new CartService();
        ProductService productService = new ProductService();
        CustomerService customerService = new CustomerService(); // 회원 정보를 가져오기 위한 서비스

        try {
            Cart cart = cartService.get(8);
            if (cart != null) {
                cart.setCount(1);

                Product product = productService.get(cart.getProductId());
                Customer customer = customerService.get(cart.getCustId());

                Cart updatedCart = cartService.modify(cart);

                if (product != null && customer != null) {
                    System.out.printf("회원 [%s]님의 장바구니 항목 수정 성공: [%s] 상품 (%d개)\n",
                            customer.getName(), product.getName(), updatedCart.getCount());
                } else {
                    System.out.println("수정한 상품 또는 회원 정보를 찾을 수 없습니다.");
                }
            } else {
                System.out.println("수정할 장바구니 항목을 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("장바구니 항목 수정 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
