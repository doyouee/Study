package com.socket.test.listener;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class EchoServerEx {
	ServerSocket server; //서버 소켓
	Socket child; //클라이언트와 통신하기 위한 소켓
	InputStream is; //클라이언트와 연결된 입력 스트림 저장
	ObjectInputStream ois; //클라이언트로부터 데이터를 전송받기 위한 스트림
	OutputStream os; //클라이언트와 연결된 출력 스트림 저장
	ObjectOutputStream oos;//클라이언트에게 데이터를 전송하기 위한 스트림
	String receiveData; //클라이언트로부터 전송받은 데이터를 저장하기 위한 변수
	List<String> inputData = new ArrayList<String>();
	public EchoServerEx(int port) { //생성자는 오픈할 포트 번호를 전달 받음
		try {
			// 1. 에코 서버 프로그램은 포트를 지정해서 서버 소켓 생성부터 한다.
			server = new ServerSocket(port);
			System.out.println("**** 에코 서버*****");
			System.out.println("Client Socket의 접속 요청을 기다리고 있음");
			
			// 2. 클라이언트의 접속을 항상 받아들일 수 있음 - 클라이언트의 요청이 없으면 대기 상태에 들어감 - 클라이언트가 접속하는 순간 클라이언트와 통신할 수 있는 소켓을 반환함
			child = server.accept();
			
			// 3. 접속이 되면 클라이언트로부터 아이피 주소를 얻어 출력함
			System.out.println(child.getInetAddress() + "로부터 연결 요청 받음");
			System.out.println("***********************");
			
			// 4. 클라이언트로 부터 보내진 데이터를 읽기 위해서 클라이언트로부터 입력 스트림을 얻어옴
			is = child.getInputStream();

			// 4-1. 입력 스트림을 ObjectInputStream으로 변환한다.
			ois = new ObjectInputStream(is);
			
			// 5. 에코 서버이므로 클라이언트로부터 받은 메시지를 다시 보내기 위해서 출력 스트림 생성
			os = child.getOutputStream();
			
			// 5_1. 출력 스트림을 ObjectOutputStream으로 변환한다.
			oos = new ObjectOutputStream(os);

			// 4-2. 스트림을 통해 데이터를 읽어옴
			while((receiveData = (String)ois.readObject()) != null) {
				StringBuilder res = new StringBuilder();
				System.out.println("Client로부터 받은 Data : " + receiveData);
				extractData(receiveData); // 데이터 처리 메서드
				for(int i=0; i<inputData.size(); i++) {
					if(inputData.get(i).substring(4,6).equals("01")) {
						res.append("[00042000]");
					} else {
						res.append("");
//						oos.writeObject("");
					}
				}
				oos.writeObject(res.toString());
				oos.flush();
				inputData.clear();
			}
		} catch(Exception e) { //예외가 발생하면
			e.printStackTrace(); //에러 메시지를 출력하고
			System.exit(0); //프로그램을 종료한다.
		} finally {
			try {
				is.close();
				ois.close();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void extractData(String receiveData) throws IOException { // 데이터 정제
		int count = 0;
		int byteLength = Integer.parseInt(receiveData.substring(0, 4)); // Data 맨 앞의 숫자 4자리는 Data Byte 크기
		while(receiveData.getBytes().length >= byteLength) {  // receiveData의 Byte 길이가 byteLength 길이보다 긴 동안
			StringBuilder stringbuilder = new StringBuilder(byteLength);
			for(char ch : receiveData.substring(4).toCharArray()) {
				count += String.valueOf(ch).getBytes().length;
				stringbuilder.append(ch); // sringbuilder 에는 실제 데이터만 담긴다. ex)밤1b
				if(count >= byteLength) break;
			}

			byte[] remainingBytes = new byte[receiveData.getBytes().length - byteLength - 4]; // 남은 데이터를 담을 바이트 배열 생성 // 첫 번째 데이터 제거
			System.arraycopy(receiveData.getBytes(), byteLength + 4, remainingBytes, 0, remainingBytes.length); // 첫 번째 데이터를 제외한 데이터를 새로운 배열에 복사
			receiveData = new String(remainingBytes); // 바이트 배열을 문자열로 변환
			count = 0;
			inputData.add(stringbuilder.toString());
			if(receiveData.length()<5) {	break;	}
			byteLength = Integer.parseInt(receiveData.substring(0, 4));
		}
		System.out.println("전체 inputData : " + inputData);
	}

	public static void main(String[] args) {
		new EchoServerEx(5000); // 포트 번호 5000을 오픈
	}
}

/*
 * 0038TEST01ABC한글SDFASDFGHDSUIFASD123123  -> 응답값 : 00042000
 * 0038TEST99ABC한글SDFASDFGHDSUIFASD123123
 *
 * 0038TEST01ABC한글SDFASDFGHDSUIFASD1231230038TEST01ABC한글SDFASDFGHDSUIFASD123123
 */