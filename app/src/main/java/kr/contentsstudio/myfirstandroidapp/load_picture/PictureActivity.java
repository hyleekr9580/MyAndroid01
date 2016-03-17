package kr.contentsstudio.myfirstandroidapp.load_picture;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;

import kr.contentsstudio.myfirstandroidapp.R;

public class PictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
    }

    //어댑터를 만들어야 한다.

    private static class PictureCursorAdapter extends CursorAdapter {

        private final LayoutInflater inflator;

        public PictureCursorAdapter(Context context, Cursor c) {
            super(context, c, false);
            inflator = LayoutInflater.from(context);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            ViewHolder holder = new ViewHolder();
            View view = inflator.inflate(R.layout.item_image_list, parent, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            return null;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

        }

        static class ViewHolder {
            ImageView imageView;
        }
    }
}
