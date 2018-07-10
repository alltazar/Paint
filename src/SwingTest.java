import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SwingTest {

    public static void main(String[] args) {
        DataModel model = new DataModel();


        JComponent paintComponent = new VisualComponent(model);
        Menu menu = new Menu(model, paintComponent);
        Controller controller = new Controller(model, paintComponent);
        paintComponent.addMouseListener(controller);
        paintComponent.addMouseMotionListener(controller);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(menu.menuBar, BorderLayout.NORTH);
        contentPanel.add(paintComponent, BorderLayout.CENTER);

        JFrame frame = new JFrame("Paint Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(contentPanel);
        frame.setBounds(50, 50, 600, 400);
        frame.setVisible(true);
    }

    static class DataModel {
        private ArrayList<Items> allShapes_;
        private Items shape_;
        private Color color_;

        public DataModel() {
            allShapes_ = new ArrayList<>();
            shape_ = new Rect();
            color_ = new Color(000000);
        }

        void addShape(Items shape) {

            Items s = shape.newInstance();
            s.setX(shape.getX());
            s.setY(shape.getY());
            s.setDX(shape.getDX());
            s.setDY(shape.getDY());
            s.setColor(shape.getColor());

            allShapes_.add(s);

        }

        void addBI(Items shape) {

            allShapes_.add(shape);

        }

        public Items getShape() {
            return shape_;
        }

        void setShape(Shape s) {
            shape_ = s;
        }

//        void setColor(Color s)
//        {
//            shape_.setColor(s);
//        }

        ArrayList<Items> getAllShapes() {
            return allShapes_;
        }

        void unDo() {
            allShapes_.remove(allShapes_.size() - 1);
        }

        void reDo(Items item) {
            addShape(item);
        }
    }

    static class VisualComponent extends JPanel {
        private final DataModel model_;

        public VisualComponent(DataModel model) {
            model_ = model;
        }

        @Override
        public void paint(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, /*g.getClipBounds().width*/ 1000, /*g.getClipBounds().height*/ 1000);

            for (Items s : model_.getAllShapes()) {
                s.paint(g, s);
            }
        }
    }

    static class Controller extends MouseAdapter {
        private final DataModel model_;
        private final JComponent visualComponent_;

        private int x_;
        private int y_;

        private Items pressedShape_ = null;
        private int pressedX_;
        private int pressedY_;

        public Controller(DataModel model, JComponent visualComponent) {
            model_ = model;
            visualComponent_ = visualComponent;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (model_.shape_ instanceof Lastik) {
                x_ = e.getX();
                y_ = e.getY();
                model_.shape_.setX(e.getX());
                model_.shape_.setY(e.getY());
                model_.shape_.setDX(e.getX() + 5);
                model_.shape_.setDY(e.getY() + 5);
                model_.addShape(model_.shape_);
                visualComponent_.repaint();
            }

            for (Items s : model_.getAllShapes()) {
                int sw = 20;
                int sh = 20;
                if (e.getX() >= s.getX() && e.getX() <= (s.getX() + sw) &&
                        e.getY() >= s.getY() && e.getY() <= (s.getY() + sh)) {
                    pressedShape_ = s;
                    pressedX_ = e.getX();
                    pressedY_ = e.getY();
                    break;
                }
            }
            x_ = e.getX();
            y_ = e.getY();
            model_.shape_.setX(e.getX());
            model_.shape_.setY(e.getY());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            pressedX_ = e.getX();
            pressedY_ = e.getY();
            model_.shape_.setDX(e.getX());
            model_.shape_.setDY(e.getY());

            if (pressedShape_ == null) {

                model_.shape_.setX(x_);
                model_.shape_.setY(y_);
                model_.shape_.setDX(pressedX_);
                model_.shape_.setDY(pressedY_);

                model_.addShape(model_.shape_);

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
            if (model_.shape_ instanceof Lastik) {
                x_ = e.getX();
                y_ = e.getY();
                model_.shape_.setX(e.getX());
                model_.shape_.setY(e.getY());
                model_.shape_.setDX(e.getX() + 5);
                model_.shape_.setDY(e.getY() + 5);
                model_.addShape(model_.shape_);
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
}