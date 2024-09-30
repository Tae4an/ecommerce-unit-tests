package edu.sm.wish;

import edu.sm.dto.Wish;
import edu.sm.service.CustomerService;
import edu.sm.service.ProductService;
import edu.sm.service.WishService;

import java.util.Date;

public class WishInsert {
    public static void main(String[] args) {
        WishService service = new WishService();
        CustomerService customerService = new CustomerService();
        ProductService productService = new ProductService();
        try {
            Wish newWish = new Wish(null, 1, 1, new Date());
            Wish addedWish = service.add(newWish);
            System.out.printf("찜이 추가되었습니다! 회원: %s, 상품: %s, 찜한 날짜: %s%n",
                    customerService.get(addedWish.getCustId()).getName(),
                    productService.get(addedWish.getProductId()).getName(),
                    addedWish.getRegDate());
        } catch (Exception e) {
            System.out.println("찜 추가 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
