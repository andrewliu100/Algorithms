package geekspearls.atlas.connectionpool;

public class Client {

    private Connection connection;

    public Client(Connection connection) {
        this.connection = connection;
    }

    public void executeQuery(String query) {
        try {
            this.connection.open();
            this.connection.executeQuery(query);
        } finally {
            this.connection.close();
        }
    }
}
