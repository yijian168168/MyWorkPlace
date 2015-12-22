package com.readExcel;

import com.readExcel.utils.ReadExcel;
import com.readExcel.utils.WriteExcel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/11 0011.
 */
public class StartRead extends JFrame {

    private JPanel contentPane = new JPanel();
    private JTextField readFilePathT = new JTextField();
    private JTextField writeFilePathT = new JTextField();
    private JTextField writeFileNameT = new JTextField();

    public StartRead() {
        this.add(contentPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        GridLayout gridlayout = new GridLayout();
//        gridlayout.setRows(4);
//        gridlayout.setColumns(2);
//        gridlayout.setHgap(10);
//        gridlayout.setVgap(10);
//        contentPane.setLayout(gridlayout);
        contentPane.setBackground(Color.CYAN);

        JLabel jLabel1 = new JLabel("   文件读取路径：");
        jLabel1.setPreferredSize(new Dimension(110, 30));
        contentPane.add(jLabel1);
        readFilePathT.setPreferredSize(new Dimension(350, 30));
        contentPane.add(readFilePathT);

        JLabel jLabel2 = new JLabel("   文件输出路径：");
        jLabel2.setPreferredSize(new Dimension(110, 30));
        contentPane.add(jLabel2);
        writeFilePathT.setPreferredSize(new Dimension(350, 30));
        contentPane.add(writeFilePathT);

        JLabel jLabel3 = new JLabel("   输出文件名：");
        contentPane.add(jLabel3);
        jLabel3.setPreferredSize(new Dimension(110, 30));
        writeFileNameT.setPreferredSize(new Dimension(350, 30));
        contentPane.add(writeFileNameT);

        JLabel jLabel4 = new JLabel("");
        jLabel4.setPreferredSize(new Dimension(400, 30));
        contentPane.add(jLabel4);
        JButton button = new JButton();
        button.add(new JLabel("确定"));
        button.setPreferredSize(new Dimension(60, 30));
        contentPane.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(readFilePathT.getText());
                System.out.println(writeFilePathT.getText());
                System.out.println(writeFileNameT.getText());
                startreadAndWrite(readFilePathT.getText(), writeFilePathT.getText(), writeFileNameT.getText());
            }
        });
        JLabel jLabel5 = new JLabel("说明：");
        jLabel5.setPreferredSize(new Dimension(450, 30));
        JLabel jLabel6 = new JLabel("1、请在“文件读取路径”处输入待整理的订单文件路径，例如：F:\\订单");
        jLabel6.setPreferredSize(new Dimension(450, 30));
        JLabel jLabel7 = new JLabel("2、请在“文件输出路径”处输入整理后文件的输出路径，例如：F:\\订单整理");
        jLabel7.setPreferredSize(new Dimension(450, 30));
        JLabel jLabel8 = new JLabel("3、请在“输出文件名”处输入工具整理后生成的文件名，例如：订单整理");
        jLabel8.setPreferredSize(new Dimension(450, 30));
        contentPane.add(jLabel5);
        contentPane.add(jLabel6);
        contentPane.add(jLabel7);
        contentPane.add(jLabel8);

        pack();
    }

    public void startreadAndWrite(String filePath,String writeFilePath,String writeFileName){
        File allExcelFile = new File(filePath);
        File[] excelfiles = allExcelFile.listFiles();
        ReadExcel readExcel = new ReadExcel();
        List<Sheet> sheets = new ArrayList<Sheet>();
        for (File file : excelfiles) {
            if (file.getName().endsWith("xls") || file.getName().endsWith("xlsx")) {
                sheets.addAll(readExcel.read(file));
            }
        }
        WriteExcel writeExcel = new WriteExcel();
        writeExcel.write(writeFilePath, writeFileName + ".xls", sheets);
        dispose();
        System.exit(0);
    }

    public static void main(String[] args) {
        StartRead startRead = new StartRead();
        startRead.setVisible(true);
        startRead.setSize(500,350);
        startRead.setTitle("牧迪工作工作订单整理小工具");
        startRead.setBackground(Color.blue);
        startRead.show();

    }
}
