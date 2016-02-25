package kr.contentsstudio.myfirstandroidapp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;

import kr.contentsstudio.myfirstandroidapp.R;

public class BroadcastActivity extends AppCompatActivity implements View.OnClickListener {


    //스트링 상수로 만든다 나만의 방송
    //나만의 액션
    public static final String ACTION_MY = "kr.contentsstudio.myfirstandroidapp.BATTERY_CHANGED";
    private MyReceiver mMyReceiver;
    private EditText mMessageEditText;

    //    카카오링크
    private KakaoLink kakaoLink;
    private KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        try {
            kakaoLink = KakaoLink.getKakaoLink(getApplicationContext());
            kakaoTalkLinkMessageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();
        } catch (KakaoParameterException e) {
            e.getMessage();
        }

        mMessageEditText = (EditText) findViewById(R.id.message);

        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.kakao_send_button).setOnClickListener(this);


//        카카오링크
        findViewById(R.id.kakao_link_button).setOnClickListener(this);
        try {
            kakaoLink = KakaoLink.getKakaoLink(getApplicationContext());
            kakaoTalkLinkMessageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();
        } catch (KakaoParameterException e) {
            e.getMessage();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        //리시버 등록하기~~~
        mMyReceiver = new MyReceiver();
        //인탠트 필터 만들기
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_MY);
        registerReceiver(mMyReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //여기서만 사용하고 리비서 해제한다.
        unregisterReceiver(mMyReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                // 버튼을 누르면 방송을 쏠꺼다~ 베터리 충전하냐~ 안하냐~
                Intent intent = new Intent(ACTION_MY);
                sendBroadcast(intent);
                break;

            //카카오톡에 메세지를 보내보자~
            case R.id.kakao_send_button:
                Intent kakaoIntent = new Intent(Intent.ACTION_SEND);
                kakaoIntent.setPackage("com.kakao.talk");
                kakaoIntent.setType("text/plain");
                kakaoIntent.putExtra(Intent.EXTRA_TEXT, mMessageEditText.getText().toString());
                if (kakaoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(kakaoIntent);
                } else {
                    Toast.makeText(BroadcastActivity.this, "카카오가 없어요", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.kakao_link_button:

                break;

        }

    }


    // 특정앱화면 실행하기
    // 특정 앱 설치 화면으로 이동
    private void install() {
        Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://naver.com"));
        startActivity(intent1);
    }

    // 이엑티비티에서만 동작하는 리시버
//메니페스트에서 작성을 안해도 됩니다.
//내부 클래스는 무조건 static으론ㄴ 잡아라~
    private static class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION_MY)) {
                Toast.makeText(context, "잘받았다. ", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
