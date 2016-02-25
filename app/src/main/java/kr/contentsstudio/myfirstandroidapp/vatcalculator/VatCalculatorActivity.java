package kr.contentsstudio.myfirstandroidapp.vatcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import kr.contentsstudio.myfirstandroidapp.R;

public class VatCalculatorActivity extends Activity implements View.OnClickListener {

    // 변수선언
    private EditText mVatEdit01;
    private Button vatbutton, mVatBotton8, mVatBotton7, mVatBotton6, mVatBotton5,
            mVatBotton4, mVatBotton3, mVatBotton2, mVatBotton1, mVatBotton0,
            mVatBotton00, mVatBotton000, mVatClear, mVatBack, mVatBotton;
    private TextView mTextView01;
    float mValue1;
    float mValue2;
    int mCalcMode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vat_calculator);

        //입력 하기
        mVatEdit01 = (EditText) findViewById(R.id.vat_edit_01);

        // 버트들
//        mVatClear = (Button) findViewById(R.id.vat_clear);
//        //mVatBotton9 = (Button) findViewById(R.id.vat_button9);
//        mVatBotton8 = (Button) findViewById(R.id.vat_button8);
//        mVatBotton7 = (Button) findViewById(R.id.vat_button7);
//        mVatBotton6 = (Button) findViewById(R.id.vat_button6);
//        mVatBotton5 = (Button) findViewById(R.id.vat_button5);
//        mVatBotton4 = (Button) findViewById(R.id.vat_button4);
//        mVatBotton3 = (Button) findViewById(R.id.vat_button3);
//        mVatBotton2 = (Button) findViewById(R.id.vat_button2);
//        mVatBotton1 = (Button) findViewById(R.id.vat_button1);
//        mVatBotton0 = (Button) findViewById(R.id.vat_button0);
        mVatBotton = (Button) findViewById(R.id.vat_button);

        // 공급가
        mTextView01 = (TextView) findViewById(R.id.vat_edit_02);

        mVatClear.setOnClickListener(this);
        //mVatBotton9.setOnClickListener(this);
        mVatBotton8.setOnClickListener(this);
        mVatBotton7.setOnClickListener(this);
        mVatBotton6.setOnClickListener(this);
        mVatBotton5.setOnClickListener(this);
        mVatBotton4.setOnClickListener(this);
        mVatBotton3.setOnClickListener(this);
        mVatBotton2.setOnClickListener(this);
        mVatBotton1.setOnClickListener(this);
        mVatBotton0.setOnClickListener(this);
        mVatBotton.setOnClickListener(this);


    }


    //@Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.vat_clear:
//                mVatEdit01.setText("");
//                break;
//            case R.id.vat_button9:
//                mVatEdit01.setText(mVatEdit01.getText().toString() + 9);
//                break;
//            case R.id.vat_button8:
//                mVatEdit01.setText(mVatEdit01.getText().toString() + 8);
//                break;
//            case R.id.vat_button7:
//                mVatEdit01.setText(mVatEdit01.getText().toString() + 7);
//                break;
//            case R.id.vat_button6:
//                mVatEdit01.setText(mVatEdit01.getText().toString() + 6);
//                break;
//            case R.id.vat_button5:
//                mVatEdit01.setText(mVatEdit01.getText().toString() + 4);
//                break;
//            case R.id.vat_button4:
//                mVatEdit01.setText(mVatEdit01.getText().toString() + 4);
//                break;
//            case R.id.vat_button3:
//                mVatEdit01.setText(mVatEdit01.getText().toString() + 3);
//                break;
//            case R.id.vat_button2:
//                mVatEdit01.setText(mVatEdit01.getText().toString() + 2);
//                break;
//            case R.id.vat_button1:
//                mVatEdit01.setText(mVatEdit01.getText().toString() + 1);
//                break;
//            case R.id.vat_button0:
//                mVatEdit01.setText(mVatEdit01.getText().toString() + 0);
//                break;
//            case R.id.vat_button:
//                printSum();
//
//        }

    //  }

    private void printSum() {

        //문자를 붙히는데 사용이 편리하다.
        //int total = (this..getText().toString()) / 2;

        StringBuilder sb = new StringBuilder();
        // sb.append("").append(total);

        mTextView01.setText(sb);

    }

    @Override
    public void onClick(View v) {

    }
}
