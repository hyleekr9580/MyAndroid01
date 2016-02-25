package kr.contentsstudio.myfirstandroidapp.com.fab_dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import kr.contentsstudio.myfirstandroidapp.R;

//AppCompatActivity 하위단말들을 사용 할 수 있도록 하기 위해서 사용한다.
public class FabAndDialogActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_and_dialog);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //다이얼로그
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("<--아이콘 / 제목");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("메세지");
        builder.setPositiveButton("확인 Positive", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == AlertDialog.BUTTON_POSITIVE) {
                    Toast.makeText(FabAndDialogActivity.this, "확인", Toast.LENGTH_SHORT).show();

                }
            }
        });
        builder.setNegativeButton("닫기 Negative", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
    }
}
