package geekspearls.atlas.connectionpool;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static int DEFAULT_POOL_SIZE = 3;

    private List<Connection> pool;
    private List<Connection> usedConnections = new ArrayList<>();

    public ConnectionPool() {
        pool = new ArrayList<>(DEFAULT_POOL_SIZE);
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            pool.add(new SimpleConnection("conn" + i));
        }
    }

    public synchronized Connection getConnection() {
        while (pool.size() == 0) {
            try {
                System.out.println("[" + Thread.currentThread().getName() + "] Connection pool is empty, waiting...");
                wait();
            } catch (InterruptedException ex) {
                return null;
            }
        }
        System.out.println("[" + Thread.currentThread().getName() + "] Getting connection from connection pool");
        Connection conn = pool.remove(pool.size() - 1);
        usedConnections.add(conn);
        return conn;
    }

    public synchronized void releaseConnection(Connection connection) {
        usedConnections.remove(connection);
        pool.add(connection);
        notifyAll();
    }
}
