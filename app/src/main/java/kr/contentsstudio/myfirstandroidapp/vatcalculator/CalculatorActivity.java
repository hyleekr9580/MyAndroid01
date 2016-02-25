package kr.contentsstudio.myfirstandroidapp.vatcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import kr.contentsstudio.myfirstandroidapp.R;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {


    private Button button00, button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9,
            clear, plus, minus, multiplication, division, dot, equals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        findViewById(R.id.button00).setOnClickListener(this);
        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.clear).setOnClickListener(this);
        findViewById(R.id.plus).setOnClickListener(this);
        findViewById(R.id.minus).setOnClickListener(this);
        findViewById(R.id.multiplication).setOnClickListener(this);
        findViewById(R.id.division).setOnClickListener(this);
        findViewById(R.id.dot).setOnClickListener(this);
        findViewById(R.id.equals).setOnClickListener(this);
        findViewById(R.id.edit_01);

    }


    @Override
    public void onClick(View v) {

    }
}










