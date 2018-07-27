package Main;

import Items.Items;
import Items.Rect;
import Items.Shape;
import Menu.Menu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

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
        frame.setBounds(0, 0, 600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static class DataModel {
        private HashSet<Items> allShapes_;
        private Items shape_;
        private Color color_;

        private ArrayList<ArrayList<Items>> history;
        private ArrayList<Note> itemsHistory = new ArrayList<>();
        private int iteratorOfItemsHistory = -1;

        public DataModel() {
            allShapes_ = new HashSet<>();
            history = new ArrayList<>();
            history.add(new ArrayList<>());
            shape_ = new Rect();
            color_ = new Color(000000);
        }

        public int getIteratorOfItemsHistory() {
            return iteratorOfItemsHistory;
        }

        public void setIteratorOfItemsHistory(int iteratorOfItemsHistory) {
            this.iteratorOfItemsHistory = iteratorOfItemsHistory;
        }

        public ArrayList<Note> getItemsHistory() {
            return itemsHistory;
        }

        public void addShape(Items shape) {
            Items s = shape.newInstance_();
            s.setX(shape.getX());
            s.setY(shape.getY());
            s.setDX(shape.getDX());
            s.setDY(shape.getDY());
            s.setColor(shape.getColor());

            allShapes_.add(s);
            saveToHistory(s, "add");
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

        public HashSet<Items> getAllShapes() {
            return allShapes_;
        }

        public void clearHistoryTail() {
            int i = iteratorOfItemsHistory + 1;
            while (i < itemsHistory.size()) {
                itemsHistory.remove(i);
            }
        }

        public void saveToHistory(Items s, String string) {
            itemsHistory.add(new Note(s, s.getX(), s.getY(), s.getDX(), s.getDY(), s.getColor(), string));
            iteratorOfItemsHistory++;
        }

        public class Note {
            Items item;
            int x;
            int y;
            int dx;
            int dy;
            Color color;
            String creator;

            public Note(Items item, int x, int y, int dx, int dy, Color color, String s) {
                this.item = item;
                this.x = x;
                this.y = y;
                this.dx = dx;
                this.dy = dy;
                this.color = color;
                creator = s;
            }

            public Items getItem() {
                return item;
            }

            public int getX() {
                return x;
            }

            public int getY() {
                return y;
            }

            public int getDX() {
                return dx;
            }

            public int getDY() {
                return dy;
            }

            public Color getColor() {
                return color;
            }

            public String getCreator() {
                return creator;
            }
        }

//        public void saveRemakeItemsToHistory(Items its){
//            itemsHistory.add(new Note(its, its.getX(), its.getY(), its.getDX(), its.getDY(), its.getColor(), "edit"));
//            iteratorOfItemsHistory++;
//        }
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

            if (model_.getAllShapes() != null) {
                for (Items s : model_.getAllShapes()) {
                    s.paint(g, s);
                }
            }
        }
    }
}