package com.websocket.basic.BasicClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws IOException {
		// client -> listener
		Socket socket = new Socket("localhost", 5555); // 클라이언트 자기 주소 + 포트번호
		DataOutputStream cdout = new DataOutputStream(socket.getOutputStream()); // 데이터 내보내기
		cdout.writeUTF("Hello Server"); // 문자열을 UTF-8 형식으로 변형하여 출력
		
		// listener -> client
		DataInputStream cdin = new DataInputStream(socket.getInputStream()); // 데이터 읽어오기
		System.out.println(cdin.readUTF()); // server에서 보낸 data가 UTF-8 형태로 찍힘 
		
		socket.close();
	}
}
