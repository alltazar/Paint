package Items;

import java.awt.*;

public class Items {

    protected int x_;
    protected int y_;
    protected int dx_;
    protected int dy_;
    protected Color color_;

    public int getX() {
        return x_;
    }

    public void setX(int x) {
        x_ = x;
    }

    public int getY() {
        return y_;
    }

    public void setY(int y) {
        y_ = y;
    }

    public int getDX() {
        return dx_;
    }

    public void setDX(int dx) {
        dx_ = dx;
    }

    public int getDY() {
        return dy_;
    }

    public void setDY(int dy) {
        dy_ = dy;
    }

    public Color getColor() {
        return color_;
    }

    public void setColor(Color c) {
        color_ = c;
    }

    public void paint(Graphics g, Items s) {

    }

    public Items newInstance() {

        return new Items();
    }
}
