package kr.contentsstudio.myfirstandroidapp.chat.client;

import android.util.Log;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {
    private final static String SERVER_HOST = "suwonsmartapp.iptime.org";
    private final static int SERVER_PORT = 5000;
    public final static String NICKNAME = "이호영";

    private Socket mSocket;
    private String mName;


    //서버쪽으로 쏘는 아이~~~
    private DataOutputStream mOutputStream;


    public boolean connect() {
        try {
            // 소켓을 생성하고, 생성자에 넘겨준 IP 주소와 포트 번호를 이용해 바로 서버에 연결을 시도한다.
            mSocket = new Socket(SERVER_HOST, SERVER_PORT);
            //            clientWrite.start();
            //쓰는거는 시도중이다.
            ClientWrite clientWrite = new ClientWrite(NICKNAME);
            ClientRead clientRead = new ClientRead();
            // 서버에 연결을 시도한다.
            clientRead.start();


        } catch (UnknownHostException e) {
            // 서버의 IP 주소와 관련된 문제가 있을 때 발생한다.
            // 주로 IP 주소나 포트 번호를 잘못 입력하거나 서버가 실행되어 있지 않아서
            // 연결할 수 없는 상태일 때 발생한다.
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            // 소켓을 생성하는 중에 에러가 생겼을 때 발생한다.

        }

        //소켓이 null인지 체크를 한다.
        if (mSocket != null) {
            return true;
        }
        return false;
    }


    //외부에서 메세지를 던지면 쏘기만 하면된다.
    public void sendMessage(String msg) {
        long time = System.currentTimeMillis();
        MsgInfo msgInfo = new MsgInfo(mName, msg, time);

        Gson gson = new Gson();
//					String json = "{\"nickName\":\"" + nickName + "\",\"msg\":\"" + msg + "\",\"time\":\"" + time + "\"}";
        try {
            mOutputStream.writeUTF(gson.toJson(msgInfo));
//                    System.out.println(gson.toJson(msgInfo));
            mOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 접속여부 확인하여 열렸을 경우만 닫아 버립니다.
    public void close() {
        if (mSocket != null){
            try {
                mSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class ClientRead extends Thread {
        private DataInputStream mInputStream;

        @Override
        public void run() {
            try {
                mInputStream = new DataInputStream(mSocket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                // 계속 듣기만
                while (mInputStream != null) {
                    // json 파싱
                    String json = mInputStream.readUTF();
                    try {
                        MsgInfo msgInfo = new Gson().fromJson(json, MsgInfo.class);
                        Log.d("read", msgInfo.toString());

                        EventBus.getDefault().post(msgInfo);
                    } catch (Exception e) {
//                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 접속 종료시
                mSocket = null;
            }
        }
    }

    class ClientWrite extends Thread {

//        private DataOutputStream mOutputStream;

        public ClientWrite(String nickName) {
            try {
                mName = nickName;
                mOutputStream = new DataOutputStream(mSocket.getOutputStream());
                //채팅을 발송하는 thread 이다.
                mOutputStream.writeUTF(nickName);
                mOutputStream.flush();
                System.out.println("id : " + nickName + "접속 완료");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("writeUTF IOException");
            }
        }

        @Override
        public void run() {
            Scanner in = new Scanner(System.in);

            while (true) {
                System.out.print("메세지 입력 : ");
                // Json구성
                String msg = in.nextLine();
                long time = System.currentTimeMillis();
                MsgInfo msgInfo = new MsgInfo(mName, msg, time);

                Gson gson = new Gson();
//					String json = "{\"nickName\":\"" + nickName + "\",\"msg\":\"" + msg + "\",\"time\":\"" + time + "\"}";
                try {
                    mOutputStream.writeUTF(gson.toJson(msgInfo));
//                    System.out.println(gson.toJson(msgInfo));
                    mOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }


    }
}
