package edu.sm.cart;

import edu.sm.dto.Cart;
import edu.sm.service.CartService;

import java.util.List;

public class CartSelect {
    public static void main(String[] args) {
        CartService cartService = new CartService();
        try {
            List<Cart> carts = cartService.get();
            System.out.println("전체 장바구니 항목 목록:");
            for (Cart cart : carts) {
                System.out.println(cart);
            }
        } catch (Exception e) {
            System.out.println("장바구니 항목 목록 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}