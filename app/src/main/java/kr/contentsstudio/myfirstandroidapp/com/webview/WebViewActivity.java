package kr.contentsstudio.myfirstandroidapp.com.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kr.contentsstudio.myfirstandroidapp.R;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEditddress;
    private Button mBtnAccess, mLotto;
    private WebView mWebView, mWebViewLotto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mEditddress = (EditText) findViewById(R.id.address_edit);

        mBtnAccess = (Button) findViewById(R.id.access_btn);
        mBtnAccess.setOnClickListener(this);

        mLotto = (Button) findViewById(R.id.lotto_btn);
        mLotto.setOnClickListener(this);

        mWebView = (WebView) findViewById(R.id.webview);


        //웹셋팅
        //이것을 설정하지 않으면 다른 웹브라우저가 살행이 됩니다.
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.access_btn:
                mWebView.loadUrl(mEditddress.getText().toString());
                if(mEditddress.getText().length() == 0){
                    Toast.makeText(WebViewActivity.this, "URL을 입력 하세요.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.lotto_btn:
                mWebView.loadUrl("https://m.search.naver.com/search.naver?query=%EB%A1%9C%EB%98%90%EB%8B%B9%EC%B2%A8%EB%B2%88%ED%98%B8&where=m&sm=mtp_hty");
                break;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }


}

