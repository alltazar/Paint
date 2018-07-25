package Main;

import Items.Items;
import Items.Rect;
import Items.Shape;
import Menu.Menu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SwingTest {

    public static void main(String[] args) {
        DataModel model = new DataModel();

        JComponent paintComponent = new VisualComponent(model);
        Menu menu = new Menu(model, paintComponent);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(menu.menuBar, BorderLayout.NORTH);
        contentPanel.add(paintComponent, BorderLayout.CENTER);

        JFrame frame = new JFrame("Paint Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(contentPanel);
        frame.setBounds(50, 50, 600, 400);
        frame.setVisible(true);
    }

    public static class DataModel {
        private ArrayList<Items> allShapes_;
        private Items shape_;
        private Color color_;

        public DataModel() {
            allShapes_ = new ArrayList<>();
            shape_ = new Rect();
            color_ = new Color(000000);
        }

        public void addShape(Items shape) {

            Items s = shape.newInstance();
            s.setX(shape.getX());
            s.setY(shape.getY());
            s.setDX(shape.getDX());
            s.setDY(shape.getDY());
            s.setColor(shape.getColor());

            allShapes_.add(s);

        }

        public void addBI(Items shape) {

            allShapes_.add(shape);

        }

        public Items getShape() {
            return shape_;
        }

        public void setShape(Shape s) {
            shape_ = s;
        }

        public ArrayList<Items> getAllShapes() {
            return allShapes_;
        }

        public void unDo() {
            allShapes_.remove(allShapes_.size() - 1);
        }

        public void reDo(Items item) {
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
}