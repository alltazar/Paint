import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

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
//                final BufferedImage image = new BufferedImage(paintComponent.getWidth(), paintComponent.getHeight(), BufferedImage.TYPE_INT_RGB);
//
//                paintComponent.paint(image.createGraphics());
//
//                String path = "";
//
//                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//
//                jfc.resetChoosableFileFilters();
//                jfc.addChoosableFileFilter(new FileNameExtensionFilter("png", "png"));
//
//                int returnValue = jfc.showSaveDialog(null);
//
//                if (returnValue == JFileChooser.APPROVE_OPTION) {
//                    File selectedFile = jfc.getSelectedFile();
//                    path = selectedFile.getAbsolutePath();
//                }
//
//                if (path != "") {
//                    try {
//                        if (jfc.getFileFilter().getDescription() == "png" && !path.contains(".png")) {
//                            ImageIO.write(image, "png", new File(path + "." + jfc.getFileFilter().getDescription()));
//                        } else {
//                            ImageIO.write(image, "png", new File(path));
//                        }
//
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    }
//                }
                try (PrintWriter out = new PrintWriter("src/external/filename.txt")) {
                    for (Items s : model.getAllShapes()) {
                        out.println("hashCode:" + s.hashCode() + ";class:" + s.getClass().getName() + ";color:" + s.getColor().getRGB() + ";x:" + s.getX() + ";y:" + s.getY() + ";dx:" + s.getDX() + ";dy:" + s.getDY());
                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
        menuBar.add(button);

        button = new JButton("import FILE");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                int returnVal = fc.showOpenDialog(null);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(new FileInputStream(file)));
                        try {
                            String line;
                            HashMap<String, String> map = new HashMap<>();
                            while ((line = br.readLine()) != null) {
                                String[] pair = line.trim().split(";");
                                for (String s : pair) {
                                    String[] p = s.trim().split(":");
                                    map.put(p[0].trim(), p[1].trim());
                                }
                                Class c = null;
                                Items o = new Items();
                                try {
                                    c = Class.forName(map.get("class"));
                                } catch (ClassNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                                try {
                                    o = (Items) c.newInstance();
                                } catch (InstantiationException e1) {
                                    e1.printStackTrace();
                                } catch (IllegalAccessException e1) {
                                    e1.printStackTrace();
                                }
                                o.setX(Integer.parseInt(map.get("x")));
                                o.setY(Integer.parseInt(map.get("y")));
                                o.setDX(Integer.parseInt(map.get("dx")));
                                o.setDY(Integer.parseInt(map.get("dy")));
                                o.setColor(new Color(Integer.parseInt(map.get("color"))));
                                model.addShape(o);
                            }
                        } finally {
                            br.close();
                        }
                        paintComponent.repaint();
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
