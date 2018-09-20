import example.Utils;

import java.io.*;
import java.net.Socket;

public class CustomClient {
    private ObjectOutputStream outputStream;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private boolean logged=false;
    private boolean connected = false;
    private Socket clientSocket;
    String username;

    public boolean isConnected() {
        return connected;
    }

    public void connect(String host, int port) throws IOException {
        if (this.connected)
            throw new IOException("Client is already connected.");


        clientSocket = new Socket(host, port);

        outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        dataInputStream = new DataInputStream(clientSocket.getInputStream());
        dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

        if(clientSocket.isConnected()) {
            System.out.println("Client: Connected.");
            connected = true;
        }
        else{
            System.out.println("Client: Server rejected the connection.");
        }
    }


    public void disconnect() {

        if (connected) {
            try {
                dataOutputStream.writeInt(4);
            } catch (IOException e) {
                System.out.println("Client disconnected.");
            }
            connected = false;
            Utils.close(clientSocket);
        }
    }

    public boolean login(String username, String password) throws IOException
    {

        if (connected && logged==false) {
            this.username=username;
            System.out.println("Client: Trying to login...");
            boolean result = false;

            LoginFrame loginFrame = new LoginFrame();

            loginFrame.setUsername(username);
            loginFrame.setPassword(password);

            dataOutputStream.writeInt(3);
            outputStream.writeObject(loginFrame);


            result = dataInputStream.readBoolean();


            if (result == false)
            {
                logged=false;
                System.out.println("Login failed. Wrong username or password.");
                return false;
            }

            else {
                System.out.println("Login successful.");
                logged=true;
                return true;
            }

        }
        else {
            System.out.println("You are not connected to the server/you have already logged in.");
            return false;
        }
    }



    public void logout() throws IOException
    {

        boolean result;

        if(connected && logged)
        {
            dataOutputStream.writeInt(5);
            dataOutputStream.writeUTF(username);
        }
        else throw new IOException("Connection error.");

        result = dataInputStream.readBoolean();
        if (result == false){
            System.out.println("Logout failed.");
        }
        else {
            System.out.println("Logout succesful.");
            logged=false;

        }


    }


    public boolean ping() throws IOException {

        if (logged) {
            dataOutputStream.writeInt(1);
            dataOutputStream.writeUTF(username);
            boolean result = dataInputStream.readBoolean();

            if (result == false) {
                System.out.println("Ping failed.");
                logged=false;
                return false;
            } else {
                System.out.println("Ping succesful.");

                return true;
            }
        }

        else{
            throw new IOException("You are not connected to the server/you are not logged in.");
        }

    }


    public boolean echo( String text ) throws IOException
    {
        if(logged)
        {
            dataOutputStream.writeInt(6);
            dataOutputStream.writeUTF(username);
            System.out.println("Sent:");
            System.out.println(text);
            dataOutputStream.writeUTF(text);
            System.out.println("Received:");
            String textFromServer=dataInputStream.readUTF();
            System.out.println(textFromServer);
            boolean result = dataInputStream.readBoolean();

            if (result == false) {
                System.out.println("Echo failed.");
                logged=false;
                return false;
            } else {
                System.out.println("Echo succesful.");

                return true;
            }
        }
        else
            {
            throw new IOException("You are not connected to the server/you are not logged in.");
        }
    }


    public void iAmAdmin(String nameToRemove) throws IOException {
        if(logged){
        dataOutputStream.writeInt(7);
        dataOutputStream.writeUTF(username);
        dataOutputStream.writeUTF(nameToRemove);

        boolean result=dataInputStream.readBoolean();

        if(result==true)
        {
            System.out.println("User has been kicked.");
        }

        else if(result==false){
            System.out.println("User hasn't been kicked - error.");
        }
        }

        else
        {
            throw new IOException("You are not connected to the server/no permission.");
        }
    }


}



