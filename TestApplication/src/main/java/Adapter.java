import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


import proto.test.TestMessage;
import proto.specs.SpecsMessage;

public class Adapter {

    private ServerSocket serverSocket;
    private int port;

    public Adapter(int port) {
        this.port = port;
    }

    public void run() {
        
        try {

            Socket notifySocket = new Socket(InetAddress.getByName("localhost"), 3000);
            OutputStream notifyOutput = notifySocket.getOutputStream();
            SpecsMessage.Specs protoNotifyMessage = SpecsMessage.Specs.newBuilder()
                .setNodeType("basic")
                .setAddress("localhost")
                .setPort(2001)
                .build();
            protoNotifyMessage.writeDelimitedTo(notifyOutput);
            notifySocket.close();

            serverSocket = new ServerSocket(port);
            System.out.println("Adapter is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();

                System.out.println("New client connected");

                InputStream input = socket.getInputStream();
                System.out.println("Building message...");
                TestMessage.Test protoMessage = TestMessage.Test.parseDelimitedFrom(input);
                System.out.println("Message built : " + protoMessage);
                
                String command = protoMessage.getMethodName();
                System.out.println("Command : " + command);

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                while (!command.equals("exit")) {
                    switch (command) {
                        case "both" :
                            writer.println(TestServer.methodOne()+TestServer.methodTwo());
                            System.out.println("3");
                            break;
                        default :
                            writer.println("No such method.");
                            System.out.println("0");
                    }
                   
                    System.out.println("Building message...");
                    protoMessage = TestMessage.Test.parseDelimitedFrom(input);
                    System.out.println("Message built : " + protoMessage);                   
                    command = protoMessage.getMethodName();
                    System.out.println("Command : " + command);
                }

                writer.println("bye");
                socket.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}



