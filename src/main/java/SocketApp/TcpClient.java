package SocketApp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
    private final Object lock = new Object();

    public void runClient(InputStream input){
        try{
            //Build Socket
            Socket socket = new Socket("127.0.0.1",4700);

            //Build connection
            InputStreamReader Sysin = new InputStreamReader(input);
            BufferedReader SysBuf = new BufferedReader(Sysin);

            InputStreamReader Socin = new InputStreamReader(socket.getInputStream());
            BufferedReader SocBuf = new BufferedReader(Socin);

            PrintWriter Socout = new PrintWriter(socket.getOutputStream());

            //Communication
            String readline = SysBuf.readLine();
            while(!readline.toUpperCase().equals("EXIT")){
                //synchronized (lock) {
                    Socout.println(readline);
                    Socout.flush();
                    System.out.println("Client:" + readline);
                    while (true) {
                        String str = SocBuf.readLine();
                        if (str.equals("end"))
                            break;
                        else
                            System.out.println("Server:" + str);
                    }

                    readline = SysBuf.readLine();
               // }
            }
            //Close IO和Socket
            Socout.close();
            Socin.close();
            socket.close();
        }catch(Exception e){
            System.out.println("Error:" + e);
        }
    }
    public static void main(String[] args)throws Exception{
        TcpClient a = new TcpClient();
        a.runClient(System.in);

    }

}
