package edu.sm.cart;

import edu.sm.dto.Cart;
import edu.sm.service.CartService;

public class CartUpdate {
    public static void main(String[] args) {
        CartService cartService = new CartService();
        try {
            Cart cart = cartService.get(1);  // 1은 수정하려는 장바구니 항목의 ID입니다.
            if (cart != null) {
                cart.setCount(3);  // 수량을 3으로 변경
                Cart updatedCart = cartService.modify(cart);
                System.out.println("장바구니 항목 수정 성공: " + updatedCart);
            } else {
                System.out.println("수정할 장바구니 항목을 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("장바구니 항목 수정 중 오류 발생:");
            e.printStackTrace();
        }
    }
}