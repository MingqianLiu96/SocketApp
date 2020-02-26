package SocketApp.UnitTest;
import SocketApp.TcpServer;
import SocketApp.TcpClient;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class CommunicationTest {

    File myObj = new File("file1.txt");
    InputStream input;


    public CommunicationTest() throws FileNotFoundException {
    }

    @Before
    public void init() throws FileNotFoundException {
        try {
            if (!myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                FileWriter myWriter = new FileWriter("file1.txt");
                for (int i = 0; i < 10; i++)
                    myWriter.write("GET:cat\n");
                for (int i = 0; i < 13; i++)
                    myWriter.write("GET:dog\n");
                for (int i = 0; i < 12; i++)
                    myWriter.write("GET:bird\n");
                myWriter.write("list\n");
                myWriter.write("exit\n");
                myWriter.close();

                input = new FileInputStream("file1.txt");
            } else {
                System.out.println("File has not existed.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    @Test
    public void communicateTest(){      //TcpServer need to run before testing
        TcpClient b = new TcpClient();
        b.runClient(input);
    }
}
