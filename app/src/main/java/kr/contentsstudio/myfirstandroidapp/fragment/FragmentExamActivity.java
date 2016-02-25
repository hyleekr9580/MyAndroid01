package kr.contentsstudio.myfirstandroidapp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import kr.contentsstudio.myfirstandroidapp.R;

public class FragmentExamActivity extends AppCompatActivity implements View.OnClickListener, ColorFragment.ColorDataReceiveListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_exam);

        // 무조건 Support 있는 것으로 사용을 한다.
        // 예제에 getFragmentManager()가 있어도 Support 붙힌다.
//        ColorFragment frag1 = (ColorFragment) getSupportFragmentManager().findFragmentById(R.id.frag1);
//        ColorFragment frag2 = (ColorFragment) getSupportFragmentManager().findFragmentById(R.id.frag2);
//
//        frag1.setColor(Color.BLUE);
//        frag2.setColor(Color.YELLOW);

        ColorFragment frag1 = (ColorFragment) getSupportFragmentManager().findFragmentById(R.id.frag1);
        frag1.setColor(Color.BLUE);

        Button button = (Button) findViewById(R.id.add_frag_button);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // 랜덤한 컬러 만들기
        int randomColor = Color.rgb((int)
                (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));

        // 프래그먼트를 코드로 추가 한다.
        ColorFragment fragment = ColorFragment.newInstance(randomColor);
        fragment.setOnColorDataReceiveListener(this);


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contents, fragment)
                .commit();
    }

    @Override
    public void onDataReceive(String data) {
    }
}
