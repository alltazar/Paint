package Menu;

import Controller.ControllerForChanges;
import Controller.ControllerUsual;
import Main.SwingTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerChooser extends JMenu {

    private ControllerForChanges cfc;
    private ControllerUsual cu;

    public ControllerChooser(SwingTest.DataModel model, JComponent paintComponent) {

        super("Режим:");

        cfc = new ControllerForChanges(model, paintComponent);
        cu = new ControllerUsual(model, paintComponent);
        paintComponent.addMouseListener(cu);
        paintComponent.addMouseMotionListener(cu);

        JMenuItem menuItem = new JMenuItem("рисование");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paintComponent.removeMouseListener(cfc);
                paintComponent.removeMouseMotionListener(cfc);
                paintComponent.addMouseListener(cu);
                paintComponent.addMouseMotionListener(cu);
//                paintComponent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        this.add(menuItem);

        menuItem = new JMenuItem("редактирование");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paintComponent.removeMouseListener(cu);
                paintComponent.removeMouseMotionListener(cu);
                paintComponent.addMouseListener(cfc);
                paintComponent.addMouseMotionListener(cfc);
//                paintComponent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        this.add(menuItem);
    }
}
