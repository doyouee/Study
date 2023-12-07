package com.socket.ecoserver.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static void main(String[] args) throws IOException {
		final int port = 7777;
		ServerSocket server = new ServerSocket(port);
		Socket sock = null;
		System.out.println("EchoServer Started..");
		sock = server.accept();
		InetAddress ia = sock.getInetAddress();
		System.out.println(ia.getHostAddress()+"님이 접속..");
		
		// Client에 메시지를 보내기 위한 스트림 연결
		PrintWriter pout = new PrintWriter(sock.getOutputStream(),true);
		pout.println("안녕하세요. 클라이언트님");
		
		// 1) Client가 보내오는 메시지를 들을 입력 스트림 얻기
		BufferedReader bf = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		
		// 2) 반복문 돌면서 Client가 보내오는 메시지를 듣고 이 메시지를 분석(파싱)한다.
		//		i) 안녕, 하이 ===> "반가워요, Client 님"의 답변
		//		ii) 오늘 날짜 ===> "2023-12-25"
		// 		iii) 그 외에는 ===> "나가세요"
		String cMsg="";
		while((cMsg=bf.readLine())!=null) {
			if(cMsg.equals("안녕")||cMsg.equals("하이")){
				pout.println("안녕하세요. [Client] 님.");
			}else if(cMsg.contains("날짜")) {
				pout.println("2023-12-25");
			}else if(cMsg.contains("날씨")) {
				pout.println("cold.");
			}else {
				pout.println("종료합니다.");
			}
		}
		bf.close();
		pout.close();
		sock.close();
		server.close();
	}
}
