package Menu;

import Items.Items;
import Main.SwingTest;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Save {
    JMenuItem saveItem = new JMenuItem("SAVE");

    Save(SwingTest.DataModel model) {
        saveItem.addActionListener(new ActionListener() {
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

                String path = "";

                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                jfc.resetChoosableFileFilters();
                jfc.addChoosableFileFilter(new FileNameExtensionFilter("txt", "txt"));

                int returnValue = jfc.showSaveDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    path = selectedFile.getAbsolutePath();
                }

                if (path != "") {
//                    try (PrintWriter out = new PrintWriter("src/external/filename.txt")) {
//                        for (Items s : model.getAllShapes()) {
//                            out.println("hashCode:" + s.hashCode() + ";class:" + s.getClass().getName()
//                                    + ";color:" + s.getColor().getRGB()
//                                    + ";x:" + s.getX() + ";y:" + s.getY() + ";dx:" + s.getDX() + ";dy:" + s.getDY());
//                        }
//                    } catch (FileNotFoundException e1) {
//                        e1.printStackTrace();
//                    }

                    if (jfc.getFileFilter().getDescription() == "txt" && !path.contains(".txt")) {
                        try (PrintWriter out = new PrintWriter(path + ".txt")) {
                            for (Items s : model.getAllShapes()) {
                                out.println("class:" + s.getClass().getName()
                                        + ";color:" + s.getColor().getRGB()
                                        + ";x:" + s.getX() + ";y:" + s.getY() + ";dx:" + s.getDX() + ";dy:" + s.getDY());
                            }
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        try (PrintWriter out = new PrintWriter(path)) {
                            for (Items s : model.getAllShapes()) {
                                out.println("class:" + s.getClass().getName()
                                        + ";color:" + s.getColor().getRGB()
                                        + ";x:" + s.getX() + ";y:" + s.getY() + ";dx:" + s.getDX() + ";dy:" + s.getDY());
                            }
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });
    }
}
