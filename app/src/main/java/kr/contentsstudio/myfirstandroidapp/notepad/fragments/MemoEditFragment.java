package kr.contentsstudio.myfirstandroidapp.notepad.fragments;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import kr.contentsstudio.myfirstandroidapp.R;
import kr.contentsstudio.myfirstandroidapp.notepad.db.MemoContract;
import kr.contentsstudio.myfirstandroidapp.notepad.facade.MemoFacade;
import kr.contentsstudio.myfirstandroidapp.notepad.provider.MemoContentProvider;
import kr.contentsstudio.myfirstandroidapp.utils.IntentUtil;

/**
 * 메모 작성하는 프레그먼트 입니다.
 */
public class MemoEditFragment extends Fragment {


    private static final int REQUEST_CODE_GALLERY_PICK = 1;
    private static final int REQUEST_CODE_CAMERA = 2;
    private EditText mTitleTextView;
    private EditText mMemoTextView;


    private String mTitle = "";
    private String mMemo = "";


    private boolean isEditMode;
    private long mId = -1;
    private MemoFacade mMemoFacade;
    private ImageView mImageView;

    public MemoEditFragment() {


    }

    public static MemoEditFragment newInstance(long id, String title, String memo, String image) {

        MemoEditFragment fragment = new MemoEditFragment();

        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
        bundle.putString("title", title);
        bundle.putString("memo", memo);
        bundle.putString("image", image);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mMemoFacade = new MemoFacade(getActivity());
        return inflater.inflate(R.layout.fragment_memo_edit, container, false);
    }

    //  툴바를 사용할꺼면 아래와 같이 해야 한다. 레이아웃을 들고 와야 한다.
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("메모작성하기");

        //프래그먼트에서 옵션 메뉴를 가질(사용) 수 있게 한다.
        setHasOptionsMenu(true);

        mTitleTextView = (EditText) view.findViewById(R.id.title_edit);
        mMemoTextView = (EditText) view.findViewById(R.id.memo_edit);
        mImageView = (ImageView) view.findViewById(R.id.image);

        Bundle bundle = getArguments();
        if (bundle != null) {
            isEditMode = true;

            mId = bundle.getLong("id");
            String title = bundle.getString("title");
            String memo = bundle.getString("memo");
            String image = bundle.getString("image");

            mTitle = title;
            mMemo = memo;

            mTitleTextView.setText(title);
            mMemoTextView.setText(memo);
            try {
                if (image != null) {
                    mImageView.setImageBitmap(MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.parse(image)));

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
//            삽입모드
            ContentValues values = new ContentValues();
            values.put(MemoContract.MemoEntry.COLUM_NAME_MEMO, "");
            Uri insertUri = getActivity().getContentResolver().insert(MemoContentProvider.CONTENT_URI,
                    values);
            if (insertUri != null) {
                mId = ContentUris.parseId(insertUri);
                Toast.makeText(getActivity(), "저장 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        //inflater가 넘어 온다.
        inflater.inflate(R.menu.menu_note_edit, menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                showdeleteDialog();
                break;
            case R.id.action_export:
                // TODO 내보내기
                String message = mTitleTextView.getText().toString() + "\n" +
                        mMemoTextView.getText().toString();
                Intent intent = IntentUtil.sendMessageIntent(message);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
                break;
            case R.id.action_attach:
                // TODO : 사진첨부하기 (다이얼 로그 띄우기)
                showAttachDialog();
                break;
        }
        return true;
    }

    private void showdeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("메모 삭제")
                .setMessage("메모를 삭제하시겠습니까?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    //익명 클레스
                    public void onClick(DialogInterface dialog, int which) {
                        int deleted = getActivity().getContentResolver().delete(MemoContentProvider.CONTENT_URI,
                                "_id=" + mId, null);
                        if (deleted > 0) {
                            Toast.makeText(getActivity(), "삭제 되었습니다", Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                        }
                    }
                })
                .setNegativeButton("아니오", null);
        builder.show();
    }

    private void showAttachDialog() {
        //final CharSequence[] items = {"카메라", "사진"};
        AlertDialog.Builder attachBuilder = new AlertDialog.Builder(getActivity());
        // 여기서 부터는 알림창의 속성 설정//다이어로그
        attachBuilder.setTitle("이미지 추가하기");
        attachBuilder.setSingleChoiceItems(R.array.item_attch_image, -1, new DialogInterface.OnClickListener() {
            //setItems(items, new DialogInterface.OnClickListener() {    // 목록 클릭시 설정
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        //카메라
                        startActivityForResult(IntentUtil.getCameraIntent(), REQUEST_CODE_CAMERA);
                        break;
                    case 1:
                        //이미지선택
                        startActivityForResult(IntentUtil.getPickImageIntent(), REQUEST_CODE_GALLERY_PICK);

                        break;
                }
            }
        });

        //AlertDialog dialog = attachBuilder.create();    // 알림창 객체 생성
        attachBuilder.show();    // 알림창 띄우기
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //받는쪽에서
        if (requestCode == REQUEST_CODE_GALLERY_PICK && resultCode == Activity.RESULT_OK && data != null) {
            //이미지 선택하기(sql에 URI값을 추가를 한다.
            //  DB를 저장한다.
            ContentValues values = new ContentValues();
            values.put(MemoContract.MemoEntry.COLUM_NAME_IMAGE, data.getDataString());

            //where조건
            String where = "_id=" + mId;
            int updated = getActivity().getContentResolver().update(MemoContentProvider.CONTENT_URI,
                    values,
                    where,
                    null);

            if (updated > 0) {
                try {
                    mImageView.setImageBitmap(MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData()));
                } catch (IOException e) {
                    Toast.makeText(getActivity(), "에러!!!!!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK && data != null) {
            // 사진 촬영
            Toast.makeText(getActivity(), data.getDataString(), Toast.LENGTH_SHORT).show();
            Bitmap bitmap = data.getParcelableExtra("data");
            if (bitmap != null) {
                try {
                    FileOutputStream fos = new FileOutputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Memo" + System.currentTimeMillis() + ".jpg");
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            mImageView.setImageBitmap(bitmap);
        }

    }


    //여기서 메모를 작성해서 DB에 저장을 한다.
    @Override
    public void onPause() {
        super.onPause();


        String title = mTitleTextView.getText().toString();
        String memo = mMemoTextView.getText().toString();
        // 수정모드
        if (!(mTitle.equals(title) && mMemo.equals(memo))) {
            ContentValues values = new ContentValues();
            values.put(MemoContract.MemoEntry.COLUM_NAME_TITLE, title);
            values.put(MemoContract.MemoEntry.COLUM_NAME_MEMO, memo);
            int updated = getActivity().getContentResolver().update(MemoContentProvider.CONTENT_URI,
                    values, "_id=" + mId, null);

            if (updated > 0) {
                Toast.makeText(getActivity(), "수정 되었습니다.", Toast.LENGTH_SHORT).show();
            }

        }
    }
}

