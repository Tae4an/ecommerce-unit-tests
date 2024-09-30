package edu.sm.wish;

import edu.sm.dto.Wish;
import edu.sm.service.CustomerService;
import edu.sm.service.ProductService;
import edu.sm.service.WishService;

import java.util.Date;

public class WishUpdate {
    public static void main(String[] args) {
        WishService service = new WishService();
        CustomerService customerService = new CustomerService();
        ProductService productService = new ProductService();
        try {
            Wish wish = service.get(1);
            if (wish != null) {
                wish.setProductId(2); // 상품 ID 1을 상품 ID2로 수정
                wish.setRegDate(new Date());
                Wish updatedWish = service.modify(wish);
                System.out.printf("찜이 수정되었습니다! 회원: %s, 상품: %s, 찜한 날짜: %s%n",
                        customerService.get(updatedWish.getCustId()).getName(),
                        productService.get(updatedWish.getProductId()).getName(),
                        updatedWish.getRegDate());
            } else {
                System.out.println("찜이 존재하지 않습니다.");
            }
        } catch (Exception e) {
            System.out.println("찜 수정 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
