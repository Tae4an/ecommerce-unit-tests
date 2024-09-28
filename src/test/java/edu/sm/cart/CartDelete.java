package edu.sm.cart;

import edu.sm.service.CartService;

public class CartDelete {
    public static void main(String[] args) {
        CartService cartService = new CartService();
        int id = 1;  // 삭제하려는 장바구니 항목의 ID
        try {
            boolean result = cartService.remove(id);
            if (result) {
                System.out.println("장바구니 항목 삭제 성공: " + id);
            } else {
                System.out.println("장바구니 항목 삭제 실패: " + id);
            }
        } catch (Exception e) {
            System.out.println("장바구니 항목 삭제 중 오류 발생:");
            e.printStackTrace();
        }
    }
}