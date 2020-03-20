import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class TestServer {

	public static void main(String[] args) {       

		Thread mainApp = new Thread(() -> {
			int port = Integer.parseInt(args[0]);

			try (ServerSocket serverSocket = new ServerSocket(port)) {

				System.out.println("Server is listening on port " + port);

				while (true) {
					Socket socket = serverSocket.accept();

					System.out.println("New client connected");



					InputStream input = socket.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(input));
					String command = reader.readLine();

					OutputStream output = socket.getOutputStream();
					PrintWriter writer = new PrintWriter(output, true);

					while (!command.equals("exit")) {
						switch (command) {
						case "one" :
							writer.println(methodOne());
							System.out.println("1");
							break;
						case "two" :
							writer.println(methodTwo());
							System.out.println("2");
							break;
						default :
							writer.println("No such method.");
							System.out.println("0");
						}
						command = reader.readLine();
					}

					writer.println("bye");
					socket.close();
				}

			} catch (IOException ex) {
				System.out.println("Server exception: " + ex.getMessage());
				ex.printStackTrace();
			}
		});

		Adapter adapter = new Adapter(Integer.parseInt(args[1]));

		Thread adapterThread = new Thread(() -> {
			adapter.run();
		});

		mainApp.start();
		adapterThread.start();
	}

	public static int methodOne() {
		return 1;
	}

	public static int methodTwo() {
		return 2;
	}
}