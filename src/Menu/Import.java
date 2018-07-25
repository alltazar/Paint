package Menu;

import Items.Items;
import Main.SwingTest;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;

public class Import {
    JButton button = new JButton("import FILE");

    Import(SwingTest.DataModel model, JComponent paintComponent) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                fc.resetChoosableFileFilters();
                fc.addChoosableFileFilter(new FileNameExtensionFilter("txt", "txt"));

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
                                Items o = null;
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
    }
}
