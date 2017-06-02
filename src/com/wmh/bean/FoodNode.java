package com.wmh.bean;

import java.util.Random;

/**
 * 食物节点
 * Created by uas on 2017/6/1.
 */
public abstract class FoodNode extends Node {

    public FoodNode() {
        super();
    }

    public FoodNode(int x, int y) {
        super(x, y);
    }

    public FoodNode(int x, int y, Direction direction) {
        super(x, y, direction);
    }

    /**
     * 随机生成一个食物节点
     *
     * @param maxX 生成区域的最大x坐标(单位节点宽度)
     * @param maxY 生成区域的最大y坐标(单位节点高度)
     */
    public void random(int maxX, int maxY) {
        random(0, 0, maxX, maxY);
    }

    /**
     * 在某个区间随机生成一个食物节点
     *
     * @param minX 生成区域的最小x坐标(单位节点宽度)
     * @param minY 生成区域的最小y坐标(单位节点高度)
     * @param maxX 生成区域的最大x坐标(单位节点宽度)
     * @param maxY 生成区域的最大y坐标(单位节点高度)
     */
    public void random(int minX, int minY, int maxX, int maxY) {
        Random random = new Random();
        setX((random.nextInt(maxX-1-minX)+ minX) * WIDTH );
        setY((random.nextInt(maxY-1-minY) + minY) * HEIGH);
        System.out.println("Food:  x:" + getX() + "   y:" + getY());
    }

    public void move(int maxX, int maxY){
        move(0, 0, maxX, maxY);
    }

    public abstract void move(int minX, int minY, int maxX, int maxY);
}
