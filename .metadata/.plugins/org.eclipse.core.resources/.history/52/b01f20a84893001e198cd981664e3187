// https://velog.io/@hahahaa8642/채팅-프로그램-Socket-활용한-간단한-콘솔-채팅-프로그램
package com.socket.chat.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        Scanner sc = new Scanner(System.in);

        try {
            socket = new Socket("localhost", 5555); // 접속할 서버의 아이피 주소와 포트를 이용해서 클라이언트 소켓 생성

            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 메시지를 읽어올 입력 스트림 생성
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // 메시지를 내보낼 출력 스트림 생성

            System.out.println("** 채팅방에 접속했습니다. **");
            while (true) {
                System.out.print("메시지를 입력하세요 : ");
                String outMsg = sc.nextLine(); // 키보드 입력 값을 String에 담기

                if (outMsg.contains("종료")) {
                    out.write("** 연결이 종료되었습니다. **");
                    out.flush(); // 스트림을 닫지 않은 상태에서 바이트를 보내고 싶은 경우, 스트림을 플러쉬
                    System.out.println("** 연결이 종료되었습니다. **");
                    break;
                }

                out.write(outMsg + "\n");
                out.flush(); // 스트림을 닫지 않은 상태에서 바이트를 보내고 싶은 경우, 스트림을 플러쉬

                String inMsg = in.readLine();
                System.out.println("상대방 : " + inMsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sc.close();
                out.close();
                in.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}