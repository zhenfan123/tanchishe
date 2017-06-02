package com.wmh.bean;

import java.awt.*;

/**
 * 正常的食物
 * Created by uas on 2017/6/2.
 */
public class NormalFood extends FoodNode {

    {
        setColor(Color.blue);
    }

    public NormalFood() {
        super();
    }

    public NormalFood(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int minX, int minY, int maxX, int maxY) {
        //no thing to do
    }
}
