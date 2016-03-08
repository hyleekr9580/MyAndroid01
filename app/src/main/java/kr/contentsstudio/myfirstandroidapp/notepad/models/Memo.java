package kr.contentsstudio.myfirstandroidapp.notepad.models;

/**
 * Created by hoyoung on 2016-03-08.
 * 메모의 모델 클래스
 */
public class Memo {

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
}
