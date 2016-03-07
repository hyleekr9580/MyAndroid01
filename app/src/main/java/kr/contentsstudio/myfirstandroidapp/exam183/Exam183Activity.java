package kr.contentsstudio.myfirstandroidapp.exam183;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import kr.contentsstudio.myfirstandroidapp.R;

public class Exam183Activity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam183);


    }

    @Override
    public void onClick(View v) {
        //세컨드 엑티비티로 전환하겠다는 인텐트(intent)
        Intent intent = new Intent(this, Exam183SecondActivity.class);
        startActivity(intent);

    }
}
