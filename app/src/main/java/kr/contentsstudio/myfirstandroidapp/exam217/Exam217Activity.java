package kr.contentsstudio.myfirstandroidapp.exam217;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import kr.contentsstudio.myfirstandroidapp.R;

public class Exam217Activity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> mData;
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam217);

        //테이블레이아웃
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        //탭설정하기
        mTabLayout.addTab(mTabLayout.newTab().setText("플레이어"));
        mTabLayout.addTab(mTabLayout.newTab().setText("아티스트"));
        mTabLayout.addTab(mTabLayout.newTab().setText("노래"));

        //view에 들어갈 데이터를 준비해야 한다.
        mData = new ArrayList<>();
        mData.add(new ArtistFragment());

        //Adapter 준비
        mAdapter = new MyPagerAdater(getSupportFragmentManager(), mData);

        // view에 어뎁터 붙이기
        mViewPager.setAdapter(mAdapter);

        // 탭레이아웃 붙히기 (뷰페이저와 탭레이아웃 연결 하기)
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));


    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //여기서 Tab를 체크 합니다.
        // Tab을 선택했을 때 ViewPager 의 페이지를 이동
        //Selected 했을때 tab을 가져온다.
        // 특정페이지를 선택하는 한다.
        //tab를 선택했을때 뷰페이지로 이동 시킨다.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    class MyPagerAdater extends FragmentPagerAdapter {
        private static final int PAGE_COUNT = 5;
        private List<Fragment> mmData;

        public MyPagerAdater(FragmentManager fm, List<Fragment> mData) {
            super(fm);

        }


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ArtistFragment();
                case 1:
                    return new ArtistFragment();
                case 2:
                    return new ArtistFragment();
                default:
                    return new ArtistFragment();
            }
        }

        @Override
        public int getCount() {

            return PAGE_COUNT;
        }
    }
}
