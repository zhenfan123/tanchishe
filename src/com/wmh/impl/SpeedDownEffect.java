package com.wmh.impl;

import com.wmh.bean.Snack;
import com.wmh.i.IEffect;

/**
 * Created by uas on 2017/6/2.
 */
public class SpeedDownEffect implements IEffect {
    @Override
    public void startEffect(Snack snack) {
        int speed = snack.initSpeed;
        snack.setSpeed(speed / 3);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        snack.setSpeed(speed);
    }
}
