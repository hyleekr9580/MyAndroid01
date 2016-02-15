package kr.contentsstudio.myfirstandroidapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class IntentActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mCallBtn;
    private Button mWebbtn;
    private Button mActionCallBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        mCallBtn = (Button) findViewById(R.id.call_btn);
        mWebbtn = (Button) findViewById(R.id.web_btn);
        mActionCallBtn = (Button) findViewById(R.id.actin_call_btn);

        mCallBtn.setOnClickListener(this);
        mWebbtn.setOnClickListener(this);
        mActionCallBtn.setOnClickListener(this);

        //아래와 같이 두줄로 사용이 가능합니다.
        //findViewById(R.id.call_btn).setOnClickListener(this);
        //findViewById(R.id.web_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.call_btn:
                // 전화걸기
                dialPhoneNumber("010-4759-9580");
                break;
            case R.id.actin_call_btn:
                dialPhoneNumberAction("114");
                break;
            case R.id.web_btn:
                openWebPage("http://www.naver.com");
                break;
        }

    }

    //개발자사이트에서 복사 붙여넣기
    public void dialPhoneNumber(String phoneNumber) {
// 아래와 같은 소스이다.
//        Intent intent2 = new Intent();
//        intent2.setAction(Intent.ACTION_DIAL);


        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));

        // 이 인텐트를 처리 할 수 있는 엑티비티가 있을때 수행을 한다. 없으면 안한다.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void dialPhoneNumberAction(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}