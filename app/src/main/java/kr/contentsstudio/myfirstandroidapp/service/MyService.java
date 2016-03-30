package kr.contentsstudio.myfirstandroidapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

//서비스를 상속하고 있고
public class MyService extends Service {

    private static final String TAG = MyService.class.getSimpleName();
    //1ㅊ에 한번씩 변수값을 주고 살아 있는지 확인
    private int count = 0;


    public MyService() {
    }

    //  재정의 해주면 된다.
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //살아 있는지 확인하는 로그

        //오래걸리는 처리는 thread로 처리해야 합니다
        new Thread(new Runnable() {
            @Override
            public void run() {

                //1초에 한번씩 무한루프로 확인~
                while (true) {
                    Log.d(TAG, "count : " + count);
                    count++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (count == 20000) {
                        break;
                    }
                }
            }
        }).start();

        //할일....보내는쪽에 intent가  넘어 온다~ flags는
        //return을 받을 수 있는 값들이 상수로 받을 수 있가.
        //onStartCommand 종료가 되면 어떻게 처리를 할것인디..
        //START_STICKY, START_STICKY_COMPATIBIL....등....
        //START_STICKY일반적으로 많이 사용을 하며 죽은 다음에 다시 살아나는키이다.
        //시스템에 의해 죽었을 때 다시 살리겠다.
//        return START_STICKY;

        //START_STICKY와 똑같으나 죽은다음에 intent를 재활용 한다.
        return START_REDELIVER_INTENT;
//        return super.onStartCommand(intent, flags, startId);
    }

    // Binder given to clients
    //Binder 서비스
    private final IBinder mBinder = new LocalBinder();

    //내부클래스로 LocalBinder 하나 만든다.
    public class LocalBinder extends Binder {
        MyService getService() {
            // Return this instance of LocalService so clients can call public methods
            return MyService.this;
        }
    }


    //바인딩 할때 사용을 한다.
    //IBinder 로 리턴을 하고 있다. 주거니 받거니 하는 인터페이스
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }

    public int getNumber() {
        return 100;
    }
}
