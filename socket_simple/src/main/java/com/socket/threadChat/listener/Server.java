package com.socket.threadChat.listener;

import java.lang.Thread;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args){
        try {
            ServerSocket server = new ServerSocket(4885);    //서버의 소켓을 5959번 포트로 생성

            while(true) {
                Socket socket = server.accept();    //클라이언트 연결 대기
                System.out.println("클라이언트가 접속하였습니다.");
                Input input = new Input(socket);    //Input 쓰레드 생성
                input.start();
                Output output = new Output(socket);            //Output 쓰레드 생성
                output.start();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Input extends Thread {
    BufferedReader in;
    Socket socket;
    String data=null;
    Input(Socket socket){
        this.socket=socket;
    }
    public void run() {
        while(true) {
            try {
                //소켓으로 데이터 받아오기
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                data = in.readLine();
                if(data == null || data.equals("exit")) {
                    break;
                }else {
                    System.out.println("Client : "+data);    //받아온 데이터 출력
                }
            } catch (IOException e) {
                break;
            }
        }
        System.out.println("클라이언트와 연결 종료 Input 쓰레드 종료");
        try {
            in.close();
            socket.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Output extends Thread{
    BufferedReader in;
    PrintWriter out;
    Socket socket;
    Output(Socket socket){
        this.socket=socket;
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
        } catch (IOException e) {
            System.out.println("aa");
            Output.interrupted();
        }
    }
    public void run() {
        String data;
        while(true) {
            try {
                //키보드로부터 입력
                data = in.readLine();
                out.println(data);                        //서버로 데이터 전송
                out.flush();
                if(data.equals("exit") || socket.isClosed()){
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("클라이언트와 연결 종료 Output 쓰레드 종료");
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}