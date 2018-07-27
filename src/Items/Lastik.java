package Items;

import java.awt.*;

public class Lastik extends Shape {

    public void paint(Graphics g, Items s) {
        g.setColor(Color.WHITE);
        g.fillRect(x_, y_, dx_ - x_, dy_ - y_);
    }

    @Override
    public Shape newInstance_() {
        return new Lastik();
    }
}
