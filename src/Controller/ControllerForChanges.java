package Controller;

import Items.Items;
import Main.SwingTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ControllerForChanges extends Controller {

    public static final String METHOD = "edit";

    private final SwingTest.DataModel model_;
    private final JComponent visualComponent_;

    private boolean drag = false;
    private boolean changes = false;

    private boolean corner = false;
    private boolean udside = false;
    private boolean lrside = false;

    private Items pressedShape_ = null;
    private int pressedX_;
    private int pressedY_;

    private int x_;
    private int y_;

    public ControllerForChanges(SwingTest.DataModel model, JComponent visualComponent) {
        model_ = model;
        visualComponent_ = visualComponent;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (Items its : model_.getAllShapes()) {
            int sw = 5;
            int sh = 5;
            if (e.getX() >= its.getX() && e.getX() <= (its.getX() + sw) && e.getY() >= its.getY() && e.getY() <= (its.getY() + sh)) {
                visualComponent_.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
                break;
            } else if (e.getX() <= its.getDX() && e.getX() >= (its.getDX() - sw) && e.getY() >= its.getY() && e.getY() <= (its.getY() + sh)) {
                visualComponent_.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
                break;
            } else if (e.getX() >= its.getX() && e.getX() <= (its.getX() + sw) && e.getY() >= its.getDY() - sh && e.getY() <= its.getDY()) {
                visualComponent_.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
                break;
            } else if (e.getX() >= its.getDX() - sw && e.getX() <= its.getDX() && e.getY() >= its.getDY() - sh && e.getY() <= its.getDY()) {
                visualComponent_.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
                break;
            } else if (e.getX() > its.getX() + sw && e.getX() < its.getDX() - sw && e.getY() >= its.getY() && e.getY() <= its.getY() + sh) {
                visualComponent_.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
                break;
            } else if (e.getX() > its.getX() + sw && e.getX() < its.getDX() - sw && e.getY() >= its.getDY() - sh && e.getY() <= its.getDY()) {
                visualComponent_.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
                break;
            } else if (e.getX() >= its.getX() && e.getX() <= its.getX() + sw && e.getY() > its.getY() + sh && e.getY() < its.getDY() - sh) {
                visualComponent_.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
                break;
            } else if (e.getX() >= its.getDX() - sw && e.getX() <= its.getDX() && e.getY() > its.getY() + sh && e.getY() < its.getDY() - sh) {
                visualComponent_.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
                break;
            } else if (e.getX() >= its.getX() && e.getX() <= its.getDX() && e.getY() >= its.getY() && e.getY() <= its.getDY()) {
                visualComponent_.setCursor(new Cursor(Cursor.HAND_CURSOR));
                break;
            } else {
                visualComponent_.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

        for (Items its : model_.getAllShapes()) {
            int sw = 5;
            int sh = 5;
            if (e.getX() >= its.getX() && e.getX() <= (its.getX() + sw) && e.getY() >= its.getY() && e.getY() <= (its.getY() + sh)) {
                corner = true;
                pressedShape_ = its;
                model_.setPressedShape(pressedShape_);
                pressedX_ = e.getX();
                pressedY_ = e.getY();
                x_ = pressedShape_.getDX();
                y_ = pressedShape_.getDY();
                break;
            } else if (e.getX() <= its.getDX() && e.getX() >= (its.getDX() - sw) && e.getY() >= its.getY() && e.getY() <= (its.getY() + sh)) {
                corner = true;
                pressedShape_ = its;
                model_.setPressedShape(pressedShape_);
                pressedX_ = e.getX();
                pressedY_ = e.getY();
                x_ = pressedShape_.getX();
                y_ = pressedShape_.getDY();
                break;
            } else if (e.getX() >= its.getX() && e.getX() <= (its.getX() + sw) && e.getY() >= its.getDY() - sh && e.getY() <= its.getDY()) {
                corner = true;
                pressedShape_ = its;
                model_.setPressedShape(pressedShape_);
                pressedX_ = e.getX();
                pressedY_ = e.getY();
                x_ = pressedShape_.getDX();
                y_ = pressedShape_.getY();
                break;
            } else if (e.getX() >= its.getDX() - sw && e.getX() <= its.getDX() && e.getY() >= its.getDY() - sh && e.getY() <= its.getDY()) {
                corner = true;
                pressedShape_ = its;
                model_.setPressedShape(pressedShape_);
                pressedX_ = e.getX();
                pressedY_ = e.getY();
                x_ = pressedShape_.getX();
                y_ = pressedShape_.getY();
                break;
            } else if (e.getX() > its.getX() + sw && e.getX() < its.getDX() - sw && e.getY() >= its.getY() && e.getY() <= its.getY() + sh) {
                udside = true;
                pressedShape_ = its;
                model_.setPressedShape(pressedShape_);
                pressedX_ = e.getX();
                pressedY_ = e.getY();
                x_ = pressedShape_.getX();
                y_ = pressedShape_.getDY();
                break;
            } else if (e.getX() > its.getX() + sw && e.getX() < its.getDX() - sw && e.getY() >= its.getDY() - sh && e.getY() <= its.getDY()) {
                udside = true;
                pressedShape_ = its;
                model_.setPressedShape(pressedShape_);
                pressedX_ = e.getX();
                pressedY_ = e.getY();
                x_ = pressedShape_.getX();
                y_ = pressedShape_.getY();
                break;
            } else if (e.getX() >= its.getX() && e.getX() <= its.getX() + sw && e.getY() > its.getY() + sh && e.getY() < its.getDY() - sh) {
                lrside = true;
                pressedShape_ = its;
                model_.setPressedShape(pressedShape_);
                pressedX_ = e.getX();
                pressedY_ = e.getY();
                x_ = pressedShape_.getDX();
                y_ = pressedShape_.getY();
                break;
            } else if (e.getX() >= its.getDX() - sw && e.getX() <= its.getDX() && e.getY() > its.getY() + sh && e.getY() < its.getDY() - sh) {
                lrside = true;
                pressedShape_ = its;
                model_.setPressedShape(pressedShape_);
                pressedX_ = e.getX();
                pressedY_ = e.getY();
                x_ = pressedShape_.getX();
                y_ = pressedShape_.getY();
                break;
            } else if (e.getX() >= its.getX() && e.getX() <= its.getDX() && e.getY() >= its.getY() && e.getY() <= its.getDY()) {
                pressedShape_ = its;
                model_.setPressedShape(pressedShape_);
                pressedX_ = e.getX();
                pressedY_ = e.getY();
                drag = true;
                break;
            }
        }
        if (pressedShape_ != null) {
            model_.clearHistoryTail();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (pressedShape_ != null && changes) {
            model_.saveToHistory(pressedShape_, METHOD);
            visualComponent_.repaint();
            //pressedShape_ = null;
            drag = false;
            changes = false;
            corner = false;
            udside = false;
            lrside = false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (pressedShape_ != null && drag) {
            pressedShape_.setDX(pressedShape_.getDX() + (e.getX() - pressedX_));
            pressedShape_.setDY(pressedShape_.getDY() + (e.getY() - pressedY_));
            pressedShape_.setX(pressedShape_.getX() + (e.getX() - pressedX_));
            pressedShape_.setY(pressedShape_.getY() + (e.getY() - pressedY_));
            visualComponent_.repaint();
            pressedX_ = e.getX();
            pressedY_ = e.getY();
            changes = true;
        } else if (pressedShape_ != null && corner) {
            if (e.getX() >= x_ && e.getY() >= y_) {
                pressedShape_.setX(x_);
                pressedShape_.setY(y_);
                pressedShape_.setDX(e.getX());
                pressedShape_.setDY(e.getY());
                changes = true;
            } else if (e.getX() >= x_ && e.getY() < y_) {
                pressedShape_.setX(x_);
                pressedShape_.setDY(y_);
                pressedShape_.setDX(e.getX());
                pressedShape_.setY(e.getY());
                changes = true;
            } else if (e.getX() < x_ && e.getY() < y_) {
                pressedShape_.setX(e.getX());
                pressedShape_.setDY(y_);
                pressedShape_.setDX(x_);
                pressedShape_.setY(e.getY());
                changes = true;
            } else if (e.getX() < x_ && e.getY() >= y_) {
                pressedShape_.setX(e.getX());
                pressedShape_.setY(y_);
                pressedShape_.setDX(x_);
                pressedShape_.setDY(e.getY());
                changes = true;
            }
            visualComponent_.repaint();
        } else if (pressedShape_ != null && udside) {
            if (e.getY() < y_) {
                pressedShape_.setDY(y_);
                pressedShape_.setY(e.getY());
                changes = true;
            } else if (e.getY() >= y_) {
                pressedShape_.setY(y_);
                pressedShape_.setDY(e.getY());
                changes = true;
            }
            visualComponent_.repaint();
        } else if (pressedShape_ != null && lrside) {
            if (e.getX() >= x_) {
                pressedShape_.setX(x_);
                pressedShape_.setDX(e.getX());
                changes = true;
            } else if (e.getX() < x_) {
                pressedShape_.setX(e.getX());
                pressedShape_.setDX(x_);
                changes = true;
            }
            visualComponent_.repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

}