package edu.sm.board;

import edu.sm.dto.Board;
import edu.sm.dto.Customer;
import edu.sm.service.BoardService;
import edu.sm.service.CustomerService;

import java.util.Date;

public class BoardInsert {
    public static void main(String[] args) {
        BoardService boardService = new BoardService();
        CustomerService customerService = new CustomerService();

        // 게시글 생성
        Board board = new Board(null, 1, 1, "Q", "좋은 상품이네요", new Date(),
                "가성비 굳", "image.jpg", 5);

        try {
            Board addedBoard = boardService.add(board);

            Customer customer = customerService.get(board.getCustId());

            if (customer != null) {
                System.out.println("-----------------------------------------------------");
                System.out.printf("회원: %s\n", customer.getName());
                System.out.print("게시글 등록 성공!\n");
                System.out.printf("게시글 ID: %d\n", addedBoard.getBoardId());
                System.out.printf("제목: %s\n", addedBoard.getTitle());
                System.out.printf("내용: %s\n", addedBoard.getContent());
                System.out.printf("등록 날짜: %s\n", addedBoard.getRegDate());
                System.out.println("-----------------------------------------------------");
            } else {
                System.out.println("게시글 등록 후 사용자 정보를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("게시글 등록 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
