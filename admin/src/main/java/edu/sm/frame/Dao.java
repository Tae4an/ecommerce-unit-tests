package edu.sm.frame;

import java.sql.Connection;
import java.util.List;

public interface Dao<K, V> {

    V insert(V v, Connection conn) throws  Exception;
    V update(V v, Connection conn) throws Exception;
    boolean delete(K k, Connection conn) throws Exception;
    V select(K k, Connection conn) throws Exception;
    List<V> select(Connection conn) throws Exception;
}