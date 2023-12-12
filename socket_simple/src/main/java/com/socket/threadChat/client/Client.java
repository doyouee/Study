// https://ninearies.tistory.com/207
package com.socket.threadChat.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in;
        PrintWriter out;
        String data=null;

        try {
            socket = new Socket("127.0.0.1", 4885);//아아디,포트
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

            //실시간으로 데이터를 주고 받기 위하여 쓰레드 사용
            Output output = new Output(socket);
            output.start();

            while(true) {
                try {
                    //소켓으로부터 데이터 받아오기
                    try {
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        data = in.readLine();
                    }catch(Exception e) {
                        break;
                    }
                    if(data == null || data.equals("exit")) {
                        socket.close();
                        output.interrupt();        //output쓰레드 종료
                        break;
                    }else if(data!=null) {
                        System.out.println("server : "+data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                socket.close();
            }catch (Exception e) {
            }
            System.out.println("서버와 연결 종료");

        }catch (Exception e) {
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
        System.out.println("서버와 연결 종료 Output 쓰레드 종료");
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}