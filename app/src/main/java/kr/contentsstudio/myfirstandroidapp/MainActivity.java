package kr.contentsstudio.myfirstandroidapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 추가 사용 하는... 화면에 레이아웃을 표시한다.
        // R(리소스) : 안드로이드(현재프로젝트, 클래스파일을 제외하고...) 자원을 사용한다.
        setContentView(R.layout.button);

        //인터페이스의 메소드 생성 할 수 있다.
        //익명클래스이다.
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "클릭 매우 잘됨", Toast.LENGTH_SHORT).show();

            }
        };
        Button button2 = (Button) findViewById(R.id.button2);
        //setOnClickListener 클릭이벤트를 처리 할 수 있는 Listener를 추가
        // 클릭할 수 있는 객체를 던져 준다.
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "클릭 매우 잘됨", Toast.LENGTH_SHORT).show();
    }

//    // botton.xml 에 연결됨
//    public void onClick(View view) {
//        Log.d(TAG, "클릭 잘됨");
//        Toast.makeText(MainActivity.this, "클릭 잘됨", Toast.LENGTH_SHORT).show();
//
//
//    }
}
