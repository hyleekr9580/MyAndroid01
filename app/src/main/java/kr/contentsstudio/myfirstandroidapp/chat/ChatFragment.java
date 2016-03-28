package kr.contentsstudio.myfirstandroidapp.chat;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Date;

import kr.contentsstudio.myfirstandroidapp.R;
import kr.contentsstudio.myfirstandroidapp.chat.client.ChatClient;
import kr.contentsstudio.myfirstandroidapp.chat.client.MsgInfo;

public class ChatFragment extends Fragment implements View.OnClickListener {
    private EditText mMessageEdit;
    private ChatClient mChatClient;
    private LinearLayout mLinearLayout;
    private ScrollView mScrollView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMessageEdit = (EditText) view.findViewById(R.id.edit_message);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        mScrollView = (ScrollView) view.findViewById(R.id.scroll);

        view.findViewById(R.id.btn_send).setOnClickListener(this);

        // 서버에 접속
        new Thread(new Runnable() {
            @Override
            public void run() {
                mChatClient = new ChatClient();
                mChatClient.connect();
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        //버튼을 클랙했을때 어떻게 해야하냐~~
        mChatClient.sendMessage(mMessageEdit.getText().toString());
        mMessageEdit.setText("");


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mChatClient.close();
    }

    @Override
    public void onResume() {
        super.onResume();

        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        EventBus.getDefault().unregister(this);
    }

    Handler mHandler = new Handler();

    //타임을 만들기 위한 포맷
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("a hh:dd");

    @Subscribe
    @WorkerThread
    public void onMessage(final MsgInfo msgInfo) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {


//        <!--<TextView-->
//        <!--android:layout_gravity="right"-->
//        <!--android:id="@+id/msg_list_text"-->
//        <!--android:text="안녕하세요"-->
//        <!--android:layout_width="wrap_content"-->
//        <!--android:layout_height="wrap_content"-->
//        <!--android:background="@drawable/bubble"-->
//        <!--/>-->


                //프래그먼트 이기 때문에 Context는 getActivity이다.
                //        <!--<TextView-->
                TextView textView = new TextView(getActivity());

                //  XML에서 LinearLayout에서 만들었기 때문에 LinearLayout.LayoutParams으로 선택
                //        <!--android:layout_width="wrap_content"-->
                //        <!--android:layout_height="wrap_content"-->
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                if (msgInfo.getNickName().equals("이호영")) {

                    //프레그먼트에서 레이아웃을 가지고 올려면 inflater을 가지고 와야 한다.
                    View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.item_chat_me, mLinearLayout, false);
                    TextView timeText = (TextView) itemView.findViewById(R.id.time_text);
                    TextView messageText = (TextView) itemView.findViewById(R.id.msg_text);
                    timeText.setText(mSimpleDateFormat.format(new Date()));
                    messageText.setText(msgInfo.getMessage());
                    mLinearLayout.addView(itemView);
//                    params.gravity = Gravity.RIGHT;
//                    textView.setBackgroundResource(R.drawable.thm_chatroom_message_bubble_me_bg);
//                    textView.setText(msgInfo.getMessage());
                } else {

                    params.gravity = Gravity.LEFT;
                    textView.setBackgroundResource(R.drawable.thm_chatroom_message_bubble_you_bg);
                    textView.setText(msgInfo.getMessage());
                    textView.setLayoutParams(params);
                    mLinearLayout.addView(textView);
                }

                //        <!--android:layout_gravity="right"-->
                //  레이아웃이 들어가 있으면 모두 params에 들어 있다고 생각하면 된다
                //        <!--android:background="@drawable/bubble"-->


                //  android:background="@drawable/bubble"

            }
        });
    }
}
