import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu {

    JMenuBar menuBar = new JMenuBar();

    Menu(SwingTest.DataModel model, JComponent paintComponent) {

        JMenu fileMenu = new JMenu("Рисуем:");
        menuBar.add(fileMenu);

        ImageIcon icon = createImageIcon("images/rect.jpg");
        JMenuItem menuItem = new JMenuItem(" квадрат", icon);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setShape(new Rect());
            }
        });
        fileMenu.add(menuItem);

        icon = createImageIcon("images/oval.jpg");
        menuItem = new JMenuItem(" овал", icon);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setShape(new Oval());
            }
        });
        fileMenu.add(menuItem);

        icon = createImageIcon("images/line.jpg");
        menuItem = new JMenuItem(" линию", icon);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setShape(new Line());
            }
        });
        fileMenu.add(menuItem);


        menuItem = new JMenuItem("ластик");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setShape(new Lastik());
            }
        });
        fileMenu.add(menuItem);

        fileMenu = new JMenu("Цвет:");
        menuBar.add(fileMenu);

        menuItem = new JMenuItem("синий");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.getShape().setColor(new Color(0, 83, 138));
            }
        });
        fileMenu.add(menuItem);

        menuItem = new JMenuItem("красный");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.getShape().setColor(new Color(193, 0, 32));
            }
        });
        fileMenu.add(menuItem);

        menuItem = new JMenuItem("желтый");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.getShape().setColor(new Color(255, 179, 0));
            }
        });
        fileMenu.add(menuItem);

        fileMenu = new JMenu("Управление:");
        menuBar.add(fileMenu);

        menuItem = new JMenuItem("отменить");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RedoUndo.unDo(model);
                paintComponent.repaint();
            }
        });
        fileMenu.add(menuItem);

        menuItem = new JMenuItem("повторить");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RedoUndo.reDo(model);
                paintComponent.repaint();
            }
        });
        fileMenu.add(menuItem);

        JButton button = new JButton("+IMG");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                int returnVal = fc.showOpenDialog(null);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {
                        BufferedImage in = ImageIO.read(file);
                        MyImage mi = new MyImage();
                        mi.setBi(in);
                        model.addBI(mi);
                        paintComponent.repaint();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        menuBar.add(button);

        button = new JButton("SAVE");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final BufferedImage image = new BufferedImage(paintComponent.getWidth(), paintComponent.getHeight(), BufferedImage.TYPE_INT_RGB);

                paintComponent.paint(image.createGraphics());

                String path = "";

                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                jfc.resetChoosableFileFilters();
                jfc.addChoosableFileFilter(new FileNameExtensionFilter("png", "png"));

                int returnValue = jfc.showSaveDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    path = selectedFile.getAbsolutePath();
                }

                if (path != "") {
                    try {
                        if (jfc.getFileFilter().getDescription() == "png" && !path.contains(".png")) {
                            ImageIO.write(image, "png", new File(path + "." + jfc.getFileFilter().getDescription()));
                        } else {
                            ImageIO.write(image, "png", new File(path));
                        }

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        menuBar.add(button);

    }

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Menu.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }


}
