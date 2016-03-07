package kr.contentsstudio.myfirstandroidapp.exam163;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import kr.contentsstudio.myfirstandroidapp.R;

public class Exam163Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditTextId, mEditTextPw, mEditTextPw2, mEditTextIdEmail;
    private Button mButtom1, mButton2, mRadio01, mRadio02;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam163);

        //초기화버튼
        mButtom1 = (Button) findViewById(R.id.btn_01);
        mButtom1.setOnClickListener(this);
        //가입버튼
        mButton2 = (Button) findViewById(R.id.btn_02);
        mButton2.setOnClickListener(this);
        //라디오버튼(그룹)
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mRadioGroup.setOnClickListener(this);

        mEditTextId = (EditText) findViewById(R.id.id_edit);
        mEditTextPw = (EditText) findViewById(R.id.pw_edit);
        mEditTextPw2 = (EditText) findViewById(R.id.pw2_edit);
        mEditTextIdEmail = (EditText) findViewById(R.id.email_edit);
        mRadio01 = (RadioButton) findViewById(R.id.radio_01);
        mRadio02 = (RadioButton) findViewById(R.id.radio_02);
    }

    @Override
    public void onClick(View v) {
//        // 이름, 나이 값을 가져오기
//        mEditTextId.getText();
//        mEditTextPw.getText();
//        mEditTextPw2.getText();
//        mEditTextIdEmail.getText();

        //세컨드 엑티비티로 전환하겠다는 인텐트(intent)
        Intent intent = new Intent(this, Exam163SecondActivity.class);


        // 이름 나이를 가져와서 intent에 추가
        intent.putExtra("id", mEditTextId.getText().toString());
        intent.putExtra("pw", mEditTextPw.getText().toString());
        intent.putExtra("pw2", mEditTextPw2.getText().toString());
        intent.putExtra("email", mEditTextIdEmail.getText().toString());


        if (mRadioGroup.getCheckedRadioButtonId() == R.id.radio_01) {
            intent.putExtra("radio", mRadio01.getText().toString());
        }
        if (mRadioGroup.getCheckedRadioButtonId() == R.id.radio_02) {
            intent.putExtra("radio", mRadio02.getText().toString());
        }


        switch (v.getId()) {
            case R.id.btn_02:
                if (!(mEditTextId.getText().toString().equals("") || mEditTextPw.getText().toString().equals("") ||
                        mEditTextPw2.getText().toString().equals("") || mEditTextIdEmail.getText().toString().equals("") ||
                        mRadioGroup.getCheckedRadioButtonId() == -1)) {
                    if (mEditTextPw.getText().toString().equals(mEditTextPw2.getText().toString())) {
                        startActivityForResult(intent, 1000);                    } else {
                        Toast.makeText(Exam163Activity.this, "비밀번호가 다릅니다. ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    Toast.makeText(Exam163Activity.this, "모두 입력하세요.", Toast.LENGTH_SHORT).show();
                }

                break;


            case R.id.btn_01:
                mEditTextId.setText(null);
                mEditTextPw.setText(null);
                mEditTextPw2.setText(null);
                mEditTextIdEmail.setText(null);
                mRadioGroup.clearCheck();
                break;
        }

        //두번째 엑티비티에서 메세지 받을 준비 오버로드

    }

    // 2엑티비티에서 1엑티비티로으로 메세지를 받았다~
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Toast.makeText(Exam163Activity.this, "전달받은 메세지 : " + data.getStringExtra("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

