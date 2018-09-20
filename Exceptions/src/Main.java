public class Main {
    public static void main(String []args)
    {
        User user1=new User("user1");
        User user2=new User("user2");
        User user3=new User("user3");


        ChatEngine mojchat=new ChatEngine();
        try {
            mojchat.loginUser(user1);
            mojchat.loginUser(user3);
            mojchat.loginUser(user2);

        } catch (UserLoginException e) {
            e.printStackTrace();
        }
        mojchat.broadcastMessage(0,"witam");

        System.out.println("Liczba userów to: " +mojchat.getNumberOfUsers());
        try {
            mojchat.logoutUser(1);
        } catch (UserRemoveException e) {
            e.printStackTrace();
        }
        System.out.println("Liczba userów po wylogowaniu to: " +mojchat.getNumberOfUsers());

        mojchat.addUserStar("user1");
        mojchat.showStars(0);


        System.out.println(user1.numberOfStars);
        System.out.println("Zawiera usera1? ");
        System.out.println(mojchat.hasUser("user1"));


        mojchat.printStatistics();
    }


}
