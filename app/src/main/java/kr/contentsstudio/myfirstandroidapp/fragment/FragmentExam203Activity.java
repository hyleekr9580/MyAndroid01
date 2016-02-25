package kr.contentsstudio.myfirstandroidapp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import kr.contentsstudio.myfirstandroidapp.R;

public class FragmentExam203Activity extends AppCompatActivity implements View.OnClickListener, ColorFragment.ColorDataReceiveListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_exam203);

        Button button01 = (Button) findViewById(R.id.add_frag_button01);
        button01.setOnClickListener(this);
        Button button02 = (Button) findViewById(R.id.add_frag_button02);
        button02.setOnClickListener(this);
        Button button03 = (Button) findViewById(R.id.add_frag_button03);
        button03.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int randomColor = Color.rgb((int)
                (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));

        // 프래그먼트를 코드로 추가 한다.
        ColorFragment fragment = ColorFragment.newInstance(randomColor);
        fragment.setOnColorDataReceiveListener(this);

        switch (v.getId()) {
            case R.id.add_frag_button01:
                getSupportFragmentManager().beginTransaction().replace(R.id.contents01, fragment).commit();
                break;
            case R.id.add_frag_button02:
                getSupportFragmentManager().beginTransaction().replace(R.id.contents02, fragment).commit();
                break;
            case R.id.add_frag_button03:
                getSupportFragmentManager().beginTransaction().replace(R.id.contents03, fragment).commit();
                break;

        }
    }

    @Override
    public void onDataReceive(String data) {

    }
}

