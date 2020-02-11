package geekspearls.atlas;

public class DeleteUserTask implements Runnable {

    private Group group;
    private User user;

    public DeleteUserTask(Group group, User user) {
        this.group = group;
        this.user = user;
    }

    @Override
    public void run() {
        System.out.println("Deleting user to group from thread " + Thread.currentThread().getName());
        group.deleteUser(user);
    }
}
