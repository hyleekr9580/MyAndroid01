package kr.contentsstudio.myfirstandroidapp.notepad.facade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import kr.contentsstudio.myfirstandroidapp.notepad.db.MemoContract;
import kr.contentsstudio.myfirstandroidapp.notepad.db.MemoDbHqlper;

/**
 * Created by hoyoung on 2016-03-10.
 * Memo 테이블에 대한 작업을 편하게 해주는 클래스
 */
public class MemoFacade {

    private MemoDbHqlper mHelper;
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public MemoFacade(Context context) {
        mHelper = new MemoDbHqlper(context);
    }

    /**
     * 메모를 저장
     *
     * @param title
     * @param memo
     * @return
     */
    public long insertMemo(String title, String memo) {
        // 쓰기 모드로 db 저장소를 얻기
        SQLiteDatabase db = mHelper.getWritableDatabase();

        // INSERT INTO Memo(title, memo, date) VALUES ('title', 'memo', '2000-10-10');
        // db.execSQL("INSERT INTO Memo(title, memo, date) VALUES ('title', 'memo', '2000-10-10')");

        String date = mSimpleDateFormat.format(new Date());

        ContentValues values = new ContentValues();
        if (title.length() != 0) {
            values.put(MemoContract.MemoEntry.COLUM_NAME_TITLE, title);
        }
        values.put(MemoContract.MemoEntry.COLUM_NAME_MEMO, memo);
        values.put(MemoContract.MemoEntry.COLUM_NAME_DATE, date);

        if (title.length() != 0 || memo.length() != 0) {
            return db.insert(MemoContract.MemoEntry.TABLE_NAME,
                    null,
                    values);
        }

        return -1;
    }

    /**
     * 모든 메모를 얻기
     *
     * @return
     */
    public Cursor queryAllMemos() {
        SQLiteDatabase db = mHelper.getReadableDatabase();

        // select * from memo;
        return db.query(MemoContract.MemoEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
    }
}