package com.wmh.factory;

import com.wmh.i.IEffect;
import com.wmh.impl.CutEffect;
import com.wmh.impl.GrowEffect;
import com.wmh.impl.SpeedDownEffect;
import com.wmh.impl.SpeedUpEffect;

import java.util.Random;

/**
 * Created by uas on 2017/6/2.
 */
public abstract class EffectFactory {

    public static IEffect getRandomEffect(){
        Random random = new Random();
        int num = random.nextInt(2);
        switch (num){
            case 0:
                return new GrowEffect();
            case 1:
                return new CutEffect();
        }
        return new SpeedDownEffect();
    }
}
