/*
 * Created by JFormDesigner on Sun Sep 22 10:22:10 CST 2019
 */

package com.blanche.view;

import com.blanche.Run;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class WindowFrame extends JFrame {
    public static JButton selectFileButton;
    public static JScrollPane scrollPane1;
    public static JTextArea content;
    public static JLabel label1;
    public static File selectedFile;
    public WindowFrame() {
        initComponents();
    }


    private void selectFileButtonActionPerformed(ActionEvent e) throws IOException {
        JFileChooser jf = new JFileChooser();
        int result = jf.showSaveDialog(null);
        selectedFile = jf.getSelectedFile();
        String path = selectedFile.getAbsolutePath();
        WindowFrame.content.append(path + "\n");
        Run.read(selectedFile,Run.passArgs);
    }

    private void initComponents() {
        selectFileButton = new JButton();
        scrollPane1 = new JScrollPane();
        content = new JTextArea();
        label1 = new JLabel();

        //======== this ========
        setResizable(false);
        setTitle("WordCount");
        Container contentPane = getContentPane();

        //---- selectFileButton ----
        selectFileButton.setText("\u9009\u62e9\u6587\u4ef6\u5939");
        selectFileButton.addActionListener(e -> {
            try {
                selectFileButtonActionPerformed(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        //======== scrollPane1 ========
        {

            //---- content ----
            content.setEnabled(false);
            content.setForeground(Color.black);
            content.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 19));
            content.setDisabledTextColor(Color.black);
            scrollPane1.setViewportView(content);
        }

        //---- label1 ----
        label1.setText("\u6267\u884c\u7ed3\u679c\uff1a");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(190, Short.MAX_VALUE))
                .addComponent(selectFileButton, GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(selectFileButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(label1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(null);
    }
}
