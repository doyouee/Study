package com.socket.chat.listener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        Scanner sc = new Scanner(System.in);

        try {
            serverSocket = new ServerSocket(5555); // 지정한 포트번호로 socket-server 열어둔다.
            System.out.println("** 연결 대기 중입니다. **");

            socket = serverSocket.accept(); // 연결 요청이 들어오면 수락하고 새 Socket 객체를 반환한다.
            System.out.println("** 연결이 완료되었습니다. **");

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while (true) {
                String inMsg = in.readLine();
                if (inMsg.contains("종료")) {
                    System.out.println("상대방 : " + inMsg);
                    System.out.println("** 연결이 종료되었습니다. **");
                    break;
                }

                System.out.println("상대방 : " + inMsg);
                System.out.print("메시지를 입력하세요 : ");

                String outMsg = sc.nextLine();
                out.write(outMsg + "\n");
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sc.close();
                out.close();
                in.close();
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}