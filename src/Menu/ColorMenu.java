package Menu;

import Main.SwingTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorMenu extends JMenu {

    ColorMenu(SwingTest.DataModel model) {
        super("Цвет");

        JMenuItem menuItem = new JMenuItem("синий");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.getShape().setColor(new Color(0, 83, 138));
            }
        });
        this.add(menuItem);

        menuItem = new JMenuItem("красный");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.getShape().setColor(new Color(193, 0, 32));
            }
        });
        this.add(menuItem);

        menuItem = new JMenuItem("желтый");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.getShape().setColor(new Color(255, 179, 0));
            }
        });
        this.add(menuItem);
    }
}
