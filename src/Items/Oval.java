package Items;

import java.awt.*;

public class Oval extends Shape {

    public void paint(Graphics g, Items s) {
        g.setColor(s.getColor());
        if (dx_ < x_) {
            int oldX = x_;
            x_ = dx_;
            dx_ = oldX;
        }
        if (dy_ < y_) {
            int oldY = y_;
            y_ = dy_;
            dy_ = oldY;
        }
        g.fillOval(x_, y_, dx_ - x_, dy_ - y_);
    }

    @Override
    public Shape newInstance() {
        return new Oval();
    }
}
