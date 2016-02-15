package kr.contentsstudio.myfirstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
//public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final int REQUEST_CODE_PICTURE = 1000;
    public static final int REQUEST_CODE_CAMERA = 2000;
    private TextView mTextView;
    private EditText mNameEditText;
    private EditText mAgeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //화면에 레이아웃 표시
        setContentView(R.layout.checkbox);

        // 이름, 나이를 xml에서 거져 와야 한다.
        mNameEditText = (EditText) findViewById(R.id.name_edit_text);
        mAgeEditText = (EditText) findViewById(R.id.age_edit_text);

        // 버튼을 보여줄꺼다~~~
        findViewById(R.id.next_activity_botton).setOnClickListener(this);

        // 체크박스 글씨 가져온다~
        mTextView = (TextView) findViewById(R.id.msg_text_view);
        ((CheckBox) findViewById(R.id.check_box)).setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Toast.makeText(MainActivity.this, "CHECK :" + isChecked, Toast.LENGTH_SHORT).show();

        //텍스트 뷰의 글자를 변경 할거다.
        if (isChecked) {
            mTextView.setVisibility(View.VISIBLE);
        } else {
            mTextView.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void onClick(View v) {
        // 이름, 나이 값을 가져오기
        // 테스트 Toast.makeText(MainActivity.this, "CHECK :" + mNameEditText.getText(), Toast.LENGTH_SHORT).show();
        mNameEditText.getText();
        mAgeEditText.getText();


        //세컨드 엑티비티로 전환하겠다는 인텐트(intent)
        Intent intent = new Intent(this, SecoundActivity.class);

        // 이름 나이를 가져와서 intent에 추가
        intent.putExtra("name", mNameEditText.getText().toString());
        intent.putExtra("age", mAgeEditText.getText().toString());


        //intent의 정보를 토대로 다른 엑티비티 실행
        startActivityForResult(intent, REQUEST_CODE_PICTURE);
    }

    // 결과를 처리 하는 부분 입니다.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICTURE || resultCode == RESULT_OK || data != null) {
            //사진을 선택하는 처리
            String result = data.getStringExtra("result");
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();

        } else if (requestCode == REQUEST_CODE_CAMERA) {
            //사진을 찍을때의 처리
        }
    }

    //  메인에서 어플 종료
    public void noTouch() {
    }


//    public boolean onKeyDown(int keycode, KeyEvent event) {
//        if (keycode == KeyEvent.KEYCODE_BACK) {
//            setAlertMessageToClose();
//        } else {
//            noTouch();
//        }
//        return true;
//    }

//    private void setAlertMessageToClose() {
//        AlertDialog.Builder builder;
//        Resources res = getResources();
//        String sText = res.getString(R.string.app_name);
//
//        builder = new AlertDialog.Builder(this);
//        builder.setMessage(sText + "을(를) 종료하시겠습니까?").setCancelable(false)
//                .setPositiveButton("종료", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.dismiss();
//                        moveTaskToBack(true);
//                        android.os.Process.killProcess(android.os.Process.myPid());
//                    }
//                }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                dialog.cancel();
//            }
//        });
//        builder.create();
//        builder.show();

}


