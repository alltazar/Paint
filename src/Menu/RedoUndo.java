package Menu;

import Items.Items;
import Main.SwingTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RedoUndo extends JMenu {

    private static ArrayList<Items> list = new ArrayList<>();
    private static int count;

    RedoUndo(SwingTest.DataModel model, JComponent paintComponent) {

        super("Управление:");

        JMenuItem menuItem = new JMenuItem("отменить");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RedoUndo.unDo(model);
                paintComponent.repaint();
            }
        });
        this.add(menuItem);

        menuItem = new JMenuItem("повторить");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RedoUndo.reDo(model);
                paintComponent.repaint();
            }
        });
        this.add(menuItem);
    }


    public static void unDo(SwingTest.DataModel data) {

        if (data.getAllShapes().size() != count) {
            list.clear();
        }

        if (data.getAllShapes().size() != 0) {

            ArrayList<Items> s = data.getAllShapes();

            list.add(s.get(s.size() - 1));
            data.unDo();

            count = s.size();

        }
    }

    public static void reDo(SwingTest.DataModel data) {

        ArrayList<Items> s = data.getAllShapes();

        if (count == (s.size()) && list.size() != 0) {
            data.reDo(list.get(list.size() - 1));
            list.remove(list.size() - 1);
            count = s.size();
        } else {
            list.clear();
        }
    }

}
