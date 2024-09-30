package edu.sm.wish;

import edu.sm.dto.Wish;
import edu.sm.service.CustomerService;
import edu.sm.service.ProductService;
import edu.sm.service.WishService;

public class WishSelectOne {
    public static void main(String[] args) {
        WishService wishService = new WishService();
        CustomerService customerService = new CustomerService();
        ProductService productService = new ProductService();
        try {
            int wishId = 1;
            Wish wish = wishService.get(wishId);
            System.out.println("조회된 찜 정보:");
            System.out.println("-------------------------------------------------");
            if (wish != null) {
                System.out.printf("회원: %s, 상품: %s, 찜한 날짜: %s%n",
                        customerService.get(wish.getCustId()).getName(),
                        productService.get(wish.getProductId()).getName(),
                        wish.getRegDate());
            } else {
                System.out.println("찜이 존재하지 않습니다.");
            }
        } catch (Exception e) {
            System.out.println("찜을 조회하는 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
