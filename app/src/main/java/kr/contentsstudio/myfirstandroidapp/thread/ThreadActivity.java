package kr.contentsstudio.myfirstandroidapp.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kr.contentsstudio.myfirstandroidapp.R;

public class ThreadActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mCountTextView;
    private int mCount = 0;
    private Button mButton;

    public static final String TAG = ThreadActivity.class.getSimpleName();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // 메세지를 처리하는 부분 입니다.
            // 핸들러에서 메인으로~ 어떠한 데이터를 보내고 싶을때~
            Log.d(TAG, "" + msg.what);
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //여기는 메인스레드 이면서 UI 스레드 라고도 한다.
        //ui를 그릴 수 있는 스레드 이다.
        setContentView(R.layout.activity_thread);

        mCountTextView = (TextView) findViewById(R.id.count_text);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);


        // 오래 걸리는 처리는 therad에 집어 넣는다.
        // 별도 처리를 해야 한다. 작업스레드 (worker therad)
        // 백그라운드 스레드라고도 한다.
        // ui를 그릴수 없다.
        Thread thread = new Thread() {
            @Override
            public void run() {
                // 메인에서 시간을 너무 잡아 먹는다. 스레드 안에 집어 넣는다.
                for (int i = 0; i < 4; i++) {

                    //스레드에서는 전역변수를 많이 사용하지 않는다. 지역변수로 많이 사용을 한다.
                    mCount++;
                    // 그리는 부분을 할 수 없다.
                    //mCountTextView.setText("" + mCount);


                    // UI갱신하는 부분 입니다~ 알아서 돌고 있다~~~~
                    // 메인 스레드는 멈춘다. 뒤에서 백그라운드 작업을 하고 있다~~
                    // UI 스레드에서 그리여 하는 부분이다. 일반적으로 그릴 수 없기 때문에
                    // 핸들러를 이용하여 그릴 수 있다.
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            // 여기서 UI를 갱신 할 수 있다.
                            mCountTextView.setText("" + mCount);
                        }
                    });
                    //메세지를 담아서 보내는 방법
                    Message message = new Message();
                    mHandler.sendMessage(message);

                    //empty로 보내는 방법 딜레이 준다
                    //2초뒤에 what값을 보내느 방법
                    // 10초동안 터치해서 몇번터리했는지 게임같은거에 딜레이를 사용하면 된다.
                    // 아무메세지도 보내지 않을 것인데 핸들러 처리를 해야 할때...sendEmptyMessage 를 사용한다.
                    //
                    mHandler.sendEmptyMessageDelayed(20, 2000);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();

        // 스레드의 새로운 방벙
        // 일반적으로 아래와 같이 많이 사용을 한다.
        // 급하게 스레드 추가를 할때 사용을 한다.
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread.start();

    }


    @Override
    public void onClick(View v) {

    }


}
