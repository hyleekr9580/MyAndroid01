package kr.contentsstudio.myfirstandroidapp.notepad.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hoyoung on 2016-03-09.
 * DB를 사용하게 해주는 헬퍼
 */
public class MemoDbHqlper extends SQLiteOpenHelper {


    //컴럼명은 상수로 따로 관리하는게 좋다. 관리적인 측면에서..MemoContract.class
    private static final String DATABASE_NAME = "memoDb.db";
    private static final int DATABASE_VERSION = 1;



    //  생성자 만들기
    //  context만 생성자 에서 사용을  한다.
    //  테이블을 만들어 주는 것만 한다.
    public MemoDbHqlper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //  최초의 DB(테이블을 생성 합니다.
        //  호출 되는 타이밍은 최초로 DB가 열리는 타이밍에 생성을 합니다.
        db.execSQL(MemoContract.MemoEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
