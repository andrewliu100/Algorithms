package geekspearls.atlas.connectionpool;

import org.testng.annotations.Test;

public class ClientTest {

    @Test
    public void testSimpleConnection() {
        Client client = new Client(new SimpleConnection("conn1"));
        client.executeQuery("query");
    }

    @Test
    public void testPooledConnection() throws InterruptedException {
        Client client = new Client(new PooledConnection(new ConnectionPool()));

        Thread t1 = new Thread(() -> client.executeQuery("query1"));
        Thread t2 = new Thread(() -> client.executeQuery("query2"));
        Thread t3 = new Thread(() -> client.executeQuery("query3"));
        Thread t4 = new Thread(() -> client.executeQuery("query4"));
        Thread t5 = new Thread(() -> client.executeQuery("query5"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
    }
}
