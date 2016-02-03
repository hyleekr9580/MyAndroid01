package kr.contentsstudio.myfirstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

        // 추가 사용 하는... 화면에 레이아웃을 표시한다.
        // R(리소스) : 안드로이드(현재프로젝트, 클래스파일을 제외하고...) 자원을 사용한다.
        setContentView(R.layout.activity_main);
    }
}
