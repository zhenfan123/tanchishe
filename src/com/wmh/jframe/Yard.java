package com.wmh.jframe;

import com.wmh.bean.*;
import com.wmh.factory.FoodFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by uas on 2017/5/31.
 */
public class Yard extends JFrame {
    /**
     * 窗口宽度（单位为节点宽度）
     */
    public static final int WIDTH = 50;
    /**
     * 窗口高度（单位为节点高度）
     */
    public static final int HEIGH = 40;
    /**
     * 蛇
     */
    private Snack snack;
    /**
     * 食物
     */
    private FoodNode food;
    /**
     * 难度(值越大越困难)
     */
    private int difficulty = 2;
    /**
     * 线程池
     */
    private ThreadPoolExecutor executor;
    /**
     * 得分
     */
    private int score;

    private int titleBarHeigh;

    public Yard() {
        init();
        initPlayer();
        initEvent();
    }

    /**
     * 初始化窗口信息
     */
    private void init() {
        setBounds(100, 100, this.WIDTH * Node.WIDTH, this.HEIGH * Node.HEIGH);
        setVisible(true);
        setTitle("jjjjj");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Insets set = this.getInsets();
        titleBarHeigh = set.top;
        setSize(this.WIDTH * Node.WIDTH, this.HEIGH * Node.HEIGH+titleBarHeigh);
        System.out.println(titleBarHeigh);
    }

    /**
     * 初始化精灵
     */
    private void initPlayer() {
        snack = new Snack();
        snack.setColor(Color.black);
        food = FoodFactory.getFood(WIDTH, HEIGH);
        executor = (ThreadPoolExecutor) Executors.newScheduledThreadPool(5);
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        //添加按键监听,修改蛇头结点移动方向
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP: //上
                        if (snack.getHead().getDirection() == Node.Direction.down) {
                            break;
                        }
                        snack.getHead().setDirection(Node.Direction.up);
                        break;
                    case KeyEvent.VK_DOWN://下
                        if (snack.getHead().getDirection() == Node.Direction.up) {
                            break;
                        }
                        snack.getHead().setDirection(Node.Direction.down);
                        break;
                    case KeyEvent.VK_LEFT://左
                        if (snack.getHead().getDirection() == Node.Direction.right) {
                            break;
                        }
                        snack.getHead().setDirection(Node.Direction.left);
                        break;
                    case KeyEvent.VK_RIGHT://右
                        if (snack.getHead().getDirection() == Node.Direction.left) {
                            break;
                        }
                        snack.getHead().setDirection(Node.Direction.right);
                        break;
                }
                snack.move();
            }
        });
    }


    /**
     * 开始游戏
     */
    boolean isStop = false;

    public void start() {
        Runnable snackRun = () -> {
            //线程停止标记
            while (!isStop) {
                snack.move();
                if (isCollision()) {
                    isStop = true;
                    continue;
                }
                eat();
                try {
                    Thread.sleep(1000 / snack.getSpeed() / difficulty);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(snack.getHead().getX() + ":" + snack.getHead().getY());
        };
        Runnable paintRun = () -> {
            while (!isStop) {
                repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable foodRun = () -> {
            while (!isStop) {
                food.move(WIDTH, HEIGH);
                try {
                    Thread.sleep(1000 / difficulty);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        executor.execute(snackRun);
        executor.execute(paintRun);
        executor.execute(foodRun);
    }

    /**
     * 碰撞判断
     */
    private boolean isCollision() {
        int x = snack.getHead().getX();
        int y = snack.getHead().getY();

        //出边界判断
        boolean existed = x < 0 || x > (Yard.WIDTH - 1) * Node.WIDTH || y < 0 || y > (Yard.HEIGH - 1) * Node.HEIGH;
        if (existed) {
            System.out.println("撞墙啦");
            return true;
        }

        //蛇吃自己判断
        for (SnackNode n = snack.getHead().getNext(); n != null; n = n.getNext()) {
            if (x == n.getX() && y == n.getY()) {
                System.out.println("自杀啦");
                return true;
            }
        }
        return false;
    }

    /**
     * 吃食物
     */
    private void eat() {
        boolean existed = snack.getHead().isInSimplePosition(food) || snack.getHead().getNext().isInSimplePosition(food);
        if (existed) {
            snack.addInEnd();
            System.out.println(snack.getLength());
            food = FoodFactory.getFood(WIDTH, HEIGH);
            if (food instanceof SpecialFood) {
                executor.execute(() ->
                        ((SpecialFood) food).getEffect().startEffect(snack)
                );
            }
            System.out.println("snack's length:" + snack.getLength());
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawSnack(g);
        drawFood(g);
        drawBorder(g);
    }

    /**
     * 绘制游戏边界
     *
     * @param g
     */
    private void drawBorder(Graphics g) {
        g.drawRect(0, 0+titleBarHeigh, this.WIDTH * Node.WIDTH, this.HEIGH * Node.HEIGH);
    }

    /**
     * 绘制食物
     *
     * @param g
     */
    private void drawFood(Graphics g) {
        Color c = g.getColor();
        g.setColor(food.getColor());
        g.fillOval(food.getX(), food.getY()+titleBarHeigh, Node.WIDTH, Node.HEIGH);
        g.setColor(c);
    }

    /**
     * 绘制蛇
     *
     * @param g
     */
    private void drawSnack(Graphics g) {
        Color c = g.getColor();

        for (SnackNode n = snack.getHead(); n != null; n = n.getNext()) {
            g.setColor(n.getColor());
            g.fillRect(n.getX(), n.getY()+titleBarHeigh, Node.WIDTH, Node.HEIGH);
        }

        g.setColor(c);
    }
}
