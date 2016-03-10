package kr.contentsstudio.myfirstandroidapp.notepad.db;

import android.provider.BaseColumns;

/**
 * Created by hoyoung on 2016-03-09.
 */
public class MemoContract {

    //BaseColumns 상속을 받는다.
    public  static abstract class MemoEntry implements BaseColumns{
        //메모에 대한 스키마
        public static final String TABLE_NAME = "Memo";
        public static final String COLUM_NAME_TITLE = "title";
        public static final String COLUM_NAME_MEMO = "memo";
        public static final String COLUM_NAME_DATE = "date";

        //쿼리문을 작성해 놔야.... 어떤 쿼리인지 확인이 가능하다.
        //CREATE TABLE Memo (_id INTEGER AUTOINCREMENT PRIMARY KEY, title TEXT DEFAULT '제목없음', memo TEXT, date TEXT NOT NULL );
        public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + MemoEntry.TABLE_NAME + " (" +
                MemoEntry._ID + " INTEGER AUTO INCREMENT PRIMARY KEY, " +
                MemoEntry.COLUM_NAME_TITLE + " TEXT DEFAULT '제목없음', " +
                MemoEntry.COLUM_NAME_MEMO + " TEXT, " +
                MemoEntry.COLUM_NAME_DATE + " TEXT NOT NULL" +
                ");";
    }
}
