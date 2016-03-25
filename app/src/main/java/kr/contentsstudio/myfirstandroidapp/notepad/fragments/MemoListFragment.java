package kr.contentsstudio.myfirstandroidapp.notepad.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import kr.contentsstudio.myfirstandroidapp.R;
import kr.contentsstudio.myfirstandroidapp.notepad.activitys.MemoEditActivity;
import kr.contentsstudio.myfirstandroidapp.notepad.adapters.MemoRecyclerAdapter;
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
public class MemoListFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnKeyListener, MemoRecyclerAdapter.OnItemClickListener {

    private static final String TAG = MemoListFragment.class.getSimpleName();
    private MemoRecyclerAdapter mAdapter;
    //SearchVIew 비동기 처리로 변경
    //private MemoFacade mFacade;
    private RecyclerView mListView;
    private boolean mMultiChecked;
    //
    //private boolean[] mIsCheckedList;
    // 리스트뷰로 변경 해야 한다. 리스트를 늘려줄 수 없기 때문에 Array로 변경을 해야 한다.
    //private ArrayList<Boolean> mIsCheckedList = new ArrayList<>();
    //Hashset를 통해서 작업을 해보자~~
    //set은 자료구조가... 지우는거 있는지 검사하는거..
    //ArrayList는 index를 얻을 수 있으나 탐색하는거는 set이 빠르다.
    //set는 구조가 map라 똑같다.
    private Set<Integer> mIsCheckedList = new HashSet<>();

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
        mListView = (RecyclerView) view.findViewById(R.id.list);
        //SearchVIew 비동기 처리로 변경
        //mFacade = new MemoFacade(getActivity());

        //RecyclerAdapter를 사용하면....주석...
//        mAdapter = new MemoCursorAdapter(getContext(), null) {
//            @Override
//            public void bindView(View view, Context context, Cursor cursor) {
//                super.bindView(view, context, cursor);
//                // TODO 검토
//                //contains 있나 없나 검사를 한다.
//                if (mIsCheckedList != null && mIsCheckedList.contains(cursor.getPosition())) {
//                    view.setBackgroundColor(Color.BLUE);
//                } else {
//                    view.setBackgroundColor(Color.WHITE);
//                }
//            }
//        };

        mAdapter = new MemoRecyclerAdapter(null) {
            @Override
            public void onBindViewHolder(Holder holder, int position) {
                super.onBindViewHolder(holder, position);

                changeColor(holder.itemView, position);
            }
        };
        mListView.setAdapter(mAdapter);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mListView.setLayoutManager(layoutManager);

        //레이아웃매니저

        //TODO 리스너구현
        //CardView를 사용할 경우 setOnItemClickListener가 없다. 만들어서 사용을 해야 한다.
        //MemoRecyclerAdapter를 통해서 만들 수 있다.
        mAdapter.setOnItemClickListener(this);
//        mListView.setOnItemClickListener(this);
//        mListView.setOnItemLongClickListener(this);


        //애니메이션 구현하기
        //RecyclerView를 사용하는 이유는 애니메이션을 구현하기 위해서 사용을 한다.
        //  기본애니메이션
        DefaultItemAnimator animator = new DefaultItemAnimator();
        // 애니메이션 수정
        animator.setAddDuration(1000);
        animator.setRemoveDuration(1000);
        animator.setChangeDuration(1000);
        animator.setMoveDuration(1000);

        mListView.setItemAnimator(animator);

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

    }


//    @Override
//    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//        setmMultiCheckMode(true);
    //       Cursor cursor = (Cursor) (parent.getAdapter()).getItem(position);
    // mIsCheckedList = new boolean[cursor.getCount()];
    //.clear() 초기화 해주는거~
//        mIsCheckedList.clear();
    //for문으로 갯수만큼 ArrayList를 늘려준다.
//        for (int i = 0; i < cursor.getCount(); i++) {
//            mIsCheckedList.add(false);
//        }
//        mMultiChecked = true;

    // 현재 롱클릭 한 아이템을 선택 하고 다시 그리기
    // mIsCheckedList[position] = true;
//        mIsCheckedList.add(position);
//        mAdapter.notifyDataSetChanged();
    // 선택 된 갯수 초기화
//        mSelectionCount = 1;
    // Title 변경
//        setTitle("" + mSelectionCount);

    // 옵션 메뉴 설정
