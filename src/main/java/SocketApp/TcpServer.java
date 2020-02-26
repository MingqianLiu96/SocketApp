package SocketApp;

import SocketApp.QuickSort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TcpServer {
    private HashMap<String,Integer> map = new HashMap<>();
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();


    public void runServer(){

        try{
            //Build Socket
            ServerSocket server = new ServerSocket(4700);
            //Monitor
            Socket socket = server.accept();

            //Build connection
            InputStreamReader Socin = new InputStreamReader(socket.getInputStream());
            BufferedReader SocBuf = new BufferedReader(Socin);

            PrintWriter Socout = new PrintWriter(socket.getOutputStream());

            //Communicate
            StringBuffer buffer = new StringBuffer();
            String readline = "";
            while(!readline.toUpperCase().equals("EXIT")){

                    String rl = SocBuf.readLine();

                    Pattern p = Pattern.compile("^GET:");
                    Matcher m = p.matcher(rl.toUpperCase());
                    if (rl.toUpperCase().equals("LIST")) {

                        ArrayList<Integer> arr = new ArrayList<>(map.values());
                        ArrayList<String> dict = new ArrayList<>(map.keySet());
                        QuickSort q = new QuickSort();
                        q.quickSort(arr, dict, 0, arr.size() - 1);

                        for (int i = 0; i < arr.size(); i++) {
                            buffer.append(dict.get(i) + ": ");
                            buffer.append(arr.get(i));
                            buffer.append("\n");
                        }
                        buffer.append("OK\n");
                        buffer.append("end\n");
                        readline = buffer.toString();
                        Socout.printf(readline);
                        Socout.flush();
                        buffer.delete(0, buffer.length());
                        System.out.println("Enter LIST condition");
                        System.out.println("Client:" + rl);
                        System.out.printf("Server: " + readline);

                        continue;

                    } else if (m.find()) {
                        buffer.append("OK\n");
                        buffer.append("end\n");
                        readline = buffer.toString();

                            Socout.printf(readline);
                            Socout.flush();
                            buffer.delete(0, buffer.length());
                        synchronized (lock1) {
                            if (map.containsKey(rl.substring(4))) {
                                map.put(rl.substring(4), map.get(rl.substring(4)) + 1);
                            } else {
                                map.put(rl.substring(4), 1);
                            }

                       }
                        System.out.println("Enter match condition");
                        System.out.println("Client:" + rl);
                        System.out.println("Server:" + readline);
                        continue;
                    } else {
                        buffer.append("Please type valid command.\n");
                        buffer.append("end\n");
                        readline = buffer.toString();
                        Socout.printf(readline);
                        Socout.flush();
                        buffer.delete(0, buffer.length());
                        System.out.println("Client:" + rl);
                        System.out.println("Server:" + readline);
                    }


            }
            //Close IO、Socket
            Socout.close();
            Socin.close();
            server.close();
        }catch (Exception e){
            System.out.println("Error" + e);
        }
    }

    public static void main(String[] args){
        TcpServer a = new TcpServer();
        a.runServer();
        TcpClient b = new TcpClient();
        b.runClient(System.in);
        TcpClient c = new TcpClient();

    }

}
