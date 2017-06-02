package com.wmh.bean;

import java.awt.*;

/**
 * 节点
 * Created by uas on 2017/5/31.
 */
public abstract class Node {
    /**
     * 节点宽度
     */
    public static final int WIDTH = 20;
    /**
     * 节点高度
     */
    public static final int HEIGH = 20;
    /**
     * 节点左上角x坐标
     */
    private int x;
    /**
     * 节点左上角y坐标
     */
    private int y;
    /**
     * 节点的移动方向
     */
    private Direction direction = Direction.right;
    /**
     * 节点颜色
     */
    private Color color;

    public Node() {
        this(0,0);
    }

    public Node(int x, int y) {
        this(x, y, Direction.no);
    }

    public Node(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * 判断两个节点是否在同一个位置
     * @param n 需要匹配的节点
     * @return true：两个节点在同一个位置，false：两个节点不在同一个位置
     */
    public boolean isInSimplePosition(Node n){
        if(n.x == this.x && n.y == this.y) {
            return true;
        }
        return false;
    }

    /**
     * 节点移动方向的枚举
     */
    public enum Direction {
        left, right, up, down, no
    }
}
