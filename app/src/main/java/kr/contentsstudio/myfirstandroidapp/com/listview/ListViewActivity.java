package kr.contentsstudio.myfirstandroidapp.com.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.contentsstudio.myfirstandroidapp.R;

public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ListView mListView, mSimpleListView, mBaseListView;
    private GridView mGridView;
    private Spinner mSpinner;
    private SimpleAdapter mSimpleAdapter;
    private MyAdapter mMyAdapter;
    private List<String> mArrayData;
    private ArrayAdapter<String> mArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        //view를가지고 온다
        mListView = (ListView) findViewById(R.id.list);
        //그리드뷰
        mGridView = (GridView) findViewById(R.id.grid);
        //스피너뷰
        mSpinner = (Spinner) findViewById(R.id.spinner);
        //mSimpleListView
        mSimpleListView = (ListView) findViewById(R.id.simplelist);
        //베이스리스트뷰
        mBaseListView = (ListView) findViewById(R.id.BaseListView);


        //Arraydata 를 만들어 준고~
        mArrayData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mArrayData.add("data" + i);
        }

        //Arrayadapter 데이터는~~~ 어뎁터에 들어갔다~~~
        mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mArrayData);


        //SimplData
        List<Map<String, String>> mSimpleData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("title", "title " + i);
            map.put("descriprion", "description " + i);
            mSimpleData.add(map);
        }

        //My베이스data
        List<Myitem> myData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i % 3 == 0) {
                myData.add(new Myitem(R.mipmap.ic_launcher, "안드로이드" + i, "안드로이드 입니다. " + i));

            } else {
                myData.add(new Myitem(R.drawable.u, "미소녀" + i, "정말\n정말\n예쁜 유선" + i));
            }

        }


        //SimplrAdapter
        mSimpleAdapter = new SimpleAdapter(this,
                mSimpleData,
                android.R.layout.simple_list_item_2,
                new String[]{"title", "descriprion"},
                new int[]{android.R.id.text1, android.R.id.text2});


        //my어뎁터
        mMyAdapter = new MyAdapter(this, myData);


        //어레이어뎁터 어댑터를 ~~ VIEW에 붙힌다~~~
        //리스트뷰에.어댑터를끼운다(mArrayAdapter를~~~);
        mListView.setAdapter(mArrayAdapter);
        //그리드어뎁터
        mGridView.setAdapter(mArrayAdapter);
        //spinner 어뎁터
        mSpinner.setAdapter(mArrayAdapter);
        //mSimpleAdapter
        mSimpleListView.setAdapter(mSimpleAdapter);
        //베이스
        mBaseListView.setAdapter(mMyAdapter);

        //클립이벤트 실행
        mListView.setOnItemClickListener(this);
        //롱클릭이벤트
        mListView.setOnItemLongClickListener(this);


    }

    @Override
    //                     AdapterView= mListView / 한칸에 대한 뷰
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(ListViewActivity.this, "position : " + position, Toast.LENGTH_SHORT).show();


    }


    @Override
    //boolean 은 처리를 했는지 안했는지 확인
    // 버튼이 두개 있으면 잘 생각하고해야 한다. return false; / return true;
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(ListViewActivity.this, "롱클릭", Toast.LENGTH_SHORT).show();


        // 데이터 삭제
        mArrayData.remove(position);
        Toast.makeText(ListViewActivity.this, position + " 번을 삭제하였습니다.", Toast.LENGTH_SHORT).show();

        //화면갱신 : Adapter에게 데이터 변경을 알려준다 > ListView에 새로운것을 반영한다.
        mArrayAdapter.notifyDataSetChanged();
        mSimpleAdapter.notifyDataSetChanged();
        return true;
    }
}
