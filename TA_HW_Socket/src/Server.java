import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;


public class Server extends Thread {

	public static final int LISTEN_PORT = 8100;
	
	boolean check = false;
	int ClientNum;
	int counter = 0;
	Random rand = new Random();
	int randNum = (int)(Math.random() * 100 + 1);
	
	
	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(LISTEN_PORT);
			System.out.println("Wait for Connect......");
			Socket socket = serverSocket.accept();
			// System.out.println("Connect Success!");
			serverSocket.close();

			DataInputStream input = new DataInputStream(socket.getInputStream());
			DataOutputStream output = new DataOutputStream(socket.getOutputStream());

			// SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			// //set time format
			// String nowDate = sdFormat.format(new Date());//get time
			// System.out.println(input.readUTF());
			// output.writeUTF("The time now is:" + nowDate); //send the data into stream
			output.writeUTF("Connect Success!");

			while (check == false) {
				ClientNum = input.readInt();
				if (ClientNum > randNum) {
					output.writeUTF("Too large");
					counter++;
				} else if (ClientNum < randNum) {
					output.writeUTF("Too small");
					counter++;
				} else if (ClientNum == randNum) {
					counter++;
					check = true;
					output.writeUTF("Bingo!\nYou guessed: " + counter + " times");
				}
				// output.flush();
			}

			output.flush();// clear buffer area and send the data
			socket.close(); // close connection
		} catch (IOException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Server server = new Server();
		server.start();
	}

}
