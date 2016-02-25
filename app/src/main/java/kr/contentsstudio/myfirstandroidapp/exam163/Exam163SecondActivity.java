package kr.contentsstudio.myfirstandroidapp.exam163;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import kr.contentsstudio.myfirstandroidapp.R;

public class Exam163SecondActivity extends AppCompatActivity {


    private TextView mViewTextId, mViewTextPw, mViewTextIdEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam163_second);

        mViewTextId = (TextView) findViewById(R.id.viewtextid);
        mViewTextId = (TextView) findViewById(R.id.viewtextpw);
        mViewTextId = (TextView) findViewById(R.id.viewtextemail);

        //나를 호출한 Intent 를 얻는다. 어떤놈이 호출한겨
        Intent intent = getIntent();
        // if 사용하는 이유 / 누구한테 값을 받아서 뿌려지는데... 해당 클래스만 단독으로 띄워야 하는
        // 이유가 있다. 값이 없을 때 null 오류가 발생 한다.
        //습관적으로  null 체크를 해야 한다.
        if (intent == null) {
            //이름과 나이를 얻어서 셋팅을 한다.
            String id = intent.getStringExtra("id");
            String pw = intent.getStringExtra("pw");
            String email = intent.getStringExtra("email");


            mViewTextId.setText(mViewTextId.getText().toString() + id);
            mViewTextPw.setText(mViewTextPw.getText().toString() + pw);
            mViewTextIdEmail.setText(mViewTextIdEmail.getText().toString() + email);

        }
    }
}
