package kr.contentsstudio.myfirstandroidapp.notepad.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import kr.contentsstudio.myfirstandroidapp.notepad.db.MemoContract;
import kr.contentsstudio.myfirstandroidapp.notepad.db.MemoDbHqlper;

public class MemoContentProvider extends ContentProvider {

    //프로바이더 하나당 테이블 하나를 관리하는게 편하다. (매칭해서 사용해야 한다. )
    //프로바이더 이름
    //URI를 정의해야 한다, 833page
    private static final String AUTHORITY = "kr.contentsstudio.myfirstandroidapp.Provider";

    //URI path
    // 프로바이더 Memo 테이블
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/"
            //+ MemoContract.MemoEntry.TABLE_NAME); 테이블 이름을 넣는다.
            + MemoContract.MemoEntry.TABLE_NAME);

    // TODO : 두번째 작성
    // 위에꺼는 1개의 아이템 요청 MIME 타입
    // 보통 디폴트가 전체데이터 가져오는거다.
    private static final String CONTENT_TYPE = "vnd.android.cursor.item/vnd.kr.contentsstudio.myfirstandroidapp.Provider."
            + MemoContract.MemoEntry.TABLE_NAME;
    // 밑에꺼는 여러개의 아이템 요청 MIME 타입니다.
    private static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.dir/vnd.kr.contentsstudio.myfirstandroidapp.Provider."
            + MemoContract.MemoEntry.TABLE_NAME;

    //생성자보다 빨리 호출이 된다.
    private static UriMatcher sUriMatcher;

    private static final int ALL = 1;
    private static final int ITEM = 2;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        //content://kr.contentsstudio.myfirstandroidapp.Provider/Memo (1번패턴)
        // 그냥하면 다~~~ 달라~~~
        sUriMatcher.addURI(AUTHORITY, MemoContract.MemoEntry.TABLE_NAME, ALL);

        //content://kr.contentsstudio.myfirstandroidapp.Provider/Memo/#3 (2번패턴)
        // _id의 3번째 부분만 달라~
        sUriMatcher.addURI(AUTHORITY, MemoContract.MemoEntry.TABLE_NAME + "/#", ITEM);

    }


    private MemoDbHqlper mMemoDbHelper;


    //ContentProvider 상속받고 있고 기본생성자 하나가 있다.
    public MemoContentProvider() {
    }


    @Override
    public boolean onCreate() {
        // DbHelper을 생성한다.
        // 파사드와 똑같다. 생성자에서 하는일은 DbHelper을 초기화 하는 일입니다.
        // TODO: Implement this to initialize your content provider on startup.
        mMemoDbHelper = new MemoDbHqlper(getContext());
        return true;
    }

    //두번째로 Uri를 정의해야 한다. 상수로 정의한다.


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        switch (sUriMatcher.match(uri)) {
            case ALL:

                break;
            case ITEM:
                selection = "_id=" + ContentUris.parseId(uri);
                selectionArgs = null;
                break;
            case UriMatcher.NO_MATCH:
                return 0;
        }
        SQLiteDatabase db = mMemoDbHelper.getWritableDatabase();
        int delete = db.delete(MemoContract.MemoEntry.TABLE_NAME,
                selection,
                selectionArgs);
        if (delete > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return delete;


    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.

        // 이프로 바이더가 처리 할 수 있는 패턴인지 검사를 한다.
        switch (sUriMatcher.match(uri)) {
            case ALL:
                return CONTENT_TYPE;

            case ITEM:
                return CONTENT_ITEM_TYPE;

        }
        throw new IllegalArgumentException("Unknown URI" + uri);
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.

        //리턴타입이 uri 타입니다.
        switch (sUriMatcher.match(uri)) {
            case ALL:

                long id = mMemoDbHelper.getWritableDatabase().insert(MemoContract.MemoEntry.TABLE_NAME,
                        null,
                        values);

                if (id > 0) {
                    // contents://kr.contentsstudio.myfirstandroidapp.Provider/Memo#10
                    Uri returnUri = ContentUris.withAppendedId(CONTENT_URI, id);
                    // 변경을 통지해 준다.
                    getContext().getContentResolver().notifyChange(returnUri, null);
                    return returnUri;
                }
                break;

        }


        return null;
    }


    @Override
    // 파사드에 있는 문을 가ㅣㅈ고와서 사용하면 된다.
    // 밖으로 던질 수 있는 것들은 던진다.
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.

        // 현재 all / ITEM 두가지 패턴이 있기 때문에 두가지가 들어 왔을때 처리를 해줘야 한다.
        switch (sUriMatcher.match(uri)) {
            case ALL:
                //혹시 전체데이터를 던지면서 where 조건을 사용할 경우 사용하는 것을 막을려면 막아 버리면 된다.
                //selection = "";

                // 외부에서 조건이 들어와도 막지 말고 풀어주자~
                // ALL이면 아무처리 안해도 된다.
                break;
            case ITEM:

                //ITEM의 경우 _ID를 주면 된다.
                //url의 #두의 숫자 (_id)만 뽑아서 조건문을 완성 한다.
                selection = "_id=" + ContentUris.parseId(uri);
                selectionArgs = null;
                break;
            case UriMatcher.NO_MATCH:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        SQLiteDatabase db = mMemoDbHelper.getReadableDatabase();

        // select * from memo;
        Cursor cursor = db.query(MemoContract.MemoEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        //  커서를 감시대상으로 설정 한다.
        cursor.setNotificationUri(getContext().getContentResolver(),uri);

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.

        switch (sUriMatcher.match(uri)) {
            case ALL:

                break;
            case ITEM:
                selection = "_id=" + ContentUris.parseId(uri);
                selectionArgs = null;
                break;
            case UriMatcher.NO_MATCH:
                return 0;
        }
        SQLiteDatabase db = mMemoDbHelper.getWritableDatabase();

        int update = db.update(MemoContract.MemoEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        if (update > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return update;
//        return db.update(MemoContract.MemoEntry.TABLE_NAME,
//                values,
//                selection,
//                selectionArgs);

    }
}
