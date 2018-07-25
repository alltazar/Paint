package Menu;

import Main.SwingTest;

import javax.swing.*;

public class Menu {

    public JMenuBar menuBar = new JMenuBar();

    public Menu(SwingTest.DataModel model, JComponent paintComponent) {

        Figure figure = new Figure(model);
        menuBar.add(figure);

        ColorMenu colorMenu = new ColorMenu(model);
        menuBar.add(colorMenu);

        RedoUndo redoUndo = new RedoUndo(model, paintComponent);
        menuBar.add(redoUndo);

        ControllerChooser cc = new ControllerChooser(model, paintComponent);
        menuBar.add(cc);

        IMG img = new IMG(model, paintComponent);
        menuBar.add(img.button);

        Save save = new Save(model);
        menuBar.add(save.button);

        Import import_ = new Import(model, paintComponent);
        menuBar.add(import_.button);


    }


}
