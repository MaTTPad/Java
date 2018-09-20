import java.io.IOException;
import java.util.Scanner;

public class MainClient {
    public static void main(String []args)
    {
        CustomClient client=new CustomClient();
        Scanner scanner = new Scanner( System.in );
        String line;

        boolean off=false;

        while (off==false)
        {
            System.out.println("Choose option: connect, login, disconnect, off, logout, ping, echo, adminKick");

            line = scanner.next();

            if(line.equals("login"))
            {
                String username,password;
                System.out.println("Username: ");
                username=scanner.next();
                System.out.println("Password: ");
                password=scanner.next();
                try {
                    client.login(username,password);
                } catch (IOException e) {
                    System.out.println("Error.");
                }

            }

            else if(line.equals("disconnect"))
            {
                client.disconnect();

            }

            else if(line.equals("off"))
            {
                off=true;
            }

            else if(line.equals("connect"))
            {
                try {
                    client.connect("127.0.0.1",8983);
                } catch (IOException e) {
                    System.out.println("Connecting error. Serwer rejected the connection.");
                }
            }

            else if(line.equals("logout"))
            {
                try {
                    client.logout();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            else if(line.equals("ping"))
            {
                try {
                    client.ping();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }


            else if(line.equals("echo"))
            {
                try {
                    System.out.println("Enter message:");
                    String text=scanner.next();
                    client.echo(text);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }


            else if(line.equals("adminKick"))
            {
                try {
                    System.out.println("Enter username to kick:");
                    String user=scanner.next();
                    client.iAmAdmin(user);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            else
            {
                System.out.println("Wrong command.");
            }

            line=null;
        }

    }
}
