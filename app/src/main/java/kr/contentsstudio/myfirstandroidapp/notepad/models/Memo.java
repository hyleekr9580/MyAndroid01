package kr.contentsstudio.myfirstandroidapp.notepad.models;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import kr.contentsstudio.myfirstandroidapp.notepad.db.MemoContract;

/**
 * Created by hoyoung on 2016-03-08.
 * 메모의 모델 클래스
 */
public class Memo {

    private long id;
    private String title;
    private String memo;
    private String date;

    public Memo(String title, String memo, String date) {
        this.date = date;
        this.memo = memo;
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static List<Memo> fetchCursor(Cursor cursor) {
        List<Memo> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUM_NAME_TITLE));
            String memo = cursor.getString(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUM_NAME_MEMO));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUM_NAME_DATE));
            list.add(new Memo(title, memo, date));
        }
        return list;
    }

    public static Memo cursorToMemo(Cursor cursor) {
        String title = cursor.getString(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUM_NAME_TITLE));
        String memo = cursor.getString(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUM_NAME_MEMO));
        String date = cursor.getString(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUM_NAME_DATE));
        return new Memo(title, memo, date);
    }
}
