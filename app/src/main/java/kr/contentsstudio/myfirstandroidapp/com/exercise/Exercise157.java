package kr.contentsstudio.myfirstandroidapp.com.exercise;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import kr.contentsstudio.myfirstandroidapp.R;

public class Exercise157 extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private int count;
    private TextView mText, mCount;
    private EditText mEditText;
    private Button mMinuse, mPlus, mOrder;
    private CheckBox mCheckBox;
    public static final int WP = 500;
    public static final int MONEY = 1500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise157);


        // 카운트
        mPlus = (Button) findViewById(R.id.plus);
        mPlus.setOnClickListener(this);
        mMinuse = (Button) findViewById(R.id.minuse);
        mMinuse.setOnClickListener(this);

        //주문버튼
        mOrder = (Button) findViewById(R.id.order);
        mOrder.setOnClickListener(this);


        //체크박스
        mCheckBox = (CheckBox) findViewById(R.id.check_box);

        // 입력하기
        mEditText = (EditText) findViewById(R.id.name);

        // order
        mText = (TextView) findViewById(R.id.view_text_01);

        // 카운트
        mCount = (TextView) findViewById(R.id.count);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.minuse:
                count--;
                if (count < 0) {
                    Toast.makeText(Exercise157.this, "0 이하로 선택 불가", Toast.LENGTH_SHORT).show();
                    count = 0;
                }
                mCount.setText("" + count);
                break;
            case R.id.plus:
                count++;
                if (count > 5) {
                    Toast.makeText(Exercise157.this, "5개 이상 선택 불가", Toast.LENGTH_SHORT).show();
                    count = 5;
                }
                mCount.setText("" + count);
                break;
            case R.id.order:

                printSum();


        }
    }

    private void printSum() {
        //문자를 붙히는데 사용이 편리하다.

        int total = MONEY * count;
        if (mCheckBox.isChecked()) {
            total += WP;
        }
        StringBuilder sb = new StringBuilder("주문요약");
        sb.append("\n이름 : ").append(mEditText.getText().toString());
        sb.append("\n수량 : ").append(count);
        sb.append("\n휘핑크림 추가 여부 : ").append(mCheckBox.isChecked());
        sb.append("\n합계 : ").append(total);

        mText.setText(sb);

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            Toast.makeText(Exercise157.this, "휘핑크림을 선택 하셨습니다.", Toast.LENGTH_SHORT).show();


        } else {
            Toast.makeText(Exercise157.this, "휘핑크림을 취소 하셨습니다.", Toast.LENGTH_SHORT).show();

        }
    }


}