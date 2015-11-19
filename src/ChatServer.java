import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer {
	//global

	//public static ArrayList<String> currentUsers = new ArrayList<String>(); // store the users connected.
	public static Hashtable<String,Socket> currentClients = new Hashtable<String,Socket>(); // store the users connected.
	public static Hashtable<String,ArrayList<String>> currentChatRooms = new Hashtable<String,ArrayList<String>>(); // store the users in each chatroom.
	//public static Hashtable<String, ArrayList<Socket>> currentChatRoomSockets = new Hashtable<String, ArrayList<Socket>>(); // store the sockets for each user.
 	public static Hashtable<String, String> userList = new Hashtable<String, String>() ;
	 
 	
	public static void main(String[] args) throws IOException{
		try{
			final int port = 4000;
			ServerSocket serSoc = new ServerSocket(port);
			System.out.println("Waiting for clients...");
			
			FileOutputStream fileOut = new FileOutputStream("userList.db");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(userList);
			
			fileOut = new FileOutputStream("chatroomList.db");
			out = new ObjectOutputStream(fileOut);
			out.writeObject(currentChatRooms);

			while(true){
				Socket soc = serSoc.accept();
				Scanner input =  new Scanner(soc.getInputStream());
				PrintWriter output = new PrintWriter(soc.getOutputStream());
				
				String username = input.nextLine();
				
				if(username.contains("!#logi#!")){
					userLogin(soc, username);
				}
				else if(username.contains("!#sign#!")){
					userSign(soc, username);
				}
								
				System.out.println("Client connected from: "+soc.getLocalAddress().getHostName());
				ChatServerApp chat = new ChatServerApp(soc, username);
				Thread x = new Thread(chat);
				x.start();
			}
		}
		catch(Exception  x){	
			System.out.println(x);
		}
	}
	
	public static void userLogin(Socket soc, String message){
		String username = message.substring(8);
		//currentUsers.add(username);
		currentClients.put(username,soc);
		
		// broadcast new chatroom to every user.
		try{
			FileInputStream fileIn = new FileInputStream("chatroomList.db");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			currentChatRooms = (Hashtable) in.readObject();

			for(String x: currentClients.keySet()){
				Socket tempSocket = (Socket)currentClients.get(x);
				PrintWriter out = new PrintWriter(tempSocket.getOutputStream());
				out.println("!#crit#!" + (Set<String>)currentChatRooms.keySet());
				out.flush();
			}
		}
		catch(Exception x){
			System.out.println(x);
		}
	}
	
	public static void userSign(Socket soc, String message){
		String tmp = message.substring(8);
		String[] tmp1 = tmp.split(",");
		String username = tmp1[0];
		String password = tmp1[1];
	//	currentUsers.add(username);
		currentClients.put(username,soc);
		Hashtable<String, String> userListTmp = new Hashtable<String, String>();
		try{
			FileInputStream fileIn = new FileInputStream("userList.db");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			userListTmp = (Hashtable) in.readObject();
			userListTmp.put(username, password);
			FileOutputStream fileOut = new FileOutputStream("userList.db");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(userListTmp);
		}
		catch(Exception x){
			System.out.println(x);
		}
		
		// broadcast new chatroom to every user.
		try{
			FileInputStream fileIn = new FileInputStream("chatroomList.db");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			currentChatRooms = (Hashtable) in.readObject();

			for(String x: currentClients.keySet()){
				Socket tempSocket = (Socket)currentClients.get(x);
				PrintWriter out = new PrintWriter(tempSocket.getOutputStream());
				out.println("!#crit#!" + (Set<String>)currentChatRooms.keySet());
				out.flush();
			}
		}
		catch(Exception x){
			System.out.println(x);
		}
	}
			

			
}
