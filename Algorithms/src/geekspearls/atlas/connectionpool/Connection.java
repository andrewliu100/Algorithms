package geekspearls.atlas.connectionpool;

public interface Connection {

    public void open();
    public void close();
    public void executeQuery(String query);
}
