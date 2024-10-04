package edu.sm.service;

import edu.sm.dao.BoardDao;
import edu.sm.dto.Board;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BoardService implements MService<Integer, Board> {

    BoardDao dao;
    ConnectionPool cp;

    public BoardService() {
        dao = new BoardDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Board add(Board board) throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            board = dao.insert(board, con);
            System.out.println("New board created with ID: " + board.getBoardId());
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return board;
    }

    @Override
    public Board modify(Board board) throws Exception {
        Connection con = cp.getConnection();
        try {
            board = dao.update(board, con);
            System.out.println("Board updated: " + board.getBoardId());
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return board;
    }

    @Override
    public Boolean remove(Integer id) throws Exception {
        Connection con = cp.getConnection();
        Boolean result = false;
        try {
            result = dao.delete(id, con);
            System.out.println("Board removed: " + id);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public Board get(Integer id) throws Exception {
        Connection con = cp.getConnection();
        Board result = null;
        try {
            result = dao.select(id, con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public List<Board> get() throws Exception {
        Connection con = cp.getConnection();
        List<Board> result = null;
        try {
            result = dao.select(con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }
}