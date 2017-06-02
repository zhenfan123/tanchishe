package com.wmh.impl;

import com.wmh.bean.Snack;
import com.wmh.i.IEffect;

import java.util.Random;

/**
 * Created by uas on 2017/6/2.
 */
public class CutEffect implements IEffect {
    @Override
    public void startEffect(Snack snack) {
        Random random = new Random();
        int cutLength = random.nextInt(6)+4;
        for (int i = 0; i <cutLength ; i++) {
            snack.removeFromEnd();
        }
    }
}
