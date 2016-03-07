package kr.contentsstudio.myfirstandroidapp.exam183;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import kr.contentsstudio.myfirstandroidapp.R;

public class Exam183SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam183_second);
    }

    public void onClick(View view) {

        switch (view.getId()){
            case R.id.text01:
                finish();
                break;
            case  R.id.text02:
                break;
        }
    }
}
