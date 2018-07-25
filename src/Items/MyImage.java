package Items;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MyImage extends Items {

    private BufferedImage bi;

    public BufferedImage getBi() {
        return bi;
    }

    public void setBi(BufferedImage b) {
        bi = b;
    }

    public void paint(Graphics g, Items s) {
        g.drawImage(((MyImage) s).getBi(), 0, 0, null);
    }

}
