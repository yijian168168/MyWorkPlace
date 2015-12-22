package utils;

import com.csvreader.CsvReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangqr on 2015-06-10  20：22.
 */
public class CVSUtils {
    //读取csv文件
    public static List<String[]> readCsv(String filePath,int startLine) throws Exception {
        List<String[]> csvList = new ArrayList<String[]>();
        if (isCsv(filePath)) {
            CsvReader reader = new CsvReader(filePath, ',', Charset.forName("GBK"));
            for (int i = 1;i <startLine;i++){
                reader.readRecord();
            }
            while (reader.readRecord()) { //逐行读入除表头的数据
                csvList.add(reader.getValues());
            }
            reader.close();
        } else {
//            System.out.println("此文件不是CSV文件！");
        }
        return csvList;
    }
    //判断是否是csv文件
    private static boolean isCsv(String fileName){
        return fileName.matches("^.+\\.(?i)(csv)$");
    }
   /* public static void WriteCsv(){
        try {
            String csvFilePath = "C:/群组.csv";
            CsvWriter wr =new CsvWriter(csvFilePath,',',Charset.forName("SJIS"));//日文编码
            String[] contents = {"警告信息","非法操作","没有权限","操作失败"};
            wr.writeRecord(contents);
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
