package kr.contentsstudio.myfirstandroidapp.notepad.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.contentsstudio.myfirstandroidapp.R;

/**
 * 메모 작성하는 프레그먼트 입니다.
 */
public class MemoEditFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_memo_edit, container, false);
    }

    //  툴바를 사용할꺼면 아래와 같이 해야 한다. 레이아웃을 들고 와야 한다.
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("메모작성하기");

        //프래그먼트에서 옵션 메뉴를 가질(사용) 수 있게 한다.
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        //inflater가 넘어 온다.
        inflater.inflate(R.menu.note_main,menu);
    }
}
