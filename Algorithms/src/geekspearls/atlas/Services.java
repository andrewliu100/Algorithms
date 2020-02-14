package geekspearls.atlas;

public class Services {

    public static UserService createUserService() {
        return new UserService();
    }

    public static GroupService createGroupService() {
        return new GroupService();
    }

    public static MembershipService createMembershipService() {
        return new MembershipService();
    }
}
