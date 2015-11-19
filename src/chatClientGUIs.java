import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;

public class chatClientGUIs {
	private static ChatroomClient chatClient;
	public static String username = "Anonymous";
	public static String[] chatroomFlag = new String[2];
//	public static Hashtable<String, String> chatConversations = new Hashtable<String,String>();
	public static boolean[] flag = new boolean[]{true,true};
	
 	// GUI globals login window
 	public static JFrame logInWindow = new JFrame();
 	private static JButton bLogin = new JButton("Log in");
 	private static JButton bSignup = new JButton("Sign up");
 	private static JLabel lUsername = new JLabel("username: ");
 	public static JTextField tUsername  = new JTextField(20);
 	private static JTextField tPassword = new JTextField(20);
 	private static JLabel lPassword = new JLabel("password: ");
 	private static JPanel pLogin = new JPanel();
 	private static JButton bExit = new JButton("Exit");
 	
	// GUI globals main window
	public static JFrame mainWindow = new JFrame();
	
	private static JLabel lUsernameMain = new JLabel();
	private static JLabel lchatroomList = new JLabel("Chatroom List");
	public static JList tchatroomList = new JList();
 	private static JScrollPane schatroomList = new JScrollPane();
 	public static JTextField tCreateChatroom = new JTextField();
 //	public static JTextField tJoinChatroom  = new JTextField();
 //	public static JTextField tSwitchChatroom = new JTextField();
 	private static JButton bCreateChatroom = new JButton("Create");
 	private static JButton bJoinChatroom = new JButton("Join");
 	private static JButton bExitMain = new JButton("Exit");
 //	private static JButton bSwitchChatroom = new JButton("Switch");
 	
 	//chatroom windows
	private static JFrame chatWindow = new JFrame();
	private static JLabel lChatroom = new JLabel();
	public static JTextField tChatroom  = new JTextField();
	private static JLabel lUsernameChat = new JLabel();
	public static JTextField tUsernameChat = new JTextField();
	private static JLabel lConversation = new JLabel("Conversation");
	public static JTextArea tConversation = new JTextArea();
	private static JScrollPane sConversation = new JScrollPane();
	private static JLabel lUserList = new JLabel("Online users");
	public static JList listUserList = new JList();
	private static JScrollPane sUserList = new JScrollPane();
	private static JLabel lMessage = new JLabel("Message: ");
	public static JTextField tMessage = new JTextField();
	private static JButton bSendChat = new JButton("Send");
	private static JButton bExitChat = new JButton("Exit");
 	
	
 	//chatroom windows 2
	private static JFrame chatWindow2 = new JFrame();
	private static JLabel lChatroom2 = new JLabel();
	public static JTextField tChatroom2  = new JTextField();
	private static JLabel lUsernameChat2 = new JLabel();
	public static JTextField tUsernameChat2 = new JTextField();
	private static JLabel lConversation2 = new JLabel("Conversation");
	public static JTextArea tConversation2 = new JTextArea();
	private static JScrollPane sConversation2 = new JScrollPane();
	private static JLabel lUserList2 = new JLabel("Online users");
	public static JList listUserList2 = new JList();
	private static JScrollPane sUserList2 = new JScrollPane();
	private static JLabel lMessage2 = new JLabel("Message: ");
	public static JTextField tMessage2 = new JTextField();
	private static JButton bSendChat2 = new JButton("Send");
	private static JButton bExitChat2 = new JButton("Exit");

 	public static void main(String[] args){
 		buildLoginWindow();
 		//buildMainWindow();
 	}
 	
