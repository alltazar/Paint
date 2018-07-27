package Menu;

import Main.SwingTest.DataModel;
import Main.SwingTest.DataModel.Note;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedoUndo extends JMenu {

    RedoUndo(DataModel model, JComponent paintComponent) {

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


    public static void unDo(DataModel data) {

        int iterator = data.getIteratorOfItemsHistory();

        if (iterator >= 0) {

            Note note = data.getItemsHistory().get(iterator);

            if (note.getCreator() == "add") {
                data.getAllShapes().remove(note.getItem());
            } else {
                int i = iterator - 1;
                while (i > 0) {
                    Note previousNote = data.getItemsHistory().get(i);
                    if (previousNote.getItem().equals(note.getItem())) {
                        previousNote.getItem().setX(previousNote.getX());
                        previousNote.getItem().setY(previousNote.getY());
                        previousNote.getItem().setDX(previousNote.getDX());
                        previousNote.getItem().setDY(previousNote.getDY());
                        previousNote.getItem().setColor(previousNote.getColor());
                        break;
                    }
                    i--;
                }
            }
            data.setIteratorOfItemsHistory(iterator - 1);
        }
    }

    public static void reDo(DataModel data) {

        int iterator = data.getIteratorOfItemsHistory();
        int itemsHistorySize = data.getItemsHistory().size();

        if (iterator < itemsHistorySize - 1) {

            Note nextNote = data.getItemsHistory().get(iterator + 1);

            if (nextNote.getCreator() == "add") {
                data.getAllShapes().add(nextNote.getItem());
            } else {
                nextNote.getItem().setX(nextNote.getX());
                nextNote.getItem().setY(nextNote.getY());
                nextNote.getItem().setDX(nextNote.getDX());
                nextNote.getItem().setDY(nextNote.getDY());
                nextNote.getItem().setColor(nextNote.getColor());
            }
            data.setIteratorOfItemsHistory(iterator + 1);
        }
    }

}
