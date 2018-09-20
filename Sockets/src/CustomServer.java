import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import example.Utils;

public class CustomServer
{
    private boolean started = false;
    private boolean looped = true;

    private ArrayList clientsSockets=new ArrayList<Socket>();
    private ServerSocket serverSocket;
    private Thread serverThread;
    private static final int PING_FRAME = 1;
    private static final int HELLO_WORLD_FRAME = 2;
    private static final int LOGIN_FRAME = 3;
    private static final int EXIT_FRAME = 4;
    private static final int LOGOUT_FRAME = 5;
    private static final int ECHO_FRAME = 6;
    private static final int ADMIN_FRAME = 7;
    private static final int MESSAGE_FRAME = 8;

    private ArrayList loggedUsers=new ArrayList<String>();

    public boolean isStarted()
    {
        return this.started;
    }

    public boolean isLooped()
    {
        return this.looped;
    }



//    private class Logic
//    {
//        private ObjectInputStream inputStream = null;
//        private ObjectOutputStream outputStream = null;
//
//        public Logic( Socket clientSocket ) throws IOException
//        {
//            try
//            {
//                this.outputStream = Utils.createOutputStream( clientSocket );
//                this.inputStream = Utils.createInputStream( clientSocket );
//            }
//            catch ( IOException ex )
//            {
//                if ( this.outputStream != null )
//                    Utils.close( this.outputStream );
//
//                throw ex;
//            }
//        }
//    }