	public static void configureChatroom(String chatroom){
		chatWindow.setTitle("Chatroom");
		chatWindow.setSize(400, 400);
		chatWindow.setLocation(1000, 320);
		chatWindow.setVisible(true);
		chatWindow.setResizable(false);
		chatWindow.setBackground(new java.awt.Color(255,255,255));
		chatWindow.setLayout(null);
 		
		chatWindow.getContentPane().add(lChatroom);
		lChatroom.setText("Chatroom:");
		lChatroom.setBounds(10,10,80,20);
		
		chatWindow.getContentPane().add(tChatroom);
		tChatroom.setText(chatroom);
		tChatroom.setForeground(new java.awt.Color(250, 0, 0));
		tChatroom.setBounds(100, 10, 80, 20);

		chatWindow.getContentPane().add(lUsernameChat);
		lUsernameChat.setText("Username:");
		lUsernameChat.setBounds(190,10,80,20);
		
		chatWindow.getContentPane().add(tUsernameChat);
		tUsernameChat.setText(username);
		tUsernameChat.setForeground(new java.awt.Color(250, 0, 0));
		tUsernameChat.setBounds(280, 10, 80, 20);
		
 		lConversation.setHorizontalAlignment(SwingConstants.CENTER);
 		lConversation.setText("Conversation");
 		chatWindow.getContentPane().add(lConversation);
 		lConversation.setBounds(20, 40, 100, 20);
 		
 		tConversation.setColumns(20);
 		tConversation.setFont(new java.awt.Font("Tahoma", 0, 12));
 		tConversation.setForeground(new java.awt.Color(0, 0, 255));
 		tConversation.setLineWrap(true);
 		tConversation.setRows(5);
 		tConversation.setEditable(false);
 		
 		sConversation.setHorizontalScrollBarPolicy(
 				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
 		sConversation.setVerticalScrollBarPolicy(
 				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
 		sConversation.setViewportView(tConversation);
 		chatWindow.getContentPane().add(sConversation);
 		sConversation.setBounds(20, 70, 150, 200);

 		chatWindow.getContentPane().add(lUserList);
 		lUserList.setHorizontalAlignment(SwingConstants.CENTER);
 		lUserList.setBounds(210, 40, 100, 20);
 		
 		listUserList.setForeground(new java.awt.Color(0, 0, 255));
 		
 		sUserList.setHorizontalScrollBarPolicy(
 				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
 		sUserList.setVerticalScrollBarPolicy(
 				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
 		sUserList.setViewportView(listUserList);
 		chatWindow.getContentPane().add(sUserList);
 		sUserList.setBounds(210, 70, 100, 200);
 		
 		
 		chatWindow.getContentPane().add(lMessage);
 		lMessage.setBounds(20, 300, 60, 20);
 		
 		chatWindow.getContentPane().add(tMessage);
 		tMessage.setForeground(new java.awt.Color(0, 0, 255));
 		tMessage.requestFocus();
 		tMessage.setBounds(80, 300, 160, 20);
 		
 		bSendChat.setBackground(new java.awt.Color(0, 0, 255));
 		bSendChat.setForeground(new java.awt.Color(255, 255, 255));
 		bSendChat.setText("Send");
 		chatWindow.getContentPane().add(bSendChat);
 		bSendChat.setBounds(260, 300, 100, 20);
 		
 		bExitChat.setBackground(new java.awt.Color(0, 0, 255));
 		bExitChat.setForeground(new java.awt.Color(255, 255, 255));
 		chatWindow.getContentPane().add(bExitChat);
 		bExitChat.setBounds(260, 330, 100, 20);
	}

	public static void configureChatroom2(String chatroom){
		chatWindow2.setTitle("Chatroom");
		chatWindow2.setSize(400, 400);
		chatWindow2.setLocation(1200, 320);
		chatWindow2.setVisible(true);
		chatWindow2.setResizable(false);
		chatWindow2.setBackground(new java.awt.Color(255,255,255));
		chatWindow2.setLayout(null);
 		
		chatWindow2.getContentPane().add(lChatroom2);
		lChatroom2.setText("Chatroom:");
		lChatroom2.setBounds(10,10,80,20);
		
		chatWindow2.getContentPane().add(tChatroom2);
		tChatroom2.setText(chatroom);
		tChatroom2.setForeground(new java.awt.Color(250, 0, 0));
		tChatroom2.setBounds(100, 10, 80, 20);

		chatWindow2.getContentPane().add(lUsernameChat2);
		lUsernameChat2.setText("Username:");
		lUsernameChat2.setBounds(190,10,80,20);
		
		chatWindow2.getContentPane().add(tUsernameChat2);
		tUsernameChat2.setText(username);
		tUsernameChat2.setForeground(new java.awt.Color(250, 0, 0));
		tUsernameChat2.setBounds(280, 10, 80, 20);
		
 		lConversation2.setHorizontalAlignment(SwingConstants.CENTER);
 		lConversation2.setText("Conversation");
 		chatWindow2.getContentPane().add(lConversation2);
 		lConversation2.setBounds(20, 40, 100, 20);
 		
 		tConversation2.setColumns(20);
 		tConversation2.setFont(new java.awt.Font("Tahoma", 0, 12));
 		tConversation2.setForeground(new java.awt.Color(0, 0, 255));
 		tConversation2.setLineWrap(true);
 		tConversation2.setRows(5);
 		tConversation2.setEditable(false);
 		
 		sConversation2.setHorizontalScrollBarPolicy(
 				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
 		sConversation2.setVerticalScrollBarPolicy(
 				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
 		sConversation2.setViewportView(tConversation2);
 		chatWindow2.getContentPane().add(sConversation2);
 		sConversation2.setBounds(20, 70, 150, 200);

 		chatWindow2.getContentPane().add(lUserList2);
 		lUserList2.setHorizontalAlignment(SwingConstants.CENTER);
 		lUserList2.setBounds(210, 40, 100, 20);
 		
 		listUserList2.setForeground(new java.awt.Color(0, 0, 255));
 		
 		sUserList2.setHorizontalScrollBarPolicy(
 				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
 		sUserList2.setVerticalScrollBarPolicy(
 				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
 		sUserList2.setViewportView(listUserList2);
 		chatWindow2.getContentPane().add(sUserList2);
 		sUserList2.setBounds(210, 70, 100, 200);
 		
 		
 		chatWindow2.getContentPane().add(lMessage2);
 		lMessage2.setBounds(20, 300, 60, 20);
 		
 		chatWindow2.getContentPane().add(tMessage2);
 		tMessage2.setForeground(new java.awt.Color(0, 0, 255));
 		tMessage2.requestFocus();
 		tMessage2.setBounds(80, 300, 160, 20);
 		
 		bSendChat2.setBackground(new java.awt.Color(0, 0, 255));
 		bSendChat2.setForeground(new java.awt.Color(255, 255, 255));
 		bSendChat2.setText("Send");
 		chatWindow2.getContentPane().add(bSendChat2);
 		bSendChat2.setBounds(260, 300, 100, 20);
 		
 		bExitChat2.setBackground(new java.awt.Color(0, 0, 255));
 		bExitChat2.setForeground(new java.awt.Color(255, 255, 255));
 		chatWindow2.getContentPane().add(bExitChat2);
 		bExitChat2.setBounds(260, 330, 100, 20);
	}

 	public static void buildLoginWindow(){
 		logInWindow.setTitle("Chatroom Application");
 		configureLoginWindow();
 		logInWindowAction();
 	}
 	
 	public static void buildMainWindow(){
 		mainWindow.setTitle(username);
 		configureMainWindow();
 		mainWindowAction();
 	}
 	
 	public static void configureMainWindow(){
 		mainWindow.setSize(300, 400);
 		mainWindow.setLocation(700, 320);
 		mainWindow.setVisible(true);
 		mainWindow.setResizable(false);
 		mainWindow.setBackground(new java.awt.Color(255,255,255));
 		mainWindow.setLayout(null);
 		
 		mainWindow.getContentPane().add(lUsernameMain);
 		lUsernameMain.setText("Username: " + username);
 		lUsernameMain.setForeground(new java.awt.Color(250, 0, 0));
 		lUsernameMain.setBounds(140,10,140,20);
 		
 		mainWindow.getContentPane().add(lchatroomList);
 		lchatroomList.setHorizontalAlignment(SwingConstants.CENTER);
 		lchatroomList.setBounds(80, 50, 140, 20);
 		
 		tchatroomList.setForeground(new java.awt.Color(0, 0, 255));
 		
 		schatroomList.setHorizontalScrollBarPolicy(
 				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
 		schatroomList.setVerticalScrollBarPolicy(
 				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
 		schatroomList.setViewportView(tchatroomList);
 		mainWindow.getContentPane().add(schatroomList);
 		schatroomList.setBounds(40, 80, 220, 180);
 		
 		mainWindow.getContentPane().add(tCreateChatroom);
 		tCreateChatroom.setForeground(new java.awt.Color(0, 0, 255));
 		tCreateChatroom.setBounds(40, 280, 100, 20);
 		
 		bCreateChatroom.setBackground(new java.awt.Color(0, 0, 255));
 		bCreateChatroom.setForeground(new java.awt.Color(255, 255, 255));
 		mainWindow.getContentPane().add(bCreateChatroom);
 		bCreateChatroom.setBounds(160, 280, 100, 20);
 		/*
 		mainWindow.getContentPane().add(tJoinChatroom);
 		tJoinChatroom.setForeground(new java.awt.Color(0, 0, 255));
 		tJoinChatroom.setBounds(40, 310, 100, 20);
 		 
 		mainWindow.getContentPane().add(bSwitchChatroom);
 		bSwitchChatroom.setBackground(new java.awt.Color(0, 0, 255));
 		bSwitchChatroom.setForeground(new java.awt.Color(255, 255, 255));
 		bSwitchChatroom.setBounds(40, 310, 100, 20);
 		*/
 		bJoinChatroom.setBackground(new java.awt.Color(0, 0, 255));
 		bJoinChatroom.setForeground(new java.awt.Color(255, 255, 255));
 		mainWindow.getContentPane().add(bJoinChatroom);
 		bJoinChatroom.setBounds(160, 310, 100, 20);	
 		
 		bExitMain.setBackground(new java.awt.Color(0, 0, 255));
 		bExitMain.setForeground(new java.awt.Color(255, 255, 255));
 		mainWindow.getContentPane().add(bExitMain);
 		bExitMain.setBounds(160, 340, 100, 20);	

 	}
 	
 	public static void configureLoginWindow(){
 		logInWindow.setSize(400, 150);
 		logInWindow.setLocation(700, 300);
 		logInWindow.setVisible(true);
 		logInWindow.setResizable(false);

 		logInWindow.setBackground(new java.awt.Color(255, 255, 255));
 		logInWindow.getContentPane().setLayout(null);

 		logInWindow.getContentPane().add(lUsername);
 		lUsername.setBounds(40, 10, 100, 20);
 		
 		logInWindow.getContentPane().add(tUsername);
 		tUsername.setBounds(140, 10, 200, 20);
 		
 		logInWindow.getContentPane().add(lPassword);
 		lPassword.setBounds(40, 35, 100, 20);
 		
 		logInWindow.getContentPane().add(tPassword);
 		tPassword.setBounds(140, 35, 200, 20);

 		bLogin.setBackground(new java.awt.Color(0, 0, 255));
 		bLogin.setForeground(new java.awt.Color(255, 255, 255));
 		logInWindow.getContentPane().add(bLogin);
 		bLogin.setBounds(30, 70, 100, 20);
 		
 		bSignup.setBackground(new java.awt.Color(0, 0, 255));
 		bSignup.setForeground(new java.awt.Color(255, 255, 255));		
 		logInWindow.getContentPane().add(bSignup);
 		bSignup.setBounds(150, 70, 100, 20);
 		
 		bExit.setBackground(new java.awt.Color(0, 0, 255));
 		bExit.setForeground(new java.awt.Color(255, 255, 255));		
 		logInWindow.getContentPane().add(bExit);
 		bExit.setBounds(270, 70, 100, 20);

 	}
 	
 	public static void chatroomWindowAction(){
 		bSendChat.addActionListener(
 				new java.awt.event.ActionListener(){
 					public void actionPerformed(java.awt.event.ActionEvent evt){
 						actionBSendChat();
 					}
 		});
 		
 		bExitChat.addActionListener(
 				new java.awt.event.ActionListener(){
 					public void actionPerformed(java.awt.event.ActionEvent evt){
 						try {
							actionBExitChat();
						} catch (IOException e) {
							e.printStackTrace();
						}
 					}
 		});
 	}
 	public static void chatroomWindowAction2(){
 		bSendChat2.addActionListener(
 				new java.awt.event.ActionListener(){
 					public void actionPerformed(java.awt.event.ActionEvent evt){
 						actionBSendChat2();
 					}
 		});
 		
 		bExitChat2.addActionListener(
 				new java.awt.event.ActionListener(){
 					public void actionPerformed(java.awt.event.ActionEvent evt){
 						try {
							actionBExitChat2();
						} catch (IOException e) {
							e.printStackTrace();
						}
 					}
 		});
 	}

 	public static void actionBSendChat(){
 		String message = tMessage.getText();
 		ChatroomClient.send(tChatroom.getText(),message);
 		tMessage.setText("");
 		tMessage.requestFocus();
 	}
 	public static void actionBSendChat2(){
 		String message = tMessage2.getText();
 		ChatroomClient.send(tChatroom2.getText(),message);
 		tMessage2.setText("");
 		tMessage2.requestFocus();
 	}

 	public static void actionBExitChat() throws IOException{
 		ChatroomClient.disconnect(tChatroom.getText());
 	}
 	public static void actionBExitChat2() throws IOException{
 		ChatroomClient.disconnect(tChatroom2.getText());
 	}

 	public static void mainWindowAction(){
 		bCreateChatroom.addActionListener(
 				new java.awt.event.ActionListener(){
 					public void actionPerformed(java.awt.event.ActionEvent evt){
 						actionBCreateChatroom();
 					}
 		});
 		
 		bJoinChatroom.addActionListener(
 				new java.awt.event.ActionListener(){
 					public void actionPerformed(java.awt.event.ActionEvent evt){
 						actionBJoinChatroom();
 					}
 		});

 		bExitMain.addActionListener(
 				new java.awt.event.ActionListener(){
 					public void actionPerformed(java.awt.event.ActionEvent evt){
 						try {
							actionBExitMain();
						} catch (IOException e) {
							e.printStackTrace();
						}
 					}
 		}); 		
 	}
 	
 	public static void logInWindowAction(){
 		bLogin.addActionListener(
 				new java.awt.event.ActionListener(){
 					public void actionPerformed(java.awt.event.ActionEvent evt){
 						actionBLogin();
 					}
 		});
 		
 		bSignup.addActionListener(
 				new java.awt.event.ActionListener(){
 					public void actionPerformed(java.awt.event.ActionEvent evt){
 						actionBSignup();
 					}
 		});
 		
 		bExit.addActionListener(
 				new java.awt.event.ActionListener(){
 					public void actionPerformed(java.awt.event.ActionEvent evt){
 					System.exit(0);
 					}
 		});

 	}
 	
 	public static void actionBExitMain() throws IOException{
 		if(!chatroomFlag[0].equals("0") || !chatroomFlag[1].equals("0")){
 	 		ChatroomClient.close();
 		}
 		System.exit(0);
 	}

 	public static void actionBSignup(){
		username = tUsername.getText();
		String password = tPassword.getText();
		Hashtable<String, String> userList = new  Hashtable<String, String>(); 
		try{
			FileInputStream fileIn = new FileInputStream("userList.db");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			userList = (Hashtable) in.readObject();
		}catch( Exception x){
			System.out.println(x);
		}
		
 		if(username.equals("") || password.equals("")){
 			JOptionPane.showMessageDialog(null, "Please enter username or passsword!");
 		}
 		else if(userList.containsKey(username)){
 			JOptionPane.showMessageDialog(null, "Username already exists!");
 		}
 		else{
 			mainWindow.setTitle(username + "'s chat box");
 			logInWindow.setVisible(false);
 			connect("!#sign#!"+username+","+password);
 		}
 	}
 	
 	public static void actionBLogin(){
		username = tUsername.getText();
		String password = tPassword.getText();
		Hashtable<String, String> userList = new  Hashtable<String, String>(); 
		try{
			FileInputStream fileIn = new FileInputStream("userList.db");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			userList = (Hashtable) in.readObject();
		}catch( Exception x){
			System.out.println(x);
		}

 		if(username.equals("") || password.equals("")){
 			JOptionPane.showMessageDialog(null, "Please enter username or passsword!");
 		}
 		else if(!userList.containsKey(username)){
 			JOptionPane.showMessageDialog(null, "Username doesn't exists!");
 		}
 		else if(!userList.get(username).equals(password)){
 			JOptionPane.showMessageDialog(null, "Wrong Password!");
 		}
 		else{
 			mainWindow.setTitle(username + "'s chat box");
 			logInWindow.setVisible(false);
 			connect("!#logi#!"+username);
 		}
 	}
 	
 	
 	public static void actionBJoinChatroom(){
 		String chatroomName = tCreateChatroom.getText().trim();
 		System.out.println(chatroomName);
 		Hashtable<String,ArrayList<String>> currentChatRooms = new Hashtable<String,ArrayList<String>>(); // store the users in each chatroom.
		try{
			FileInputStream fileIn = new FileInputStream("chatroomList.db");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			currentChatRooms = (Hashtable)in.readObject();
		}catch( Exception x){
			System.out.println(x);
		}

 		if(chatroomName.equals("")){
 			JOptionPane.showMessageDialog(null, "Please enter chatroom name!");
 		}
 		else if(!currentChatRooms.containsKey(chatroomName)){
 			JOptionPane.showMessageDialog(null, "This chatroom doesn't exist! ");
 		}
 		else if(currentChatRooms.get(chatroomName).contains(username)){
 			JOptionPane.showMessageDialog(null, "You are already in this room!");
 		}
 		else if(!chatroomFlag[0].equals("0") && !chatroomFlag[1].equals("0")){
 			JOptionPane.showMessageDialog(null, "You have already entered two chatrooms!");
 		}
 		else if(!currentChatRooms.get(chatroomName).contains(username)){ 			
 			ChatroomClient.newChatroom("!#join#!"+chatroomName+","+username, chatroomName);
 		}
 		
 		tCreateChatroom.setText("");
 	}

 	public static void actionBCreateChatroom(){
 		String chatroomName = tCreateChatroom.getText().trim();
 		Hashtable<String,ArrayList<String>> currentChatRooms = new Hashtable<String,ArrayList<String>>(); // store the users in each chatroom.
		try{
			FileInputStream fileIn = new FileInputStream("chatroomList.db");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			currentChatRooms = (Hashtable)in.readObject();
		}catch( Exception x){
			System.out.println(x);
		}

 		if(chatroomName.equals("")){
 			JOptionPane.showMessageDialog(null, "Please enter chatroom name!");
 		}
 		else if(currentChatRooms.containsKey(chatroomName)){
 			JOptionPane.showMessageDialog(null, "This chatroom already exists! ");
 		}
 		else if(!chatroomFlag[0].equals("0") && !chatroomFlag[1].equals("0")){
 			JOptionPane.showMessageDialog(null, "You have already entered two chatrooms!");
 		}
 		else{ 			
 			ChatroomClient.newChatroom("!#crit#!"+chatroomName+","+username,chatroomName);
 		}
 		tCreateChatroom.setText("");
 	}

 	public static void connect(String message){
 		try{
 			final int port = 4000;
 			final String host  = "localhost";
 			Socket soc = new Socket(host,port);
 			System.out.println("You connected to: "+host);
 			
 			chatClient = new ChatroomClient(soc, username);
 			
 			PrintWriter out = new PrintWriter(soc.getOutputStream());
 			out.println(message);
 			out.flush();
 			
 			buildMainWindow();
 			chatroomFlag[0] = "0";
 			chatroomFlag[1] = "0";
 			Thread x = new Thread(chatClient);
 			x.start();
 		}
 		catch(Exception x){
 			System.out.println(x);
 			JOptionPane.showMessageDialog(null, "Server not responding!");
 			System.exit(0);
 		}
 	}
 	
 	public static void closeChatRoom(int flag){
 		if(flag==0){
 			chatWindow.setVisible(false);
 			tConversation.setText("");
 		}
 		else{
 			chatWindow2.setVisible(false);
 			tConversation2.setText("");
 		}
 	}

}

