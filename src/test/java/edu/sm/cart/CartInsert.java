package edu.sm.cart;

import edu.sm.dto.Cart;
import edu.sm.service.CartService;

import java.util.Date;

public class CartInsert {
    public static void main(String[] args) {
        CartService cartService = new CartService();
        Cart cart = new Cart(null, 1, 1, 2, new Date());
        
        try {
            Cart addedCart = cartService.add(cart);
            System.out.println("장바구니 항목 추가 성공: " + addedCart);
        } catch (Exception e) {
            System.out.println("장바구니 항목 추가 중 오류 발생:");
            e.printStackTrace();
        }
    }
}