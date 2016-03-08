package kr.contentsstudio.myfirstandroidapp.com.listview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kr.contentsstudio.myfirstandroidapp.R;


public class MyAdapter extends BaseAdapter {


    private final LayoutInflater minflater;
    private Context mContext;
    private List<Myitem> mData;

    //생성자를 만들고 context를 무조검 받아야 한다.
    public MyAdapter(Context context, List<Myitem> data) {
        mContext = context;
        mData = data;
        minflater = LayoutInflater.from(mContext);

    }




    // 어뎁터가 가지고 있는 아이템의 개수를 지정하는 곳이다.
    @Override

    public int getCount() {
        return mData.size();
    }

    // position 번째 있는 아이템을 리턴 해주는 메소드
    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    // position 번째 아이템의 ID를 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 가장중요한 부분
    // 아이템 한개의 view를 완성하는 곳
    //position(위치) / convertView(한칸의뷰) / parent(부모 리스트뷰)
    //getView는 소비가 너무 많다. inflater, findViewById, 등 뷰홀더패턴을 사용하면 소비를 줄일 수 있다.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoler holder;

        //최초의 뷰가 생겼을때 아무것도 없다. null로 들어 온다 null인지 아닌지 확인
        if (convertView == null) {

            holder = new ViewHoler();

            //최초 로드 할때 convertView는 아무것도 없다.
            // xml 로드 해야 한다. findviewbyid는 없다.
            //context를 이용해서  가지고 와야 한다.

            // 레이아웃을 가지고 오게 하는 객체
            //LayoutInflater.from(mContext)
            //LayoutInflater inflater = LayoutInflater.from(mContext);
            //findviewbyid 와 같은 역활을 한다.
            convertView = minflater.inflate(R.layout.item_list, parent, false);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.title_img);
            TextView title = (TextView) convertView.findViewById(R.id.title_text);
            TextView description = (TextView) convertView.findViewById(R.id.title_itemtext);

            holder.imageView = imageView;
            holder.title = title;
            holder.description = description;

            //이상한 짓ㅅ을 한다...
            //setTag,,,getTag를 잘 알 고 있어라~~ 사용할때가 있다.~~~
            convertView.setTag(holder);

        } else {
            //재활용할때 사용한다... 필요는 없다.
            holder = (ViewHoler) convertView.getTag();
        }

        //둘중 하나 사용해도 된다.
            Myitem item = mData.get(position);
        //Myitem item2 = (Myitem) getItem(position);

        //꽂아 줄꺼다~~~~ convertView에서 들고 온다. 텍스트 타이틀


        //데이터 연결하기 할꺼다~~~ ( 데이터 설정)
        //구글 페이지.... holder 사용했고 스레드를 이용해서 백그라운드에서 로딩 하면서 사용 하고 있다.
        holder.imageView.setImageResource(item.getImageRes());
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());

        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.BLUE);
        } else {
            convertView.setBackgroundColor(Color.RED);
        }
        //완성품은 리턴
        return convertView;
    }
    //뷰홀더패턴
    //내부클래스는 static 으로 사용한다고 생각하라.

    private static class ViewHoler {
        ImageView imageView;
        TextView title;
        TextView description;

    }
}
