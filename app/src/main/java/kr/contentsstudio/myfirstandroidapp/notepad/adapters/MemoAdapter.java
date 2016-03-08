package kr.contentsstudio.myfirstandroidapp.notepad.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import kr.contentsstudio.myfirstandroidapp.R;
import kr.contentsstudio.myfirstandroidapp.notepad.models.Memo;

/**
 * Created by hoyoung on 2016-03-08.
 */
public class MemoAdapter extends BaseAdapter {
    private final List<Memo> mData;
    private final LayoutInflater mInflator;

    public MemoAdapter(Context context, List<Memo> data) {

        mData = data;
        mInflator = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = mInflator.inflate(R.layout.item_memo, parent, false);

            //뷰홀더에 담는다.
            holder = new ViewHolder();
            holder.date = (TextView) convertView.findViewById(R.id.date_text);
            holder.title = (TextView) convertView.findViewById(R.id.title_text);
            holder.memo = (TextView) convertView.findViewById(R.id.memo_text);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }

        //데이터 셋팅

        //아래와 같이 가지고 와도 된다.
        //mData.get(position);

        Memo memo = (Memo) getItem(position);

        holder.date.setText(memo.getDate());
        holder.title.setText(memo.getTitle());
        holder.memo.setText(memo.getMemo());


        return convertView;
    }

    private static class ViewHolder {
        TextView title;
        TextView memo;
        TextView date;
    }
}
