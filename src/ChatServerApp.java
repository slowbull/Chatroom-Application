import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServerApp implements Runnable {
	Socket soc;
	String username;
	private Scanner input;
	private PrintWriter output;
	String message = "";
	
	public ChatServerApp(Socket x, String username){
		this.soc = x;
		this.username = username;
	}
	
	public void closeConnection(String message) throws IOException {
		if(true){
			// remove user from currentUsers and currentClients.
			String user = message.substring(8);
			//ChatServer.currentUsers.remove(user);
			ChatServer.currentClients.remove(user);
		}
	}
	
	public void closeChatroom(String message) throws IOException {
		if(true){
			String[] tmp = message.split("!#rm#!");
			String chatroomName = tmp[0];
			username = tmp[1];
			message = tmp[2];
			
	 		Hashtable<String,ArrayList<String>> currentChatRooms = new Hashtable<String,ArrayList<String>>(); // store the users in each chatroom.
			try{
				FileInputStream fileIn = new FileInputStream("chatroomList.db");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				currentChatRooms = (Hashtable)in.readObject();

				currentChatRooms.get(chatroomName).remove(username);

				if(currentChatRooms.get(chatroomName).size()==0){
					currentChatRooms.remove(chatroomName);
					// broadcast new chatroom to every user.
					for(String x: ChatServer.currentClients.keySet()){
						Socket tempSocket = (Socket)ChatServer.currentClients.get(x);
						PrintWriter out = new PrintWriter(tempSocket.getOutputStream());
						out.println("!#crit#!" + currentChatRooms.keySet());
						out.flush();
					}
				}

				
	//			System.out.println(username);
				System.out.println(username + " leaves " + chatroomName );
				FileOutputStream fileOut = new FileOutputStream("chatroomList.db");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(currentChatRooms);
				
			}catch( Exception x){
				System.out.println(x);
			}

			for(String x: ChatServer.currentClients.keySet()){
				Socket tempSoc = (Socket) ChatServer.currentClients.get(x);
				PrintWriter tempOut = new PrintWriter(tempSoc.getOutputStream());
				tempOut.println("!#mess#!"+ chatroomName + "!#rm#!" +message);
				tempOut.flush();
				tempOut.println("!#exit#!" + chatroomName + "!#rm#!" +currentChatRooms.get(chatroomName));
				tempOut.flush();
			}
			
		}
	}
	
	
	public static void addChatroom(Socket soc, String message) throws IOException{			
		String temp = message.substring(8);
		String[] tempArray = temp.split(",");
		String chatroomName = tempArray[0];
		String username = tempArray[1];
		
		// generate chatroom userlists map.
		Hashtable<String,ArrayList<String>> chatRoomsListTemp = new Hashtable<String,ArrayList<String>>();
		try{
			FileInputStream fileIn = new FileInputStream("chatroomList.db");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			chatRoomsListTemp = (Hashtable) in.readObject();
			ArrayList<String> users = new ArrayList<String>();
			users.add(username);
			chatRoomsListTemp.put(chatroomName,users); 
			FileOutputStream fileOut = new FileOutputStream("chatroomList.db");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(chatRoomsListTemp);
		}
		catch(Exception x){
			System.out.println(x);
		}
		
		// broadcast new chatroom to every user.
		for(String x: ChatServer.currentClients.keySet()){
			Socket tempSocket = (Socket)ChatServer.currentClients.get(x);
			PrintWriter out = new PrintWriter(tempSocket.getOutputStream());
			out.println("!#crit#!" + chatRoomsListTemp.keySet());
			out.flush();
		}
		
		// broadcast every user in the chatroom.
		for(int i=0; i < chatRoomsListTemp.get(chatroomName).size(); i++){
			Socket tempSocket = (Socket)ChatServer.currentClients.get(chatRoomsListTemp.get(chatroomName).get(i));
			PrintWriter out = new PrintWriter(tempSocket.getOutputStream());
			out.println( "!#mess#!" +chatroomName + "!#rm#!"+ username + " joins the chatroom.");
			out.flush();
			out.println("!#join#!" + chatroomName +"!#rm#!"+chatRoomsListTemp.get(chatroomName));
			out.flush();
			System.out.println(chatRoomsListTemp.keySet()+"#############3");
		}
		
	}
	 	
	public static void joinChatroom(Socket soc, String message) throws IOException{
		String temp = message.substring(8);
		String[] tempArray = temp.split(",");
		String chatroomName = tempArray[0];
		String username = tempArray[1];
		
		// update chatroom list
		Hashtable<String,ArrayList<String>> chatRoomsListTemp = new Hashtable<String,ArrayList<String>>();
		try{
			FileInputStream fileIn = new FileInputStream("chatroomList.db");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			chatRoomsListTemp = (Hashtable) in.readObject();
			ArrayList<String> users = chatRoomsListTemp.get(chatroomName);
			if(!users.contains(username)) users.add(username);
			chatRoomsListTemp.replace(chatroomName,users); 
			
			FileOutputStream fileOut = new FileOutputStream("chatroomList.db");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(chatRoomsListTemp);
		}
		catch(Exception x){
			System.out.println(x);
		}
	
		// broadcast every user in the chatroom.
		for(int i=0; i < chatRoomsListTemp.get(chatroomName).size(); i++){
			Socket tempSocket = (Socket)ChatServer.currentClients.get(chatRoomsListTemp.get(chatroomName).get(i));
			PrintWriter out = new PrintWriter(tempSocket.getOutputStream());
			out.println( "!#mess#!" +chatroomName + "!#rm#!"+ username + " joins the chatroom.");
			out.flush();
			
			out.println("!#join#!" +chatroomName +"!#rm#!"+ chatRoomsListTemp.get(chatroomName));
			out.flush();
		}
	}

	
	public void run(){
		try{
			try{
				input = new Scanner(soc.getInputStream());
				output = new PrintWriter(soc.getOutputStream());
				
				while(true){
					
					if(!input.hasNext()){
						continue;
					}
					
					message = input.nextLine();
					if(message.equals("")){
						continue;
					}
					System.out.println("Client said: "+message);
					
					//disconnect
					if(message.contains("!#clos#!")){
						closeConnection(message);
					}
					else if(message.contains("!#crit#!")){
						addChatroom(soc, message);
					}
					else if(message.contains("!#join#!")){
						joinChatroom(soc, message);
					}
					// receive messages. message = !#mess#! + chatroomName + "!#rm#!" + sentences.
					else if(message.contains("!#mess#!")){
					    String[] mes = message.substring(8).split("!#rm#!");
					    String chatroomName = mes[0];
					    String sentence = mes[1];
					    
					    // send messages to other users in the chat room.
						for(String x: ChatServer.currentClients.keySet()){
							Socket tempSoc = ChatServer.currentClients.get(x);
							PrintWriter tempOut = new PrintWriter(tempSoc.getOutputStream());
							tempOut.println(message);
							tempOut.flush();
							System.out.println("Sent to: "+ tempSoc.getLocalAddress().getHostName());
	 					}
					}
					// leave the chatroom. message = !#exit#! + chatroomName
					else if(message.contains("!#exit#!")){
						closeChatroom(message.substring(8));
					}
				}
				
			}
			finally{
				soc.close();
			}
		}
		catch(Exception x){
			System.out.println(x);
		}
	}
	
}
