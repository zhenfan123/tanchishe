package com.wmh.bean;

import java.awt.*;

/**
 * Created by uas on 2017/5/31.
 */
public class Snack {
    /**
     * 蛇的长度
     */
    private int length;
    /**
     * 蛇的初始长度
     */
    private static final int START_LENGTH = 5;
    /**
     * 蛇的头节点
     */
    private SnackNode head;
    /**
     * 蛇的尾节点
     */
    private SnackNode end;
    /**
     * 蛇的颜色
     */
    private Color color = Color.black;
    /**
     * 蛇的初始速度(每秒前进格数)
     */
    public final int initSpeed = 3;
    /**
     * 蛇的当前速度(每秒前进格数)
     */
    private int currentSpeed = initSpeed;

    public Snack() {
        SnackNode n = new SnackNode(Node.WIDTH * 10, Node.HEIGH * 20);
        n.setDirection(Node.Direction.right);
        n.setColor(color);
        setHead(n);
        setEnd(n);
        setLength(1);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public SnackNode getHead() {
        return head;
    }

    public void setHead(SnackNode head) {
        this.head = head;
    }

    public SnackNode getEnd() {
        return end;
    }

    public void setEnd(SnackNode end) {
        this.end = end;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        for (SnackNode n = head; n != null; n = n.getNext()) {
            n.setColor(color);
        }
    }

    public int getSpeed() {
        return currentSpeed;
    }

    public void setSpeed(int speed) {
        this.currentSpeed = speed;
    }

    /**
     * 蛇向头节点方向移动一个节点的距离
     */
    public void move() {
        boolean existed = this.getLength() < START_LENGTH;
        if (existed) {
            addInEnd();
        }
        SnackNode endFront = end.getFront();
        endFront.setNext(null);

        end.setFront(null);
        end.setNext(head);
        head.setFront(end);

        switch (head.getDirection()) {
            case left:
                end.setX(head.getX() - Node.WIDTH);
                end.setY(head.getY());
                break;
            case right:
                end.setX(head.getX() + Node.WIDTH);
                end.setY(head.getY());
                break;
            case up:
                end.setX(head.getX());
                end.setY(head.getY() - Node.HEIGH);
                break;
            case down:
                end.setX(head.getX());
                end.setY(head.getY() + Node.HEIGH);
                break;
            default:
                break;
        }
        end.setDirection(head.getDirection());
        head = end;
        end = endFront;
    }

    /**
     * 蛇增加一个节点并置于开头
     */
    @Deprecated
    public void addInHead() {
        length++;

        SnackNode n = new SnackNode();
        n.setX(head.getX());
        n.setY(head.getY());
        switch (head.getDirection()) {
            case left:
                n.setX(n.getX() - Node.WIDTH);
                break;
            case right:
                n.setX(n.getX() + Node.WIDTH);
                break;
            case up:
                n.setY(n.getY() - Node.HEIGH);
                break;
            case down:
                n.setY(n.getY() + Node.HEIGH);
                break;
            default:
                break;
        }
        n.setDirection(head.getDirection());
        n.setColor(color);

        head.setFront(n);
        n.setNext(head);
        head = n;
    }

    /**
     * 蛇增加一个节点并置于结尾
     */
    public void addInEnd() {
        length++;

        SnackNode n = new SnackNode();
        n.setX(end.getX());
        n.setY(end.getY());
        n.setColor(color);

        n.setFront(end);
        end.setNext(n);
        end = n;
    }

    public void removeFromEnd() {
        length--;
        if (length <= START_LENGTH) {
            return;
        }
        end = end.getFront();
        end.getNext().setFront(null);
        end.setNext(null);
    }
}
