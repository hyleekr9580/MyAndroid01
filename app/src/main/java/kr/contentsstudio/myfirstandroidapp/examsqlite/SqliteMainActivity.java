package kr.contentsstudio.myfirstandroidapp.examsqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import kr.contentsstudio.myfirstandroidapp.R;

public class SqliteMainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mDisplayDbEt = null;
    public StudentDBManager mDbManager = null;
    private Button mInsert;
    private Button mQuery;
    private Button mUpdate;
    private Button mDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_main);

//        SD카드 저장
        String str = Environment.getExternalStorageState();
        if (str.equals(Environment.MEDIA_MOUNTED)) {

            String dirPath = "/sdcard/example";
            File file = new File(dirPath);
            if (!file.exists())  // 원하는 경로에 폴더가 있는지 확인
                file.mkdirs();
        } else {
            Toast.makeText(this, "SD Card 인식 실패", Toast.LENGTH_SHORT).show();
        }


        mDisplayDbEt = (EditText) findViewById(R.id.edit_text);
        mDbManager = StudentDBManager.getInstance(this);

        mInsert = (Button) findViewById(R.id.insert);
        mInsert.setOnClickListener(this);
        mQuery = (Button) findViewById(R.id.query);
        mQuery.setOnClickListener(this);
        mUpdate = (Button) findViewById(R.id.update);
        mUpdate.setOnClickListener(this);
        mDelete = (Button) findViewById(R.id.delete);
        mDelete.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            // DB추가하기
//            case R.id.insert:
//                ContentValues addRowValue = new ContentValues();
//                addRowValue.put("number", "99990101");
//                addRowValue.put("name", "홍길동");
//                addRowValue.put("department", "컴퓨터");
//                addRowValue.put("grade", 3);
//
//                long insertRecordId = mDbManager.insert(addRowValue);
//                mDisplayDbEt.setText("레코드추가 : " + insertRecordId);
//                break;
            // 여러개의 DB추가하기
            case R.id.insert: {

                ContentValues[] insertDataList = new ContentValues[1000];
                for (int i = 0; i < 10; i++) {
                    ContentValues addRowValue = new ContentValues();

                    addRowValue.put("number", "99990101");
                    addRowValue.put("name", "홍길동");
                    addRowValue.put("department", "컴퓨터");
                    //나이추가
                    addRowValue.put("age", "34");
                    addRowValue.put("grade", 3);

                    insertDataList[i] = addRowValue;
                }
                //1000개의 DB를 삽입한다.
                mDbManager.insertAll(insertDataList);
                mDisplayDbEt.setText("10개의 DB를 추가 하였습니다. ");
                break;
            }
            //DB처리방법
            case R.id.query: {
                String[] colums = new String[]{"_id", "number", "name", "department", "age","grade"};
                Cursor c = mDbManager.query(colums, null, null, null, null, null);
                if (c != null) {
                    mDisplayDbEt.setText("");
                    while (c.moveToNext()) {
                        int id = c.getInt(0);
                        String number = c.getString(1);
                        String name = c.getString(2);
                        String department = c.getString(3);
                        String age = c.getString(4);
                        int grade = c.getInt(5);

                        mDisplayDbEt.append("id : " + id + "\n" +
                                "number : " + number + "\n" +
                                "name : " + name + "\n" +
                                "department : " + department + "\n" +
                                "age : " + age + "\n" +
                                "grade : " + grade + "\n" +
                                "----------------------" + "\n");
                    }
                    mDisplayDbEt.append("\n Total : " + c.getCount());
                    c.close();
                }
                break;
            }

            // 특절 레코드 수정하기
            case R.id.update: {
                ContentValues updateRowValue = new ContentValues();
                updateRowValue.put("name", "고길동");

                int updateRecordCnt = mDbManager.update(updateRowValue, "number = 99990101", null);
                mDisplayDbEt.setText("레코드갱신 : " + updateRecordCnt);

                break;
            }

            //삭제하기
            case R.id.delete: {
                int deleteRecordCnt = mDbManager.delete(null, null);
                mDisplayDbEt.setText("삭제된 레코드 수 : " + deleteRecordCnt);
                break;
            }


        }
    }
}
