package edu.sm.board;

import edu.sm.dto.Board;
import edu.sm.dto.Customer;
import edu.sm.service.BoardService;
import edu.sm.service.CustomerService;

import java.util.List;

public class BoardSelect {
    public static void main(String[] args) {
        BoardService boardService = new BoardService();
        CustomerService customerService = new CustomerService();

        try {
            List<Board> boards = boardService.get();
            System.out.println("전체 게시글 목록:");
            System.out.println("-----------------------------------------------------");
            for (Board b : boards) {
                Customer customer = customerService.get(b.getCustId());
                System.out.printf("게시글 ID: %d\n", b.getBoardId());
                if (customer != null) {
                    System.out.printf("작성자: %s\n", customer.getName());
                } else {
                    System.out.println("작성자 정보를 찾을 수 없습니다.");
                }
                System.out.printf("제목: %s\n", b.getTitle());
                System.out.printf("등록 날짜: %s\n", b.getRegDate());
                System.out.println("-----------------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("게시글 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
