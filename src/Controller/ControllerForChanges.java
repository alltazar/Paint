package Controller;

import Items.Items;
import Main.SwingTest;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class ControllerForChanges extends Controller {
    private final SwingTest.DataModel model_;
    private final JComponent visualComponent_;

    private boolean drag = false;

    private int x_;
    private int y_;

    private boolean rightUp = false;
    private boolean rightDown = false;
    private boolean leftUp = false;
    private boolean leftDown = false;

    private Items pressedShape_ = null;
    private int pressedX_;
    private int pressedY_;

    public ControllerForChanges(SwingTest.DataModel model, JComponent visualComponent) {
        model_ = model;
        visualComponent_ = visualComponent;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (Items its : model_.getAllShapes()) {
            int sw = 20;
            int sh = 20;
            if (e.getX() >= its.getX() && e.getX() <= (its.getX() + sw) && e.getY() >= its.getY() && e.getY() <= (its.getY() + sh)) {
                leftUp = true;
                pressedShape_ = its;
                pressedX_ = e.getX();
                pressedY_ = e.getY();
                break;
            } else if (e.getX() <= its.getDX() && e.getX() >= (its.getDX() - sw) && e.getY() >= its.getY() && e.getY() <= (its.getY() + sh)) {
                rightUp = true;
                pressedShape_ = its;
                pressedX_ = e.getX();
                pressedY_ = e.getY();
                break;
            } else if (e.getX() >= its.getX() && e.getX() <= (its.getX() + sw) && e.getY() >= its.getDY() - sh && e.getY() <= its.getDY()) {
                leftDown = true;
                pressedShape_ = its;
                pressedX_ = e.getX();
                pressedY_ = e.getY();
                break;
            } else if (e.getX() >= its.getDX() - sw && e.getX() <= its.getDX() && e.getY() >= its.getDY() - sh && e.getY() <= its.getDY()) {
                rightDown = true;
                pressedShape_ = its;
                pressedX_ = e.getX();
                pressedY_ = e.getY();
                break;
            } else if (e.getX() >= its.getX() && e.getX() <= its.getDX() && e.getY() >= its.getY() && e.getY() <= its.getDY()) {
                pressedShape_ = its;
                pressedX_ = e.getX();
                pressedY_ = e.getY();
                drag = true;
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (pressedShape_ != null && drag) {
            pressedShape_.setDX(pressedShape_.getDX() + (e.getX() - pressedX_));
            pressedShape_.setDY(pressedShape_.getDY() + (e.getY() - pressedY_));
            pressedShape_.setX(pressedShape_.getX() + (e.getX() - pressedX_));
            pressedShape_.setY(pressedShape_.getY() + (e.getY() - pressedY_));
            visualComponent_.repaint();
            pressedShape_ = null;
            drag = false;
        } else if (pressedShape_ != null) {
            if (rightDown) {
                pressedShape_.setDX(e.getX());
                pressedShape_.setDY(e.getY());
                visualComponent_.repaint();
                pressedShape_ = null;
                rightDown = false;
            }
            if (leftDown) {
                pressedShape_.setX(pressedShape_.getX() + (e.getX() - pressedX_));
                pressedShape_.setDY(e.getY());
                visualComponent_.repaint();
                pressedShape_ = null;
                leftDown = false;
            }
            if (rightUp) {
                pressedShape_.setY(e.getY());
                pressedShape_.setDX(e.getX());
                visualComponent_.repaint();
                pressedShape_ = null;
                rightUp = false;
            }
            if (leftUp) {
                pressedShape_.setY(e.getY());
                pressedShape_.setX(e.getX());
                visualComponent_.repaint();
                pressedShape_ = null;
                leftUp = false;
            }
        }
        drag = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
}