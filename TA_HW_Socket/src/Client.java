
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
	private String address = "127.0.0.1";
	private int port = 8100;
	java.util.Scanner scanner = new java.util.Scanner(System.in);
	Boolean same = false;
	String textFromServer;

	@SuppressWarnings("resource")
	public Client() {

		Socket Client = new Socket();
		InetSocketAddress isa = new InetSocketAddress(this.address, this.port);

		DataInputStream input = null;
		DataOutputStream output = null;
		try {
			Client.connect(isa, 10000);
			input = new DataInputStream(Client.getInputStream());
			output = new DataOutputStream(Client.getOutputStream());

			// output.writeUTF("What time is it?");
			// System.out.println(input.readUTF());

			// String textFromServer = input.readUTF();

			System.out.println(input.readUTF());
			System.out.println("Please enter a number from 1-100:");
			while (same == false) {
				output.writeInt(scanner.nextInt());
				textFromServer = input.readUTF();
				System.out.println(textFromServer);
				if (textFromServer == "Bingo!") {
					same = true;
				}
			}

		} catch (java.io.IOException e) {
			System.out.println("Socket Connect Error!");
			System.out.println("IOException :" + e.toString());
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Client();
	}

}
