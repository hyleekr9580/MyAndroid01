package kr.contentsstudio.myfirstandroidapp.notepad.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

import kr.contentsstudio.myfirstandroidapp.R;
import kr.contentsstudio.myfirstandroidapp.notepad.activitys.MemoEditActivity;
import kr.contentsstudio.myfirstandroidapp.notepad.adapters.MemoCursorAdapter;
import kr.contentsstudio.myfirstandroidapp.notepad.db.MemoContract;
import kr.contentsstudio.myfirstandroidapp.notepad.models.Memo;
import kr.contentsstudio.myfirstandroidapp.notepad.provider.MemoContentProvider;

/**
 * Created by hoyoung on 2016-03-08.
 */

/*
 * 커서로덜를 사용하면 비동기화가 쉽다.
  * 우리는 커서로더를 사용한다. 로더 라는것이 있다.
  * 로더 : 프래그먼트에서 비동기식으로 데이터를 쉽게 로딩 할 수 있다.
  * 로더매니저를 통해서 관리하고 / 에이싱크테스크 / 커서로더 (컨텐트프로바이더가 있을 경우 같이 사용하면 최선의 방법이다.)
  * 데이터 100만건등... 많은 데이터가 있을 경우 로더를 이용해서 사용하면 빠르게 처리 할 수 있다.
  * 로더가 리셋되었을때는 값을 비워주면 된다.
 */
public class MemoListFragment extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, View.OnKeyListener {

    private static final String TAG = MemoListFragment.class.getSimpleName();
    private MemoCursorAdapter mAdapter;
    //SearchVIew 비동기 처리로 변경
    //private MemoFacade mFacade;
    private ListView mListView;
    private boolean mMultiChecked;
    //
    //private boolean[] mIsCheckedList;
    // 리스트뷰로 변경 해야 한다. 리스트를 늘려줄 수 없기 때문에 Array로 변경을 해야 한다.
    private ArrayList<Boolean> mIsCheckedList;
    private int mSelectionCount = 0;

    //SearchVIew 비동기 처리로 변경
    //private MemoFacade mMemoFacade;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_memo_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        //가짜 데이터 만들어서 확인
//        ListView listView = (ListView) view.findViewById(R.id.list);
//        List<Memo> list = new ArrayList<>();
//        list.add(new Memo("타이틀", "ㅁㄴ아ㅓㄹㅁㅣㄴ얼ㅓㄴ이ㄹㅁㅣㄴ얼미ㅏ넝ㄹㅣ마ㅓㄴ이럼ㅣㄴ어ㅏㄹㅁ", "2016-3-8"));
//        MemoAdapter adapter = new MemoAdapter(getActivity(), list);
//        listView.setAdapter(adapter);

        setTitle("메모 리스트");
        mListView = (ListView) view.findViewById(R.id.list);
        //SearchVIew 비동기 처리로 변경
        //mFacade = new MemoFacade(getActivity());
        mAdapter = new MemoCursorAdapter(getContext(), null) {
            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                super.bindView(view, context, cursor);
//                // TODO 검토
//                if (mIsCheckedList != null && mIsCheckedList[cursor.getPosition()]) {
//                    view.setBackgroundColor(Color.BLUE);
//                } else {
//                    view.setBackgroundColor(Color.WHITE);
//                }
            }
        };

        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);


        // fragment에서의 back key 처리
        // http://stackoverflow.com/questions/7992216/android-fragment-handle-back-button-press
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(this);

        //SearchVIew 비동기 처리로 변경
        //mMemoFacade = new MemoFacade(getActivity()


        //initLoader를 하면 로더를 토기화 하는 것이다.
        //  ID : 아무거나 숫자로 준다,\
        // Loader " 계산해서 넘길께 없으면 null
        //  <Cursor> 타입을 변경 할 수 있으나 해당 로더를 따로 만들어 줘야 한다.
        // Loader 시작
        // 시작을 하면 onCreateLoader 호출이 된다.
        getLoaderManager().initLoader(0, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                //  커서로더를 만들어서 직접 리턴을 시킬꺼다~
                //  프래그먼트에서는 반드시 getActivity()로 넘겨야 합니다.
                //  MemoContentProvider 를 통해서 쿼리를 날린다(수행).
                //  여기서 수행을 하면 백그라운드로 돈다. (백그라운드 수행됨) / 백그라운드 스레드
                return new CursorLoader(getActivity(), MemoContentProvider.CONTENT_URI,
                        null, null, null, null);
            }

            //  onCreateLoader의 리턴값이 onLoadFinished의 loader으로 들어온다.
            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                //  UI Thread
                mAdapter.swapCursor(data);
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {
                // 리셋이 되는 경우
                //커서에 null을 넣어서 비워주면 된다.
                mAdapter.swapCursor(null);
            }
        });
    }

    //여기서 커서를 로드 하고 있다.
    //로더는 사용할 경우 onViewCreated에서 로더를 작성한다.
    //로더를 사용할 셩우 onResume은 필요가 없다.
