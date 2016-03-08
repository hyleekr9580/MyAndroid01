package kr.contentsstudio.myfirstandroidapp.notepad;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import kr.contentsstudio.myfirstandroidapp.R;
import kr.contentsstudio.myfirstandroidapp.notepad.activitys.MemoEditActivity;
import kr.contentsstudio.myfirstandroidapp.notepad.fragments.MemoListFragment;

//OnNavigationItemSelectedListener 를 통해서 왼쪽 메뉴를 사용한다.
public class NoteMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_main);

        // 액션바를 툴바를 통해서 대체하고 있습니다.
        // 툴바 부분 셋팅 하는 부분 입니다. 툴바를 액션바터럼 사용 할 수 있다.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FloatingActionButton
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar 아래에서 올라오는 메세지 토스트 메세지와 비슷하다.
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                //엑티비티를 띄운다.
                startActivity(new Intent(NoteMainActivity.this, MemoEditActivity.class));
            }
        });

        // 네비게이션 DrawerLayout 입니다.
        // 해당 코드는 반듯기 필요 합니다.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // 네비게이션 메뉴 리스너
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //        //  기본화면으로 2번째 프래그먼트를 뿌린다.
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contents, new MemoListFragment())
                .commit();
    }

    // onBackPressed 네비를 사용하기 때문에 꼭 필요한 처리 입니다.
    // 그냥 아무것도 하지 않고 사용 하면 됩니다.
    // 네이가 열려 있으면 닫고, 아니면 엑티비티를 종료 한다.
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //onCreateOptionsMenu 메뉴를 생성하는 부분 입니다. 상단의 메뉴 (오른쪽)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.note_main, menu);
        return true;
    }

    // 메뉴를 선택했을때 onOptionsItemSelected 에서 처리 합니다.
    // 오른쪽 상단 부분 처리
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_memos) {
            // Handle the camera action
            //TODO 메모 프레그먼트 표시
        } else if (id == R.id.nav_setting) {
            //TODO 설정 프레그먼트 표시

        }

        // 닫는 부분을 사용한다.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
