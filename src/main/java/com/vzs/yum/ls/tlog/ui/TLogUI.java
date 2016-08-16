package com.vzs.yum.ls.tlog.ui;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.TimeUnit;

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
        this.add(generate);
        generate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedFilePath == null || selectedFilePath.length() == 0) {
                    warningDialog("必须选择一个文件路径");
                    return;
                }
                Stopwatch stopWatch = Stopwatch.createStarted();

                TLogUIExecutor.execute(selectedFilePath);
                stopWatch.stop();
                long elapsed = stopWatch.elapsed(TimeUnit.SECONDS);
                warningDialog("报表生成完毕(耗时 " + elapsed + "秒)");
            }
        });
    }

    protected void warningDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Vzs Message", JOptionPane.PLAIN_MESSAGE);

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
        setSize(300,88);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
        setLayout(new FlowLayout());
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
