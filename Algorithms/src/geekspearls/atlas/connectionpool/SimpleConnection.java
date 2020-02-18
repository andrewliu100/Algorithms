package geekspearls.atlas.connectionpool;

import java.util.Random;

public class SimpleConnection implements Connection {

    private String name;

    public SimpleConnection(String name) {
        this.name = name;
    }

    @Override
    public void open() {
        System.out.println("[" + Thread.currentThread().getName() + "] Open a simple connection: " + name);
    }

    @Override
    public void close() {
        System.out.println("[" + Thread.currentThread().getName() + "] Close a simple connection: " + name);
    }

    @Override
    public void executeQuery(String query) {
        try {
            int sleepTime = (new Random()).nextInt(3) * 1000;
            System.out.println("[" + Thread.currentThread().getName() + "] Sleeping for : " + sleepTime);
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            //
        }
        System.out.println("[" + Thread.currentThread().getName() + "] Execute query: " + query + " by simple connection: "  + name);
    }
}
