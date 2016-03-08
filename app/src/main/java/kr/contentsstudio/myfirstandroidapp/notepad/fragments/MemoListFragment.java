package kr.contentsstudio.myfirstandroidapp.notepad.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import kr.contentsstudio.myfirstandroidapp.R;
import kr.contentsstudio.myfirstandroidapp.notepad.adapters.MemoAdapter;
import kr.contentsstudio.myfirstandroidapp.notepad.models.Memo;

/**
 * Created by hoyoung on 2016-03-08.
 */
public class MemoListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_memo_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("메모 리스트");


        //가짜 데이터 만들어서 확인
        ListView listView = (ListView) view.findViewById(R.id.list);

        List<Memo> list = new ArrayList<>();
        list.add(new Memo("타이틀", "ㅁㄴ아ㅓㄹㅁㅣㄴ얼ㅓㄴ이ㄹㅁㅣㄴ얼미ㅏ넝ㄹㅣ마ㅓㄴ이럼ㅣㄴ어ㅏㄹㅁ", "2016-3-8"));

        MemoAdapter adapter = new MemoAdapter(getActivity(), list);

        listView.setAdapter(adapter);
    }
}
