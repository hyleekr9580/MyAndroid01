package kr.contentsstudio.myfirstandroidapp.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kr.contentsstudio.myfirstandroidapp.R;

public class FragmentExam212Activity extends AppCompatActivity implements View.OnClickListener {

    private int count;
    private TextView mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_exam212);

        Button buttona01 = (Button) findViewById(R.id.frag_button_a01);
        buttona01.setOnClickListener(this);
        Button buttona02 = (Button) findViewById(R.id.frag_button_a02);
        buttona02.setOnClickListener(this);
        Button buttona03 = (Button) findViewById(R.id.frag_button_a03);
        buttona03.setOnClickListener(this);

        Button buttonb01 = (Button) findViewById(R.id.frag_button_b01);
        buttonb01.setOnClickListener(this);
        Button buttonb02 = (Button) findViewById(R.id.frag_button_b02);
        buttonb02.setOnClickListener(this);
        Button buttonb03 = (Button) findViewById(R.id.frag_button_b03);
        buttonb03.setOnClickListener(this);
    }

//    @Override
//    public void onClick(View v) {
//
//        // 프래그먼트를 코드로 추가 한다.
//        ColorFragment fragment = ColorFragment.newInstance();
//        fragment.setOnColorDataReceiveListener(this);
//
//
//        switch (v.getId()) {
//            case R.id.frag_button_a01:
//                getSupportFragmentManager().beginTransaction().replace(R.id.count_a, fragment).commit();
//                break;
//            case R.id.frag_button_a02:
//                getSupportFragmentManager().beginTransaction().replace(R.id.count_a, fragment).commit();
//                break;
//            case R.id.frag_button_a03:
//                getSupportFragmentManager().beginTransaction().replace(R.id.count_a, fragment).commit();
//                break;
//            case R.id.frag_button_b01:
//                getSupportFragmentManager().beginTransaction().replace(R.id.count_b, fragment).commit();
//                break;
//            case R.id.frag_button_b02:
//                getSupportFragmentManager().beginTransaction().replace(R.id.count_b, fragment).commit();
//
//                break;
//            case R.id.frag_button_b03:
//                getSupportFragmentManager().beginTransaction().replace(R.id.count_b, fragment).commit();
//                break;
//
//        }
//    }

    @Override
    public void onClick(View v) {

    }
}

