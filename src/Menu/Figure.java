package Menu;

import Items.Line;
import Items.Oval;
import Items.Rect;
import Main.SwingTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Figure extends JMenu {

    Figure(SwingTest.DataModel model) {

        super("Рисуем:");

        ImageIcon icon = createImageIcon("../images/rect.jpg");
        JMenuItem menuItem = new JMenuItem(" квадрат", icon);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setShape(new Rect());
            }
        });
        this.add(menuItem);

        icon = createImageIcon("../images/oval.jpg");
        menuItem = new JMenuItem(" овал", icon);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setShape(new Oval());
            }
        });
        this.add(menuItem);

        icon = createImageIcon("../images/line.jpg");
        menuItem = new JMenuItem(" линию", icon);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setShape(new Line());
            }
        });
        this.add(menuItem);

    }

    private static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Menu.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
