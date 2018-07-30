package Controller;

import Items.Items;
import Items.Lastik;
import Main.SwingTest;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class ControllerUsual extends Controller {

    final String s = "add";

    private final SwingTest.DataModel model_;
    private final JComponent visualComponent_;
    Items item;
    private int x_;
    private int y_;
    private int pressedX_;
    private int pressedY_;

    public ControllerUsual(SwingTest.DataModel model, JComponent visualComponent) {
        model_ = model;
        visualComponent_ = visualComponent;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        model_.clearHistoryTail();

        //model_.clearHistoryTail();
        if (model_.getShape() instanceof Lastik) {
            x_ = e.getX();
            y_ = e.getY();
            model_.getShape().setX(e.getX());
            model_.getShape().setY(e.getY());
            model_.getShape().setDX(e.getX() + 5);
            model_.getShape().setDY(e.getY() + 5);
            visualComponent_.repaint();
        }

        x_ = e.getX();
        y_ = e.getY();

        model_.getShape().setX(e.getX());
        model_.getShape().setY(e.getY());
        model_.getShape().setDX(e.getX());
        model_.getShape().setDY(e.getY());

        model_.addShape(model_.getShape());
        item = model_.getAllShapes().get(model_.getAllShapes().size() - 1);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

//        model_.getShape().setDX(e.getX());
//        model_.getShape().setDY(e.getY());

        model_.saveToHistory(item, "add");
        visualComponent_.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (model_.getShape() instanceof Lastik) {
            Items item = model_.getShape();
            x_ = e.getX();
            y_ = e.getY();
            item.setX(e.getX());
            item.setY(e.getY());
            item.setDX(e.getX() + 5);
            item.setDY(e.getY() + 5);
            model_.addShape(item);
        }

        //Items item = model_.getAllShapes().get(model_.getAllShapes().size() - 1);

        if (e.getX() >= x_ && e.getY() >= y_) {
            item.setX(x_);
            item.setY(y_);
            item.setDX(e.getX());
            item.setDY(e.getY());
        } else if (e.getX() >= x_ && e.getY() < y_) {
            item.setX(x_);
            item.setDY(y_);
            item.setDX(e.getX());
            item.setY(e.getY());
        } else if (e.getX() < x_ && e.getY() < y_) {
            item.setX(e.getX());
            item.setDY(y_);
            item.setDX(x_);
            item.setY(e.getY());
        } else if (e.getX() < x_ && e.getY() >= y_) {
            item.setX(e.getX());
            item.setY(y_);
            item.setDX(x_);
            item.setDY(e.getY());
        }

        visualComponent_.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        x_ = e.getX();
        y_ = e.getY();
        pressedX_ = e.getX();
        pressedY_ = e.getY();
    }
}
