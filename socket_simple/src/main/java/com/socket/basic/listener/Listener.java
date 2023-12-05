package com.socket.basic.listener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener {
	public static void main(String[] args) throws IOException {
		// client -> listener
		ServerSocket serversocket = new ServerSocket(5555); // 5555port 번호로 서버 소켓 만들기
		System.out.println("접 속 대 기");
		Socket socket = serversocket.accept(); // serversocket이 accept 할 때 발생
		DataInputStream sdin = new DataInputStream(socket.getInputStream());
		System.out.println(sdin.readUTF()); // Client에서 output한 Data가 찍혀나온다.
		
		// listener -> client
		DataOutputStream sdout = new DataOutputStream(socket.getOutputStream());
		sdout.writeUTF("Hello Client");
		
		serversocket.close();
	}
}
