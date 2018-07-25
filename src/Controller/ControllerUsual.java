package Controller;

import Items.Items;
import Items.Lastik;
import Main.SwingTest;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class ControllerUsual extends Controller {
    private final SwingTest.DataModel model_;
    private final JComponent visualComponent_;

    private int x_;
    private int y_;

    private Items pressedShape_ = null;
    private int pressedX_;
    private int pressedY_;

    public ControllerUsual(SwingTest.DataModel model, JComponent visualComponent) {
        model_ = model;
        visualComponent_ = visualComponent;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (model_.getShape() instanceof Lastik) {
            x_ = e.getX();
            y_ = e.getY();
            model_.getShape().setX(e.getX());
            model_.getShape().setY(e.getY());
            model_.getShape().setDX(e.getX() + 5);
            model_.getShape().setDY(e.getY() + 5);
            model_.addShape(model_.getShape());
            visualComponent_.repaint();
        }

        x_ = e.getX();
        y_ = e.getY();
        model_.getShape().setX(e.getX());
        model_.getShape().setY(e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressedX_ = e.getX();
        pressedY_ = e.getY();
        model_.getShape().setDX(e.getX());
        model_.getShape().setDY(e.getY());

        if (pressedShape_ == null) {

            model_.getShape().setX(x_);
            model_.getShape().setY(y_);
            model_.getShape().setDX(pressedX_);
            model_.getShape().setDY(pressedY_);

            model_.addShape(model_.getShape());

            visualComponent_.repaint();

        } else {

            pressedShape_.setX(pressedX_ - x_ + pressedShape_.getX());
            pressedShape_.setY(pressedY_ - y_ + pressedShape_.getY());
            pressedShape_.setDX(pressedX_ - x_ + pressedShape_.getDX());
            pressedShape_.setDY(pressedY_ - y_ + pressedShape_.getDY());
            visualComponent_.repaint();
            pressedShape_ = null;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (model_.getShape() instanceof Lastik) {
            x_ = e.getX();
            y_ = e.getY();
            model_.getShape().setX(e.getX());
            model_.getShape().setY(e.getY());
            model_.getShape().setDX(e.getX() + 5);
            model_.getShape().setDY(e.getY() + 5);
            model_.addShape(model_.getShape());
            visualComponent_.repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        x_ = e.getX();
        y_ = e.getY();
        pressedX_ = e.getX();
        pressedY_ = e.getY();
    }
}
