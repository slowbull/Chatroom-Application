import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class ChatroomClient implements Runnable{
	//globals
	static Socket soc;
	static String username;
	static String[] chatroom;
	Scanner input;
	Scanner send = new Scanner(System.in);
	static PrintWriter out;
	

	public ChatroomClient(Socket x, String username){
		this.soc = x;
		this.username = username;
	}
	
	public void run(){
		try{
			try{
				input = new Scanner(soc.getInputStream());
				out = new PrintWriter(soc.getOutputStream());
				out.flush();
				checkStream();
			}
			finally{
				soc.close();
			}
		}
		catch(Exception x){
			System.out.println(x);
		}
	}
	
	public static void disconnect(String chatroomName) throws IOException{
		out.println("!#exit#!" + chatroomName + "!#rm#!"+username+"!#rm#!" + chatClientGUIs.username + " leaves this chatroom.");
		out.flush();

		JOptionPane.showMessageDialog(null, "You leave the chatroom! ");
		if(chatClientGUIs.chatroomFlag[0].equals(chatroomName)){
			chatClientGUIs.chatroomFlag[0]="0";
			chatClientGUIs.closeChatRoom(0);
		}
		else if(chatClientGUIs.chatroomFlag[1].equals(chatroomName)){
			chatClientGUIs.chatroomFlag[1]="0";
			chatClientGUIs.closeChatRoom(1);
		}
	}
	
	public static void close() throws IOException{
		for(int i=0;i<2; i++){
			if(!chatClientGUIs.chatroomFlag[i].equals("0"))
				disconnect(chatClientGUIs.chatroomFlag[i]);
		}
		out.println("!#clos#!"+username);
	}
	
	public void checkStream(){
		while(true){
			receive();
		}
	}
	
	public void receive(){
		if(input.hasNext()){
			String message = input.nextLine();
			if(message.contains("!#join#!")){
				String[] tmp = message.substring(8).split("!#rm#!");
				String chatroomName = tmp[0];
				String temp1 = tmp[1];
				temp1 = temp1.replace("[", "");
				temp1 = temp1.replace("]", "");
				String[] currentUsers = temp1.split(", ");
				if(chatClientGUIs.chatroomFlag[0].equals(chatroomName)){
					chatClientGUIs.listUserList.setListData(currentUsers);
				}
				else if(chatClientGUIs.chatroomFlag[1].equals(chatroomName)){
					chatClientGUIs.listUserList2.setListData(currentUsers);
				}
			}
			else if(message.contains("!#crit#!")){
				String temp1 = message.substring(8);
				temp1 = temp1.replace("[", "");
				temp1 = temp1.replace("]", "");
				String[] currentChatrooms = temp1.split(", ");
				chatClientGUIs.tchatroomList.setListData(currentChatrooms);
			}
			else if(message.contains("!#mess#!")){
				//chatClientGUIs.chatConversations.get(chatroom).concat(message.substring(8)+"\n");
				String[] tmp = message.substring(8).split("!#rm#!");
				String chatrm = tmp[0];
				String words = tmp[1];
				if(chatClientGUIs.chatroomFlag[0].equals(chatrm)){
					chatClientGUIs.tConversation.append(words+"\n");
				}
				else if(chatClientGUIs.chatroomFlag[1].equals(chatrm)){
					chatClientGUIs.tConversation2.append(words+"\n");
				}
			}
			else if(message.contains("!#exit#!")){
				String[] tmp = message.substring(8).split("!#rm#!");
				String chatrm = tmp[0];
				String temp1 = tmp[1];

				temp1 = temp1.replace("[", "");
				temp1 = temp1.replace("]", "");
				String[] currentUsers = temp1.split(", ");
				if(chatClientGUIs.chatroomFlag[0].equals(chatrm)){
					chatClientGUIs.listUserList.setListData(currentUsers);
				}
				else if(chatClientGUIs.chatroomFlag[1].equals(chatrm)){
					chatClientGUIs.listUserList2.setListData(currentUsers);
				}
			}
			else{
				//ChatClientGUI.tConversation.append(message+"\n");
			}
		}
	}

	public static void send(String chatroom, String x){
		out.println("!#mess#!"+chatroom+"!#rm#!"+username+": "+x);
		out.flush();
	}
	
	public static void newChatroom(String message, String chatroomName){
		if(chatClientGUIs.chatroomFlag[0]=="0"){
			chatClientGUIs.chatroomFlag[0]=chatroomName;
			chatClientGUIs.configureChatroom(chatroomName);
			if(chatClientGUIs.flag[0]==true){
				chatClientGUIs.chatroomWindowAction();
				chatClientGUIs.flag[0] = false;
			}
		}
		else if(chatClientGUIs.chatroomFlag[1]=="0"){
			chatClientGUIs.chatroomFlag[1]=chatroomName;
			chatClientGUIs.configureChatroom2(chatroomName);
			if(chatClientGUIs.flag[1]==true){
				chatClientGUIs.chatroomWindowAction2();
				chatClientGUIs.flag[1]=false;
			}
		}
		out.println(message);
		out.flush();
 	}
	
}
