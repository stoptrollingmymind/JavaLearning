package rus.google.clientserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
	static ServerSocket server;
	static Socket client;
	static BufferedReader in;
	static BufferedWriter out;
	
	public static void main(String[] args) {
		try {
			try {
				server = new ServerSocket(4004);
				System.out.println("Сервер создан!");
				client = server.accept();
				System.out.println("Да у нас подключение!");
				try {		
					in = new BufferedReader(new InputStreamReader(client.getInputStream()));
					out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
				
					String inputWord = in.readLine();
					out.write(inputWord);
					out.flush();
					}finally {
						client.close();
						in.close();
						out.close();
						}
				
				}finally{
					server.close();
					System.out.println("Сервер закрыт!");
				
			    } 
			}catch(IOException e){
				System.err.println(e);
		}
	}
}
			