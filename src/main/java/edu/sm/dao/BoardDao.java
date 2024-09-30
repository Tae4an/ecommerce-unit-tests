package edu.sm.dao;

import edu.sm.dto.Board;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDao implements Dao<Integer, Board> {

    @Override
    public Board insert(Board board, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(Sql.insertBoard, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, board.getCustId());
            pstmt.setInt(2, board.getProductId());
            pstmt.setString(3, board.getNtype());
            pstmt.setString(4, board.getTitle());
            pstmt.setDate(5, new java.sql.Date(board.getRegDate().getTime()));
            pstmt.setString(6, board.getContent());
            pstmt.setString(7, board.getImg());
            pstmt.setInt(8, board.getRate());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("게시글 생성 실패, 영향받은 행이 없습니다.");
            }

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                board.setBoardId(rs.getInt(1));
            } else {
                throw new SQLException("게시글 생성 실패, ID를 가져올 수 없습니다.");
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return board;
    }

    @Override
    public Board update(Board board, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.updateBoard);
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setString(3, board.getImg());
            pstmt.setInt(4, board.getRate());
            pstmt.setInt(5, board.getBoardId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("게시글 수정 실패, 해당 ID의 게시글이 없습니다.");
            }
        } finally {
            if (pstmt != null) pstmt.close();
        }
        return board;
    }

    @Override
    public boolean delete(Integer id, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(Sql.deleteBoard);
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } finally {
            if (pstmt != null) pstmt.close();
        }
    }

    @Override
    public Board select(Integer id, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Board board = null;
        try {
            pstmt = conn.prepareStatement(Sql.selectBoard);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                board = new Board(
                    rs.getInt("board_id"),
                    rs.getInt("cust_id"),
                    rs.getInt("product_id"),
                    rs.getString("ntype"),
                    rs.getString("title"),
                    rs.getDate("reg_date"),
                    rs.getString("content"),
                    rs.getString("img"),
                    rs.getInt("rate")
                );
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return board;
    }

    @Override
    public List<Board> select(Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Board> boards = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(Sql.selectAllBoards);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                boards.add(new Board(
                    rs.getInt("board_id"),
                    rs.getInt("cust_id"),
                    rs.getInt("product_id"),
                    rs.getString("ntype"),
                    rs.getString("title"),
                    rs.getDate("reg_date"),
                    rs.getString("content"),
                    rs.getString("img"),
                    rs.getInt("rate")
                ));
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return boards;
    }
}