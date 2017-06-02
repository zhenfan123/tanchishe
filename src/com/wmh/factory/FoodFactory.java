package com.wmh.factory;

import com.wmh.bean.FoodNode;
import com.wmh.bean.NormalFood;
import com.wmh.bean.SpecialFood;

/**
 * Created by uas on 2017/6/2.
 */
public class FoodFactory {

    public static FoodNode getFood(int maxX, int maxY){
        double prob = Math.random();
        FoodNode food = null;
        if(prob<1){
            food = new SpecialFood();
        } else {
            food = new NormalFood();
        }
        food.random(maxX, maxY);
        return food;
    }
}
