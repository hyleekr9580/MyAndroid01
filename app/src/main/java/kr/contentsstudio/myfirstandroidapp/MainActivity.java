package kr.contentsstudio.myfirstandroidapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {


    public static final String TAG = MainActivity.class.getSimpleName();
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //화면에 레이아웃 표시
        setContentView(R.layout.checkbox);

        mTextView = (TextView) findViewById(R.id.msg_text_view);
        ((CheckBox) findViewById(R.id.check_box)).setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Toast.makeText(MainActivity.this, "CHECK" + isChecked, Toast.LENGTH_SHORT).show();

        //텍스트 뷰의 글자를 변경 할거다.
        if (isChecked) {
            mTextView.setText("체크됨");
        } else {
            mTextView.setText("");
        }
    }
}

