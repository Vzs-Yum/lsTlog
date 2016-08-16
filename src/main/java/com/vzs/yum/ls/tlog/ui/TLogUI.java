package com.vzs.yum.ls.tlog.ui;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by byao on 8/16/16.
 */
@Slf4j
public class TLogUI extends JFrame {
    private String selectedFilePath;
    public TLogUI () {
        addComponent();
        createFrame();

    }

    private void addComponent() {
        JButton directlyChooser = new JButton("TLog 目录选择");
        this.add(directlyChooser);
        directlyChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFileChooser();
            }
        });

        JButton generate = new JButton("生成报表");
    }

    private void openFileChooser() {
        JFileChooser fileChooser = new JFileChooser(new File("/Users/byao/Ben/doc/yum/"));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
            log.info(selectedFilePath);
        }
    }

    private void createFrame() {
        setTitle("TLog report generator (vzs)");
        setSize(300,200); // default size is 0,0
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
        setLayout(new FlowLayout());
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
