package kr.contentsstudio.myfirstandroidapp.notepad.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import kr.contentsstudio.myfirstandroidapp.R;
import kr.contentsstudio.myfirstandroidapp.notepad.activitys.MemoEditActivity;
import kr.contentsstudio.myfirstandroidapp.notepad.adapters.MemoCursorAdapter;
import kr.contentsstudio.myfirstandroidapp.notepad.db.MemoContract;
import kr.contentsstudio.myfirstandroidapp.notepad.facade.MemoFacade;
import kr.contentsstudio.myfirstandroidapp.notepad.models.Memo;

/**
 * Created by hoyoung on 2016-03-08.
 */
public class MemoListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private MemoFacade mFacade;
    private MemoCursorAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_memo_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("메모 리스트");


        //가짜 데이터 만들어서 확인
//        ListView listView = (ListView) view.findViewById(R.id.list);
//
//        List<Memo> list = new ArrayList<>();
//        list.add(new Memo("타이틀", "ㅁㄴ아ㅓㄹㅁㅣㄴ얼ㅓㄴ이ㄹㅁㅣㄴ얼미ㅏ넝ㄹㅣ마ㅓㄴ이럼ㅣㄴ어ㅏㄹㅁ", "2016-3-8"));
//
//        MemoAdapter adapter = new MemoAdapter(getActivity(), list);
//
//        listView.setAdapter(adapter);
        ListView listView = (ListView) view.findViewById(R.id.list);

        mFacade = new MemoFacade(getActivity());

        mAdapter = new MemoCursorAdapter(getActivity(), null);

        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(this);
    }
    @Override
    public void onResume() {
        super.onResume();

        // BaseAdapter 에서의 데이터 변경 후 notifyDataSetChanged 와 동일
        mAdapter.swapCursor(mFacade.queryAllMemos());
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Cursor cursor = (Cursor) (parent.getAdapter()).getItem(position);

        Memo memo = Memo.cursorToMemo(cursor);

        Intent intent = new Intent(getActivity(), MemoEditActivity.class);
        intent.putExtra(MemoContract.MemoEntry._ID, cursor.getLong(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry._ID)));
        intent.putExtra(MemoContract.MemoEntry.COLUM_NAME_TITLE, memo.getTitle());
        intent.putExtra(MemoContract.MemoEntry.COLUM_NAME_MEMO, memo.getMemo());
        startActivity(intent);
    }
}
