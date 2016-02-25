package kr.contentsstudio.myfirstandroidapp.com.lifecycle;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import kr.contentsstudio.myfirstandroidapp.com.listview.ListViewActivity;

public class LifeCycleActivity extends AppCompatActivity {

    private static final String TAG = LifeCycleActivity.class.getSimpleName();
    private String mvalue = "값 없음";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 레이아웃  필요 없다.
        //setContentView(R.layout.activity_life_cycle2);

        Button button = new Button(this);
        button.setText("버튼");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //현재 무명클래스라서... this만 쓸수 없다. LifeCycleActivity.this 명시해야 한다.
                //신경스기 싫으면 무조건 쓰는 걸로 공부해라.
                startActivity(new Intent(LifeCycleActivity.this, ListViewActivity.class));
                mvalue = "mvalue <=========================== 값바뀜@@@@@@@@@@";
            }
        });

        setContentView(button);
        //생성...
        Log.d(TAG, "onCreate : <=========================== onCreate : 생성");

        mvalue = "mvalue <=========================== 값 있음!!!!!!!!!!";

        if (savedInstanceState != null) {
            mvalue = savedInstanceState.getString("value");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume : <=========================== onResume : 재개");
        Log.d(TAG, "값 : <=========================== 값 : " + mvalue);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause : <=========================== onPause : 일시정지");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart : <=========================== onStart : 시작");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart : <=========================== onRestart : 재시작");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop : <=========================== onStop : 정지");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy : <=========================== onDestroy :소멸");

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged : <=========================== onConfigurationChanged");

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Bundle 아무거나 넣을 수 있다.
        // 호출되는 시점
        Log.d(TAG, "onSaveInstanceState : <=========================== onSaveInstanceState");

        // 강제종료 시 보존해야 하는 값을 outState 에 저장
        outState.putString("value", mvalue);
    }


}
