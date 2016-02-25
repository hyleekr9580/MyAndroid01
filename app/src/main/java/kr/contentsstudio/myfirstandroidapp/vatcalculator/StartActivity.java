package kr.contentsstudio.myfirstandroidapp.vatcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import kr.contentsstudio.myfirstandroidapp.R;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        findViewById(R.id.general).setOnClickListener(this);
        findViewById(R.id.vat).setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.general:
                Intent general = new Intent(this, CalculatorActivity.class);
                startActivity(general);
                break;
            case R.id.vat:
                Intent vat = new Intent(this, VatCalculatorActivity.class);
                startActivity(vat);
                break;

        }

    }
}
