package com.socket.ecoserver.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// https://chanqun.tistory.com/128
public class EchoClient {
	public static void main(String[] args) throws IOException {
		String ip="192.168.18.128"; // 접속을 요청 할 서버의 아이피 주소
		Socket sock = new Socket(ip,7777); // 접속을 요청 할 서버의 포트 번호
		System.out.println("서버와 연결 됨...");
		InputStream is = sock.getInputStream();
		
		// 1) Server가 보내오는 메시지를 듣는 스트림 얻기
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		String serverMsg = bf.readLine();
		System.out.println("From EchoServer >> "+serverMsg);
		
		// 2) 키보드와 노드 연결한 스트림 생성
		BufferedReader key = new BufferedReader(new InputStreamReader(System.in)); // 키보드로부터 메시지를 읽어 올 입력 스트림
		
		// 3) 서버에 메시지를 보내기 위한 출력 스트림 얻기
		PrintWriter out = new PrintWriter(sock.getOutputStream(),true);
		
		// 키보드 입력받고 Server로 보내기
		String myMsg="";
		while((myMsg=key.readLine())!=null) {
			// Server에 메시지 보내기
			out.println(myMsg);
			// Server가 보내오는 메아리 듣기
			serverMsg=bf.readLine();
			System.out.println("From Server >> "+serverMsg);
		}
		bf.close();
		is.close();
		key.close();
		out.close();
		sock.close();
	}
}