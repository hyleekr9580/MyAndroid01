package kr.contentsstudio.myfirstandroidapp.notepad.adapters;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kr.contentsstudio.myfirstandroidapp.R;
import kr.contentsstudio.myfirstandroidapp.notepad.db.MemoContract;

/**
 * Created by hoyoung on 2016-03-24.
 */
public class MemoRecyclerAdapter extends RecyclerView.Adapter<MemoRecyclerAdapter.Holder> {

    private final Cursor mCursor;

    //생성자를 하나 만들어서 onBindViewHolder 에서 사용 할 수 있는 cursor를 하나 만든다.
    public MemoRecyclerAdapter(Cursor cursor) {
        mCursor = cursor;
    }

    //새로운 VIEW가 생기는.....
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Context를 받아 올 수 있다.
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memo, parent, false);

        //holder을 만들어서 리컨
        return new Holder(itemView);
//        Holder holder = new Holder(itemView);
//        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Cursor cursor = mCursor;

        //커서어댑터마 가이니기 때문에 강제로 커서를 이동시킨다.
        cursor.moveToPosition(position);

        String title = cursor.getString(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUM_NAME_TITLE));
        String memo = cursor.getString(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUM_NAME_MEMO));
        String date = cursor.getString(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUM_NAME_DATE));

        holder.title.setText(title);
        holder.memo.setText(memo);
        holder.date.setText(date);

    }

    @Override
    public int getItemCount() {

        //null처리
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }

    //static을 사용하는 이유는 내부클래스는 static으로 사용하도록 한다.
    public static class Holder extends RecyclerView.ViewHolder {
        TextView title;
        TextView memo;
        TextView date;


        //생성자에서 셋팅을 해준다.
        public Holder(View itemView) {
            super(itemView);

            // 한칸에 대한 레이아웃
            title = (TextView) itemView.findViewById(R.id.title_text);
            memo = (TextView) itemView.findViewById(R.id.memo_text);
            date = (TextView) itemView.findViewById(R.id.date_text);
        }
    }
}
