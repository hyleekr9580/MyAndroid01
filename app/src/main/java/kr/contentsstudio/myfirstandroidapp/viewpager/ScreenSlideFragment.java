package kr.contentsstudio.myfirstandroidapp.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.contentsstudio.myfirstandroidapp.R;

/**
 * Created by hoyoung on 2016-02-23.
 */
public class ScreenSlideFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_slide_paga, container, false);
        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);


    }
}
