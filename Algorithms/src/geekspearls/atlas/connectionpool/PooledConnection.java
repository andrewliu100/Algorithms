package geekspearls.atlas.connectionpool;

public class PooledConnection implements Connection {

    private ConnectionPool connectionPool;
    private ThreadLocal<Connection> currentConnection = new ThreadLocal<>();

    public PooledConnection(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void open() {
        this.currentConnection.set(this.connectionPool.getConnection());
        currentConnection.get().open();
    }

    @Override
    public void close() {
        currentConnection.get().close();
        this.connectionPool.releaseConnection(currentConnection.get());
    }

    @Override
    public void executeQuery(String query) {
        currentConnection.get().executeQuery(query);
    }
}
