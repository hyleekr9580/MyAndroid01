package kr.contentsstudio.myfirstandroidapp.examsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hoyoung on 2016-03-03.
 */
public class StudentDBManager extends SQLiteOpenHelper {
    //DB명, 테이블명, DB버전 정보를 정의한다.
    static final String DB_STUDENTS = "Students.db";
    static final String TABLE_STUDENTS = "Students";
    // SQL DB업데이트 가 발생 할 경우 DB_VERSION을 하나 올려줘야 한다.

    //static final int DB_VERSION = 1;
    // SQL DB업데이트
    static final int DB_VERSION = 2;

    Context mContext = null;
    private static StudentDBManager mDBManager = null;

    //SQLiteOpenHelper 사용시 필요 없음
    //private SQLiteDatabase mDatabase = null;


    //DB매니저 객체는 싱글톤으로 구현한다.
    //getInstance는 정적함수로 DB매니저 객체를 얻어온다.
    public static StudentDBManager getInstance(Context context) {
//        if (mDBManager == null) {
//            mDBManager = new StudentDBManager(context);
//        }

        //SQLiteOpenHelper 사용
        if (mDBManager == null) {
            mDBManager = new StudentDBManager(context, DB_STUDENTS, null, DB_VERSION);
        }
        return mDBManager;
    }

//    public StudentDBManager(Context context1, String dbStudents, Context context, int dbVersion) {

    //SQLiteOpenHelper 사용
    public StudentDBManager(Context context, String dbNmae, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbNmae, factory, version);
        mContext = context;
//        //1. 디비매니저를 생성할때 DB를 생성하고 연다.
//        mDatabase = context.openOrCreateDatabase(DB_STUDENTS, context.MODE_PRIVATE, null);
//        //2. 만일 DB에 테이블이 존재하지 않으면 생성을 한다.
//        mDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_STUDENTS +
//                "(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "number TEXT, " + "name  TEXT, " + "department TEXT, " +
//                "grade INTEGER );");
    }

    //SQLiteOpenHelper 사용
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_STUDENTS +
                "(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "number TEXT, " + "name  TEXT, " + "department TEXT, " +
                "age TEXT, " +
                "grade INTEGER );");
    }

    //SQLiteOpenHelper 사용
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// SQL DB업데이트
        if(oldVersion < newVersion){
            //기존테이블을 삭제하고 새로운 테이블을 생성 한다.
            //db.execSQL("DROP TABLE IF EXISTS" + TABLE_STUDENTS);
            db.execSQL("DROP TABLE IF EXISTS '" + TABLE_STUDENTS + "'");
                    onCreate(db);
        }
    }

    // 추가
    public long insert(ContentValues addRowValue) {
        //execSQL 함수를 이용해서 직접 SQL 문으로 레코드를 추가할 수 있다.
        // ("INSERT INTO" + TABLE_STUDENTS +
        //"VALUES("+"null, "+"'99990101',"+"'홍길동',"+"'컴퓨터',"+"3);");
        //return mDatabase.insert(TABLE_STUDENTS, null, addRowValue);

        //SQLiteOpenHelper 사용
        return getWritableDatabase().insert(TABLE_STUDENTS, null, addRowValue);
    }

    // 추가 함수 구현
    public long insertAll(ContentValues[] values) {
//        Log.d("superdroid", "START insertAll.........................................");
//
//        // 트랜잭션 시작
//        // 만드는 시간을 3배 줄여 준다. 기존 6초 정도 트랜잭션 적용 1초
//        mDatabase.beginTransaction();
//
//        for (ContentValues contentValues : values) {
//            mDatabase.insert(TABLE_STUDENTS, null, contentValues);
//        }
//
//        // 트랜잭션 끝
//        mDatabase.setTransactionSuccessful();
//        mDatabase.endTransaction();
//        Log.i("superdroid", "END insertAll...........................................");
//        return values.length;

        //SQLiteOpenHelper 사용

        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        for (ContentValues contentValues : values) {
            db.insert(TABLE_STUDENTS, null, contentValues);

        }
        db.setTransactionSuccessful();
        db.endTransaction();

        return values.length;
    }


    // 쿼리
    public Cursor query(String[] colums, String selection,
                        String[] selectionArgs, String groupBy,
                        String Having, String orderBy) {
        //다음과 같이 rawQuery 함수를 이용해서 직접 SELECT 문으로 추가된 레코드 정보를 얻어 올 수도 있다.
        //Cursor c = null;
        //c=mDatabase.query("SELECT * FROM" + TABLE_STUDENTS, null);
//        return mDatabase.query(TABLE_STUDENTS, colums, selection,
//                selectionArgs, groupBy,
//                Having, orderBy);

        //SQLiteOpenHelper 사용
        return getReadableDatabase().query(TABLE_STUDENTS, colums, selection, selectionArgs, groupBy, Having, orderBy);

    }


    //SQLiteOpenHelper 사용
    public int update(ContentValues updateRowValue, String whereClause, String[] whereArgs) {
        return getWritableDatabase().update(TABLE_STUDENTS, updateRowValue, whereClause, whereArgs);
    }


    public int delete(String whereClause, String[] whereArgs) {
        //다음과 같이 execSQL 함수를 이용해서 직접 SQL 레코드를 삭제 할 수 있다.
        //mStudentsDB.execSQL("DELETE FROM" + TABLE_STUDENTS);
        //return mDatabase.delete(TABLE_STUDENTS, whereClause, whereArgs);

        //SQLiteOpenHelper 사용
        return getWritableDatabase().delete(TABLE_STUDENTS, whereClause, whereArgs);
    }
}
