package kr.contentsstudio.myfirstandroidapp.json_parsing;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import kr.contentsstudio.myfirstandroidapp.R;
import kr.contentsstudio.myfirstandroidapp.json_parsing.models.SampleJoaModel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GsonActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = GsonActivity.class.getSimpleName();
    private ListView mListView;
    private SwipeRefreshLayout mSwipRefresf;
    private SampleJoaAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);

        new SampleJoaAsyncTask().execute();

        mListView = (ListView) findViewById(R.id.list_view);
        mSwipRefresf = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mSwipRefresf.setOnRefreshListener(this);
        mSwipRefresf.setProgressBackgroundColorSchemeColor(Color.WHITE);

        mAdapter = new SampleJoaAdapter(null);
        mListView.setAdapter(mAdapter);
//        SampleJoaAdapter adapter = new SampleJoaAdapter();

    }

    @Override
    public void onRefresh() {
        new SampleJoaAsyncTask().execute();
    }

    private class SampleJoaAsyncTask extends AsyncTask<Void, Void, List<SampleJoaModel>> {
        private OkHttpClient client = new OkHttpClient();

        @WorkerThread
        @Override
        protected List<SampleJoaModel> doInBackground(Void... params) {
            List result = null;
            try {

                JSONArray jsonarray = new JSONArray(parsing("http://clubcoffee.cafe24.com/home/SampleJoa_Test/query.php"));
                Gson gson = new Gson();
                result = gson.fromJson(jsonarray.toString(), new TypeToken<List<SampleJoaModel>>() {
                }.getType());
                Log.d(TAG, jsonarray.toString());

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @UiThread
        @Override
        protected void onPostExecute(List<SampleJoaModel> result) {
            if (result != null) {
                mAdapter.mData = result;
//                Toast.makeText(GsonActivity.this, "갱신이 완료 되었습니다.", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
                mListView.setAdapter(mAdapter);
            }
            if (mSwipRefresf.isRefreshing()) {
                mSwipRefresf.setRefreshing(false);
            }
        }

        private String parsing(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }
    }

    private class SampleJoaAdapter extends BaseAdapter {

        private List<SampleJoaModel> mData;
        String mUrl = "http://clubcoffee.cafe24.com/home/SampleJoa_Test/sample/";

        public SampleJoaAdapter(List<SampleJoaModel> data) {
            mData = data;
        }


        @Override
        public int getCount() {
            if (mData == null) {
                return 0;
            }
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_samplejoa, parent, false);
                holder = new ViewHolder();
                holder.sp_img1 = (ImageView) convertView.findViewById(R.id.sp_img1);
                holder.sp_title = (TextView) convertView.findViewById(R.id.sp_title);
                holder.sp_title2 = (TextView) convertView.findViewById(R.id.sp_title2);
                holder.sp_img2 = (ImageView) convertView.findViewById(R.id.sp_img2);

                holder.sp_info1 = (TextView) convertView.findViewById(R.id.sp_info1);
                holder.sp_info2 = (TextView) convertView.findViewById(R.id.sp_info2);
                holder.sp_info3 = (TextView) convertView.findViewById(R.id.sp_info3);
                holder.sp_info4 = (TextView) convertView.findViewById(R.id.sp_info4);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();

            }

            SampleJoaModel samplejoamodel = (SampleJoaModel) getItem(position);

            Log.e(TAG, "=============================================" + samplejoamodel.getSp_img1());
            Log.e(TAG, "=============================================" + samplejoamodel.getSp_img2());

            String sp_img1 = samplejoamodel.getSp_img1();
            String sp_img2 = samplejoamodel.getSp_img2();

            //isEmpty 는 빈값을 체크 합니다.
            if (TextUtils.isEmpty(sp_img2)) {

                holder.sp_img2.setVisibility(View.GONE);

            } else {
                holder.sp_img2.setVisibility(View.VISIBLE);
                Glide.with(parent.getContext())
                        .load(mUrl + sp_img2)
                        .into(holder.sp_img2);
            }


            holder.sp_title.setText(samplejoamodel.getSp_title());
            holder.sp_title2.setText(samplejoamodel.getSp_title2());
            holder.sp_info1.setText(samplejoamodel.getSp_info1());
            holder.sp_info2.setText(samplejoamodel.getSp_info2());
            holder.sp_info3.setText(samplejoamodel.getSp_info3());
            holder.sp_info4.setText(samplejoamodel.getSp_info4());

            Glide.with(parent.getContext())
                    .load(mUrl + sp_img1)
//                    .error(R.mipmap.ic_launcher)
                    .into(holder.sp_img1);


            return convertView;


        }

        private class ViewHolder {
            TextView sp_title;
            TextView sp_title2;
            TextView sp_info1;
            TextView sp_info2;
            TextView sp_info3;
            TextView sp_info4;
            ImageView sp_img1;
            ImageView sp_img2;
        }


    }

}