    public void run(int port) throws IOException
    {
        if ( started )
            throw new IOException( "Server is already started." );

        serverSocket = new ServerSocket(port);
        serverThread = new Thread()
        {
            @Override
            public void run()
            {
                while (looped )
                {
                    System.out.println("Serwer: Rozmiar listy socketow: " + clientsSockets.size());
                    if(clientsSockets.size()==5)
                    {
                        System.out.println("Serwer: Osiągnięta max liczba klientów. Czekam na zwolnienie.");
                        while(clientsSockets.size()==5)
                        {
                            try {
                                serverThread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            try {
                                serverSocket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            serverSocket=new ServerSocket(port);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    Socket clientSocket;

                    try
                    {
                        System.out.println("Serwer: Czekam na klientów.");

                        clientSocket = serverSocket.accept();
                        System.out.println("Serwer: Akceptuje nowego klienta.");
                    }
                    catch ( IOException e )
                    {
                        looped = false;
                        Utils.close(serverSocket );

                        break;
                    }

                    prepareClient( clientSocket );

                }
                finalizeClients();
                started = false;
            }
        };

        serverThread.start();
        started = true;
    }









    private void prepareClient( final Socket clientSocket )
    {
        System.out.println("Serwer: Połączył się klient: "+ clientSocket.getLocalAddress());

        Thread thread = new Thread()
        {
            @Override
            public void run(){
                while(looped)
                {
                    try {
                        doClientLogic(clientSocket);
                    } catch (IOException e) {
                        System.out.println("Serwer: Klient rozłączony.");
                        break;
                    }
                }}
        };
        thread.start();

        String adress= String.valueOf(clientSocket.getLocalAddress());

        System.out.println("Serwer: Dodaje na liste" + adress);

        clientsSockets.add(clientSocket);


    }

    private void doClientLogic( Socket socket ) throws IOException
    {

        int inputt;
        try
                (
                  DataInputStream input = new DataInputStream( socket.getInputStream() );
                  DataOutputStream output = new DataOutputStream( socket.getOutputStream() );
                  ObjectInputStream inputStream = new ObjectInputStream( socket.getInputStream() );
                )
        {
            while( looped )
            {

                try
                {
                    inputt=input.readInt();
                }
                catch( EOFException e )
                {
                    inputt=-1;
                    System.out.println("Bład wczytywania inputt.");
                    e.printStackTrace();
                }

                switch( inputt )
                {
                    case PING_FRAME:
                    {
                        String name=input.readUTF();

                        if(loggedUsers.contains(name))
                        {
                            output.writeBoolean( true );

                        }

                        else {

                            output.writeBoolean( false );
                        }


                        break;
                    }


                    case HELLO_WORLD_FRAME:
                    {
                        try {
                            output.writeInt(HELLO_WORLD_FRAME);
                            output.writeUTF("HELLO WORLD!");
                            output.flush();
                        }
                        finally {
                            if(output!=null) output.close();
                        }
                        break;
                    }

                    case LOGIN_FRAME:
                    {
                        System.out.println("Serwer: Logowanie");
                        //odbiór ramki
                        Frame frame= null;
                        LoginFrame loginFrame=null;

                        try {
                            frame = (Frame) inputStream.readObject();
                            loginFrame=(LoginFrame) frame;
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }


                        if ( "root".equals( loginFrame.getUsername() ) && "root".equals( loginFrame.getPassword() ) )
                        {
                            loggedUsers.add(loginFrame.getUsername());
                            System.out.println( "Login success." );
                            output.writeBoolean( true );
                        }

                        else if( "user1".equals( loginFrame.getUsername() ) && "123".equals( loginFrame.getPassword() ) )
                        {
                            loggedUsers.add(loginFrame.getUsername());
                            System.out.println( "Login success." );
                            output.writeBoolean( true );
                        }

                        else if ( "user2".equals( loginFrame.getUsername() ) && "123".equals( loginFrame.getPassword() ) )
                        {
                            loggedUsers.add(loginFrame.getUsername());
                            System.out.println( "Login success." );
                            output.writeBoolean( true );
                        }

                        else if ( "user3".equals( loginFrame.getUsername() ) && "123".equals( loginFrame.getPassword() ) )
                        {
                            loggedUsers.add(loginFrame.getUsername());
                            System.out.println( "Login success." );
                            output.writeBoolean( true );
                        }

                        else
                        {
                            System.out.println( "Login failed." );
                            output.writeBoolean( false );
                        }

                        break;
                    }

                    case EXIT_FRAME:
                    {
                        releaseClient(socket);

                        break;
                    }


                    case LOGOUT_FRAME:
                    {
                        String name=input.readUTF();
                        System.out.println("Odczytane name");
                        System.out.println(name);
                        if(loggedUsers.contains(name))
                        {
                            System.out.println("User logged out.");
                            loggedUsers.remove(name);
                            output.writeBoolean( true );

                        }
                        else {
                            System.out.println("User is not logged in.");
                            output.writeBoolean( false );

                        }


                        break;
                    }

                    case ECHO_FRAME:
                    {
                        System.out.println("Serwer log: echo");
                        String name=input.readUTF();
                        String text=input.readUTF();

                        if(loggedUsers.contains(name))
                        {
                            output.writeUTF(text);
                            output.writeBoolean( true );

                        }

                        else {

                            output.writeUTF("NULL");
                            output.writeBoolean( false );
                        }


                        break;
                    }

                    case ADMIN_FRAME:
                    {
                        String name=input.readUTF();
                        String userToRemove=input.readUTF();
                        if(name.equals("root"))
                        {
                            if(loggedUsers.contains(userToRemove)) {
                                loggedUsers.remove(userToRemove);
                                output.writeBoolean(true);
                            }
                            else
                                output.writeBoolean(false);
                        }

                        else {
                            output.writeBoolean(false);
                        }



                        break;
                    }

                }
            }
        }
        finally
        {
            Utils.close( socket );
        }
    }


    public void stop()
    {
        if ( started )
        {

            System.out.println("Serwer: Wywołano stop.");

            looped = false;

            this.closeObjects();
            Utils.join( serverThread );
            finalizeClients();
            started = false;

        }
    }

    private void releaseClient( Socket clientSocket )
    {
        Utils.close( clientSocket );

        synchronized ( this.clientsSockets )
        {
            System.out.println("Serwer: Usuwam klienta.");
            this.clientsSockets.remove(clientSocket);
            System.out.println(clientsSockets.size());
        }
    }

    private void finalizeClients()
    {
        ArrayList<Socket> tempList=new ArrayList<>();

        synchronized ( clientsSockets )
        {
            for ( Object el : clientsSockets ) {
                closeObject((Socket) el);
                tempList.add((Socket) el);
            }
            clientsSockets.removeAll(tempList);
        }

    }



    private boolean closeObject( Closeable object )
    {
        if ( object == null )
            return false;

        try
        {
            object.close();

            return true;
        }

        catch ( IOException e )
        {
            return false;
        }

    }


    private void closeObjects()
    {
        this.closeObject( serverSocket );
    }



}
