package kr.contentsstudio.myfirstandroidapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.contentsstudio.myfirstandroidapp.broadcast.BroadcastActivity;
import kr.contentsstudio.myfirstandroidapp.com.exercise.Execrise157_S;
import kr.contentsstudio.myfirstandroidapp.com.exercise.Exercise152;
import kr.contentsstudio.myfirstandroidapp.com.exercise.Exercise157;
import kr.contentsstudio.myfirstandroidapp.com.fab_dialog.FabAndDialogActivity;
import kr.contentsstudio.myfirstandroidapp.com.listview.ListViewActivity;
import kr.contentsstudio.myfirstandroidapp.com.webview.WebViewActivity;
import kr.contentsstudio.myfirstandroidapp.exam163.Exam163Activity;
import kr.contentsstudio.myfirstandroidapp.exam217.Exam217Activity;
import kr.contentsstudio.myfirstandroidapp.fragment.FragmentExam203Activity;
import kr.contentsstudio.myfirstandroidapp.fragment.FragmentExam212Activity;
import kr.contentsstudio.myfirstandroidapp.fragment.FragmentExamActivity;
import kr.contentsstudio.myfirstandroidapp.thread.AsyncTaskActivity;
import kr.contentsstudio.myfirstandroidapp.thread.ThreadActivity;
import kr.contentsstudio.myfirstandroidapp.vatcalculator.StartActivity;
import kr.contentsstudio.myfirstandroidapp.viewpager.ScreenSlideActivity;

/**
 * 예제의 첫 화면
 */
public class MainListActivity extends ListActivity {

    private final static Comparator<Map<String, Object>> sDisplayNameComparator =
            new Comparator<Map<String, Object>>() {
                private final Collator collator = Collator.getInstance();

                public int compare(Map<String, Object> map1, Map<String, Object> map2) {
                    return collator.compare(map1.get("title"), map2.get("title"));
                }
            };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new SimpleAdapter(this, getData(),
                android.R.layout.simple_list_item_1, new String[]{
                "title"
        },
                new int[]{
                        android.R.id.text1
                }));
        getListView().setTextFilterEnabled(true);
    }

    protected List<Map<String, Object>> getData() {
        List<Map<String, Object>> myData = new ArrayList<>();

        // 메뉴 추가 부분
        addItem(myData, "버튼 이벤트", MainActivity.class);
        addItem(myData, "ScrollView", ScrollActivity.class);
        addItem(myData, "DH 이벤트 01 카톡", MainDhActivity.class);
        addItem(myData, "DH 이벤트 02 텍스트", MainDh2Activity.class);
        addItem(myData, "암시적 인텐트", IntentActivity.class);
        addItem(myData, "연습문제 152 Page", Exercise152.class);
        addItem(myData, "연습문제 157 Page", Exercise157.class);
        addItem(myData, "원장님 커피주문", Execrise157_S.class);
        addItem(myData, "웹뷰예제", WebViewActivity.class);
        addItem(myData, "리스트뷰", ListViewActivity.class);
        addItem(myData, "계산시", StartActivity.class);
        addItem(myData, "다이얼로그", FabAndDialogActivity.class);
        addItem(myData, "프래그먼트", FragmentExamActivity.class);
        addItem(myData, "프래그먼트203", FragmentExam203Activity.class);
        addItem(myData, "프래그먼트212", FragmentExam212Activity.class);
        addItem(myData, "ViewPager", ScreenSlideActivity.class);
        addItem(myData, "Exam217", Exam217Activity.class);
        addItem(myData, "Therad", ThreadActivity.class);
        addItem(myData, "AsyncTask", AsyncTaskActivity.class);
        addItem(myData, "Broadcast", BroadcastActivity.class);
        addItem(myData, "Exam163", Exam163Activity.class);



        // ----- 메뉴 추가 여기까지

        // 이름 순 정렬
        // Collections.sort(myData, sDisplayNameComparator);

        return myData;
    }

    protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
        Map<String, Object> temp = new HashMap<>();
        temp.put("title", name);
        temp.put("intent", intent);
        data.add(temp);
    }

    protected void addItem(List<Map<String, Object>> data, String name, Class cls) {
        this.addItem(data, name, new Intent(this, cls));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);

        Intent intent = (Intent) map.get("intent");
        startActivity(intent);
    }
}
