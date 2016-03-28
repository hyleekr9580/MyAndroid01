package kr.contentsstudio.myfirstandroidapp.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//유닛테스트 진행하기
/**
 * Created by hoyoung on 2016-03-25.
 */
public class CalcTest {

    private Calc mCalc;

    @Before
    public void setUp() throws Exception {
        //  초기화
        mCalc = new Calc();

    }

    @After
    public void tearDown() throws Exception {
        //  끝나고나서
    }

    /**
     * 1. 합계 입니다.
     *
     * @throws Exception
     */
    @Test
    public void 합계테스트() throws Exception {
        //  테스트 할려고 하는 부분
        int result = mCalc.sum(1, 10);
        Assert.assertEquals(11, result);

        result = mCalc.sum(-10, -20);
        Assert.assertEquals(-30, result);


    }

    @Test
    public void 곱하기테스트() throws Exception {
        int result = mCalc.product(1, 10);
        Assert.assertEquals(10, result);

        result = mCalc.product(-10, -20);
        Assert.assertEquals(200, result);
    }
}