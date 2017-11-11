import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ServerOnClass {
	
	static ArrayList clients;
	
	
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, ClassNotFoundException
	{
		int numOfConnection = 0;
		InetAddress address = InetAddress.getLocalHost();
		System.out.println(address);
		ServerSocket server = new ServerSocket(5000);
		System.out.println("TCP server is waiting on 5000");
		
		clients = new ArrayList();
		
		
		while (true) {
			Socket connect = server.accept();
			System.out.println("Connection established");
			System.out.println( ++numOfConnection);
			clients.add(connect);
			
			for (int i = 0; i < clients.size(); i++) {
	            Object value = clients.get(i);
	            System.out.println("Element: " + value.toString());
	        }
			
			// client info:start
			Integer portId = connect.getPort();
			System.out.println("port "+portId);
			int localPort = connect.getLocalPort();
			System.out.println("localPort "+localPort);
			InetAddress senderAddress = connect.getInetAddress();
			System.out.println("senderAddress "+senderAddress);			
			
			Random rand = new Random(); 
			int send = rand.nextInt(50); 
			String senderID = "client" + send;
			int receive = rand.nextInt(50);
			String recieverID = "client" + receive;
			System.out.println("senderID "+senderID);
			System.out.println("recieverID "+recieverID);
			//client info :end
			
			ArrayList registerInfo = new ArrayList();
			registerInfo.add(portId);
			registerInfo.add(senderAddress);
			
			//form a hashmap with senderID as key	
			HashMap<String, ArrayList> registerInfoMap = new HashMap<String, ArrayList>();
			if (!registerInfoMap.containsKey(senderID)){			
			registerInfoMap.put(senderID, registerInfo);
			}
			
		
			for (HashMap.Entry entry : registerInfoMap.entrySet()) {
			    System.out.println(entry.getKey() + ", " + entry.getValue());
			}
			
			
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(512);
			KeyPair serverKey = keyGen.generateKeyPair();
			// send server's public key to client
			ObjectOutputStream oos = new ObjectOutputStream(connect.getOutputStream());
			oos.writeObject(serverKey.getPublic());
			System.out.println(serverKey.getPublic());
			oos.flush();
			ObjectInputStream ois = new ObjectInputStream(connect.getInputStream());
			Object rmessage = ois.readObject();
			System.out.println("rmessage in ServerOnClass"+rmessage.toString());
			ois.toString();
			//Server.main((String)rmessage);
		}
	}
	
}
