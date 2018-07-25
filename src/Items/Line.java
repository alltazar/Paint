package Items;

import java.awt.*;

public class Line extends Shape {

    public void paint(Graphics g, Items s) {
        g.setColor(s.getColor());
        g.drawLine(x_, y_, dx_, dy_);
    }

    @Override
    public Shape newInstance() {
        return new Line();
    }
}
