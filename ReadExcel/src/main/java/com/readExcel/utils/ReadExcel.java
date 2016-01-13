package com.readExcel.utils;

import com.readExcel.Sheet;
import com.readExcel.model.Order;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/11 0011.
 */
public class ReadExcel {

    public List<Sheet> read(File file) {

        if (file.getName().endsWith("xls")) {
            try {
                List<Sheet> sheets = new ArrayList<Sheet>();
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                // 打开HSSFWorkbook
                POIFSFileSystem fs = new POIFSFileSystem(in);
                HSSFWorkbook wb = new HSSFWorkbook(fs);

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {

                    //订单对象
                    Sheet sheet = new Sheet();
                    //获取sheet
                    HSSFSheet st = wb.getSheetAt(sheetIndex);
                    HSSFRow r0 = st.getRow(0);
                    if (null == r0) break;
                    HSSFRow r1 = st.getRow(1);
                    HSSFRow r2 = st.getRow(2);
                    HSSFRow r3 = st.getRow(3);

                    //公司名称
                    HSSFCell r0c1 = r0.getCell(1);
                    String r0c1Value = r0c1.getStringCellValue();
                    System.out.println(r0c1Value);

                    //区域编号
                    String r1c5Value = readOneCell(r1.getCell(5));
                    sheet.setCustomerId(r1c5Value);
                    System.out.println(r1c5Value);

                    //订单编号
                    String r1c6Value = readOneCell(r1.getCell(6));
                    sheet.setOrderId(r1c6Value);
                    System.out.println(r1c6Value);

                    //客户名称
                    String r2c1Value = readOneCell(r2.getCell(1));
                    sheet.setCustomerName(r2c1Value);
                    System.out.println(r2c1Value);

                    //电话
                    String r2c5Value = readOneCell(r2.getCell(5));
                    sheet.setPhone(r2c5Value);
                    System.out.println(r2c5Value);

                    //地   址
                    String r3c1Value = readOneCell(r3.getCell(1));
                    sheet.setAddress(r3c1Value);
                    System.out.println(r3c1Value);

                    //送货日期
                    HSSFCell r3c5 = r3.getCell(5);
                    String r3c5Value = dateFormat.format(r3c5.getDateCellValue());
                    sheet.setSendDate(r3c5Value);
                    System.out.println(r3c5Value);

                    //订单
                    List<Order> orderList = new ArrayList<Order>();
                    for (int i = 4; i < st.getLastRowNum(); i++) {
                        HSSFRow ri = st.getRow(i);
                        if (null == ri)continue;
                        Order order = new Order();
                        if (null != ri.getCell(3) && HSSFCell.CELL_TYPE_NUMERIC == ri.getCell(3).getCellType()) {
                            //产品代码
                            String ric0Value = readOneCell(ri.getCell(0));
                            order.setProductId(ric0Value);
                            System.out.print(ric0Value + ",");

                            //产品名称
                            String ric1Value = readOneCell(ri.getCell(1));
                            order.setProductName(ric1Value);
                            System.out.print(ric1Value + ",");

                            //规格
                            String ric2Value = readOneCell(ri.getCell(2));
                            order.setSize(ric2Value);
                            System.out.print(ric2Value + ",");

                            //批发价（元）
                            String ric3Value = readOneCell2(ri.getCell(3));
                            order.setPrice(ric3Value);
                            System.out.print(ric3Value + ",");

                            //数量（包）
                            String ric4Value = readOneCell2(ri.getCell(4));
                            order.setNum(ric4Value);
                            System.out.print(ric4Value + ",");

                            //折扣
                            String ric5Value = readOneCell2(ri.getCell(5));
                            order.setDiscount(ric5Value);
                            System.out.print(ric5Value + ",");

                            //金额（元）
                            String ric6Value = getPrice(ri);
                            order.setSum(ric6Value);
                            System.out.print(ric6Value);
                            System.out.println();

                            orderList.add(order);

                        } else {
                            if ("备  注：".equals(readOneCell(ri.getCell(0)))) {
                                //备注
                                String ric1Value = readOneCell(ri.getCell(1));
                                sheet.setReverse(ric1Value);
                                System.out.println(ric1Value);
                            }

                            if ("快递费用".equals(readOneCell(ri.getCell(4)).trim())) {
                                //快递费用
                                sheet.setEmailFei(readOneCell(ri.getCell(6)));
                                System.out.println("快递费用:" + readOneCell(ri.getCell(6)));
                            }
                        }
                        getAllPriceAndNum(orderList, sheet);
                        sheet.setOrders(orderList);
                    }

                    sheets.add(sheet);
                }
                in.close();
                return sheets;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ArrayList<Sheet>();
        } else if (file.getName().endsWith("xlsx")){
            try {
                List<Sheet> sheets = new ArrayList<Sheet>();
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                // 打开HSSFWorkbook
                XSSFWorkbook wb = new XSSFWorkbook(in);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {

                    //订单对象
                    Sheet sheet = new Sheet();
                    //获取sheet
                    XSSFSheet st = wb.getSheetAt(sheetIndex);
                    XSSFRow r0 = st.getRow(0);
                    if (null == r0) break;
                    XSSFRow r1 = st.getRow(1);
                    XSSFRow r2 = st.getRow(2);
                    XSSFRow r3 = st.getRow(3);

                    //公司名称
                    XSSFCell r0c1 = r0.getCell(1);
                    String r0c1Value = r0c1.getStringCellValue();
                    System.out.println(r0c1Value);

                    //区域编号
                    String r1c5Value = readOneCellX(r1.getCell(5));
                    sheet.setCustomerId(r1c5Value);
                    System.out.println(r1c5Value);

                    //订单编号
                    String r1c6Value = readOneCellX(r1.getCell(6));
                    sheet.setOrderId(r1c6Value);
                    System.out.println(r1c6Value);

                    //客户名称
                    String r2c1Value = readOneCellX(r2.getCell(1));
                    sheet.setCustomerName(r2c1Value);
                    System.out.println(r2c1Value);

                    //电话
                    String r2c5Value = readOneCellX(r2.getCell(5));
                    sheet.setPhone(r2c5Value);
                    System.out.println(r2c5Value);

                    //地   址
                    String r3c1Value = readOneCellX(r3.getCell(1));
                    sheet.setAddress(r3c1Value);
                    System.out.println(r3c1Value);

                    //送货日期
                    XSSFCell r3c5 = r3.getCell(5);
                    String r3c5Value = dateFormat.format(r3c5.getDateCellValue());
                    sheet.setSendDate(r3c5Value);
                    System.out.println(r3c5Value);

                    //订单
                    List<Order> orderList = new ArrayList<Order>();
                    for (int i = 4; i < st.getLastRowNum(); i++) {
                        XSSFRow ri = st.getRow(i);
                        if (null == ri)continue;
                        Order order = new Order();
                        if (null != ri.getCell(3) && XSSFCell.CELL_TYPE_NUMERIC == ri.getCell(3).getCellType()) {
                            //产品代码
                            String ric0Value = readOneCellX(ri.getCell(0));
                            order.setProductId(ric0Value);
                            System.out.print(ric0Value + ",");

                            //产品名称
                            String ric1Value = readOneCellX(ri.getCell(1));
                            order.setProductName(ric1Value);
                            System.out.print(ric1Value + ",");

                            //规格
                            String ric2Value = readOneCellX(ri.getCell(2));
                            order.setSize(ric2Value);
                            System.out.print(ric2Value + ",");

                            //批发价（元）
                            String ric3Value = readOneCell2X(ri.getCell(3));
                            order.setPrice(ric3Value);
                            System.out.print(ric3Value + ",");

                            //数量（包）
                            String ric4Value = readOneCell2X(ri.getCell(4));
                            order.setNum(ric4Value);
                            System.out.print(ric4Value + ",");

                            //折扣
                            String ric5Value = readOneCell2X(ri.getCell(5));
                            order.setDiscount(ric5Value);
                            System.out.print(ric5Value + ",");

                            //金额（元）
                            String ric6Value = getPriceX(ri);
                            order.setSum(ric6Value);
                            System.out.print(ric6Value);
                            System.out.println();

                            orderList.add(order);

                        } else {
                            if ("备  注：".equals(readOneCellX(ri.getCell(0)))) {
                                //备注
                                String ric1Value = readOneCellX(ri.getCell(1));
                                sheet.setReverse(ric1Value);
                                System.out.println(ric1Value);
                            }

                            if ("快递费用".equals(readOneCellX(ri.getCell(4)).trim())) {
                                //快递费用
                                sheet.setEmailFei(readOneCellX(ri.getCell(6)));
                                System.out.println("快递费用:" + readOneCellX(ri.getCell(6)));
                            }
                        }
                        getAllPriceAndNum(orderList, sheet);
                        sheet.setOrders(orderList);
                    }

                    sheets.add(sheet);
                }

                in.close();
                return sheets;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<Sheet>();
    }

    private String readOneCell(HSSFCell hssfCell) {
        if(null == hssfCell)return "";

        if (HSSFCell.CELL_TYPE_NUMERIC == hssfCell.getCellType()) {
            DecimalFormat df = new DecimalFormat("###########");
            return df.format(hssfCell.getNumericCellValue());
        }
        if (HSSFCell.CELL_TYPE_STRING == hssfCell.getCellType()) {
            return hssfCell.getStringCellValue();
        }
        return "";
    }
    private String readOneCellX(XSSFCell hssfCell) {

        if(null == hssfCell ) return "";
        if (XSSFCell.CELL_TYPE_NUMERIC == hssfCell.getCellType()) {
            DecimalFormat df = new DecimalFormat("###########");
            return df.format(hssfCell.getNumericCellValue());
        }
        if (XSSFCell.CELL_TYPE_STRING == hssfCell.getCellType()) {
            return hssfCell.getStringCellValue();
        }
        return "";
    }

    private String readOneCell2(HSSFCell hssfCell) {

        if(null == hssfCell)return "";
        if (HSSFCell.CELL_TYPE_NUMERIC == hssfCell.getCellType()) {
            DecimalFormat df = new DecimalFormat("0.00");
            return df.format(hssfCell.getNumericCellValue());
        }
        if (HSSFCell.CELL_TYPE_STRING == hssfCell.getCellType()) {
            return hssfCell.getStringCellValue();
        }
        return "";
    }
    private String readOneCell2X(XSSFCell hssfCell) {

        if(null == hssfCell)return "";
        if (XSSFCell.CELL_TYPE_NUMERIC == hssfCell.getCellType()) {
            DecimalFormat df = new DecimalFormat("0.00");
            return df.format(hssfCell.getNumericCellValue());
        }
        if (XSSFCell.CELL_TYPE_STRING == hssfCell.getCellType()) {
            return hssfCell.getStringCellValue();
        }
        return "";
    }

    private String getPrice(HSSFRow ri) {

        DecimalFormat df = new DecimalFormat("0.00");
        Double price = ri.getCell(3).getNumericCellValue() * ri.getCell(4).getNumericCellValue() * ri.getCell(5).getNumericCellValue();
        return df.format(price);
    }

    private String getPriceX(XSSFRow ri) {

        DecimalFormat df = new DecimalFormat("0.00");
        Double price = ri.getCell(3).getNumericCellValue() * ri.getCell(4).getNumericCellValue() * ri.getCell(5).getNumericCellValue();
        return df.format(price);
    }

    private void getAllPriceAndNum(List<Order> orderList, Sheet sheet) {
        double allNum = 0;
        double allPrice = 0;
        for (Order order1 : orderList) {
            allNum += Double.parseDouble(order1.getNum());
            allPrice += Double.parseDouble(order1.getSum());
        }
        DecimalFormat df = new DecimalFormat("0.00");
        sheet.setAllNum(df.format(allNum));
        sheet.setAllPrice(df.format(allPrice));
        sheet.setAllPrice2(df.format(allPrice));
    }


}
