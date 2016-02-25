package kr.contentsstudio.myfirstandroidapp.com.exercise;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import kr.contentsstudio.myfirstandroidapp.R;


public class ExerciseSecond157 extends Activity implements View.OnClickListener {

    Button btn1, btn2, btn3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_second152);

        btn1 = (Button) findViewById(R.id.one_btn);
        btn2 = (Button) findViewById(R.id.two_btn);
        btn3 = (Button) findViewById(R.id.three_btn);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one_btn:
                Toast.makeText(ExerciseSecond157.this, btn1.getText(), Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.two_btn:
                Toast.makeText(ExerciseSecond157.this, btn2.getText(), Toast.LENGTH_SHORT).show();
                finish();
                break;

            case R.id.three_btn:
                Toast.makeText(ExerciseSecond157.this, btn3.getText(), Toast.LENGTH_SHORT).show();
                finish();
                break;


        }

    }


}
