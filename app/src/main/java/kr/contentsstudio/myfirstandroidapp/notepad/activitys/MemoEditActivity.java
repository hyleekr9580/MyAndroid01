package kr.contentsstudio.myfirstandroidapp.notepad.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import kr.contentsstudio.myfirstandroidapp.R;
import kr.contentsstudio.myfirstandroidapp.notepad.fragments.MemoEditFragment;

public class MemoEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        // 레이아웃을 뿌려준다~
        getSupportFragmentManager().beginTransaction().replace(R.id.contents, new MemoEditFragment()).commit();
    }
}