//        setHasOptionsMenu(true);
//        return true;
//    }


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
            showdeleteDialog();
            return true;
        } else if (id == R.id.action_export) {

        }

        return super.onOptionsItemSelected(item);

    }

    private void showdeleteDialog() {
        String ids = "";
        //iterator 반복자 / 반복할 수 있는 것들은 iterator를 사용한다.
        Iterator<Integer> iterator = mIsCheckedList.iterator();
        while (iterator.hasNext()) {
            int position = iterator.next();
            ids = ids + mAdapter.getItemId(position);
            if (iterator.hasNext()) {
                ids += ",";
            }
        }
        Log.d(TAG, ids);

        final String finalIds = ids;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("메모 삭제")
                .setMessage("메모를 삭제하시겠습니까?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    //익명 클레스
                    public void onClick(DialogInterface dialog, int which) {
                        int deleted = getActivity().getContentResolver().delete(MemoContentProvider.CONTENT_URI,
                                "_id IN (" + finalIds + ")", null);
                        if (deleted > 0) {
                            Toast.makeText(getActivity(), "삭제 되었습니다", Toast.LENGTH_SHORT).show();
//                            mMultiChecked = false;

                            setmMultiCheckMode(false);
                            //mAdapter.notifyDataSetChanged();
                        }
                    }

                })
                .setNegativeButton("아니오", null);
        builder.show();
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (mMultiChecked) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
//                mMultiChecked = false;
//                setTitle("메모 리스트");
//                setHasOptionsMenu(false);

                // 모두 false로 셋팅
//                Collections.fill(mIsCheckedList, false);

//                mIsCheckedList.clear();
                setmMultiCheckMode(false);
                mAdapter.notifyDataSetChanged();
                return true;
            }
        }
        return false;
    }

    private void setmMultiCheckMode(boolean isMultiCheckMode) {
        mMultiChecked = isMultiCheckMode;
        setHasOptionsMenu(isMultiCheckMode);
        mIsCheckedList.clear();


        if (isMultiCheckMode) {
            // 선택 된 갯수 초기화

            mSelectionCount = 1;
            // Title 변경

            setTitle("" + mSelectionCount);
        } else {
            setTitle("메모리스트");
        }

    }

    @Override
    public void onItemClick(View view, int position) {

        Toast.makeText(getActivity(), "position : " + position, Toast.LENGTH_SHORT).show();
        if (mMultiChecked == false) {
            Cursor cursor = mAdapter.getItem(position);
            Memo memo = Memo.cursorToMemo(cursor);
            Intent intent = new Intent(getActivity(), MemoEditActivity.class);
            intent.putExtra(MemoContract.MemoEntry._ID, cursor.getLong(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry._ID)));
            intent.putExtra(MemoContract.MemoEntry.COLUM_NAME_TITLE, memo.getTitle());
            intent.putExtra(MemoContract.MemoEntry.COLUM_NAME_MEMO, memo.getMemo());
            intent.putExtra(MemoContract.MemoEntry.COLUM_NAME_IMAGE, cursor.getString(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUM_NAME_IMAGE)));
            startActivity(intent);
        } else {

//                    Cursor cursor = (Cursor) (mAdapter.getItem(position);
            //int Position = cursor.getPosition();

            //get = contains / set = add

            //  색상변경상태
            if (mIsCheckedList.contains(position)) {
                mIsCheckedList.remove(position);
                mSelectionCount--;
            } else {
                mIsCheckedList.add(position);
                mSelectionCount++;
            }
            setTitle("" + mSelectionCount);

            //  색사값설정
//            changeColor(view, position);

            // 멀티체크 모드 벗어나기
            if (mSelectionCount < 1) {
//                mMultiChecked = false;
//                setTitle("메모 리스트");
//                setHasOptionsMenu(false);
                setmMultiCheckMode(false);
            }

            // 변경 적용
            mAdapter.notifyItemChanged(position);
        }
    }

    private void changeColor(View view, int position) {
        if (mIsCheckedList != null && mIsCheckedList.contains(position)) {
            if (view instanceof CardView) {
                ((CardView) view).setCardBackgroundColor(Color.BLUE);
            }
        } else {
            if (view instanceof CardView) {
                ((CardView) view).setCardBackgroundColor(Color.WHITE);
            }
        }
    }


    @Override
    public void onItemLongClick(View view, int position) {
        setmMultiCheckMode(true);

        mIsCheckedList.add(position);

//        changeColor(view, position);

        mAdapter.notifyDataSetChanged();
    }
}
