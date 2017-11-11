import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.security.PublicKey;

public class ClientOnClass {
	public static void main(String args) throws IOException, SocketException, ClassNotFoundException
	{
		InetAddress address = InetAddress.getLocalHost();
		System.out.println(address);
		System.out.println("You are on client side");
		Socket clientsocket = new Socket("localhost",5000);
		System.out.println("Success");

		//Client.main(args);
		
		ObjectInputStream ois = new ObjectInputStream(clientsocket.getInputStream());
		PublicKey serverPublicKey = (PublicKey) ois.readObject();
		System.out.println(serverPublicKey);
		ObjectOutputStream oos = new ObjectOutputStream(clientsocket.getOutputStream());
		oos.writeObject(args);
		oos.flush();
	}
}
