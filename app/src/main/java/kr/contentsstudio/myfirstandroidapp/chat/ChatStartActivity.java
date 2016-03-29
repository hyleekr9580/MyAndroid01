package kr.contentsstudio.myfirstandroidapp.chat;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import kr.contentsstudio.myfirstandroidapp.R;

public class ChatStartActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButtom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_start);

        //채팅방입장하기버튼
        mButtom = (Button) findViewById(R.id.chat_btn);
        mButtom.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ChatActivity.class);

        if (!isNetworkConnected(this)) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("네트워크 연결 오류")
                    .setMessage("사용하시는 단말기의 네트워크 상태가 좋지 않습니다.\nWIFI망 혹은 모바일 네트워크 연결상태를 확인해 주세요")
                    .setPositiveButton("확인", null).show();
        } else {
            startActivity(intent);
        }

    }

    public boolean isNetworkConnected(Context context) {
        boolean isConnected = false;

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (mobile.isConnected() || wifi.isConnected()) {
            isConnected = true;
        } else {
            isConnected = false;
        }
        return isConnected;
    }
}
