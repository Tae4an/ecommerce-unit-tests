package edu.sm.board;

import edu.sm.dto.Board;
import edu.sm.service.BoardService;
import edu.sm.service.CustomerService;

public class BoardSelectOne {
    public static void main(String[] args) {
        BoardService boardService = new BoardService();
        CustomerService customerService = new CustomerService();
        int id = 12;

        try {
            Board board = boardService.get(id);
            if (board != null) {
                System.out.println("조회된 게시글 정보:");
                System.out.println("--------------------------------------------------");
                System.out.printf("게시글 ID: %d%n", board.getBoardId());
                System.out.printf("작성자 : %s%n", customerService.get(board.getCustId()).getName());
                System.out.printf("상품 ID: %d%n", board.getProductId());
                System.out.printf("게시글 유형: %s%n", board.getNtype());
                System.out.printf("제목: %s%n", board.getTitle());
                System.out.printf("작성일: %s%n", board.getRegDate());
                System.out.printf("내용: %s%n", board.getContent());
                System.out.printf("이미지: %s%n", board.getImg());
                System.out.printf("평점: %d%n", board.getRate());
                System.out.println("--------------------------------------------------");
            } else {
                System.out.println("해당 ID의 게시글이 없습니다: " + id);
            }
        } catch (Exception e) {
            System.out.println("게시글 조회 중 오류 발생:");
            e.printStackTrace();
        }
    }
}
