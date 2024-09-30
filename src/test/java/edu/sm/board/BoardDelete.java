package edu.sm.board;

import edu.sm.dto.Board;
import edu.sm.dto.Customer;
import edu.sm.service.BoardService;
import edu.sm.service.CustomerService;

public class BoardDelete {
    public static void main(String[] args) {
        BoardService boardService = new BoardService();
        CustomerService customerService = new CustomerService();

        int id = 1;
        try {
            Board board = boardService.get(id);
            if (board != null) {
                Customer customer = customerService.get(board.getCustId());

                boolean result = boardService.remove(id);
                if (result) {
                    System.out.println("-----------------------------------------------------");
                    if (customer != null) {
                        System.out.printf("회원: %s\n", customer.getName());
                    } else {
                        System.out.println("작성자 정보를 찾을 수 없습니다.");
                    }
                    System.out.printf("게시글 삭제 성공: 게시글 ID %d, 제목: %s\n", id, board.getTitle());
                    System.out.println("-----------------------------------------------------");
                } else {
                    System.out.println("게시글 삭제 실패: " + id);
                }
            } else {
                System.out.println("삭제할 게시글을 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("게시글 삭제 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
