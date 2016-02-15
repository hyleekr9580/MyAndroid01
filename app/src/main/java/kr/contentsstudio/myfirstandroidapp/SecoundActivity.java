package kr.contentsstudio.myfirstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 호영 on 2016-02-05.
 */
public class SecoundActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mNameTextView;
    private TextView mAgeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.checkbox_secound);

        mNameTextView = (TextView) findViewById(R.id.name_text_view);
        mAgeTextView = (TextView) findViewById(R.id.age_text_view);
        findViewById(R.id.exit_button).setOnClickListener(this);


        //나를 호출한 Intent 를 얻는다. 어떤놈이 호출한겨
        Intent intent = getIntent();
        // if 사용하는 이유 / 누구한테 값을 받아서 뿌려지는데... 해당 클래스만 단독으로 띄워야 하는
        // 이유가 있다. 값이 없을 때 null 오류가 발생 한다.
        //습관적으로  null 체크를 해야 한다.
        if (intent != null) {
            //이름과 나이를 얻어서 셋팅을 한다.
            String name = intent.getStringExtra("name");
            String age = intent.getStringExtra("age");


            mNameTextView.setText(mNameTextView.getText().toString() + name);
            mAgeTextView.setText(mAgeTextView.getText().toString() + age);


        }

    }


    @Override
    public void onClick(View v) {
        // 현재 엑티비티 종료 한다. backkey 누른것과 동일하다.
        //      Toast.makeText(SecoundActivity.this, "종료합니다.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();
        intent.putExtra("result", mNameTextView.getText().toString() + ", " + mAgeTextView.getText().toString());

        setResult(RESULT_OK, intent);
        finish();


    }
}
