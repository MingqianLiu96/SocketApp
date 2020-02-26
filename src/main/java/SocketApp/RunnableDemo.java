package SocketApp;

import java.io.*;

public class RunnableDemo implements Runnable {
    private Thread t;
    private String threadName;
    File myObj = new File("file1.txt");
    File myObj2 = new File("file2.txt");

    InputStream input;
    InputStream input2;


    RunnableDemo( String name) {
        threadName = name;
        System.out.println("Creating " +  threadName );
        try {
            if (!myObj.createNewFile()) {
//                PrintWriter writer = new PrintWriter("file1.txt");
//                writer.print("");
//                writer.close();
                System.out.println("File created: " + myObj.getName());
                FileWriter myWriter = new FileWriter("file1.txt");
                for (int i = 0; i < 4; i++)
                    myWriter.write("GET:cat\n");
                for (int i = 0; i < 5; i++)
                    myWriter.write("GET:dog\n");
                for (int i = 0; i < 2; i++)
                    myWriter.write("GET:bird\n");
                myWriter.write("list\n");
                myWriter.close();

                input = new FileInputStream("file1.txt");
            } else {
                System.out.println("File has not existed.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        try {
            if (!myObj2.createNewFile()) {
//                PrintWriter writer = new PrintWriter("file1.txt");
//                writer.print("");
//                writer.close();
                System.out.println("File created: " + myObj2.getName());
                FileWriter myWriter = new FileWriter("file1.txt");

                    myWriter.write("GET:cat\n");

                    myWriter.write("GET:dog\n");

                    myWriter.write("GET:bird\n");
                myWriter.write("list\n");
                myWriter.close();

                input2 = new FileInputStream("file2.txt");
            } else {
                System.out.println("File has not existed.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }

    public void run() {
        System.out.println("Running run()" +  threadName );
        try {
            TcpClient a = new TcpClient();
            if(threadName.equals("Thread-1")) {
                a.runClient(input);
                System.out.println("running input1");
            }
            else {
                a.runClient(input2);
                System.out.println("running input2");
            }

        }catch (Exception e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
    }

    public void start () {
        System.out.println("Starting start()" +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}
