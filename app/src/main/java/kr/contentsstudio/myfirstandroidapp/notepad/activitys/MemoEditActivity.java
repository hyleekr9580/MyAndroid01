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
        String extra_text = intent.getStringExtra(Intent.EXTRA_TEXT);


        if (intent != null) {
            if (extra_text != null) {
                //외부에서 호출됨
                fragment = MemoEditFragment.newInstance(extra_text);

            } else {
                //내부에서 처리리

                long id = intent.getLongExtra(MemoContract.MemoEntry._ID, -1);

                if (id != -1) {
                    String title = intent.getStringExtra(MemoContract.MemoEntry.COLUM_NAME_TITLE);
                    String memo = intent.getStringExtra(MemoContract.MemoEntry.COLUM_NAME_MEMO);
                    String image = intent.getStringExtra(MemoContract.MemoEntry.COLUM_NAME_IMAGE);
                    fragment = MemoEditFragment.newInstance(id, title, memo, image);
                } else {
                    fragment = new MemoEditFragment();
                }
            }
        }


        // 레이아웃을 뿌려준다~
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contents, fragment)
                .commit();
    }
}
