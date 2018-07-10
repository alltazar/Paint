import java.awt.*;

public class Rect extends Shape {

    public void paint(Graphics g, Items s) {
        g.setColor(s.getColor());
        g.fillRect(x_, y_, dx_ - x_, dy_ - y_);
    }

    @Override
    public Shape newInstance() {
        return new Rect();
    }
}
