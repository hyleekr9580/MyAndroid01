package kr.contentsstudio.myfirstandroidapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class IntentActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mCallBtn;
    private Button mWebbtn;
    private Button mActionCallBtn;
    private Button mCameraBtn;
    private Button mStilBtn;
    private Button mPicBtn;


    public static final int MEDIA_TYPE_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        mCallBtn = (Button) findViewById(R.id.call_btn);
        mWebbtn = (Button) findViewById(R.id.web_btn);
        mActionCallBtn = (Button) findViewById(R.id.actin_call_btn);
        mCameraBtn = (Button) findViewById(R.id.camera_btn);
        mStilBtn = (Button) findViewById(R.id.stil_btn);
        mPicBtn = (Button) findViewById(R.id.pic_btn);

        mCallBtn.setOnClickListener(this);
        mWebbtn.setOnClickListener(this);
        mActionCallBtn.setOnClickListener(this);
        mCameraBtn.setOnClickListener(this);
        mStilBtn.setOnClickListener(this);
        mPicBtn.setOnClickListener(this);

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
            // 직접걸기
            case R.id.actin_call_btn:
                dialPhoneNumberAction("114");
                break;
            //웹페이지열기
            case R.id.web_btn:
                openWebPage("http://www.naver.com");
                break;
            //카메라오픈
            case R.id.camera_btn:
                capturePhoto();
                break;
            case R.id.stil_btn:
                capturePhotoStil();
                break;
            case R.id.pic_btn:
                pic();
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

    public void capturePhoto() {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_VIDEO_CAMERA);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public void capturePhotoStil() {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void pic() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}