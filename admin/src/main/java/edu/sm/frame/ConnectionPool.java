package edu.sm.frame;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
@Getter
// 데이터베이스 연결 풀 관리 클래스
public class ConnectionPool {

    // 사용 가능한 연결 목록
    private List<Connection> connectionPool;
    // 현재 사용 중인 연결 목록
    private List<Connection> usedConnections = new ArrayList<>();
    // 초기 연결 풀 크기
    private static int INITIAL_POOL_SIZE = 3;
    // 리소스 번들 (데이터베이스 설정 정보)
    static ResourceBundle rb;

    // 정적 초기화 블록
    static {
        rb = null;
        // 한국 로케일 기반 mysql 리소스 번들 로드
        rb = ResourceBundle.getBundle("mysql", Locale.KOREA);
        try {
            // MySQL JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("OK");
            System.out.println(rb.getString("url"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 생성자
    public ConnectionPool(List<Connection> connectionPool) {
        this.connectionPool = connectionPool;
    }


    // ConnectionPool 인스턴스 생성 메소드
    public static ConnectionPool create() throws SQLException {
        // 데이터베이스 연결 정보
        String url = rb.getString("url");
        String user = rb.getString("user");
        String password = rb.getString("password");

        // 초기 연결 풀 생성
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
        }
        return new ConnectionPool(pool);
    }


    // 연결 획득 메소드
    public Connection getConnection() {
        Connection connection = connectionPool
                .remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    // 연결 반환 메소드
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    // 새로운 데이터베이스 연결 생성 메소드
    private static Connection createConnection(
            String url, String user, String password)
            throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}