//    @Override
//    public void onResume() {
//        super.onResume();
//
//        // BaseAdapter 에서의 데이터 변경 후 notifyDataSetChanged 와 동일
//        // BaseAdapter 에서의 데이터 변경 후 notifyDataSetChanged 와 동일
//        Cursor cursor = getActivity().getContentResolver().query(MemoContentProvider.CONTENT_URI,
//                null,
//                null,
//                null,
//                null);
//        mAdapter.swapCursor(cursor);
//    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mMultiChecked == false) {
            Cursor cursor = (Cursor) (parent.getAdapter()).getItem(position);
            Memo memo = Memo.cursorToMemo(cursor);
            Intent intent = new Intent(getActivity(), MemoEditActivity.class);
            intent.putExtra(MemoContract.MemoEntry._ID, cursor.getLong(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry._ID)));
            intent.putExtra(MemoContract.MemoEntry.COLUM_NAME_TITLE, memo.getTitle());
            intent.putExtra(MemoContract.MemoEntry.COLUM_NAME_MEMO, memo.getMemo());
            intent.putExtra(MemoContract.MemoEntry.COLUM_NAME_IMAGE, cursor.getString(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUM_NAME_IMAGE)));
            startActivity(intent);
        } else {
            Cursor cursor = (Cursor) (parent.getAdapter()).getItem(position);
            //int Position = cursor.getPosition();

            if (mIsCheckedList.get(position) == true) {
                mIsCheckedList.set(position, false);
                mSelectionCount--;
            } else {
                mIsCheckedList.set(position, true);
                mSelectionCount++;
            }
            setTitle("" + mSelectionCount);

            // 멀티체크 모드 벗어나기
            if (mSelectionCount < 1) {
                mMultiChecked = false;
                setTitle("메모 리스트");
                setHasOptionsMenu(false);
            }

            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Cursor cursor = (Cursor) (parent.getAdapter()).getItem(position);
        // mIsCheckedList = new boolean[cursor.getCount()];
        mIsCheckedList = new ArrayList<>();
        //for문으로 갯수만큼 ArrayList를 늘려준다.
        for (int i = 0; i < cursor.getCount(); i++) {
            mIsCheckedList.add(false);
        }


        mMultiChecked = true;

        // 현재 롱클릭 한 아이템을 선택 하고 다시 그리기
        // mIsCheckedList[position] = true;
        mIsCheckedList.set(position, true);
        mAdapter.notifyDataSetChanged();
        // 선택 된 갯수 초기화
        mSelectionCount = 1;
        // Title 변경
        setTitle("" + mSelectionCount);

        // 옵션 메뉴 설정
        setHasOptionsMenu(true);
        return true;
    }


    private void setTitle(String title) {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_note, menu);

// SearchView
        // https://pluu.github.io/blog/android/2015/05/19/android-toolbar-searchview/
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            android.os.Handler handler = new android.os.Handler();

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                Log.d(TAG, "onQueryTextChange : " + newText);

//                String selection2 = "title LIKE '%"++"%' OR memo LIKE %?%"
//                String[] selectionArgs = new String[]{
//                        MemoContract.MemoEntry.COLUM_NAME_TITLE,
//                        MemoContract.MemoEntry.COLUM_NAME_MEMO
//                };

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String selection = "title LIKE '%" + newText + "%' OR memo LIKE '%" + newText + "%'";
                        final Cursor cursor = getActivity().getContentResolver().query(MemoContentProvider.CONTENT_URI,
                                null,
                                selection,
                                null,
                                null);

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter.swapCursor(cursor);
                            }
                        });
                    }
                }).start();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (mMultiChecked) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                mMultiChecked = false;
                setTitle("메모 리스트");
                setHasOptionsMenu(false);

                // 모두 false로 셋팅
                Collections.fill(mIsCheckedList, false);

                mAdapter.notifyDataSetChanged();
                return true;
            }
        }
        return false;
    }
}
