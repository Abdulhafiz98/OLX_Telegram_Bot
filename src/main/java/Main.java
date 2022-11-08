import model.User;
import service.UserService;

public class Main {
    static UserService userService = new UserService();
    public static void main(String[] args) {
        User user = new User("Abdulhafiz","Xalimjonov","994367470","hhhhhhh");
        User user1 = new User("Abdulhafiz1","Xalimjonov1","9943674701","hhhhhhh");
        User user2 = new User("sagfgfgafgf","ghgdgshhgsh","9943674701","agfgasgdfsgadsg");
        //userService.add(user);
        //userService.add(user1);
        userService.add(user2);

    }
}
