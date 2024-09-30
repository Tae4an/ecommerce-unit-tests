package edu.sm.wish;

import edu.sm.dto.Wish;
import edu.sm.service.CustomerService;
import edu.sm.service.ProductService;
import edu.sm.service.WishService;

public class WishDelete {
    public static void main(String[] args) {
        WishService service = new WishService();
        CustomerService customerService = new CustomerService();
        ProductService productService = new ProductService();
        try {
            int wishId = 2;
            Wish wish = service.get(wishId);
            Boolean isDeleted = service.remove(wishId);
            System.out.println("--------------------------------------------------");
            if (isDeleted) {
                System.out.printf("찜이 삭제 되었습니다! 회원: %s, 상품: %s%n",
                        customerService.get(wish.getCustId()).getName(),
                        productService.get(wish.getProductId()).getName());
            } else {
                System.out.printf("찜 ID %d 삭제에 실패하였습니다.%n", wishId);
            }
            System.out.println("--------------------------------------------------");
        } catch (Exception e) {
            System.out.println("찜 삭제 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
