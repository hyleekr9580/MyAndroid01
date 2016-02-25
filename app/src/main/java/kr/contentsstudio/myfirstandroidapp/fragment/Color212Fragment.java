package kr.contentsstudio.myfirstandroidapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.contentsstudio.myfirstandroidapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Color212Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Color212Fragment extends Fragment {


    public Color212Fragment() {
        // Required empty public constructor
    }

    public static Color212Fragment newInstance() {
        Color212Fragment fragment = new Color212Fragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_color212, container, false);
    }

}
