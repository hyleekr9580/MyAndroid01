package kr.contentsstudio.myfirstandroidapp.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import kr.contentsstudio.myfirstandroidapp.R;

public class ServiceMainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mStartButton;
    private Button mBoundButton;
    private Button mIntentButton;
    private Button mCallButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_main);

        findViewById(R.id.start_service_btn).setOnClickListener(this);
        findViewById(R.id.bound_service_btn).setOnClickListener(this);
        findViewById(R.id.intent_service_btn).setOnClickListener(this);
        findViewById(R.id.call_service_btn).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.start_service_btn:
                Intent intent = new Intent("kr.contentsstudio.myAction");
                intent.setClass(this, MyService.class);

                startService(intent);
//            startService(new Intent(this, MyService.class));
                break;

            case R.id.intent_service_btn:
                startService(new Intent(this, MyIntentService.class));
                break;
            case R.id.bound_service_btn:
                bindService(new Intent(this, MyService.class), mConnection, Context.BIND_AUTO_CREATE);
                break;

            case R.id.call_service_btn:
                if (mBound) {
                    Toast.makeText(ServiceMainActivity.this, "mService.getNumber() : " + mService.getNumber(),
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    private MyService mService;
    private boolean mBound;
    /**
     * Defines callbacks for service binding, passed to bindService()
     * http://developer.android.com/intl/ko/guide/components/bound-services.html
     */
    private ServiceConnection mConnection = new ServiceConnection() {

        //서비스콜백
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MyService.LocalBinder binder = (MyService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
            Log.d("ServiceConnection", "onServiceConnected");
        }

        //서비스종료
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
            Log.d("ServiceConnection", "onServiceDisconnected");

        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }


}
