package kr.contentsstudio.myfirstandroidapp.utils;

import android.content.Intent;
import android.provider.MediaStore;

/**
 * Created by hoyoung on 2016-03-18.
 */
public class IntentUtil {


    /**
     * 메오리에 저장된 이미지를 선택하는 INTENT
     *
     * @return
     */
    public static Intent getPickImageIntent() {
//        Intent intent = new Intent(Intent.ACTION_PICK,
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.setType("image/*");
//        return intent;
        return new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    }

    public static Intent getCameraIntent() {

        return new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

    }

    // 내보내기
    public static Intent sendMessageIntent(String message) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        return intent;
    }


}
