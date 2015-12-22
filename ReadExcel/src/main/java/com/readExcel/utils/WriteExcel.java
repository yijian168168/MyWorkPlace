package com.readExcel.utils;

import com.readExcel.Sheet;
import com.readExcel.model.Order;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2015/12/12 0012.
 */
public class WriteExcel {

    public void write(String filepath, String fileName, List<Sheet> sheets) {
        try {
            File filePath = new File(filepath);
            filePath.mkdirs();
            File file = new File(filepath + "/" + fileName);
            file.createNewFile();
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("sheet1");
            int rowNum = 0;

            //创建第一行
            HSSFRow r0 = sheet.createRow(rowNum);
            List<String> r0Values = Arrays.asList("订单日期","单号", "客户名称", "电话", "客户付款日期", "交公司日期", "快递运单号", "客户签收日期", "确认收货时间", "产品代码", "产品名称", "规格", "批价", "数量", "折扣", "金额", "快递费用", "应收金额", "备注");
            for (int i = 0; i < r0Values.size(); i++) {
                HSSFCell r0ci = r0.createCell(i);
                r0ci.setCellValue(r0Values.get(i));
            }

            for (int i = 0; i < sheets.size(); i++) {
                List<Order> orders = sheets.get(i).getOrders();
                //创建行
                for (int j = 0; j < orders.size(); j++) {
                    sheet.createRow(++rowNum);
                }
                //合并单元格
                for (int j = 0; j < 8; j++) {
                    CellRangeAddress cra = new CellRangeAddress(rowNum-orders.size() + 1, rowNum, j, j);
                    sheet.addMergedRegion(cra);
                }

                HSSFRow ri = sheet.getRow(rowNum-orders.size()+1);
                HSSFCell ric0 = ri.createCell(0);
                ric0.setCellValue(sheets.get(i).getSendDate());
                HSSFCell ric1 = ri.createCell(1);
                ric1.setCellValue(sheets.get(i).getOrderId());
                HSSFCell ric2 = ri.createCell(2);
                ric2.setCellValue(sheets.get(i).getCustomerName());
                HSSFCell ric3 = ri.createCell(3);
                ric3.setCellValue(sheets.get(i).getPhone());

                for (int j = 0; j < orders.size(); j++) {
                    HSSFRow rj = sheet.getRow(rowNum-orders.size() + j + 1);
                    HSSFCell rjc9 = rj.createCell(9);
                    rjc9.setCellValue(orders.get(j).getProductId());
                    HSSFCell rjc10 = rj.createCell(10);
                    rjc10.setCellValue(orders.get(j).getProductName());
                    HSSFCell rjc11 = rj.createCell(11);
                    rjc11.setCellValue(orders.get(j).getSize());
                    HSSFCell rjc12 = rj.createCell(12);
                    rjc12.setCellValue(orders.get(j).getPrice());
                    HSSFCell rjc13 = rj.createCell(13);
                    rjc13.setCellValue(orders.get(j).getNum());
                    HSSFCell rjc14 = rj.createCell(14);
                    rjc14.setCellValue(orders.get(j).getDiscount());
                    HSSFCell rjc15 = rj.createCell(15);
                    rjc15.setCellValue(orders.get(j).getSum());
                }
                for (int j = 16; j < 19; j++) {
                    CellRangeAddress cra = new CellRangeAddress(rowNum -orders.size() + 1, rowNum, j, j);
                    sheet.addMergedRegion(cra);
                }
                HSSFCell rjc16 = ri.createCell(16);
                rjc16.setCellValue(sheets.get(i).getEmailFei());
                HSSFCell rjc17 = ri.createCell(17);
                rjc17.setCellValue(sheets.get(i).getAllPrice());
                HSSFCell rjc18 = ri.createCell(18);
                rjc18.setCellValue(sheets.get(i).getReverse());
            }

            workbook.write(new FileOutputStream(file));
            workbook.close();
            System.out.println("");
            System.out.println("文件生成完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
