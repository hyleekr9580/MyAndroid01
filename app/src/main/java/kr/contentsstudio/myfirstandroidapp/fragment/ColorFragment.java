package kr.contentsstudio.myfirstandroidapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import kr.contentsstudio.myfirstandroidapp.R;


//Fragment를 상속 받고 있다.
public class ColorFragment extends Fragment {

    private ColorDataReceiveListener mListener;


    public interface ColorDataReceiveListener {
        void onDataReceive(String data);
    }


    private ImageView mImageView;

    //사용하지는 않는다.
    public ColorFragment() {
        // Required empty public constructor
    }

    //생성자 역활을 한다.
    public static ColorFragment newInstance(int color) {

        ColorFragment fragment = new ColorFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("color", color);
        fragment.setArguments(bundle);
        return fragment;
    }


    //inflater 가 있으니까~ 메소드를 가지고 특정 레이아웃을 불러 올 수 있다.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_color, container, false);

    }

    //사용해도 되고 안해도 된다. 사용은 마음대로~ 이미지뷰의 색상을 변경하는것으로 사용한다.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mImageView = (ImageView) view.findViewById(R.id.color_image);

        //색상을 받았다~~~
        Bundle bundle = getArguments();
        if (bundle != null) {
            int color = bundle.getInt("color");
            mImageView.setBackgroundColor(color);

            //액티비티로 콜백을 발생 시킨다...
            if (mListener != null) {
                mListener.onDataReceive(String.valueOf(color));

            }
        }
    }

    //메소드 추가 메소드에서 컬러값 변경하면 onViewCreated 받는다.
    public void setColor(int color) {
        mImageView.setBackgroundColor(color);

    }

    //연결하는 메소드
    public void setOnColorDataReceiveListener(ColorDataReceiveListener listener) {
        mListener = listener;


    }
}
