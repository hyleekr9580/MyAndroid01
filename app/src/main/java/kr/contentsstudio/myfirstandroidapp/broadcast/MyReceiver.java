package kr.contentsstudio.myfirstandroidapp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    //생성장
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        //여기서 방송을 받아서 처리 하면 된다~
        // 배터리 전원 on/off 수신을 받겠다
        // 매니 패스트 꼭 추가해줘야 한다. 리시버.
        if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {
            Toast.makeText(context, "충전상태 변경됨", Toast.LENGTH_SHORT).show();

            //내가 수신한 다음에 방송을 없애버린다.
            // 나만 방송을 수신하고 싶어~~~
            abortBroadcast();
        }
        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
            Toast.makeText(context, "충전 연결 됨", Toast.LENGTH_SHORT).show();

            // 내가 수신 한 다음에 방송을 제거
            abortBroadcast();
        } else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
            Toast.makeText(context, "충전 해제 됨", Toast.LENGTH_SHORT).show();
        }
    }
}
