package rus.google.clientserver;

import java.io.*;
import java.net.Socket;

public class Client {
	static BufferedReader in;
	static BufferedWriter out;
	static BufferedReader reader;
	static Socket clientsocket;
	
	public static void main(String[] args) {
		try {
			try {
			
			clientsocket = new Socket("localhost", 4004);
			out = new BufferedWriter(new OutputStreamWriter(clientsocket.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
			reader = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Может, вы что-то хотите сказать серверу?");
			String word = reader.readLine();
			out.write(word + "\n");
			out.flush();
			String fromserverword = in.readLine();
			System.out.println("От сервера принятно сообщение - " + fromserverword);
			
			}finally {
				out.close();
				in.close();
				reader.close();
				clientsocket.close();
			}
		}catch(IOException e) {
				System.err.println(e);
			}
	}
}
