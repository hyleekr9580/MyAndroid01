package kr.contentsstudio.myfirstandroidapp.chat;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.contentsstudio.myfirstandroidapp.R;
import kr.contentsstudio.myfirstandroidapp.chat.client.ChatClient;
import kr.contentsstudio.myfirstandroidapp.chat.client.MsgInfo;

public class ChatFragment extends Fragment implements View.OnClickListener {
    private EditText mMessageEdit;
    private ChatClient mChatClient;
    //    private LinearLayout mLinearLayout;
    private ListView mListView;
    private List<MsgInfo> mChatData;
    private MyAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMessageEdit = (EditText) view.findViewById(R.id.edit_message);
//        mLinearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        mListView = (ListView) view.findViewById(R.id.list_view);

        //리스트뷰에 어댑터를 붙히자~~
        mChatData = new ArrayList<>();

        myAdapter = new MyAdapter(getActivity(), mChatData);
        mListView.setAdapter(myAdapter);


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


    @Subscribe
    @WorkerThread
    public void onMessage(final MsgInfo msgInfo) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mChatData.add(msgInfo);
                myAdapter.notifyDataSetChanged();

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
//                TextView textView = new TextView(getActivity());
//
//                //  XML에서 LinearLayout에서 만들었기 때문에 LinearLayout.LayoutParams으로 선택
//                //        <!--android:layout_width="wrap_content"-->
//                //        <!--android:layout_height="wrap_content"-->
//                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//
//                if (msgInfo.getNickName().equals(ChatClient.NICKNAME)) {
//
//                    //프레그먼트에서 레이아웃을 가지고 올려면 inflater을 가지고 와야 한다.
//                    View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.item_chat_me, mLinearLayout, false);
//                    TextView timeText = (TextView) itemView.findViewById(R.id.time_text);
//                    TextView messageText = (TextView) itemView.findViewById(R.id.message_me);
////                    timeText.setText(mSimpleDateFormat.format(new Date()));
//                    messageText.setText(msgInfo.getMessage());
//                    mLinearLayout.addView(itemView);
////                    params.gravity = Gravity.RIGHT;
////                    textView.setBackgroundResource(R.drawable.thm_chatroom_message_bubble_me_bg);
////                    textView.setText(msgInfo.getMessage());
//                } else {
//
//                    params.gravity = Gravity.LEFT;
//                    textView.setBackgroundResource(R.drawable.thm_chatroom_message_bubble_you_bg);
//                    textView.setText(msgInfo.getMessage());
//                    textView.setLayoutParams(params);
//                    mLinearLayout.addView(textView);
//                }
//
//                //        <!--android:layout_gravity="right"-->
//                //  레이아웃이 들어가 있으면 모두 params에 들어 있다고 생각하면 된다
//                //        <!--android:background="@drawable/bubble"-->
//
//
//                //  android:background="@drawable/bubble"

            }
        });
    }

    //어댑터를 내부클래스로 만든다
    // 베이스어댑터를 상속 받는다.
    private static class MyAdapter extends BaseAdapter {

        //타임을 만들기 위한 포맷
        private SimpleDateFormat mmSimpleDateFormat = new SimpleDateFormat("a hh:dd");

        private final LayoutInflater mmLayoutInflater;
        private final List<MsgInfo> mmData;

        //생성자를 하나 만들고
        public MyAdapter(Context context, List<MsgInfo> data) {
            mmLayoutInflater = LayoutInflater.from(context);
            mmData = data;

        }

        @Override
        public int getCount() {
            return mmData.size();
        }

        @Override
        public Object getItem(int position) {
            return mmData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();

                convertView = mmLayoutInflater.inflate(R.layout.item_chat_me, parent, false);
                holder.image = (ImageView) convertView.findViewById(R.id.image_you);
                holder.message_me = (TextView) convertView.findViewById(R.id.message_me);
                holder.message_you = (TextView) convertView.findViewById(R.id.message_you);
                holder.time_me = (TextView) convertView.findViewById(R.id.time_me);
                holder.time_you = (TextView) convertView.findViewById(R.id.time_you);
                holder.nickname = (TextView) convertView.findViewById(R.id.nickname_you);
                holder.layout_me = (LinearLayout) convertView.findViewById(R.id.layout_me);
                holder.layout_you = (LinearLayout) convertView.findViewById(R.id.layout_you);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();

            }

            //  데이터를 셋팅 할꺼다~~
            MsgInfo msgInfo = (MsgInfo) getItem(position);
            if (msgInfo.getNickName().equals(ChatClient.NICKNAME)) {
                holder.message_me.setText(msgInfo.getMessage());
                holder.time_me.setText(mmSimpleDateFormat.format(new Date()));

                holder.layout_me.setVerticalGravity(View.VISIBLE);
                holder.layout_you.setVerticalGravity(View.GONE);
            } else {

                holder.message_you.setText(msgInfo.getMessage());
                holder.time_you.setText(mmSimpleDateFormat.format(new Date()));
                holder.nickname.setText(msgInfo.getNickName());
                holder.image.setImageResource(R.mipmap.ic_launcher);

                holder.layout_me.setVerticalGravity(View.GONE);
                holder.layout_you.setVerticalGravity(View.VISIBLE);

                if (position > 0 && msgInfo.getNickName().equals(((MsgInfo) getItem(position - 1)).getNickName())) {
                    holder.image.setVisibility(View.INVISIBLE);
                    holder.nickname.setVisibility(View.GONE);

                } else {
                    holder.image.setVisibility(View.VISIBLE);
                    holder.nickname.setVisibility(View.VISIBLE);
                }

            }
            return convertView;
        }

        private static class ViewHolder {

            TextView time_me;
            TextView time_you;
            TextView message_me;
            TextView message_you;
            TextView nickname;
            ImageView image;
            LinearLayout layout_me;
            LinearLayout layout_you;
        }
    }
}
