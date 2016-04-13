package kr.contentsstudio.myfirstandroidapp.json_parsing.models;

/**
 * Created by hoyoung on 2016-04-12.
 */
public class SampleJoaModel {

    private String sp_title;
    private String sp_title2;
    private String sp_info1;
    private String sp_info2;
    private String sp_info3;
    private String sp_info4;
    private String sp_img1;
    private String sp_img2;

    public String getSp_img2() {

        return sp_img2;
    }

    public void setSp_img2(String sp_img2) {
        this.sp_img2 = sp_img2;
    }

    public String getSp_img1() {

        return sp_img1;
    }

    public void setSp_img1(String sp_img1) {
        this.sp_img1 = sp_img1;
    }

    public String getSp_info4() {
        return sp_info4;
    }

    public void setSp_info4(String sp_info4) {
        this.sp_info4 = sp_info4;
    }

    public String getSp_info3() {
        return sp_info3;
    }

    public void setSp_info3(String sp_info3) {
        this.sp_info3 = sp_info3;
    }

    public String getSp_info2() {
        return sp_info2;
    }

    public void setSp_info2(String sp_info2) {
        this.sp_info2 = sp_info2;
    }

    public String getSp_info1() {
        return sp_info1;
    }

    public void setSp_info1(String sp_info1) {
        this.sp_info1 = sp_info1;
    }

    public String getSp_title2() {
        return sp_title2;
    }

    public void setSp_title2(String sp_title2) {
        this.sp_title2 = sp_title2;
    }

    public String getSp_title() {
        return sp_title;
    }

    public void setSp_title(String sp_title) {
        this.sp_title = sp_title;
    }


    @Override
    public String toString() {
        return "SampleJoaModel{" +
                "sp_title='" + sp_title + '\'' +
                ", sp_title2='" + sp_title2 + '\'' +
                ", sp_info1='" + sp_info1 + '\'' +
                ", sp_info2='" + sp_info2 + '\'' +
                ", sp_info3='" + sp_info3 + '\'' +
                ", sp_info4='" + sp_info4 + '\'' +
                ", sp_img1='" + sp_img1 + '\'' +
                ", sp_img2='" + sp_img2 + '\'' +
                '}';
    }
}
