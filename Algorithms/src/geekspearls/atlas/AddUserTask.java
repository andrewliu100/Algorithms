package geekspearls.atlas;

public class AddUserTask implements Runnable {

    private Group group;
    private User userToAdd;

    public AddUserTask(Group group, User userToAdd) {
        this.userToAdd = userToAdd;
        this.group = group;
    }

    @Override
    public void run() {
        try {
            System.out.println("Adding user to group from thread " + Thread.currentThread().getName());
            Thread.sleep(1000);
            group.addUser(userToAdd);
        } catch (InterruptedException ex) {
            System.out.println("I'm interrupted");
        }
    }
}
