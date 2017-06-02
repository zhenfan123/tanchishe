package com.wmh.test;

import com.wmh.bean.Node;
import com.wmh.jframe.Yard;

/**
 * Created by uas on 2017/5/31.
 */
public class Test {

    public static void main(String[] args) {
        Yard y = new Yard();
        y.start();
    }

    @org.junit.Test
    public void test(){
        System.out.println(Node.Direction.values().length);
//aaa
    }
}
