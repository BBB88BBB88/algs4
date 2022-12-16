/******************************************************************************
 *  Compilation:  javac-algs4 Point.java
 *  Execution:    java-algs4 Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/
// 下面这个库就是这个作业老师开发的，所以compile时候也需要加上 algs4才行

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x; // x-coordinate of this point
    private final int y; // y-coordinate of this point

    public Point(int x, int y) {
        /* DO NOT MODIFY 这个是原有代码里写好的 */
        this.x = x;
        this.y = y;
    }

    // 用StdDraw这个库来把点画出来
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // 用StdDraw这个库把两点连线画出来
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // 计算这个点到那个点的坡度的方法（比较规则在原题目里）
    public double slopeTo(Point that) {
        if (that.x == this.x && that.y == this.y) return Double.NEGATIVE_INFINITY;
        double jX = that.x - this.x; // x坐标距离
        double jY = that.y - this.y; // y坐标距离
        if (jY == 0.0) return +0.0;  // 俩点平行
        if (jX == 0.0) return Double.POSITIVE_INFINITY; // 俩点竖直
        return jY / jX; // 计算坡度
    }

    // 比较两个点大小的方法（比较规则在原题目里）
    public int compareTo(Point that) {
        // corner cases
        if (that == null) {
            throw new NullPointerException();
        }
        // same
        if (this.x == that.x && this.y == that.y) {
            return 0;
        }
        // less
        if (this.y < that.y || (this.y == that.y && this.x < that.x)) {
            return -1;
        }
        // bigger
        return 1;
    }

    // 根据点到基准点的坡度来比较两个点的方法，就是这里很蛋疼，语法一直搞不定
    public Comparator<Point> slopeOrder() {
        return new SortBySlope();
    }

    private class SortBySlope implements Comparator<Point> {

        @Override
        public int compare(Point arg0, Point arg1) {
            double jSlope0 = slopeTo(arg0);
            double jSlope1 = slopeTo(arg1);
            return Double.compare(jSlope0, jSlope1);
        }
    }

    // String表达， 这个方法主要用于debug
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // main方法，我在这里搞一个点的数组，然后给点排序。排序规则就是各个点离基准点的坡度。就是在这里无法call SortBySlope这个方法
    public static void main(String[] args) {
        Point[] p = new Point[4];
        p[0] = new Point(1, 1);
        p[2] = new Point(2, 2);
        p[3] = new Point(2, 3);
        p[4] = new Point(3, 1);
        // 这里就不会写了，上面的比较器的语法也感觉有问题，但是不知道怎么搞了
    }
}
