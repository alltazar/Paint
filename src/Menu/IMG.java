package Menu;

import Items.MyImage;
import Main.SwingTest;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class IMG {
    JMenuItem imgItem = new JMenuItem("+IMG");

    IMG(SwingTest.DataModel model, JComponent paintComponent) {
        imgItem.addActionListener(new ActionListener() {
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
    }
}
