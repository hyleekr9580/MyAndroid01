package kr.contentsstudio.myfirstandroidapp.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


public class MyIntentService extends IntentService {
    private static final String TAG = MyIntentService.class.getSimpleName();
    private int count = 0;


    public MyIntentService() {
        super("intentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
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
}
