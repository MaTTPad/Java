import java.io.IOException;

public class MainServer {
    public static void main(String []args)
    {
        CustomServer server=new CustomServer();
        try {
            server.run(8983);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            Thread.sleep(30000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        server.stop();


    }
}
