package kr.contentsstudio.myfirstandroidapp.com.listview;

/**
 * Created by 호영 on 2016-02-17.
 */

//모델클래스이다/
    /*
    m(모델), v(뷰), c(컨트롤러)
    mvc 패턴이라고 한다.
    모델 : 데이터를 담을 만한 클래스 타입을 만들자
     */
public class Myitem {
    //데이터만 담을 수 있게 만든다.
    private int imageRes;
    private String title;
    private String description;


    public Myitem(int imageRes, String title, String description) {
        this.imageRes = imageRes;
        this.title = title;
        this.description = description;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
