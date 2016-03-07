package kr.contentsstudio.myfirstandroidapp.json_parsing;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import kr.contentsstudio.myfirstandroidapp.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JsonParsingActivity extends AppCompatActivity {

    private List<? extends Map<String, ?>> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_parsing);

        ListView listView = (ListView) findViewById(R.id.list);
        SimpleAdapter adapter = new SimpleAdapter(this, mData, android.R.layout.simple_expandable_list_item_2,
                new String[]{"name", "agr"},
                new int[]{android.R.id.text1, android.R.id.text2});

        new Thread(new Runnable() {
            @Override
            public void run() {

                //파싱시작
                try {
                    String result = parsing("http://oh8112191.cafe24.com/test.json");
                    //토스트는 스레드 안에서 사용 할 수 없다.
                    //Toast.makeText(JsonParsingActivity.this, "aaaas", Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //    샘플코드
    OkHttpClient client = new OkHttpClient();

    String parsing(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private class ParsingAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }
    }

}
