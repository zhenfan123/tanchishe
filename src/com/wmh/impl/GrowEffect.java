package com.wmh.impl;

import com.wmh.bean.Snack;
import com.wmh.i.IEffect;

import java.util.Random;

/**
 * Created by uas on 2017/6/2.
 */
public class GrowEffect implements IEffect {
    @Override
    public void startEffect(Snack snack) {
        Random random = new Random();
        int addLength = random.nextInt(5)+5;
        for (int i = 0; i <addLength ; i++) {
            snack.addInEnd();
        }
    }
}
