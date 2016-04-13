package kr.contentsstudio.myfirstandroidapp.viewpager;

import android.graphics.Color;
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
import kr.contentsstudio.myfirstandroidapp.fragment.ColorFragment;

public class ScreenSlideActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    //tablayout 들고 오자~~~
    private TabLayout mTabLayout;

    // view
    private ViewPager mViewPager;

    //data
    private List<Fragment> mData;

    // Adapter
    private FragmentPagerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab);

        //tab설정
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 222222"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 33333333333"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 4"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 5"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 5"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 5"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 5"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 5"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 5"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 5"));
        //setOnTabSelectedListener(OnTabSelectedListener) 탭연결
        mTabLayout.setOnTabSelectedListener(this);


        //data준비
        mData = new ArrayList<>();
        mData.add(new ScreenSlideFragment());
        mData.add(ColorFragment.newInstance(Color.BLUE));
        mData.add(ColorFragment.newInstance(Color.YELLOW));
        mData.add(ColorFragment.newInstance(Color.RED));
        mData.add(ColorFragment.newInstance(Color.BLACK));


        //Adapter 준비
        mAdapter = new MyPagerAdater(getSupportFragmentManager(), mData);

        // view에 어뎁터 붙이기
        mViewPager.setAdapter(mAdapter);
        // 탭레이아웃 붙히기 (뷰페이저와 탭레이아웃 연결 하기)
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
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

        public MyPagerAdater(FragmentManager fm, List<Fragment> data) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ScreenSlidePageFragment();
                case 1:
                    return ColorFragment.newInstance(Color.BLUE);
                case 2:
                    return ColorFragment.newInstance(Color.YELLOW);
                case 3:
                    return ColorFragment.newInstance(Color.RED);
                case 4:
                    return ColorFragment.newInstance(Color.BLACK);
                default:
                    return new ScreenSlidePageFragment();
            }
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
    }
}
