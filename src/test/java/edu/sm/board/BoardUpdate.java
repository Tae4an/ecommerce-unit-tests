package edu.sm.board;

import edu.sm.dto.Board;
import edu.sm.dto.Customer;
import edu.sm.service.BoardService;
import edu.sm.service.CustomerService;

import java.util.Date;

public class BoardUpdate {
    public static void main(String[] args) {
        BoardService boardService = new BoardService();
        CustomerService customerService = new CustomerService();

        try {
            int boardId = 1;

            Board board = boardService.get(boardId);
            if (board != null) {
                Customer customer = customerService.get(board.getCustId());

                board.setTitle("생각이 바뀜");
                board.setContent("몇 번 써보니까 생각보다 별로네요");
                board.setRate(2);
                board.setRegDate(new Date());

                Board updatedBoard = boardService.modify(board);

                System.out.println("-----------------------------------------------------");
                if (customer != null) {
                    System.out.printf("회원: %s\n", customer.getName());
                } else {
                    System.out.println("작성자 정보를 찾을 수 없습니다.");
                }
                System.out.print("게시글 수정 성공!\n");
                System.out.printf("게시글 ID: %d\n", updatedBoard.getBoardId());
                System.out.printf("수정된 제목: %s\n", updatedBoard.getTitle());
                System.out.printf("수정된 내용: %s\n", updatedBoard.getContent());
                System.out.printf("수정된 평가: %d\n", updatedBoard.getRate());
                System.out.printf("수정 날짜: %s\n", updatedBoard.getRegDate());
                System.out.println("-----------------------------------------------------");
            } else {
                System.out.println("수정할 게시글을 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("게시글 정보 수정 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
