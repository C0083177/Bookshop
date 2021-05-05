import java.net.*;
import java.io.*;
import java.util.*;

public class Client
{
    public static void main (String arg[]) throws IOException
    {
        //WHILE LOOP WITHIN THE TRY AFTER SOCKET IS CREATED
        try
        {
            Socket socket = new Socket("127.0.0.1", 3000);
            //System.out.println("Connected!");
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            String received = inputStream.readUTF();
            System.out.println(received);
        }
        catch(Exception e) {}

        IO ioMenu = new IO();
        ioMenu.runMenu();
    }
}
