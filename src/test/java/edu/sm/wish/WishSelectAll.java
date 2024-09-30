package edu.sm.wish;

import edu.sm.dto.Wish;
import edu.sm.service.CustomerService;
import edu.sm.service.ProductService;
import edu.sm.service.WishService;

import java.util.List;

public class WishSelectAll {
    public static void main(String[] args) {
        WishService wishService = new WishService();
        CustomerService customerService = new CustomerService();
        ProductService productService = new ProductService();

        try {
            List<Wish> allWishes = wishService.get();
            System.out.println("모든 찜 목록:");
            System.out.println("-------------------------------------------------");
            for (Wish wish : allWishes) {
                System.out.printf("회원: %s, 상품: %s, 찜한 날짜: %s%n",
                        customerService.get(wish.getCustId()).getName(),
                        productService.get(wish.getProductId()).getName(),
                        wish.getRegDate());
            }
            System.out.println("-------------------------------------------------");
        } catch (Exception e) {
            System.out.println("모든 찜을 조회하는 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
