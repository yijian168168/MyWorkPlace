package com;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import utils.CVSUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * User: Administrator
 */
public class CVSUtilsdTest {


    @Test
    public void readTest() {
        try {
            List<String[]> result = CVSUtils.readCsv("F:/CVSUtilsTest.csv",1);
            for(String[] info : result) {
                for (int i = 0 ;i < info.length;i++){
                    System.out.println("+++++:"+info[i]);
                }
            }
            System.out.println(result.toString());
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    @Test
    public void readCsvTest(){
        try {
            List<String> xlsMessages = FileUtils.readLines(new File("f:/test.csv"),"gbk");
            System.out.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
