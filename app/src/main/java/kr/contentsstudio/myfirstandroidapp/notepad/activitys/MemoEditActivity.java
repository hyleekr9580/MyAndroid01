package kr.contentsstudio.myfirstandroidapp.notepad.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import kr.contentsstudio.myfirstandroidapp.R;
import kr.contentsstudio.myfirstandroidapp.notepad.db.MemoContract;
import kr.contentsstudio.myfirstandroidapp.notepad.fragments.MemoEditFragment;

public class MemoEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_edit);

        Fragment fragment = null;

        // 내가 호출 된 Intent 의 내용을 확인
        Intent intent = getIntent();
        if (intent != null) {
            long id = intent.getLongExtra(MemoContract.MemoEntry._ID, -1);

            if (id != -1) {
                String title = intent.getStringExtra(MemoContract.MemoEntry.COLUM_NAME_TITLE);
                String memo = intent.getStringExtra(MemoContract.MemoEntry.COLUM_NAME_MEMO);

                fragment = MemoEditFragment.newInstance(id, title, memo);
            } else {
                fragment = new MemoEditFragment();
            }
        }

        // 레이아웃을 뿌려준다~
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contents, fragment)
                .commit();    }
}
