package com.wmh.bean;

/**
 * 蛇节点
 * Created by uas on 2017/6/1.
 */
public class SnackNode extends Node {
    /**
     * 蛇节点的下一个节点
     */
    private SnackNode next;
    /**
     * 蛇节点的前一个节点
     */
    private SnackNode front;

    public SnackNode() {
    }

    public SnackNode(int x, int y) {
        super(x, y);
    }

    public SnackNode(int x, int y, Direction direction) {
        super(x, y, direction);
    }

    public SnackNode getNext() {
        return next;
    }

    public void setNext(SnackNode next) {
        this.next = next;
    }

    public SnackNode getFront() {
        return front;
    }

    public void setFront(SnackNode front) {
        this.front = front;
    }

}
