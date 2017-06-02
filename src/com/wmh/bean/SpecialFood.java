package com.wmh.bean;

import com.wmh.factory.EffectFactory;
import com.wmh.i.IEffect;

import java.awt.*;
import java.util.Random;

/**
 * 带有特效的食物
 * Created by uas on 2017/6/2.
 */
public class SpecialFood extends FoodNode {

    private IEffect effect;

    {
        setColor(Color.red);
        setRandomDirection();
        setEffect(EffectFactory.getRandomEffect());
    }

    public SpecialFood() {
        super();
    }

    public SpecialFood(int x, int y) {
        super(x, y);
    }

    public SpecialFood(int x, int y, Direction direction) {
        super(x, y, direction);
    }

    public IEffect getEffect() {
        return effect;
    }

    public void setEffect(IEffect effect) {
        this.effect = effect;
    }

    @Override
    public void move(int minX, int minY, int maxX, int maxY) {
        switch (getDirection()) {
            case left:
                setX(getX() - WIDTH);
                if (getX() <= minX * WIDTH) {
                    setDirection(Direction.right);
                }
                break;
            case right:
                setX(getX() + WIDTH);
                if (getX() >= (maxX - 1) * WIDTH) {
                    setDirection(Direction.left);
                }
                break;
            case up:
                setY(getY() - HEIGH);
                if (getY() <= minY * HEIGH) {
                    setDirection(Direction.down);
                }
                break;
            case down:
                setY(getY() + HEIGH);
                if (getY() >= (maxY - 1) * HEIGH) {
                    setDirection(Direction.up);
                }
                break;
            case no:
                break;
            default:
                break;
        }
        System.out.println(getDirection());
    }

    private void setRandomDirection() {
        Random random = new Random();
        Direction[] directions = Direction.values();
        int num = random.nextInt(directions.length);
        setDirection(directions[num]);
    }
}
