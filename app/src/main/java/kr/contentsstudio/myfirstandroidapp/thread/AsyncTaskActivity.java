package kr.contentsstudio.myfirstandroidapp.thread;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kr.contentsstudio.myfirstandroidapp.R;

/**
 * Created by hoyoung on 2016-02-24.
 */
public class AsyncTaskActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mCountTextView;
    private Button mButton;
    private MyAsyncTask mTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        mCountTextView = (TextView) findViewById(R.id.count_text);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);

        // MyAsyncTask를 실행한다~~~~
        //<Void, Void, Void> 처음값에 인자를 넣어 주면 execute();값에 넣어 줄수 있다.
        // 1. 반드시 메인(UI) 스레드에서 실행을 해야 한다.
        // 2. new 자체의 생성도 메인(UI)스레드에서 생성을 해야 한다.
        // 3. execute도 메인(UI)스레드에서 생성을 해야 한다.
        // 4. 임의로 절대 콜백을 호출하지 말것
        // 5. MyAsyncTask()은 반드시 한번만 실행이 가능하다.
        // 직렬실행 순서대로~~
        mTask = new MyAsyncTask();
        // 순서대로 실행
        mTask.execute();
    }

    @Override
    public void onClick(View v) {

        // 클릭했을때 실행 해보자~~~`
        // mTask().execute();
        // 이렇게 또 실행을 시키면 안된다. 어플이 죽는다.
        // 클릭할때마다. 할려면 계속 new를 해야 한다.
        // new MyAsyncTask().execute();
        // 현재는 4.0 부터는 하나 실행이 되고 하나 실행이 된다.
        // 4.0 밑 에는 여러가지가 실행이 되었다. 아래와 같이 작성하면 한거번에 여러개 실행이 된다.
        // 동시에 AsyncTask 가 동작 되도록 실행
        // 병렬실행
        // 어러개를 불러와야 할때~~ 문서 500개 등,,,
        //new MyAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        mTask.cancel(true);

    }

    //Void, Void, Void 처음값, 중간값, 끝값
    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {
        private int mmCount = 0;
        private ProgressDialog mmDialog;
        private static final int MAX_PROGRESS = 100;

        //다이얼 로그 하나 실행
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mmDialog = new ProgressDialog((AsyncTaskActivity.this));
            mmDialog.setTitle("기다려~~");
            mmDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mmDialog.setProgress(0);
            mmDialog.setMax(MAX_PROGRESS);

            // 다이얼 로그 멈추지 않게~~~
            //mmDialog.setCancelable(false);
            mmDialog.show();
        }


        //여기는~~~~백그라운드 스레드
        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < 10; i++) {
                mmCount++;

                // 여기서 UI갱신을 하고 싶다~~~~ 어떻게 해야 하냐~~
                // onProgressUpdate를 호출 하는 부분 입니다~~~직접 호출을 할 수 없다
                // onProgressUpdate(); 절대로 직접 콜백을 호출 금지
                // 임의로 절대 콜백을 호출하지 말것
                mmDialog.incrementProgressBy(10);
                publishProgress();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;

        }

        // 오버로드 하나하고~~~
        // ...은 []배열과 같다. 배열일수도 있고 아닐 수 있다.
        // 배열을 보낼수도 있고 배열이 아닐수도 있다.
        //
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            //메인(UI)스레드 이다~~~
            mCountTextView.setText("" + mmCount);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mmDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();


        }
    }
}


